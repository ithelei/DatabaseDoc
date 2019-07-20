package com.dbdoc.db.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileUtils {
	private static Logger logger = Logger.getLogger(FileUtils.class);

	/**
	 * @param path
	 *            文件路径
	 * @param suffix
	 *            后缀�? 为空则表示所有文�?
	 * @param isdepth
	 *            是否遍历子目�?
	 * @return list
	 */
	public static List<File> getListFiles(String path, String suffix, boolean isdepth) {
		List<File> lstFileNames = new ArrayList<File>();
		File file = new File(path);
		return listFile(lstFileNames, file, suffix, isdepth);
	}

	private static List<File> listFile(List<File> lstFileNames, File f, String suffix, boolean isdepth) {
		// 若是目录, 采用递归的方法遍历子目录
		if (f.isDirectory()) {
			File[] t = f.listFiles();

			for (int i = 0; i < t.length; i++) {
				// System.out.println("dd=="+t[i].getName().substring(0, t[i].getName().lastIndexOf(".")));
				listFile(lstFileNames, t[i], suffix, isdepth);
			}
		} else {
			String filePath = f.getAbsolutePath();
			if (!suffix.equals("")) {
				int begIndex = filePath.lastIndexOf("."); // �?���?��.(即后�?��前面�?)的索�?
				String tempsuffix = "";

				if (begIndex != -1) {
					tempsuffix = filePath.substring(begIndex + 1, filePath.length());
					if (tempsuffix.equals(suffix)) {
						lstFileNames.add(f);
					}
				}
			} else {
				lstFileNames.add(f);
			}
		}
		return lstFileNames;
	}

}