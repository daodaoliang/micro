package cn.ms.micro;

import cn.ms.micro.extension.ExtensionLoader;
import cn.ms.micro.threadpool.ThreadPool;

/**
 * 线程池工具类
 * 
 * @author lry
 */
public class ThreadPoolService {

	public static ThreadPool getThreadPool(String type) {
		ThreadPool pool = ExtensionLoader.getExtensionLoader(ThreadPool.class).getExtension(type);
		return pool;
	}

}
