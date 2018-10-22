package org.demobox.general.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 使用GZIP压缩文件
 * @author Chason Wu
 */
public class GZipUtils {

	/**
	 * GZip压缩
	 * @param srcFile：要压缩的文件
	 * @param zipFile：压缩后文件存放的地址
	 * @throws IOException
	 */
	public static void zip(File srcFile, File zipFile) throws IOException {
		GZIPOutputStream gos = null;
		FileInputStream fis = null;
		
		try {
			gos = new GZIPOutputStream(new FileOutputStream(zipFile));
			fis = new FileInputStream(srcFile);
			
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				gos.write(buffer, 0, len);
			}
		} finally {
			IOUtils.closeStream(fis);
			IOUtils.closeStream(gos);
		}
	}

	/**
	 * GZip解压
	 * @param zipFile
	 * @param unzipFile
	 * @throws IOException
	 */
	public static void unzip(File zipFile, File unzipFile) throws IOException {
		GZIPInputStream gis = null;
		FileOutputStream fos = null;
		
		try {
			gis = new GZIPInputStream(new FileInputStream(zipFile));
			fos = new FileOutputStream(unzipFile);

			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = gis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
		} finally {
			IOUtils.closeStream(gis);
			IOUtils.closeStream(fos);
		}
	}

}
