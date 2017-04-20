package cn.ms.micro.threadpool;

import java.util.concurrent.Executor;

import cn.ms.micro.common.URL;
import cn.ms.micro.extension.Scope;
import cn.ms.micro.extension.Spi;

/**
 * ThreadPool
 * 
 * @author lry
 */
@Spi(scope = Scope.PROTOTYPE)
public interface ThreadPool {

	public static final String THREADPOOL_KEY = "threadpool";
	public static final String THREAD_NAME_KEY = "threadname";
	public static final String IO_THREADS_KEY = "iothreads";
	public static final String CORE_THREADS_KEY = "corethreads";
	public static final String THREADS_KEY = "threads";
	public static final String QUEUES_KEY = "queues";
	public static final int DEFAULT_QUEUES = 0;
	public static final String DEFAULT_THREAD_NAME = "Ms";
	public static final int DEFAULT_CORE_THREADS = 0;
	public static final int DEFAULT_THREADS = 200;
	public static final String ALIVE_KEY = "alive";
	public static final int DEFAULT_ALIVE = 60 * 1000;

	/**
	 * 线程池
	 * 
	 * @param url
	 *            线程参数
	 * @return 线程池
	 */
	Executor getExecutor(URL url);

}