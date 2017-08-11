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
		//检查overview参数是否符合业务逻辑,如果不符合则抛出错误
		checkOverviewParam();

		//创建
		String createPath = outFolderPath + "create.pdf";
		PDFUtils.createNotPagingPDF(url, createPath);

		//缩放
		String zoomPath = outFolderPath + "zoom.pdf";
		PDFUtils.zoomPDF(createPath, zoomPath, printType, scale, printWidth);

		//分页
		String pagingPath = outFolderPath + "paging.pdf";
		PDFUtils.pagingPDF(zoomPath, pagingPath, printType, printWidth, printHeight);

		//添加页尾
		String footPath = outFolderPath + "foot.pdf";
		PDFUtils.addFooterToPdf(pagingPath, footPath, footer);
	}

	/** 检查overview参数是否符合业务逻辑,如果不符合则抛出错误 **/
	private void checkOverviewParam() throws Exception {
		//URL不能为空
		if (url == null) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: url is null !");
		}
		//文件夹路径不能为空
		if (outFolderPath == null) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: outFolderPath is null !");
		}
		//宽度不能为空而且要大于0
		if (printWidth == null || printWidth <= 0) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: printWidth is null or printWidth<=0 !");
		}
		//高度不能为空而且要大于0
		if (printHeight == null || printHeight <= 0) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by: printHeight is null or printHeight<=0 !");
		}
		//当printType=0 , scale不能为空而且要在 0%-200%的区间内
		boolean checkScale = scale == null || scale < 0 || scale > 2;
		if (printType == PRINT_TYPE_PERCENTAGE && checkScale) {
			throw new Exception("[OverviewPrintPDF.checkOverviewParam] Cause by:when PrintType=0, Scale is null or it not in 0%-200% !");
		}
	}

}
