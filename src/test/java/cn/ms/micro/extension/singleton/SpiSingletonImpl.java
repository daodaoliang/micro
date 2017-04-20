package cn.ms.micro.extension.singleton;

import java.util.concurrent.atomic.AtomicLong;

import cn.ms.micro.extension.SpiMeta;

@SpiMeta(name = "spiSingletonImpl")
public class SpiSingletonImpl implements SpiSingleton {
    private static AtomicLong counter = new AtomicLong(0);
    private long index = 0;

    public SpiSingletonImpl() {
        index = counter.incrementAndGet();
    }

    @Override
    public long spiHello() {
        return index;
    }

}
