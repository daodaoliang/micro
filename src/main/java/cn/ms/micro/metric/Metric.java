package cn.ms.micro.metric;

import java.util.Map;

import cn.ms.micro.extension.Scope;
import cn.ms.micro.extension.Spi;

@Spi(scope = Scope.SINGLETON)
public interface Metric {

	Map<String, Object> getMetrices();

}
