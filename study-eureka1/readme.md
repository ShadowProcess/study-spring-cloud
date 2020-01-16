###Eureka Server
- 服务端-没有存储，内存保持，每服务实例需要发送心跳去续约
- 客户端-在内存中缓存着eureka的注册信息，因此不必每请求到eureka查找服务
- eureka之间会做注册服务同步，从而保证状态一致，客户端只需访问一个eureka

###Service Provider
- 会向Eureka Server做Register（服务注册）、Renew（服务续约）、Cancel（服务下线）等操作

###Service Consumer
- 会向Eureka Server获取注册服务列表，并消费服务


###########################################
Eureka进入自我保护模式:
```
EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.
```
这是因为Eureka进入了自我保护机制，默认情况下，如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳时，
EurekaServer将会注销该实例（默认90s）。但是当网络发生故障时，微服务与EurekaServer之间无法通信，这样就会很危险了，
因为微服务本身是很健康的，此时就不应该注销这个微服务，而Eureka通过自我保护机制来预防这种情况，当网络健康后，该EurekaServer节点就会自动退出自我保护模式；
这时再次将客户端微服务启动，刷新服务注册中心会发现，自我保护状态已取消。

##综上所述，我们可以看出来Eureka的两个组件EurekaServer和EurekaClient的作用：

1.EurekaServer 提供服务发现的能力，各个微服务启动时，会向EurekaServer注册自己的信息（例如：ip、端口、微服务名称等），EurekaServer会存储这些信息；

2.EurekaClient是一个Java客户端，用于简化与EurekaServer的交互；

3.微服务启动后，会定期性（默认30s）的向EurekaServer发送心跳以续约自己的“租期”；

4.如果EurekaServer在一定时间内未接收某个微服务实例的心跳，EurekaServer将会注销该实例（默认90s）；
默认情况下，EurekaServer同时也是EurekaClient。多个EurekaServer实例，互相之间通过复制的方式，来实现服务注册表中数据的同步；

5.EurekaClient也会缓存服务注册表中的信息；

综上，Eureka通过心跳检查、客户端缓存等机制，提高了系统的灵活性、可伸缩性和可用性，所以作为一个微服务架构，需要一个服务注册中心来统筹管理服务；
####



