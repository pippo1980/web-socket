/**
 * project : des-rss-fetcher
 * user created : pippo
 * date created : 2009-8-29 - 上午01:00:51
 */
package com.rensea.message.api.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 2009-8-29
 * @author pippo
 */
public class HttpClientHelper {

	private static Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);

	//"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9) Gecko/2008061015 Firefox/3.0";
	private String USER_AGENT = "RENJIAN-BOT";

	private Pattern charsetpattern = Pattern.compile("charset=(.*?)['\"]");

	// 对有错误的charset进行替换
	private Map<String, String> charsetCorrectMap = new HashMap<String, String>() {

		private static final long serialVersionUID = 8661288657743361265L;

		{
			this.put("zh-cn", "GBK");
			// 因为如果某个网站没有返回charset,httpclient会默认设置为ISO-8859-1
			this.put("ISO-8859-1", "GBK");
		}
	};

	private static HttpClientHelper helper = new HttpClientHelper();

	public static HttpClientHelper get() {
		return helper;
	}

	private MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();

	private HttpClientHelper() {
		HttpConnectionManagerParams httpConnManagerParams = new HttpConnectionManagerParams();
		httpConnManagerParams.setMaxTotalConnections(5000);
		httpConnManagerParams.setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, 500);
		httpConnManagerParams.setConnectionTimeout(6 * 1000);
		httpConnManagerParams.setSoTimeout(10 * 1000);
		this.manager.setParams(httpConnManagerParams);
	}

	public void execute(GetMethod getMethod) {
		try {
			HttpClient client = new HttpClient(this.manager);
			client.executeMethod(getMethod);
		} catch (Exception e) {
			logger.error("get resource due to error", e);
		} finally {
			getMethod.releaseConnection();
		}
	}

	public String get(String url) {
		if (logger.isDebugEnabled()) {
			logger.debug("new get request:" + url);
		}

		String result = null;
		GetMethod gm = null;
		try {
			HttpClient client = new HttpClient(this.manager);
			gm = new UTF8GetMethod(url);
			gm.setFollowRedirects(true);
			gm.setRequestHeader("User-Agent", this.USER_AGENT);

			client.executeMethod(gm);

			byte[] responseBody = gm.getResponseBody();
			result = new String(responseBody, gm.getResponseCharSet() != null ? gm.getResponseCharSet()
					: CharEncoding.UTF_8);
			if (logger.isDebugEnabled()) {
				logger.debug("the response content is:" + result);
			}
		} catch (Exception e) {
			logger.error("get resource due to error", e);
		} finally {
			gm.releaseConnection();
		}
		return result;
	}

	public String get(String url, String... charset) {
		String result = null;
		GetMethod gm = null;
		try {
			HttpClient client = new HttpClient(this.manager);
			gm = new GZipAwareGetMethod(url);
			gm.setFollowRedirects(true);
			gm.setRequestHeader("User-Agent", this.USER_AGENT);

			client.executeMethod(gm);

			byte[] responseBody = gm.getResponseBody();
			String ch = "";
			if (charset == null || charset.length == 0) {
				ch = gm.getResponseCharSet();
			} else {
				ch = charset[0];
			}
			String correctCharset = this.charsetCorrectMap.get(ch);
			if (correctCharset != null) {
				Matcher match = this.charsetpattern.matcher(new String(responseBody));
				if (match.find()) {
					ch = match.group(1);
				} else {
					ch = correctCharset;
				}
			}

			String content = EncodingUtil.getString(responseBody, ch);
			if (logger.isDebugEnabled()) {
				logger.debug("the response content is:" + content);
			}
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			gm.releaseConnection();
		}
		return result;
	}

	public String post(String url, Map<String, String> params) {
		PostMethod pm = new UTF8PostMethod(url);
		// 发生可恢复的异常时重复尝试3次
		pm.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
		pm.setRequestHeader("Cookie", "_renjian_sess_c=f618dd0b4285f92c025c60904daaa681282981d");

		try {

			for (String name : params.keySet()) {
				pm.addParameter(name, params.get(name));
			}

			if (logger.isDebugEnabled()) {
				logger.debug("new post request:" + url + ", the params is:" + params);
			}

			HttpClient client = new HttpClient(this.manager);
			client.executeMethod(pm);
			String content = pm.getResponseBodyAsString();
			if (logger.isDebugEnabled()) {
				logger.debug("the response content is:" + content);
			}
			return content;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.releaseConnection();
		}
		return null;
	}

	public String post(String url, Map<String, String> params, final AuthScope authscope, final Credentials credentials) {
		PostMethod pm = new UTF8PostMethod(url);
		// 发生可恢复的异常时重复尝试3次
		pm.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
		pm.setRequestHeader("Cookie", "_renjian_sess_c=f618dd0b4285f92c025c60904daaa681282981d");

		try {
			for (String name : params.keySet()) {
				pm.addParameter(name, params.get(name));
			}

			if (logger.isDebugEnabled()) {
				logger.debug("new post request:" + url + ", the params is:" + params);
			}

			HttpClient client = new HttpClient(this.manager);
			client.getState().setCredentials(authscope, credentials);
			client.getParams().setAuthenticationPreemptive(true);

			client.executeMethod(pm);
			String content = pm.getResponseBodyAsString();
			if (logger.isDebugEnabled()) {
				logger.debug("the response content is:" + content);
			}
			return content;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.releaseConnection();
		}
		return null;
	}

	private class GZipAwareGetMethod extends GetMethod {

		public GZipAwareGetMethod(String uri) {
			super(uri);
		}

		@Override
		protected void processResponseBody(HttpState state, HttpConnection conn) {
			Header codingHeader = this.getResponseHeader("Content-Encoding");
			InputStream is;
			try {
				is = super.getResponseBodyAsStream();
				if (codingHeader != null && "gzip".equals(codingHeader.getValue())) {
					is = new GZIPInputStream(is);
				}
				this.setResponseStream(is);
			} catch (IOException e) {
				//TODO:throw runtime exception
				e.printStackTrace();
			}

		}
	}

	public static class UTF8PostMethod extends PostMethod {

		public UTF8PostMethod() {
			super();
		}

		/**
		 * @param uri
		 */
		public UTF8PostMethod(String uri) {
			super(uri);
		}

		@Override
		public String getRequestCharSet() {
			return CharEncoding.UTF_8;
		}
	}

	public static class UTF8GetMethod extends GetMethod {

		public UTF8GetMethod() {
			super();
		}

		/**
		 * @param uri
		 */
		public UTF8GetMethod(String uri) {
			super(uri);
		}

		@Override
		public String getRequestCharSet() {
			return CharEncoding.UTF_8;
		}
	}
}
