package cn.ms.micro.extension.singleton;

import cn.ms.micro.extension.Scope;
import cn.ms.micro.extension.Spi;

@Spi(scope = Scope.SINGLETON)
public interface SpiSingleton {
    long spiHello();
}
