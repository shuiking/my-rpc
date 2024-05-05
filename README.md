## 项目介绍 🔥
该项目为基于 ZooKeeper + Vert.x + 自定义协议实现的轻量级 RPC 框架。开发者可以引入 Spring Boot Starter，通过注解和配置文件快速使用框架，像调用本地方法一样轻松调用远程服务。  
rpc调用流程图
![image](https://github.com/shuiking/my-rpc/assets/86963048/bca5fa90-538c-4646-8dfc-f7e6a829cc35)

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
* 支持jdk的动态代理方式  
* 支持fastJson、hessian、kryo、jdk的序列化方式  
* 支持 SPI 机制，便于扩展  
* 加入了Spring Boot Starter

## 系统架构图 💫




