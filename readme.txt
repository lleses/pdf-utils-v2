pdf-utils使用介绍

1.基础工具类 -- PDFUtils (用于自由拼装PDF功能)
	功能介绍: a.创建PDF 	//具体用例请查看 -- TestCreatePDF.java
				//创建PDF(不分页)
				PDFUtils.createNotPagingPDF(url, outPath);
				//创建PDF(根据格式分页)
				PDFUtils.createPagingPDFByFormat(url, outPath, format);//打印格式:'A3', 'A4', 'A5', 'Legal', 'Letter', 'Tabloid'.
				//创建PDF(根据宽高分页)
				PDFUtils.createPagingPDFByWidthAndHeight(url, outPath, width, height);
				
			b.PDF缩放	//具体用例请查看 -- TestZoomPDF.java
				//缩放PDF(通过自适应)
				PDFUtils.zoomPDByAdaptive(sourcePath, outPath, printWidth);
				//缩放PDF(根据百分比)
				PDFUtils.zoomPDFByPercentage(sourcePath, outPath, scale);
				
			c.PDF分页	//具体用例请查看 -- TestPagingPDF.java
				//分页PDF(根据自适应)
				PDFUtils.pagingPDFByAdaptive(sourcePath, outPath, printWidth, printHeight);
				//分页PDF(根据百分百)
				PDFUtils.pagingPDFByPercentage(sourcePath, outPath, printWidth, printHeight);
				
			d.页头页尾  	//具体用例请查看 -- ....
				//PDF添加页尾
				PDFUtils.addFooterToPdf(sourcePath, outPath, footer);
				//PDF添加页头
				PDFUtils.addHeaderToPdf(sourcePath, outPath, header);
				//PDF添加页头页尾
				PDFUtils.addHeaderFooterToPdf(sourcePath, outPath, header, footer);

2.业务工具类 -- PDFServiceUtils (用于overview, dal 等具体业务的使用)
			//具体用例请查看 -- TestOverviewPrintPDF.java
			a.PDFServiceUtils.overviewPrint(url, outFolderPath, printWidth, printHeight, printType, scale, footer);
			
			b.PDFServiceUtils.dalPrint(url, outFolderPath, format, footer);
			.....





