package com.pd.system.utils;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.BarcodeEncoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BarcodePainter;
import org.jbarcode.paint.TextPainter;
import org.jbarcode.util.ImageUtil;

/**
 * 条码工具
 * */
public class BarcodeTools {

	private static final String org_jbarcode_encode = "org.jbarcode.encode.";
	private static final String org_jbarcode_paint = "org.jbarcode.paint.";
	private static final String GET_INSTANCE = "getInstance";

	/**
	 * 条码种类
	 * */
	public static enum EncoderType {

		/**
		 * 库德巴码也可表示数字和字母信息，主要用于医疗卫生、图书情报、物资等领域的自动识别。
		 * */
		CodabarEncoder(org_jbarcode_encode + "CodabarEncoder",
				org.jbarcode.encode.CodabarEncoder.class),

		Code11Encoder(org_jbarcode_encode + "Code11Encoder",
				org.jbarcode.encode.Code11Encoder.class),
		/**
		 * 128可表示ASCII 0 到 ASCII 127 共计128个ASCII字符。
		 * */
		Code128Encoder(org_jbarcode_encode + "Code128Encoder",
				org.jbarcode.encode.Code128Encoder.class),
		/**
		 * 39码是一种可表示数字、字母等信息的条码，主要用于工业、图书及票证的自动化管理，目前使用极为广泛。
		 * */
		Code39Encoder(org_jbarcode_encode + "Code39Encoder",
				org.jbarcode.encode.Code39Encoder.class),
		/**
		 * 39码扩展
		 * */
		Code39ExtEncoder(org_jbarcode_encode + "Code39ExtEncoder",
				org.jbarcode.encode.Code39ExtEncoder.class),
		/**
		 * Code 93码与39码具有相同的字符集，但它的密度要比39码高，所以在面积不足的情况下，可以用93码代替39码。
		 * */
		Code93Encoder(org_jbarcode_encode + "Code93Encoder",
				org.jbarcode.encode.Code93Encoder.class),
		/**
		 * 93码扩展
		 * */
		Code93ExtEncoder(org_jbarcode_encode + "Code93ExtEncoder",
				org.jbarcode.encode.Code93ExtEncoder.class),
		/**
		 * EAN码是国际物品编码协会制定的一种商品用条码，通用于全世界。EAN标准版（EAN-13）
		 * */
		EAN13Encoder(org_jbarcode_encode + "EAN13Encoder",
				org.jbarcode.encode.EAN13Encoder.class),
		/**
		 * EAN码是国际物品编码协会制定的一种商品用条码，通用于全世界。EAN缩短版（EAN-8）
		 * */
		EAN8Encoder(org_jbarcode_encode + "EAN8Encoder",
				org.jbarcode.encode.EAN8Encoder.class),
		/**
		 * UPC-A条码， UPC码是美国统一代码委员会制定的一种商品用条码，主要用于美国和加拿大地区，我们在美国进口的商品上可以看到
		 * */
		UPCAEncoder(org_jbarcode_encode + "UPCAEncoder",
				org.jbarcode.encode.UPCAEncoder.class),
		/**
		 * UPC-E条码， UPC码是美国统一代码委员会制定的一种商品用条码，主要用于美国和加拿大地区，我们在美国进口的商品上可以看到
		 * */
		UPCEEncoder(org_jbarcode_encode + "UPCEEncoder",
				org.jbarcode.encode.UPCEEncoder.class);

		// ====================================
		private String name;
		private Class clazz;

		private EncoderType(String name, Class clazz) {
			this.name = name;
			this.clazz = clazz;
		}

