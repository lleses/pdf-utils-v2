package net.print.pdf.util;

/**
 * 打印PDF
 * 
 * @author David
 * @date   2017年8月9日
 */
public class PDFServiceUtils {

	/**
	 * Overview打印PDF
	 * 
	 * @param url
	 * 			要访问的url地址
	 * @param outFolderPath
	 * 			输出PDF的上一级文件夹路径
	 * @param printWidth
	 * 			实际打印的pdf宽度
	 * @param printHeight
	 * 			实际打印的pdf高度
	 * @param printType
	 * 			打印类型:(0:百分百;其他:自适应)
	 * @param scale
	 * 			百分比(0%-200%) 格式类似: 0.1, 0.12,......
	 * @param footer
	 * 			页尾
	 */
	public static void overviewPrint(String url, String outFolderPath, Float printWidth, Float printHeight, int printType, Float scale, String footer) {
		OverviewPrintPDF PDF = new OverviewPrintPDF(url, outFolderPath, printWidth, printHeight, printType, scale, footer);
		PDF.print();
	}

	/**
	 * Dal打印PDF
	 * 
	 * @param url
	 * 			要访问的url地址
	 * @param outFolderPath
	 * 			输出PDF的上一级文件夹路径
	 * @param format
	 * 			打印格式:'A3', 'A4', 'A5', 'Legal', 'Letter', 'Tabloid'.
	 * @param footer
	 * 			页尾
	 */
	public static void dalPrint(String url, String outFolderPath, String format, String footer) {
		DalPrintPDF PDF = new DalPrintPDF(url, outFolderPath, format, footer);
		PDF.print();
	}

}
