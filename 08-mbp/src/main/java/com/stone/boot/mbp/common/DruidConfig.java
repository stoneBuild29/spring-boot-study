package com.stone.boot.mbp.common;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * @className: DruidConfig
 * @author: Scarlet
 * @date: 2024/8/20
 **/
//@Configuration
public class DruidConfig {

    /**
     * 监控信息配置
     */
    @Bean
    public ServletRegistrationBean statViewServle(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.setUrlMappings(Arrays.asList("/druid/*"));

        //白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");

        //黑名单(同时存在的时候，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "192.168.0.0.1");

        //登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUserName", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");

        //是否能够重制数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());

        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");

        //不监控静态资源以及Druid本身
        filterRegistrationBean.addInitParameter("exclusions", "*.js, *.gif, *.jpg, *.png, *.css, *.ico, /druid/*");
        return filterRegistrationBean;
    }

    /**
     * 配置Spring监控
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor(){
        return new DruidStatInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut(){
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();

        //AOP切点
        String[] patterns = {"com.stone.boot.*.controller.*", "com.stone.boot.*.service.*", "com.stone.boot.*.mapper.*"};
        druidStatPointcut.setPatterns(patterns);
        return druidStatPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor(){
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}
