#微核心

致力于解决各类分布式基础设施或微服务应用的核心机制,包括数据模型、扩展机制、插件机制等。

+ DataModle
	+ EURL
	+ CURL
	+ Store
	+ Stack
	+ MultiHashMap
	+ ConcurrentHashSet
+ Utils
+ SPI
+ Logger
+ Metric
	+ Cache
	+ DataStore
	+ Plugin
	+ Micro
		+ Conf
		+ FileLock
		+ Perf
		+ Sequence
		+ Snapshot
		+ SystemClock
+ Status
+ Switcher
+ ThreadPool


##1 数据模型(CURL/EURL)
主要用于解决分布式基础组件中常用的数据模型定义。

+ CURL：基于Motan中URL进行升级后的数据模型。
+ EURL：基于Dubbo(Dubbox)中URL进行升级后的数据模型。
+ Store：KV型模型
+ ConcurrentHashSet：高并发安全Set

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

##5 开关服务


##6 线程池服务


##7 微神器
###7.1 插件机制(IPlugin)
+ 支持即插即用(扔进去则可用)
+ 支持双模式的插件启动方式(模式一：全部初始化成功后准个启动；模式二：单个初始化并启动成功后再启动下一个)
+ 支持插件排序
+ 支持忽略插件初始化失败
+ 支持忽略插件初始化异常
+ 支持忽略插件启动失败
+ 支持忽略插件启动化异常

###7.2 微配置(Conf)
+ 支持配置分组,分组层级之间使用英文点(即“.”)隔开,即“test.user.name=xxx”
+ 配置分组的最后一项支持单项值与数组项值的使用,即“test.user.name[0]=xxx”

###7.3 微序列(Sequence)
每秒钟可以生产超过四百万的有序无重复的ID(理论QPS:400w+/s)

###7.4 微时间(SystemClock)
用于解决高并发场景下System.currentTimeMillis()的性能问题优化

