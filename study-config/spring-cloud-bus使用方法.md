###config服务端和客户端都添加此依赖
```
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```

###在config服务端添加配置      
```
#暴露接口 【该接口用来刷新配置文件，而不需要重新启动，使用的spring-cloud-bus插件】
management:
  endpoints:
     web:
       expose: "*"
```        
       
###想要刷新配置,使用浏览器访问config服务端接口： 
GET http://localhost:8080/actuator/bus-refresh
[也可以使用git的WebHooks功能]
[如果使用webHooks测试本地，可以使用内网穿透 natapp.cn]
