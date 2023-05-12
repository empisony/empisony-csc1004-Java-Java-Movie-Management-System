package com.movie.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

	/**
	 * 上传文件到服务器
	 * 
	 * @param file      需要上传的文件
	 * @param finalPath 文件存放的服务器路径
	 * @return 上传成功返回true，失败返回false
	 */
	public static String upload(String picPath, String storagePath) {
		int index = picPath.lastIndexOf('.');
		String suffix = picPath.substring(index, picPath.length());
		String targetPic = IdWorker.generateId() + suffix;
		FileInputStream input;
		try {
			input = new FileInputStream(picPath);
			byte[] buffer = new byte[1024];
			File des = new File(storagePath, targetPic);
			FileOutputStream out = new FileOutputStream(des);
			int len = 0;
			while (-1 != (len = input.read(buffer))) {
				out.write(buffer, 0, len);
			}
			out.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetPic;
	}
}
