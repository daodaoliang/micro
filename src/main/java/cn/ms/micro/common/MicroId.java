package cn.ms.micro.common;

import java.util.concurrent.atomic.AtomicLong;


/**
 * 通过MicroId能够知道大致请求的时间
 * <pre>
 * 		目前是 currentTimeMillis * (2^20) + offset.incrementAndGet()
 * 		通过 requestId / (2^20 * 1000) 能够得到秒
 * </pre>
 * 
 * @author lry
 */
public class MicroId {
	
    protected static final AtomicLong offset = new AtomicLong(0);
    protected static final int BITS = 20;
    protected static final long MAX_COUNT_PER_MILLIS = 1 << BITS;


    /**
     * 获取 requestId
     * 
     * @return
     */
    public static long getRequestId() {
        long currentTime = System.currentTimeMillis();
        long count = offset.incrementAndGet();
        while(count >= MAX_COUNT_PER_MILLIS){
            synchronized (MicroId.class){
                if(offset.get() >= MAX_COUNT_PER_MILLIS){
                    offset.set(0);
                }
            }
            count = offset.incrementAndGet();
        }
        return (currentTime << BITS) + count;
    }

}
