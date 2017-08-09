package com.lierl.api.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author lierl
 * @create 2017-08-08 14:31
 **/
public class ExcelUtil {

	/**
	 * 通过文件输入流和文件名称获取workbook对象
	 * @param fis
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkbook(InputStream fis, String filename) throws Exception{
		Workbook wb = null;
		if (StringUtils.isNotEmpty(filename)) {
			if(filename.toUpperCase().endsWith(".XLSX")){//2007
				wb = new XSSFWorkbook(fis);
			}else if(filename.toUpperCase().endsWith(".XLS")){//2003
				wb = new HSSFWorkbook(fis);
			}
		}
		return wb;
	}

	/**
	 * 通过文件路径获取workbook
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkbook(String path) throws Exception{
		Workbook wb = null;
		if (StringUtils.isNotEmpty(path)) {
			InputStream fis = new FileInputStream(path);
			if(path.toUpperCase().endsWith(".XLSX")){//2007
				wb = new XSSFWorkbook(fis);
			}else if(path.toUpperCase().endsWith(".XLS")){//2003
				wb = new HSSFWorkbook(fis);
			}
		}
		return wb;
	}

	public static void exportExcel(){

	}
}
