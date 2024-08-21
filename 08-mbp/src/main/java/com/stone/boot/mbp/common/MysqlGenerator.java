package com.stone.boot.mbp.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @className: MysqlGenerator
 * @author: Scarlet
 * @date: 2024/8/20
 **/
public class MysqlGenerator {

    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/springboot?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";

    public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String DATABASE_USERNAME = "root";

    public static final String DATABASE_PASSWORD = "lHgfP?EsP4eH";

    public static final String OUT_PUT_PATH = "/08-mbp/src/main/java";
    public static final String TEMPLATES_MAPPER_XML_PATH = "/templates/mapper.xml.ftl";
    public static final String XML_PATH = "/08-mbp/src/main/resources/mapper";
    public static final String XML_POSTFIX = "Mapper";

    public static final String AUTHOR = "stone";
    public static final String PARENT_PACKAGE = "com.stone.boot.mbp";
    public static final String[] SUPER_ENTITY_COLUMNS = {"id", "create_time", "update_time", "creator", "modifier"};

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + ":");
        if (scanner.hasNext()){
            String ipt = scanner.next();
            if(StringUtils.isNotBlank(ipt)){
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args){
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("usr.dir");
        gc.setOutputDir(projectPath + OUT_PUT_PATH);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setBaseResultMap(true);
        gc.setActiveRecord(true);
        gc.setBaseColumnList(true);
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DATABASE_URL);
        dsc.setDriverName(DATABASE_DRIVER);
        dsc.setUsername(DATABASE_USERNAME);
        dsc.setPassword(DATABASE_PASSWORD);
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc = new PackageConfig();
        //不设置模块名的话 Controller请求路径会多一个“/”
        pc.setModuleName(null);
        pc.setParent(PARENT_PACKAGE);
        mpg.setPackageInfo(pc);

        //自定义配置 可以设置自定义参数，然后在模版中使用
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("parent", PARENT_PACKAGE);
                setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(TEMPLATES_MAPPER_XML_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输入文件名称
                return projectPath + XML_PATH + tableInfo.getEntityPath() + XML_POSTFIX + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setSuperEntityClass(BaseEntity.class);
        strategyConfig.setSuperEntityColumns(SUPER_ENTITY_COLUMNS);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setChainModel(true);
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));

        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setRestControllerStyle(true);

        mpg.setStrategy(strategyConfig);
        //选择freemarker引擎需要制定如下加，注意pom依赖必须有
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();


    }



}
