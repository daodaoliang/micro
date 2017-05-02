package cn.ms.micro;

import cn.ms.micro.extension.ExtensionLoader;
import cn.ms.micro.metric.Metric;

public class MetricService {

	public static Metric getMetric(String type) {
		Metric metric = ExtensionLoader.getExtensionLoader(Metric.class).getExtension(type);
		return metric;
	}
	
}
