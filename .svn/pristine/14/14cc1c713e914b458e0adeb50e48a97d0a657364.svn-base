package com.pd.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.ServletOutputStream;

import com.pd.system.framework.BaseAction;
import com.pd.webservice.util.Base64Image;
import com.pd.webservice.util.FileHelper;
import com.pd.wjpda.util.Base64Tool;

public class Base64ToImageAction extends BaseAction {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	
	private static final long serialVersionUID = 1L;

	public void getPic(){
		try{
			String picpath = request.getParameter("picpath");
			ServletOutputStream sos = response.getOutputStream();
			FileInputStream fis = new FileInputStream(picpath);
			byte[] buf = new byte[2048];  //缓冲区大小
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
			    sos.write(buf, 0, len);
			}
			sos.flush();
			sos.close();
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void getBase64ToImagePath(){
		String picpath = request.getParameter("picpath");
		byte[] data = null;
		if(picpath.endsWith(".jpg")){
			data = FileHelper.read(new File(picpath));
		}else{
			data = Base64Image.getBase64ToByte(picpath);
		}
		
		
		
		System.out.println(new Date()+"data-----"+data);
		if(picpath != null){
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				os.write(data);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


