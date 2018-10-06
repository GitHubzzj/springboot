springboot简介
#### [SpringBoot背景](https://spring.io/)
SpringBoot是伴随着Spring4.0诞生的；

从字面理解，Boot是引导的意思，因此SpringBoot帮助开发者快速搭建Spring框架；

多年来,Spring IO平台饱受非议的一点就是大量的XML配置以及复杂的依赖管理.在2013年的SpringOne 2GX会议上,
Pivotal的CTO 回应了这些批评,并且特别提到,该平台的目标之一就是实现免XML配置的开发体验.
Boot所实现的功能超出了该任务的描述,开发人员不仅不再需要编写XML配置,而且在一些场景中甚至不需要
编写繁琐的import语句,在对外公开的beta版本刚刚发布时,Boot描述了如何使用该框架在140个字符之内
实现可运行的web应用,从而获得了极大的关注度.

- 依赖复杂
    
    >无论是手动下载还是通过maven等构建工具，都必须知道所依赖的jar包版本。当依赖的jar越来越多，特别是嵌套依赖时，很容易让人分不清所依赖的jar到底需要怎样的版本才能正常运行。通过查询官网或尝试虽然可以解决问题，但往往会耗费大量的时间和精力
- 配置复杂
    
    >spring配置相对于普通web项目，基本是通用、一成不变的（相对而言）。但每次创建项目都必须重复配置（通过复制只是减少了工作量，并未减少工作内容）
- 部署复杂
    
    >把打好的war包复制（手工或jenkins)到tomcat中。并启动tomcat    
- 监控复杂
    
    >如果每个项目都需要一些非业务功能，如健康检查、内存监控等，要么做一套这样的功能到处复制，要么打成jar包各个项目引用

***原本需要你小半天才能做好的工作，现在只需要十几分钟甚至几分钟就做好***
#### Spring Boot特性
1. 能够快速创建基于Spring的应用程序

2. 自动配置：针对很多Spring应用程序和常见的应用功能，Spring Boot能自动提供相关配置。
    
     1) 基本可以完全不使用XML配置文件，采用注解配置。
     2) 根据项目的Maven依赖配置，Spring boot自动配置Spring、Spring mvc等
     3) 外部化配置,使用 properties,YAML文件,环境变量,命令行参数等配置参数
3. 起步依赖：告诉Spring Boot需要什么功能， 它就能引入需要的依赖库
    
    1) 提供约定的starter POM来简化Maven配置(约定优先于配置)，让Maven的配置变得简单.如:spring-boot-starter-web,spring-boot-starter-*
        
    
4. [Actuator](https://www.jianshu.com/p/481134c3fab7)：让你能够深入运行中的Spring Boot应用程序，一探Spring Boot程序的内部信息。
     
    1)提供生产就绪型功能，如指标，健康检查和外部配置
     
5. 命令行界面：这是Spring Boot的可选特性，主要针对Groovy语言使用。

6. 能够直接使用java main方法启动内嵌的Tomcat，Jetty服务器运行Spring boot程序，不需要部署war包文件,一个可执行jar即使一个服务 。

#### SpringBoot是什么，解决哪些问题

     1) Spring Boot使编码变简单
            springboot-quickstart
     2) Spring Boot使配置变简单
     
>属性优先级排列：
>1. 命令行参数
    java -jar springboot-helloworld-0.0.1-SNAPSHOT.jar --server.port=8088
