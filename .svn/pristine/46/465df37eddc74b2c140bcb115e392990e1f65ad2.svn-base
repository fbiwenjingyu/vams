package com.pd.webservice.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.params.CookiePolicy;
import org.apache.log4j.Logger;


/**
 * 请求六合一接口客户端程序
 * 
 * @author braveheartwzm
 * 
 */
@SuppressWarnings("deprecation")
public final class HttpExecuter {

    public static final Logger logger = Logger.getLogger(HttpExecuter.class);
    

    public static final HttpClient httpClient = new HttpClient();

    /**
     * 调用接口
     * @param requestData 请求参数对象
     * */
    public static ResponseVO invokeTmriWsByHttp(RequestVO requestData) throws Exception{
    	
    	//取出六合一中间件URL
    	String url = requestData.getUrl();
    	//未找到中间件地址，返回异常信息
    	if (null == url) {
			return new ResponseVO(0, "六合一中间件请求地址不能为空");
		}
    	
    	//错误信息返回提示
    	String errorMsg=null;
    	
    	//创建请求对象
        PostMethod postMethod = new PostMethod(url);
        
        try {
 
            //创建参数
            postMethod.setRequestBody(requestData.getParamsToArray());
            //设置重试处理方案，采用默认
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置编码方式
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,requestData.getRequestCharset());
            
            //设置请求头部信息
            postMethod.setRequestHeader("User-Agent","Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.04 (lucid) Firefox/3.6.13");
            postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            postMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
            postMethod.setRequestHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            // getMethod.setRequestHeader("Keep-Alive", "300");
            postMethod.setRequestHeader("Connection", "close");

            //WsConsts.SOCK_TIMEOUT_TIME
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, WsConsts.SOCK_TIMEOUT_TIME);
            postMethod.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);

            DefaultMethodRetryHandler retryhandler = new DefaultMethodRetryHandler();
            // retryhandler.setRequestSentRetryEnabled(false);
            retryhandler.setRetryCount(1);
            postMethod.setMethodRetryHandler(retryhandler);
            
            //WsConsts.CONN_TIMEOUT_TIME
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(WsConsts.CONN_TIMEOUT_TIME);
            // solve Chinese charset 1
            httpClient.getParams().setContentCharset("UTF-8");

            //发送请求。请求结果代码，200成功
            int statusCode = httpClient.executeMethod(postMethod);
		
            
            // 请求成功
            if (statusCode == HttpStatus.SC_OK) {
                ResponseVO responseContent = new ResponseVO();
                //设置请求结果代码
                responseContent.setStatusCode(statusCode);
                //获取请求地址
                responseContent.setUri(postMethod.getURI().toString());
                //获取HTTP数据格式
                responseContent.setContentType(postMethod.getResponseHeader("Content-Type").getValue());
                
                if (null != responseContent.getContentType()) {
                    //获取响应的编码方式
                    responseContent.setResponseCharset(postMethod.getResponseCharSet());
                    // 获取返回的http数据
                    responseContent.setRespDoc(postMethod.getResponseBodyAsString());
                }
                
                //**********处理返回结果json，并返回*************
                return responseContent.executeResp();
            } else {
            	//请求失败
            	errorMsg="接口调用失败，HTTP请求失败（"+statusCode+"）。status:" + postMethod.getStatusLine() + ". url:" + url;
            	logger.error(errorMsg);
            }
        } catch (HttpException e) {
        	errorMsg="接口调用失败，HTTP参数异常（HttpException）：" + url;
			logger.info(errorMsg);
		} catch (IOException e) {
			//中间件服务未启动？
			errorMsg="接口调用失败，HTTP连接异常（IOException）：" + url;
			logger.info(errorMsg);
		}finally {
            if (null != postMethod) {
            	//关闭资源
                postMethod.abort();
                postMethod.releaseConnection();
            }
        }
        return new ResponseVO(0, errorMsg);
    }
    
    
	/**
	 * 快速调用：查询
	 * @param jkid 接口ID
	 * @param param 调用参数
	 * @param logger log4j日志对象
	 * */
	public static WsRet fastInvokeQuery(String jkid, Map<String, String> param,Logger logger) {
		// 创建请求对象
		RequestVO reqvo = RequestVO.getQueryData(jkid, param);
		// 调用接口
		try {
			return invokeTmriWsByHttp(reqvo).getRespRet();
		} catch (Exception e) {
			String str ="本地处理异常：接口调用失败：" + jkid + "；" + param;
			logger.error(str, e);
			return new WsRet(str);
		}
	}
	
	/**
	 * 快速调用：写入
	 * @param jkid 接口ID
	 * @param param 调用参数
	 * @param logger log4j日志对象
	 * */
	public static WsRet fastInvokeWrite(String jkid, Map<String, String> param,Logger logger) {
		// 创建请求对象
		RequestVO reqvo = RequestVO.getWriteData(jkid, param);
		// 调用接口
		try {
			return invokeTmriWsByHttp(reqvo).getRespRet();
		} catch (Exception e) {
			String str ="本地处理异常：接口调用失败：" + jkid + "；" + param;
			logger.error(str, e);
			return new WsRet(str);
		}
	}
    
    
	// =======================================================================
	public static void main(String[] args) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("lsh", "1111111111111");

		RequestVO vo = RequestVO.getQueryData("01C26", param);
		 vo.setUrl("http://192.168.1.251:9999/tmriws/service/query.action");
//		vo.setUrl("http://127.0.0.1:8888/tmriws/service/query.action");

		while (true) {
			ResponseVO rvo = invokeTmriWsByHttp(vo);
			if (rvo.getWsCode() == 100) {
				System.out.println(rvo.toString());
				List<Map<String, String>> list = rvo.getWsList();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
			}else{
				System.out.println("循环结束");
				break;
			}
		}

		// fastInvokeQuery("01C26", param,logger);

	}
    
    
}
