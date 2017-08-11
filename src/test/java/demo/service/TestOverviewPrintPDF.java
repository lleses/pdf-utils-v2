package demo.service;

import net.print.pdf.util.PDFServiceUtils;
import net.print.pdf.util.PDFUtils;

/**
 * 测试Overview业务的打印功能
 * 
 * @author David
 * @date   2017年8月9日
 */
public class TestOverviewPrintPDF {

	public static void main(String[] args) {
		PDFUtils.isTest = true;//测试模式
		overviewPrintPDF();//测试overview
		System.out.println("[overviewPrintPDF]end..");
	}

	/** 测试overview **/
	private static void overviewPrintPDF() {
		//组装参数
		String url = "http://news.baidu.com";
		String outFolderPath = "C:/Users/ann/Desktop/testPDF/";
		Float printWidth = 300f;
		Float printHeight = 300f;
		int printType = 0;
		Float scale = 0.5f;
		String footer = "Printed by David, 2017-08-09";
		//打印
		PDFServiceUtils.overviewPrint(url, outFolderPath, printWidth, printHeight, printType, scale, footer);
	}

}
