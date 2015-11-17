/**
 * DefaultHttpClient.java
 * com.howbuy.gwsgateway.common.http
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年9月16日 		chao.li
 *
 * Copyright (c) 2015, Howbuy Rights Reserved.
 */

package com.keji50.k5.service.out.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * ClassName:DefaultHttpClient Function: TODO ADD FUNCTION Reason: TODO ADD
 * REASON
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年9月25日 下午2:59:22
 *
 * @see
 */
public class HttpClientService implements InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger("monitorlog");

    // socket 读写超时时间， 默认为5s
    private int socketTimeout = 5 * 1000;

    // 请求连接超时时间， 默认为5s
    private int connectTimeout = 5 * 1000;

    // 从connection manager中获取连接的超时时间， 默认为3s
    private int connectionRequestTimeout = 3 * 1000;

    // 最大连接数， 默认为200
    private int maxTotal = 200;

    private CloseableHttpClient httpClient = null;
    private PoolingHttpClientConnectionManager connManager = null;

    /**
     * 获取当前connection manager 连接池状态
     *
     */
    public PoolStats getPoolStat() {
        return connManager == null ? null : connManager.getTotalStats();
    }

    public String get(String url) throws IOException {
        return get(url, null, null);
    }

    public String get(String url, Map<String, String> map) throws IOException {
        return get(url, map, null);
    }

    public String get(String url, String charset) throws IOException {
        return get(url, null, charset);
    }

    public String get(String url, Map<String, String> paramsMap, String charset) throws IOException {
        if (url == null || url.isEmpty()) {
            return null;
        }
        charset = (charset == null ? "GBK" : charset);
        if (null != paramsMap && !paramsMap.isEmpty()) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> map : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
            }
            String querystring = URLEncodedUtils.format(params, charset);
            url += "?" + querystring;
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept-Encoding", "*");
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpGet);
            result = HttpEntityUtils.toString(response.getEntity(), charset);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
        }
        return result;
    }

    public String post(String url, String request) throws IOException {
        return post(url, request, null);
    }

    public String post(String url, String request, String charset) throws IOException {
        if (url == null || url.isEmpty()) {
            return null;
        }
        charset = (charset == null ? "GBK" : charset);
        CloseableHttpResponse response = null;
        String res = null;
        try {
            StringEntity entity = new StringEntity(request, charset);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Accept-Encoding", "*");
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            res = HttpEntityUtils.toString(response.getEntity(), charset);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
        }
        return res;
    }

    public String post(String url, Map<String, String> map) throws IOException {
        return post(url, map, null);
    }

    public String post(String url, Map<String, String> paramsMap, String charset) throws IOException {
        if (url == null || url.isEmpty()) {
            return null;
        }
        charset = (charset == null ? "GBK" : charset);
        CloseableHttpResponse response = null;
        String res = null;
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> map : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, charset);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Accept-Encoding", "*");
            httpPost.setEntity(formEntity);
            response = httpClient.execute(httpPost);
            res = HttpEntityUtils.toString(response.getEntity(), charset);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
        }
        return res;
    }

    @Override
    public void afterPropertiesSet() {
        log.info("Init http client service begin, socketTimeout {}, connectTimeout {}, connectionRequestTimeout {}, maxTotal {}", socketTimeout,
                connectTimeout, connectionRequestTimeout, maxTotal);

        connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(maxTotal);
        connManager.setDefaultMaxPerRoute(connManager.getMaxTotal());

        RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();
        HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(0, false);
        httpClient = HttpClients.custom().setDefaultRequestConfig(config).setRetryHandler(retryHandler).setConnectionManager(connManager).build();

        log.info("Http client service opened");
    }

    @Override
    public void destroy() {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        if (connManager != null) {
            connManager.close();
        }

        log.info("http client service closed");
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

}
