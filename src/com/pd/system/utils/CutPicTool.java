package com.pd.system.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class CutPicTool {
	// ԴͼƬ·�������:c:/1.jpg
	private String srcpath;

	// ����ͼƬ���·�����.��:c:/2.jpg
	private String subpath;

	// ���е�x���
	private int x;

	private int y;

	// ���е���
	private int width;

	private int height;

	public CutPicTool(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void cutPic(){
		FileInputStream fis = null ;
		ImageInputStream iis = null;
		
		try {
			fis = new FileInputStream(srcpath);
			/*
             * ���ذ����е�ǰ��ע�� ImageReader �� Iterator����Щ ImageReader 
             * ����ܹ�����ָ����ʽ�� ����formatName - �����ʽ��ʽ��� .
             *������ "jpeg" �� "tiff"���� �� 
             */
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			//��ȡͼƬ��
			iis = ImageIO.createImageInputStream(fis);
			/* 
             * iis:��ȡԴ.true:ֻ��ǰ����
             * ��������ζ�Ű�������Դ�е�ͼ��ֻ��˳���ȡ���������� reader
             * ���⻺�������ǰ�Ѿ���ȡ��ͼ���������ݵ���Щ���벿�֡�
             */
			reader.setInput(iis,true);
			
			/* 
             * ������ζ������н������ ����ָ�����������ʱ�� Java Image I/O 
             * ��ܵ��������е���ת��һ��ͼ���һ��ͼ�������ض�ͼ���ʽ�Ĳ��
             * ������ ImageReader ʵ�ֵ� getDefaultReadParam �����з��� 
             * ImageReadParam ��ʵ��  
             */
			ImageReadParam param = reader.getDefaultReadParam();
			
			/*
             * ͼƬ�ü�����Rectangle ָ�������ռ��е�һ������ͨ�� Rectangle ����
             * �����϶������꣨x��y������Ⱥ͸߶ȿ��Զ���������� 
             */ 
			Rectangle rect = new Rectangle(x,y,width,height);
			
			//�ṩһ�� BufferedImage��������������������ݵ�Ŀ�ꡣ
			param.setSourceRegion(rect);
			
			/*
             * ʹ�����ṩ�� ImageReadParam ��ȡͨ������ imageIndex ָ���Ķ��󣬲���
             * ����Ϊһ������� BufferedImage ���ء�
             */
			BufferedImage bi = reader.read(0, param);
			//������ͼƬ
			ImageIO.write(bi, "jpg", new File(subpath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(iis!=null){
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getSrcpath() {
		return srcpath;
	}

	public void setSrcpath(String srcpath) {
		this.srcpath = srcpath;
	}

	public String getSubpath() {
		return subpath;
	}

	public void setSubpath(String subpath) {
		this.subpath = subpath;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}

