package cn.ms.micro.threadpool.support;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.ms.micro.common.URL;
import cn.ms.micro.extension.SpiMeta;
import cn.ms.micro.threadpool.AbortPolicyWithReport;
import cn.ms.micro.threadpool.NamedThreadFactory;
import cn.ms.micro.threadpool.ThreadPool;

/**
 * 此线程池可伸缩，线程空闲一分钟后回收，新请求重新创建线程，来源于：<code>Executors.newCachedThreadPool()</code>
 * 
 * @see java.util.concurrent.Executors#newCachedThreadPool()
 * @author lry
 */
@SpiMeta(name = "cached")
public class CachedThreadPool implements ThreadPool {

	@Override
	public Executor getExecutor(URL url) {
		String name = url.getParameter(THREAD_NAME_KEY, DEFAULT_THREAD_NAME);
		int cores = url.getParameter(CORE_THREADS_KEY, DEFAULT_CORE_THREADS);
		int threads = url.getParameter(THREADS_KEY, Integer.MAX_VALUE);
		int queues = url.getParameter(QUEUES_KEY, DEFAULT_QUEUES);
		int alive = url.getParameter(ALIVE_KEY, DEFAULT_ALIVE);
		return new ThreadPoolExecutor(cores, threads, alive, TimeUnit.MILLISECONDS,
				queues == 0 ? new SynchronousQueue<Runnable>() : (queues < 0 ? new LinkedBlockingQueue<Runnable>()
				: new LinkedBlockingQueue<Runnable>(queues)), new NamedThreadFactory(name, true), new AbortPolicyWithReport(name, url));
	}

}