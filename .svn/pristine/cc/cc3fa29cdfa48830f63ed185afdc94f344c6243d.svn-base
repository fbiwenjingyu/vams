package com.pd.webservice.util;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.util.StringHelper;

/**
 * Description		: Read and write file helper class
 * <p/>
 * JDK Version Used	: JDK 6.0 
 * @author zy
 * @version 0.2.1
 */
public class FileHelper {

	private static Log log = LogFactory.getLog(FileHelper.class);

	/**
	 * Read file content
	 * @param filename
	 * @return
	 */
	public static List<String> reader(String filename) {
		if (StringHelper.isEmpty(filename)) {
			log.info("filename is null.");
			return null;
		}

		log.info("filename=" + filename);

		BufferedReader reader = null;
		List<String> lineList = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = null;
			while (null != (line = reader.readLine())) {
				lineList.add(line);
			}
		} catch (FileNotFoundException e) {
			log.info("file not found; filename=" + filename);
		} catch (IOException e) {
			log.info("read file exception.", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.info("close file exception.", e);
				}
			}
		}
		log.info("the file total line count:" + lineList.size());
		return lineList;
	}

	/**
	 * Append a line to file tail
	 * @param filename
	 * @param line
	 */
	public static void append(String filename, String line) {
		if (StringHelper.isEmpty(filename) || StringHelper.isEmpty(line)) {
			log.info("filename or line is null.");
			return;
		}

		FileWriter writer = null;
		try {
			writer = new FileWriter(filename, true);
			writer.write(line);
			writer.write("\n");
		} catch (FileNotFoundException e) {
			log.info("file not found; filename. filename=" + filename);
		} catch (IOException e) {
			log.info("write file exception.", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.info("close file exception", e);
				}
			}
		}
	}

	/**
	 * Append more lines to file tail 
	 * @param filename
	 * @param lineSet
	 */
	public static void append(String filename, Set<String> lineSet) {
		if (StringHelper.isEmpty(filename)) {
			log.info("filename is null.");
			return;
		}

		log.info("filename=" + filename);

		if (lineSet == null || lineSet.size() == 0) {
			log.info("lineSet container is null.");
			return;
		}

		log.info("lineSet.size=" + lineSet.size());

		FileWriter writer = null;
		try {
			writer = new FileWriter(filename, true);
			for (Iterator<String> iter = lineSet.iterator(); iter.hasNext();) {
				String line = iter.next();
				if (!StringHelper.isEmpty(line)) {
					writer.write(line);
				}
			}
		} catch (FileNotFoundException e) {
			log.info("file not found; filename=" + filename);
		} catch (IOException e) {
			log.info("write file exception.", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.info("close file exception", e);
				}
			}
		}
	}
	
	/**
	 * Read bytes from a given file.
	 * @param file
	 * @return
	 */
	public static byte[] read(File file) {
		InputStream in = null;
		ByteArrayOutputStream data = null;
		byte[] buffer = new byte[512];
		byte[] content = null;
		int readBytes = 0;
		try {
			in = new FileInputStream(file);
			data = new ByteArrayOutputStream();
			while((readBytes=in.read(buffer))!=-1) {
				data.write(buffer, 0, readBytes);
			}
			content = data.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(data!=null) {
					data.close();
				}
				if(in!=null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
}
