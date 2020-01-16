###
因为有冗余代码，order里边也用到了，它的代码

这个业务应该拆分为三个模块
*client
*common
*server

然后就有三个包，其它业务用到时，直接引用即可


```
<groupId>com.xx</groupId>
    <artifactId>xx</artifactId>
    <version>1.0.0-SNAPSHOT</version>
<packaging>pom</packaging>
```
