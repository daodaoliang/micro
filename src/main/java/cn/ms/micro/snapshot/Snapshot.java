package cn.ms.micro.snapshot;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 快照中心
 * 
 * @author lry
 */
public class Snapshot {

	// 日志输出
	private static final Logger logger = LoggerFactory.getLogger(Snapshot.class);

	private File SNAPSHOT_FILE;
	private String SNAPSHOT_PATH = null;
	private int VALUE_COUNT = 2;
	private int APP_VERSION = 100;

	// 获取当前工程路径
	private String PATH_ID = UUID.randomUUID().toString();

	private DiskLruCache diskLruCache;

	/**
	 * System.getProperty("user.home") /.snapshot/MD5(PATH)/<font
	 * color="red">snapshotName</font> .snapshot"
	 */
	public Snapshot(String snapshotName) {
		try {
			SNAPSHOT_PATH = System.getProperty("user.home") + "/.snapshot/"
					+ PATH_ID + "/" + snapshotName + ".snapshot";
			logger.info("Snapshot store file: " + SNAPSHOT_PATH);

			SNAPSHOT_FILE = new File(SNAPSHOT_PATH);
			diskLruCache = DiskLruCache.open(SNAPSHOT_FILE, APP_VERSION,
					VALUE_COUNT, Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getSnapshotFile() {
		return SNAPSHOT_FILE;
	}

	public void put(String key, String data) {
		try {
			DiskLruCache.Editor creator = diskLruCache.edit(key);
			creator.set(0, data);
			creator.commit();
		} catch (Exception e) {
			logger.error("Put Snapshot is failure", e);
		}
	}

	public void put(String key, Object obj) {
		this.put(key, JSON.toJSONString(obj));
	}

	public String getString(String key) {
		try {
			DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
			return snapshot.getString(0);
		} catch (Exception e) {
			logger.error("Put Snapshot is failure", e);
			return null;
		}
	}

	public <T> T getObj(String key, Class<T> clazz) {
		return JSON.parseObject(this.getString(key), clazz);
	}

	public <T> List<T> getArrayObj(String key, Class<T> clazz) {
		return JSON.parseArray(this.getString(key), clazz);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(String key) {
		return JSON.parseObject(this.getString(key), Map.class);
	}

}