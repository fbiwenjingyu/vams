package com.pd.wjpda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class ZipUtil {
	
	/*----------------------------     打包压缩        ------------------------*/
	public static void zipFiles(File zip, String path, File...srcFiles){
		ZipOutputStream out;
		try {
			out = new ZipOutputStream(new FileOutputStream(zip));
			ZipUtil.zipFiles(out, path, srcFiles);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void zipFiles(ZipOutputStream out, String path, File...srcFiles){
		byte[] buf = new byte[1024];
		for (int i = 0; i < srcFiles.length; i++) {
			if(srcFiles[i].isDirectory()){
				File[] files = srcFiles[i].listFiles();
				String srcPath = srcFiles[i].getName();
				try {
					out.putNextEntry(new ZipEntry(path + srcPath));
					zipFiles(out, path + srcPath, files);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					FileInputStream fis = new FileInputStream(srcFiles[i]);
					out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
					int len;
					while ((len=fis.read(buf))>0) {
						out.write(buf,0,len);
					}
					out.closeEntry();
					fis.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/*   -------------------     解包       --------------------   */

	public static void unZipFiles(String zipPath, String descDir){
		unZipFiles(new File(zipPath), descDir);
	}

	@SuppressWarnings("unchecked")
	public static void unZipFiles(File zipFiles, String descDir){
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		try {
			ZipFile zip = new ZipFile(zipFiles);
			for (Enumeration entries = zip.entries();entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream is = zip.getInputStream(entry);
				//String outPath = (descDir + zipEntryName);
				String outPath = descDir + File.separator + zipEntryName;
				File file = new File(descDir);
				if(!file.exists()){
					file.mkdirs();
				}
				if(new File(outPath).isDirectory()){
					continue;
				}
				OutputStream out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[1024];
				int len;
				while((len=is.read(buf1))>0){
					out.write(buf1,0,len);
				}
				is.close();
				out.close();
			}
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
