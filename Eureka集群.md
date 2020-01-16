###Eureka怎么集群?
Eureka1 -> Eureka2,Eureka3

Eureka2 -> Eureka1,Eureka3

Eureka3 -> Eureka1,Eureka2

Client -> Eureka1,Eureka2,Eureka3

============================



##Eureka总结##

1-- @EnableEurekaServer @EnableEurekaClient
2-- 心跳检测，健康检查，负载均衡等功能
3-- Eureka的高可用，生产上建议至少两台以上
4-- 分布式系统中，服务注册中心是重要的基础部分



##分布式系统中为什么需要服务发现？##
客户端发现:
Eureka

服务端发现：
Nginx
Zookeeper
Kubernetes

#############MicroService###############
业务形态不适合的项目
 1.系统中包含很多强事务场景的
 2.业务相对稳定，迭代周期长
 3.访问压力不大，可用性要求不高
 
服务拆分的方法论
 如何拆“功能”
单一职责，松耦合，高内聚  



 

            
