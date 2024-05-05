## 项目介绍 🔥
该项目为基于 ZooKeeper + Vert.x + 自定义协议实现的轻量级 RPC 框架。开发者可以引入 Spring Boot Starter，通过注解和配置文件快速使用框架，像调用本地方法一样轻松调用远程服务。  
![image](https://github.com/shuiking/my-rpc/assets/86963048/bca5fa90-538c-4646-8dfc-f7e6a829cc35)  
<p align="center">  
简易的rpc调用流程图   
</p>  

## 组织结构 🚀
my-rpc  
├── consumer        -- 服务消费者  
├── example-common  -- 示例代码的公共依赖  
├── provider        -- 服务提供者  
├── rpc-core        -- rpc核心实现类


## 功能⭐   
* 基于Vert.x 实现长连接通信，包括心跳检测、解决粘包半包等  
* 基于Zookeeper实现分布式服务注册与发现  
* 实现了轮询、随机、加权随机等负载均衡算法
* 支持重试机制、容错机制  
* 支持jdk的动态代理方式  
* 支持fastJson、hessian、kryo、jdk的序列化方式  
* 支持 SPI 机制，便于扩展  
* 加入了Spring Boot Starter

## 系统架构图 💫

![image](https://github.com/shuiking/my-rpc/assets/86963048/7497529e-41c7-4641-8d81-4660403e4c3d)  
* 注册中心 ：注册中心使用 Zookeeper。注册中心负责服务地址的注册与查找。服务端启动的时候将服务名称及其对应的地址(ip+port)注册到注册中心，服务消费端根据服务名称找到对应的服务地址。有了服务地址之后，服务消费端就可以通过网络请求服务端了。  
* 网络传输 ：既然要调用远程的方法就要发请求，请求中至少要包含你调用的类名、方法名以及相关参数,使用Vert.x。  
* 序列化 ：使用 hession2、kyro、fastjson 作为序列化协议。  
* 动态代理 ： 使用JDK动态代理。  
* 负载均衡 ：实现了轮询、随机、加权随机等负载均衡。  
