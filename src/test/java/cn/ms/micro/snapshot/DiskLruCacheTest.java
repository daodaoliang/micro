package cn.ms.micro.snapshot;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DiskLruCacheTest {

	private final int appVersion = 100;
	private File cacheDir;
	private DiskLruCache cache;

	@Rule
	public TemporaryFolder tempDir = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		cacheDir = new File("/Users/lry/temp/DiskLruCacheTest");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
	}

	@Test
	public void writeAndReadEntry() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(0, "ABC");
		creator.set(1, "DE");
		creator.commit();
		DiskLruCache.Snapshot snapshot = cache.get("k1");
		System.out.println(snapshot.getString(0));
		System.out.println(snapshot.getString(1));
	}

	@After
	public void tearDown() throws Exception {
		cache.close();
	}

}
