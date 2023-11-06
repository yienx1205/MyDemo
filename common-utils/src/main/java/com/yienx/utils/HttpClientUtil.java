package com.yienx.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * httpclient连接池工具类
 */
public class HttpClientUtil {
    private final static Object syncLock = new Object();
    private static final String CHARSET_DEFAULT = "utf-8";
    public static final String HTTP = "http:";
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static PoolingHttpClientConnectionManager clientConnectionManager=null;
    private static CloseableHttpClient httpClient=null;
    private static RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();

    static {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        clientConnectionManager =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        clientConnectionManager.setMaxTotal(100);
        clientConnectionManager.setDefaultMaxPerRoute(20);
    }

    private static CloseableHttpClient getHttpClient(){
        if(httpClient == null){
            synchronized (syncLock){
                if(httpClient == null){
                    httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager).setDefaultRequestConfig(config).build();
                }
            }
        }
        return httpClient;
    }

    public static String get(String url) {
        return get(url, CHARSET_DEFAULT);
    }

    public static String get(String url,String charset){

        String result = null;
        CloseableHttpClient httpClient = getHttpClient();
        HttpRequest httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try{
            response =httpClient.execute((HttpGet)httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, charset);
            }
        }catch (Exception e){
            logger.error("get err.",e);

        }
        return result;
    }

    public static byte[] getByteArray(String url) {
        byte[] result = null;
        CloseableHttpClient httpClient = getHttpClient();
        HttpRequest httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute((HttpGet) httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toByteArray(entity);
            }
        } catch (Exception e) {
            logger.error("getByteArray err.", e);

        }
        return result;
    }
}
