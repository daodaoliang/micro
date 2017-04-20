package cn.ms.micro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ms.micro.extension.ExtensionLoader;
import cn.ms.micro.extension.SpiMeta;
import cn.ms.micro.status.Status;
import cn.ms.micro.status.StatusChecker;
import cn.ms.micro.status.StatusManager;

/**
 * 状态服务
 * 
 * @author lry
 */
public class StatusService {

	public static StatusChecker getStatusChecker(String name) {
		StatusChecker service = ExtensionLoader.getExtensionLoader(StatusChecker.class).getExtension(name);
		if (null == service) {
			throw new IllegalArgumentException("No found statusChecker log service.");
		}

		return service;
	}

	public static Status getStatus(String... names) {
		List<StatusChecker> services = ExtensionLoader.getExtensionLoader(StatusChecker.class).getExtensions();

		if (services != null) {
			List<String> namesList = null;
			if (names != null) {
				namesList = Arrays.asList(names);
			}

			Map<String, Status> statuses = new HashMap<String, Status>();
			for (StatusChecker statusChecker : services) {
				Status status = statusChecker.check();
				SpiMeta spiMeta = statusChecker.getClass().getAnnotation(SpiMeta.class);
				String id = null;
				if (spiMeta == null || spiMeta.name() == null || spiMeta.name().length() < 1) {
					id = statusChecker.getClass().getName();
				} else {
					id = spiMeta.name();
				}

				if (namesList != null) {
					if (!namesList.contains(id)) {
						continue;
					}
				}

				statuses.put(spiMeta.name(), status);
			}

			return StatusManager.getSummaryStatus(statuses);
		}

		return null;
	}

}
