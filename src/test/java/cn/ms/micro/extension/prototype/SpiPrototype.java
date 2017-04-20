package cn.ms.micro.extension.prototype;

import cn.ms.micro.extension.Scope;
import cn.ms.micro.extension.Spi;

@Spi(scope = Scope.PROTOTYPE)
public interface SpiPrototype {
    long spiHello();
}
