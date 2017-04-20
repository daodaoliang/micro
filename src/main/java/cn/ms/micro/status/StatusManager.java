package cn.ms.micro.status;

import java.util.Map;

import cn.ms.micro.status.Status.Level;

/**
 * StatusManager
 * 
 * @author lry
 */
public class StatusManager {
	
	
    /**
     * 聚合状态
     * 
     * @param statuses
     * @return
     */
    public static Status getSummaryStatus(Map<String, Status> statuses) {
        Level level = Level.OK;
        StringBuilder msg = new StringBuilder();
        for (Map.Entry<String, Status> entry : statuses.entrySet()) {
            String key = entry.getKey();
            Status status = entry.getValue();
            Level l = status.getLevel();
            if (Level.ERROR.equals(l)) {
                level = Level.ERROR;
                if (msg.length() > 0) {
                    msg.append(",");
                }
                msg.append(key);
            } else if (Level.WARN.equals(l)) {
                if(! Level.ERROR.equals(level)) {
                    level = Level.WARN;
                }
                if (msg.length() > 0) {
                    msg.append(",");
                }
                msg.append(key);
            }
        }
        
        return new Status(level, msg.toString());
    }

}