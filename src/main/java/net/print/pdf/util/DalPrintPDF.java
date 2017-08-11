package net.print.pdf.util;

/**
 * Dal打印PDF
 * 
 * @author David
 * @date   2017年8月9日
 */
public class DalPrintPDF implements PrintPDF {
	/** 要访问的url地址 **/
	private String url;
	/** 输出PDF的上一级文件夹路径 **/
	private String outFolderPath;
	/** 打印格式:'A3', 'A4', 'A5', 'Legal', 'Letter', 'Tabloid'. **/
	private String format;
	/** 页尾 **/
	private String footer;

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
	public DalPrintPDF(String url, String outFolderPath, String format, String footer) {
		this.url = url;
		this.outFolderPath = outFolderPath;
		this.format = format;
		this.footer = footer;
	}

	/** 打印PDF **/
	public void print() {
		try {
			handle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handle() throws Exception {
		//检查参数是否符合业务逻辑
		checkParam();

		//创建
		String createPath = outFolderPath + "create.pdf";
		PDFUtils.createPagingPDFByFormat(url, createPath, format);

		//添加页尾
		String footPath = outFolderPath + "foot.pdf";
		PDFUtils.addFooterToPdf(createPath, footPath, footer);
	}

	/** 检查参数是否符合业务逻辑,如果不符合则抛出错误 **/
	private void checkParam() throws Exception {
		//URL不能为空
		if (url == null) {
			throw new Exception("[DalPrintPDF.checkParam] Cause by: url is null !");
		}
		//文件夹路径不能为空
		if (outFolderPath == null) {
			throw new Exception("[DalPrintPDF.checkParam] Cause by: outFolderPath is null !");
		}
		//检查打印格式是否正确
		checkFormat(format);
	}

	/** 检查打印格式是否正确 **/
	private static void checkFormat(String format) throws Exception {
		String[] arr = { "A3", "A4", "A5", "Legal", "Letter", "Tabloid" };
		for (String type : arr) {
			if (type.equals(format)) {
				return;
			}
		}
		throw new Exception("[DalPrintPDF.checkFormat] Cause by:printFormat=" + format + ",  printFormat must in (A3,A4,A5,Legal,Letter,Tabloid) !");
	}
}
