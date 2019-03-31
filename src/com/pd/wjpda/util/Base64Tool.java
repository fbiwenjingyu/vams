package com.pd.wjpda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.pd.webservice.util.FileHelper;

import sun.misc.*;

public class Base64Tool {

	/**
	 * 将指定的文件通过base64写到制定路径下的图片中 操作成功后删除源文件
	 * @param file	指定的文件
	 * @param path	目标图片
	 * @throws IOException 
	 */
	public static void toBase64Pic(File file,String path) {
		 byte[] data = FileHelper.read(file);
		 String encode = new BASE64Encoder().encode(data);
		 
		 /**
		  * 获取加密后的byte
		  */
		 byte[] buffer = encode.getBytes();
		 FileOutputStream out = null;
		try {
			file.delete();	
			/**
			 * 将字符串写入某个文件内
			 */
			out = new FileOutputStream(path);
			out.write(buffer);
			out.close();
			/**
			 * 删除原文件
			 */
			//file.delete();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	/**
	 * 将加密后的文件解密
	* @Title: decoderBase64File 
	* @Description: TODO
	* @param @param path
	* @param @return    设定文件 
	* @return byte[]    返回类型 
	* @throws 
	* @author zl
	* 2014-8-7
	 */
	public static  byte[] decoderBase64File(String path){
		if (path == null){ // 图像数据为空
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try { 
			if(path.endsWith(".jpg")){
				return FileHelper.read(new File(path));
			}
			String base64Str = new String(FileHelper.read(new File(path)));
			byte[] bytes = decoder.decodeBuffer(base64Str);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			return bytes;
		} catch (Exception e) {
			return null;
		}
	 }   
	
	/**
	 * 文件解密供测试用
	* @Title: decoderBase64File 
	* @Description: TODO
	* @param @param path
	* @param @param targetPath
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-7
	 */
	public static void decoderBase64File(String path, String targetPath){
		 InputStream in = null;
		 FileOutputStream out = null;
		 try {
			in = new FileInputStream(new File(path));
			byte[] buffer = new BASE64Decoder().decodeBuffer(in);
			out = new FileOutputStream(targetPath);
			out.write(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 }   

	
//	 public static void main(String[] arg){
//		 try {
//			 //toBase64Pic(new File("D:/usr/parkupload/20140714121953369062.jpg"),"D:/usr/parkupload/01.jpg");
//			 System.out.println("开始运行时间----------"+new Date());
//			 decoderBase64File("D:/usr/parkupload/01.jpg", "D:/usr/parkupload/1.jpg");
//			 System.out.println("-----将图片加密后在解密输出完成-----"+new Date());
//			 byte[] tt = decoderBase64File("D:/usr/parkupload/01.jpg");
//			System.out.println("将图片输出为byte----------"+new Date());
//			  } catch (Exception e) {
//			   e.printStackTrace();
//
//			  } 
//	 }
}
