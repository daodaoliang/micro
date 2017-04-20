#微核心

致力于解决各类分布式基础设施或微服务应用的核心机制,包括数据模型、扩展机制等。


##1 数据模型(CURL/EURL)
主要用于解决分布式基础组件中常用的数据模型定义。

+ Perf：性能测试工具
+ MultiHashMap：三元Map
+ ConcurrentHashSet：高并发安全Set
+ MicroId：基于AtomicLong算法的ID生成器
+ Snowflake：基于Snowflake算法的高性能ID生成器，每秒钟可以生产超过四百万的有序无重复的ID(理论QPS:400w+/s)
+ URL：基于Dubbo(Dubbox)中URL进行升级后的数据模型
+ SystemClock：高并发场景下System.currentTimeMillis()的性能问题的优化

##2 SPI机制(@Spi)
+ 支持SPI扩展(类似于JDK SPI的加强版)
+ 支持SPI扩展实现的排序
+ 支持SPI扩展实现的分组
+ 支持SPI扩展实现的单例/多例模式

##3 日志服务
基于Slf4j实现统一的日志门面服务。

##4 状态服务
+ LoadStatusChecker：System Load Status
+ MemoryStatusChecker：Jvm Memory Status

##5 线程池服务
+ CachedThreadPool：缓存线程池
+ FixedThreadPool：固定大小线程池
+ LimitedThreadPool：自动增长线程池
