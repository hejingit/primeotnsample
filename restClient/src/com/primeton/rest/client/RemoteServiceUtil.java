package com.primeton.rest.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class RemoteServiceUtil {
	
	public static String post( String serviceUrl, String jsonData )throws Exception{
		HttpClient httpClient = new HttpClient();
		PostMethod method = null;
        try{
        	method = new PostMethod( serviceUrl);
        	method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
        	if (jsonData!=null){
        		RequestEntity requestEntity = new StringRequestEntity(jsonData,"text/xml","UTF-8");
        		method.setRequestEntity(requestEntity);
        	}
            
            //执行
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK)  {
                //读取内容
                String ResponseHTML = method.getResponseBodyAsString();
                return ResponseHTML;
            }else if (statusCode == HttpStatus.SC_NO_CONTENT)  {
            	return "";
            }
            throw new Exception("Method failed: " + method.getStatusLine());

        }catch(Exception e){
            throw e;
        }
        finally
        {
            //释放连接
        	if (method!=null)
        		method.releaseConnection();
        }
	}
	public static String put( String serviceUrl, String jsonData )throws Exception{
		HttpClient httpClient = new HttpClient();
		PutMethod method = null;
        try{
        	method = new PutMethod( serviceUrl);
        	method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
        	if (jsonData!=null){
        		RequestEntity requestEntity = new StringRequestEntity(jsonData,"text/xml","UTF-8");
        		method.setRequestEntity(requestEntity);
        	}
            
            //执行
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK)  {
                //读取内容
                String ResponseHTML = method.getResponseBodyAsString();
                return ResponseHTML;
            }else if (statusCode == HttpStatus.SC_NO_CONTENT)  {
            	return "";
            }
            throw new Exception("Method failed: " + method.getStatusLine());

        }catch(Exception e){
            throw e;
        }
        finally
        {
            //释放连接
        	if (method!=null)
        		method.releaseConnection();
        }
	}	
	public static String delete( String serviceUrl )throws Exception{
		HttpClient httpClient = new HttpClient();
		DeleteMethod method =  null;
        try{
        	String postUrl= serviceUrl;
        	
        	method = new DeleteMethod( postUrl);
        	method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
            //执行
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK)  {
                //读取内容
                String ResponseHTML = method.getResponseBodyAsString();
                return ResponseHTML;
            }else if (statusCode == HttpStatus.SC_NO_CONTENT)  {
            	return "";
            }
            throw new Exception("Method failed: " + method.getStatusLine());

        }catch(Exception e){
        	throw e;
        }
        finally
        {
            //释放连接
        	if (method!=null)
        		method.releaseConnection();
        }
	}
	
	public static String get( String serviceUrl )throws Exception{
		HttpClient httpClient = new HttpClient();
		GetMethod method =  null;
        try{
        	String postUrl= serviceUrl;
        	
        	method = new GetMethod( postUrl);
        	method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
            //执行
            int statusCode = httpClient.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK)  {
                //读取内容
                String ResponseHTML = method.getResponseBodyAsString();
                return ResponseHTML;
            }else if (statusCode == HttpStatus.SC_NO_CONTENT)  {
            	return "";
            }
            throw new Exception("Method failed: " + method.getStatusLine());

        }catch(Exception e){
        	throw e;
        }
        finally
        {
            //释放连接
        	if (method!=null)
        		method.releaseConnection();
        }
	}
}
