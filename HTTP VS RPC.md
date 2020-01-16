【HTTP VS RPC】
[RPC]    Dubbo
[HTTP]   SpringCloud 


=======================Spring Cloud====================================
#SpringCloud中服务间两种restful调用方式

###1.RestTemplate 【RestTemplate用于服务与服务之间的调用】
```
 @Autowired
 private LoadBalancerClient loadBalancerClient;
 @Autowired
 private RestTemplate restTemplate;
```

```
//1.第一种方式(直接使用restTemplate,url写死)
//RestTemplate restTemplate = new RestTemplate();
//String response = restTemplate.getForObject("http://localhost:8080/msg",String.class);
//log.info("response={}",response);
```

```
//2.第二种方式[如果有多台提供相同服务的服务器]
ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
String host = serviceInstance.getHost();
Integer port = serviceInstance.getPort();

String url = String.format("http://%s:%s", host, port)+"/msg";
RestTemplate restTemplate = new RestTemplate();
String response = restTemplate.getForObject(url,String.class);

log.info("response={}", response);
```

```
//3.第三种方式(利用@LoadBalanced,可在restTemplate里直接使用名字)
/**
 * 增加配置类：
 * @Component
 * public class RestTemplateConfig {
 *
 *     @Bean
 *     @LoadBalanced
 *     public RestTemplate restTemplate(){
 *         return new RestTemplate();
 *     }
 * }
 */
String response = restTemplate.getForObject("http://PRODUCT/msg", String.class); //PRODUCT 应用名
log.info("response={}", response);
```




###2.Feign  HTTP客户端负载均衡器  【feign用于服务与服务之间的调用】
*声明式REST客户端(伪RPC)
*采用了基于接口的注解
*Feign内部也使用Ribbon做负载均衡


使用方法：
1.引入依赖
```
<!--增加Feign注解-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
2.写接口
```
@FeignClient(name = "product") //参数name,表示你要访问那个应用的接口
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();
}
```
3.调用
```
@Autowired
private ProductClient productClient;

@GetMapping("/getProductMsg")
public String getProductMsg(){
   String response = productClient.productMsg();
   log.info("【Feign】 response={}",response);
   return response;
}
```
4.启动类添加
```
@EnableFeignClients    //feign:客户端负载均衡器
```






###3.Ribbon HTTP客户端负载均衡器  【Ribbon用于服务与服务之间的调用】
这个技术是在客户端完成的,并不需要服务端的参与，是Netflix公司开发
三点核心：
a.服务发现 
     就是依据服务的名称，把所有该服务的实例都找出来 
b.服务选择规则
     如何在多个服务中，选择一个有效的服务
c.服务监听
     检测失效的服务，高效剔除

三大组件：
* ServerList
* IRule
* ServerListFilter     

Ribbon源码分析
  相信很多熟悉Sprint的读者看到这里一定会产生这样的疑问：RestTemplate不是Spring自己提供的嘛？跟Ribbon的客户端负载均衡又有什么关系呢？
  在本节中，我们透过现象看本质，探索一下Ribbon是如何通过RestTemplate实现客户端负载均衡的。
  首先，回顾一下之前的消费者示例：我们是如何实现客户端负载均衡的？仔细观察一下之前的实现代码，可以发现载消费者的例子中，
  可能就@LoadBalanced这个注解是之前没有接触过的，并且从命名上来看也与负载均衡相关。我们不妨以此为线索来看看Spring Cloud Ribbon的源码实现。
  从@LoadBalanced注解码的注释中，可以知道该注解用来给RestTemplate标记，以使用负载均衡的客户端（LoadBalancerClient）来配置它。

```
ServiceInstanceChooser     父接口            
          |
LoadBalancerClient         儿子接口
          | 
RibbonLoadBalancerClient   孙子接口                     
```     
通过分析源码：
得到Ribbon会从Eureka获取，所有名字叫XXX这个名字的服务，然后采用轮训调用的方式  
轮训就是：第一次调用A，第二次调用B，第三次调用A，第四次调用B
妈的，这就叫负载均衡

默认负载均衡规则为轮训，这个是可以修改的，直接在配置文件中添加一个配置就行了，
这个配置可以去官网找
```
应用名字:
  ribbon:
     NFLoadBalanceRuleClassName: com.netflix.loadbalancer.RandomRule
```     
     
     
     
=======================Dubbo====================================     




