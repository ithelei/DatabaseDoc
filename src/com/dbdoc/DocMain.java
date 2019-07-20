package com.dbdoc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.apache.log4j.Logger;

import com.dbdoc.db.model.provider.TableProvider;
import com.dbdoc.utils.FreemarkerUtils;

/***
 * 
 * 数据库设计文档生成器
 * 
 *  
 *
 * 
 */
public class DocMain {
	public static final Logger log = Logger.getLogger(DocMain.class);
	public static final String template_file = "template/doc.xml";
	public static final String out_dir = "c:\\doc\\dbdoc.doc";

	public static void main(String args[]) throws IOException {
		Map propMap = new HashMap();
		try {
			List tables = TableProvider.getInstance().getAllTables();
			propMap.put("tableList", tables);
			FreemarkerUtils.writeTemplateToFile(DocMain.template_file, propMap,
					DocMain.out_dir);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("生成完毕!!!");
		// 打开生成的文件
		Runtime.getRuntime().exec("cmd.exe /c start " + out_dir);
	}
}