>2. java:comp/env 里的 JNDI 属性
>3. JVM 系统属性
>4. 操作系统环境变量
>5. RandomValuePropertySource 属性类生成的 random.* 属性
>6. 应用以外的 application.properties（或 yml）文件
>7. 打包在应用内的 application.properties（或 yml）文件
>8. 在应用 @Configuration 配置类中，用 @PropertySource 注解声明的属性文件
>9. SpringApplication.setDefaultProperties 声明的默认属性
      
        见 spring-boot-config
        springboot-dubbo //todo:
        springboot-es //todo:
     3) Spring Boot使部署变简单
        jar + Docker部署  //todo:
     4) [Spring Boot使监控变简单](http://rensanning.iteye.com/blog/2363526)
         应用配置类
        /configprops /autoconfig /beans /env /info /mappings
         度量指标类
         /dump /health
         操作控制类
         /shutdown
         

#### SpringBoot常用注解

@Condition

组合注解:

@Import原理 
https://www.cnblogs.com/davidwang456/p/6245635.html?utm_source=itdadao&utm_medium=referral
ConfigurationClassParser.doProcessConfigurationClass
---

@Enable*注解的工作原理:

@EnableAspectJAutoProxy

@EnableAsync

@EnableScheduling
- 第一类:直接导入配置类
    @EnableScheduling -> SchedulingConfiguration -> ScheduledAnnotationBeanPostProcessor
    
- 第二类:依据条件选择配置类
    @EnableAsync -> ProxyAsyncConfiguration -> AsyncAnnotationBeanPostProcessor
    @Import(AsyncConfigurationSelector.class)
- 第三类:动态注册Bean
    @EnableAspectJAutoProxy
    @Import(AspectJAutoProxyRegistrar.class) -> 实现了 ImportBeanDefinitionRegistrar 接口,作用是:通过重写方法,自动添加Bean到已有的配置类

WebApplicationInitializer ->  web.xml

HTTP长连接 http://ju.outofmemory.cn/entry/28814     //todo:


springboot运行原理 颠覆者  
包名:
spring-boot-autoconfigure-xx.RELEASE.jar
debug=true 观察
@EnableAutoConfiguration 
    -> AutoConfigurationImportSelector 
    -> getCandidateConfigurations 读取
    -> SpringFactoriesLoader.loadFactoryNames
    ->  FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories" 该文件里面有类完整名的配置
    
    
@ConditionOnxxx
@ConditionalOnBean：当SpringIoc容器内存在指定Bean的条件
@ConditionalOnClass：当SpringIoc容器内存在指定Class的条件
@ConditionalOnExpression：基于SpEL表达式作为判断条件
@ConditionalOnJava：基于JVM版本作为判断条件
@ConditionalOnJndi：在JNDI存在时查找指定的位置
@ConditionalOnMissingBean：当SpringIoc容器内不存在指定Bean的条件
@ConditionalOnMissingClass：当SpringIoc容器内不存在指定Class的条件
@ConditionalOnNotWebApplication：当前项目不是Web项目的条件
@ConditionalOnProperty：指定的属性是否有指定的值
@ConditionalOnResource：类路径是否有指定的值
@ConditionalOnSingleCandidate：当指定Bean在SpringIoc容器内只有一个，或者虽然有多个但是指定首选的Bean
@ConditionalOnWebApplication：当前项目是Web项目的条件
     -> OnWebApplicationCondition 
     HttpEncodingAutoConfiguration

springboot工作机制  //todo:
springboot-starter  //todo:

|注解|	作用|
|-----------|-----------|
|@SpringBootApplication|	SpringBoot的核心注解，主要作用是开启自动配置。|
|@SpringBootApplication|@SpringBootApplication=@ComponentScan+@Configuration+@EnableAutoConfiguration|
|@SpringBootApplication|关闭特定的自动配置：@SpringBootApplication注解的exclude参数。<br>例如：@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class} )|
|@EnableAutoConfiguration|让springboot根据类路径中的jar包依赖为当前项目进行自动配置。|
|@ImportResource|	加载xml配置。<br>例如：@ImportResource({"classpath:some-context.xml","classpath:another-context.xml"})|
|@ConfigurationProperties|	将properties属性和一个bean及其属性关联。   写在bean里面。<br>例如：@ConfigurationProperties(prefix = "author" , location = "{classpath:config/author.properties}" )|      

#### SpringBoot源码浅析  //todo:

springboot + dubbo  //todo:
     
#### 推荐学习资源
   《 JavaEE开发的颠覆者 Spring Boot实战 》
   [springboot官方入门源码](https://github.com/spring-guides/gs-spring-boot.git)
   
  [Spring Boot 2.x 核心技术实战](https://github.com/JeffLi1993/springboot-learning-example)
  
  [spring-boot-in-action-译稿](https://github.com/digitalsonic/spring-boot-in-action)
 
  [spring-boot-in-action-源码](https://github.com/habuma/sbia-samples)

  [SpringBoot参考指南](https://qbgbook.gitbooks.io/spring-boot-reference-guide-zh/)
  
  [spring-boot官方源码](https://github.com/spring-projects/spring-boot)
  
  
  [xml注解混合配置](https://blog.csdn.net/lrcoop/article/details/78669613)
  
  [banner网站](http://patorjk.com/software/taag/)     