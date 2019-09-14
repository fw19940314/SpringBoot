package com.cache.common;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/11
 * @Description:
 */
public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final String USER_AGENT = "user-agent";
    private static final String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";
    private static final String CONNECTION = "connection";
    private static final String CONNECTION_VALUE = "Keep-Alive";
    private static final String ACCEPT = "accept";
    private static final String UTF8 = "utf-8";
    private static final String ACCEPT_CHARSET = "Accept-Charset";
    private static final String CONTENTTYPE = "contentType";
    private static final String SSL = "ssl";

    protected HttpUtil() {

    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) throws IOException {
        String urlNameString = url + "?" + param;
        URL realUrl = new URL(urlNameString);
        URLConnection connection = realUrl.openConnection();
        StringBuilder result = new StringBuilder();
        connection.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
        connection.setRequestProperty(CONNECTION, CONNECTION_VALUE);
        connection.setRequestProperty(ACCEPT, "*/*");
        connection.connect();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            log.error("发送GET请求出现异常！", e);
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) throws IOException {
        StringBuilder result = new StringBuilder();

        String urlNameString = url + "?" + param;
        URL realUrl = new URL(urlNameString);
        URLConnection conn = realUrl.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty(CONTENTTYPE, UTF8);
        conn.setRequestProperty(ACCEPT_CHARSET, UTF8);
        conn.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
        conn.setRequestProperty(CONNECTION, CONNECTION_VALUE);
        conn.setRequestProperty(ACCEPT, "*/*");
        try (PrintWriter out = new PrintWriter(conn.getOutputStream()); BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF8))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            out.flush();
            out.print(param);
        } catch (Exception e) {
            log.error("发送 POST 请求出现异常！", e);
        }
        return result.toString();
    }

    public static String sendSSLPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try {
            SSLContext sc = SSLContext.getInstance(SSL);
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty(ACCEPT, "*/*");
            conn.setRequestProperty(CONNECTION, CONNECTION_VALUE);
            conn.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
            conn.setRequestProperty(ACCEPT_CHARSET, UTF8);
            conn.setRequestProperty(CONTENTTYPE, UTF8);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader indata = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while (ret != null) {
                ret = indata.readLine();
                if (ret != null && !ret.trim().equals("")) {
                    result.append(ret);
                }
            }
            conn.disconnect();
            indata.close();
        } catch (Exception e) {
            log.error("发送SSL POST 请求出现异常！", e);
        }
        return result.toString();
    }

    public static String HttpPostWithJson(String url, String json) {
        log.info("HttpPostWithJson,url={},json={}",url,json);
        String returnValue = "HTTP接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try{
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json,"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法
            log.info("returnValue={}",returnValue);
//	       CloseableHttpResponse httpResonse = httpClient.execute(httpPost);
//	       int statusCode = httpResonse.getStatusLine().getStatusCode();
//	       if(statusCode!=200)
//	       {
//	        	System.out.println("请求发送失败，失败的返回参数为："+httpResonse.getStatusLine());
//	        	returnValue = httpResonse.getStatusLine().toString();
//	       }
//

        }
        catch(Exception e)
        {
            log.error("httpClient exception json="+json+",url="+url,e);
            e.printStackTrace();
        }

        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            //trust anything
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            //trust anything
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