		public Class getClazz() {
			return clazz;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * 标签文本打印对象
	 * */
	public static enum TextType {
		/**
		 * 基本文本，建议使用此文本格式
		 * */
		BaseLineTextPainter(org_jbarcode_paint + "BaseLineTextPainter",
				org.jbarcode.paint.BaseLineTextPainter.class),
		/**
		 * EAN13文本
		 * */
		EAN13TextPainter(org_jbarcode_paint + "EAN13TextPainter",
				org.jbarcode.paint.EAN13TextPainter.class),
		/**
		 * EAN8文本
		 * */
		EAN8TextPainter(org_jbarcode_paint + "EAN8TextPainter",
				org.jbarcode.paint.EAN8TextPainter.class),
		/**
		 * UPC-A文本
		 * */
		UPCATextPainter(org_jbarcode_paint + "UPCATextPainter",
				org.jbarcode.paint.UPCATextPainter.class),
		/**
		 * UPC-E文本
		 * */
		UPCETextPainter(org_jbarcode_paint + "UPCETextPainter",
				org.jbarcode.paint.UPCETextPainter.class);

		// ====================================
		private String name;
		private Class clazz;

		private TextType(String name, Class clazz) {
			this.name = name;
			this.clazz = clazz;
		}

		public Class getClazz() {
			return clazz;
		}

		public String getName() {
			return name;
		}

	}

	/**
	 * 标签图像打印对象
	 * */
	public static enum LabelType {

		/**
		 * 圆形标签
		 * */
		CircularPainter(org_jbarcode_paint + "CircularPainter",
				org.jbarcode.paint.CircularPainter.class),
		/**
		 * 高标签
		 * */
		HeightCodedPainter(org_jbarcode_paint + "HeightCodedPainter",
				org.jbarcode.paint.HeightCodedPainter.class),

		/**
		 * 标准比例标签
		 * */
		WideRatioCodedPainter(org_jbarcode_paint + "WideRatioCodedPainter",
				org.jbarcode.paint.WideRatioCodedPainter.class),

		/**
		 * 标准加宽标签，建议使用此标签
		 * */
		WidthCodedPainter(org_jbarcode_paint + "WidthCodedPainter",
				org.jbarcode.paint.WidthCodedPainter.class);

		// ====================================
		private String name;
		private Class clazz;

		private LabelType(String name, Class clazz) {
			this.name = name;
			this.clazz = clazz;
		}

		public Class getClazz() {
			return clazz;
		}

		public String getName() {
			return name;
		}

	}

	/**
	 * 创建条码
	 * */
	public static BufferedImage createCode(String code,
			EncoderType encoderType, LabelType labelType, TextType textType) {
		try {
			BarcodeEncoder barcodeEncoder = (BarcodeEncoder) encoderType
					.getClazz().getMethod(GET_INSTANCE, null)
					.invoke(encoderType.getClazz(), null);
			BarcodePainter barcodePainter = (BarcodePainter) labelType
					.getClazz().getMethod(GET_INSTANCE, null)
					.invoke(labelType.getClazz(), null);
			TextPainter textPainter = (TextPainter) textType.getClazz()
					.getMethod(GET_INSTANCE, null)
					.invoke(textType.getClazz(), null);
			return new JBarcode(barcodeEncoder, barcodePainter, textPainter)
					.createBarcode(code);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InvalidAtributeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存到文件
	 * 
	 * @param paramBufferedImage
	 *            文件image对象
	 * @param dirpath
	 *            文件夹路径
	 * @param filename
	 *            要保存的文件名
	 * @param ext
	 *            文件扩展名，PNG、JPEG、GIF
	 * */
	public static void saveToFile(BufferedImage paramBufferedImage,
			String dirpath, String filename, String ext, int dpi_x, int dpi_y) {
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(
					dirpath + filename + "." + ext);
			ImageUtil.encodeAndWrite(paramBufferedImage, ext,
					localFileOutputStream, dpi_x, dpi_y);
			localFileOutputStream.close();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	/**
	 * 获取code128条码（加宽），图片扩展名限定：PNG、JPG、GIF
	 * */
	public static BufferedImage getCode128(String code) {
		return createCode(code, EncoderType.Code128Encoder,
				LabelType.WidthCodedPainter, TextType.BaseLineTextPainter);
	}

	
	// ===================================
	public static void main(String[] args) {
		saveToFile(
				createCode("11223xddfferew111", EncoderType.Code128Encoder,
						LabelType.WidthCodedPainter,
						TextType.BaseLineTextPainter), "C:/TEMP/", "code",
				"png", 50, 50);
		
	}

}
