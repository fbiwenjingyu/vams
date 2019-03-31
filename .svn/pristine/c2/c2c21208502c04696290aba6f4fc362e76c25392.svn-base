package com.pd.system.utils;



import java.io.File;

public class DiskUtil {
	
	/**
	 * 获取空余空间
	 * @param filePath
	 * 			文件路径
	 * @return
	 * 
	 * @author WangWei
	 * 2014-3-18
	 */
	public static long getFreeSpace(String filePath){
		File file;
		try {
			file = new File(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return file.getFreeSpace();
	}
	
	/**
	 * 获取总空间
	 * @param filePath
	 * 			文件路径
	 * @return
	 * 
	 * @author WangWei
	 * 2014-3-18
	 */
	public static long getTotalSpace(String filePath){
		File file;
		try {
			file = new File(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return file.getTotalSpace();
	}
	
	
	/*public static int OS = 0;
	
	private final static int OS_WINDOWS = 1;
	private final static int OS_LINUX = 2;*/
	
	/**
	 * 获取操作系统
	 */
	/*static{
		String os = System.getProperty("os.name");
		if(os!=null && os.length()>0){
			if(os.startsWith("Windows")){
				OS = OS_WINDOWS;
			}else if(os.startsWith("Linux")){
				OS = OS_LINUX;
			}
		}
	}*/
	
	/**
	 * 使用CMD方式
	 * 获得磁盘空间
	 * @param dirName
	 * 			目录路径
	 * @return
	 * 		可用空间（单位：byte）
	 * 
	 * @author WangWei
	 * 2014-3-18
	 */
	/*
	public static long getFreeDiskSpace(String dirName){
		String command = "";
		int index = -1;
		// 单位比，按bytes大小返回
		int block = 1;
		switch (OS) {
		case OS_WINDOWS:
			command = " cmd.exe /c dir " + dirName;
			index = 2;
			block = 1;
			break;
			
		case OS_LINUX:
			command = " df -k " + dirName;
			index = 2;
			block = 1024;
			break;

		default:
			break;
		}
		
		if(OS != 0){
			Runtime runtime = Runtime.getRuntime();
			try {
				Process process = runtime.exec(command);
				if(process == null){
					return -1;
				}
				BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GB2312"));
				String line;
				String freeSpace = null;
				while((line = in.readLine()) != null){
					freeSpace = line;
				}
				if(freeSpace == null){
					return -1;
				}
				process.destroy();
				freeSpace = freeSpace.trim();
				freeSpace = freeSpace.replaceAll("\\.", "");
				freeSpace = freeSpace.replaceAll(",", "");
				// 分割 " " 多个空格的情况
				String[] items = freeSpace.split("[ ]+");
				try {
					long bytes = Long.parseLong(items[index]);
					bytes = bytes * block;
					return bytes;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return -1;
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
			
		}else{
			System.out.println("操作系统识别失败");
		}
		return -1;
	}
	*/
	
	public static void main(String[] args) {
		
	}
	
}
