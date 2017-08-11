package net.print.pdf.util;

/**
 * Overview打印PDF
 * 
 * @author David
 * @date   2017年8月9日
 */
public class OverviewPrintPDF implements PrintPDF {
	/** 打印类型(百分比) **/
	private final int PRINT_TYPE_PERCENTAGE = 0;
	/** 要访问的url地址 **/
	private String url;
	/** 输出PDF的上一级文件夹路径 **/
	private String outFolderPath;
	/** 实际打印的pdf宽度 **/
	private Float printWidth;
	/** 实际打印的pdf高度 **/
	private Float printHeight;
	/** 打印类型:(0:百分百;其他:自适应) **/
	private int printType;
	/** 百分比(0%-200%) 格式类似: 0.1, 0.12,...... **/
	private Float scale;
	/** 页尾 **/
	private String footer;

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
	public OverviewPrintPDF(String url, String outFolderPath, Float printWidth, Float printHeight, int printType, Float scale, String footer) {
		this.url = url;
		this.outFolderPath = outFolderPath;
		this.printWidth = printWidth;
		this.printHeight = printHeight;
		this.printType = printType;
		this.scale = scale;
		this.footer = footer;
	}

	/** overview打印PDF **/
	public void print() {
		try {
			handle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handle() throws Exception {
		checkOverviewParam();

		String createPath = outFolderPath + "create.pdf";
		PDFUtils.createNotPagingPDF(url, createPath);

		String zoomPath = outFolderPath + "zoom.pdf";
		PDFUtils.zoomPDF(createPath, zoomPath, printType, scale, printWidth);

		String pagingPath = outFolderPath + "paging.pdf";
		PDFUtils.pagingPDF(zoomPath, pagingPath, printType, printWidth, printHeight);

		String footPath = outFolderPath + "foot.pdf";
		PDFUtils.addFooterToPdf(pagingPath, footPath, footer);
	}

	/** 检查overview参数是否符合业务逻辑 **/
	private void checkOverviewParam() throws Exception {
		if (url == null) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: url is null !");
		}
		if (outFolderPath == null) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: outFolderPath is null !");
		}
		if (printWidth == null || printWidth <= 0) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: printWidth is null or printWidth<=0 !");
		}
		if (printHeight == null || printHeight <= 0) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: printHeight is null or printHeight<=0 !");
		}
		if (checkScale()) {
			String msg = "[OverviewPrintPDF.checkOverviewParam] Cause by:Scale is null or it not in 0%-200%,When PrintType=0 !";
			throw new Exception(msg);
		}
	}

	/**
	 * 检查scale是否在0%-200%范围内,当打印类型为百分比的时候
	 * 
	 * @return true:不在 / false:在
	 */
	private boolean checkScale() {
		if (printType == PRINT_TYPE_PERCENTAGE) {
			if (scale == null || scale < 0 || scale > 2) {
				return true;
			}
		}
		return false;
	}
}
