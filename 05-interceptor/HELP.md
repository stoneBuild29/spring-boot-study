# Getting Started
!img.img[https://raw.githubusercontent.com/wenjinglee1104/blog_file/master/20240813144452.png]

拦截器Interceptor的原理是Spring框架中的一种机制，用于在特定的点(例如请求处理前、方法执行前后等)执行一些逻辑。
用途：用于拦截HTTP请求/响应的生命周期，特别是在Spring MVC中。它们在请求处理的不同阶段（如请求到达控制器之前，试图渲染之前和请求完成之后）执行特定的逻辑。
应用层级：通常在Web层使用，用于拦截和处理Http请求，比如认证、授权和日志记录等。
实现方式：
- 通过"HandlerInterceptor"接口实现拦截器，在重写的方法里面写入日志记录或其他方法。（典型的拦截器用于Spring MVC中拦截HTTP请求）
- 通过"WebMvcConfgurer"注册到Spring MVC的拦截器中。
作用范围：主要是Spring MVC的控制器方法。它们的触发条件是HTTP请求，所以拦截器的作用范围限定在Web层。


