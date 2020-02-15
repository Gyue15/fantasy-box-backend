# fantasy-box-backend
 backend of fantasy box

#### 使用说明：

1. 将database-script文件夹中的fantasy-box.sql导入mysql数据库中。注意：该脚本仅有结构没有数据。

2. 在fantasy-box/src/main/resources文件夹下新建application-dev-xx.yml（如：application-dev-g.yml），并向里面写入如下内容：

   ```yml
   #本地开发环境的配置文件
   
   server:
     port: {端口号}
   
   spring:
     datasource:
       username: {数据库用户名}
       password: {数据库密码}
       url: jdbc:mysql://{数据库ip}:{数据库端口（3306）}/{数据库名称}?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
     session:
       store-type: jdbc
       jdbc:
         initialize-schema: always
     mail:
       host: {邮件smtp服务器地址}
       username: {邮箱名称}
       password: {邮箱授权码}
       default-encoding: UTF-8
       port: 465
       properties:
         mail:
           smtp:
             auth: true
             starttls:
               required: true
               enable: true
             socketFactory:
               fallback: false
               class: javax.net.ssl.SSLSocketFactory
               port: 465
           debug: true
     freemarker:
       template-loader-path: classpath:/templates/
       charset: UTF-8
       cache: false
       expose-request-attributes: true
       expose-session-attributes: true
       expose-spring-macro-helpers: true
       suffix: .ftl
       content-type: text/html
   
   mybatis:
     mapper-locations: classpath:mapping/*Mapper.xml
     type-aliases-package: com.example.entity
   
   #showSql
   logging:
     level:
       cn.edu.nju.fantasybox.mapper: debug
   
   file-service:
     local: file:{本地存放文件的路径}
     url-pattern: /file/**
     url-prefix: {访问文件资源的url前缀，如http://localhost:8080/file/}
   
   local-url: {服务器地址，如Http://localhost:8080}
   active-account-url: ${local-url}/api/user/activate-account
   
   
   public-key: {加密公钥文件的路径}
   private-key: {加密私钥文件的路径}
   
   hot-num: 9
   ```

   其中，{xxx}替换为本机配置

3. 将application.yml修改为：

  ```yml
  #选择配置文件
  spring:
    profiles:
      active: dev-g
  ```
  
   