package org.demobox.general.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 方便操作IO流
 * @author Chason Wu
 */
public class IOUtils {
	
	/**
	 * 关闭流资源对象
	 * @param io
	 */
	public static void closeStream(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			io = null;
		}
	}

}
