package org.gocom.components.coframe.policy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eos.access.http.IWebInterceptor;
import com.eos.access.http.IWebInterceptorChain;
import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.ICache;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;

import commonj.sdo.DataObject;

public class RulesIpFilter implements IWebInterceptor {

	private static final Logger log = TraceLoggerFactory
			.getLogger(RulesIpFilter.class);

	private final static String CACHE_NAME = "RULES_IP_CACHE";

	public void doIntercept(HttpServletRequest request,
			HttpServletResponse response, IWebInterceptorChain interceptorChain)
			throws IOException, ServletException {
		try {
			String callIp = "";
			if (request.getHeader("x-forwarded-for") == null) {
				callIp = request.getRemoteAddr();
			} else {// 使用了代理
				callIp = request.getHeader("x-forwarded-for");
			}
			if ((callIp.split("\\.").length) == 4 && callIp != null) {
				if (isProhibitIp(callIp)) {// 是否禁止访问IP
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("您的IP受限，请与管理员联系!");
				} else {
					// 将请求传递到其它过滤器中
					interceptorChain.doIntercept(request, response);
				}
			} else {
				// 将请求传递到其它过滤器中
				interceptorChain.doIntercept(request, response);
			}
		} catch (Throwable e) {
			// ignore exception
			log.error("Error to Filter url of " + request.getRequestURL(), e);
			interceptorChain.doIntercept(request, response);
		}

	}

	/**
	 * 判断IP地址是否禁止访问
	 * 
	 * @param callIp
	 *            访问的IP地址
	 * @return 禁止访问返回true 允许访问返回false
	 */
	private Boolean isProhibitIp(String callIp) {
		ICache<String, Object> rulesIpCache = rulesIpCache = (ICache<String, Object>) CacheFactory
				.getInstance().findCache(CACHE_NAME);
		DataObject[] datas = (DataObject[]) rulesIpCache.get("rulesIpCache");
		String startIp = "";
		String endIp = "";
		for (int i = 0; i < datas.length; i++) {
			if (!"prohibit".equals(datas[i].getString("rulesType"))
					|| !"Y".equals(datas[i].getString("enabled"))) {
				continue;
			}
			startIp = datas[i].getString("startIP");
			endIp = datas[i].getString("endIP");
			if (CompareIpOfCtmIpIsBig(callIp, startIp)
					&& CompareIpOfCtmIpIsSmall(callIp, endIp)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 比较2个IP的大小
	 * 
	 * @param ip
	 *            比较的IP
	 * @param ctmIp
	 *            被比较的IP
	 * @return 比较的IP大于等于被比较的IP则返回true,否则返回false
	 */
	private Boolean CompareIpOfCtmIpIsBig(String ip, String ctmIp) {
		String ips[] = ip.split("\\.");
		String ctmIps[] = ctmIp.split("\\.");
		for (int i = 0; i < ips.length; i++) {
			if (Integer.parseInt(ips[i]) < Integer.parseInt(ctmIps[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 比较2个IP的大小
	 * 
	 * @param ip
	 *            比较的IP
	 * @param ctmIp
	 *            被比较的IP
	 * @return 比较的IP小于等于被比较的IP则返回true,否则返回false
	 */
	private Boolean CompareIpOfCtmIpIsSmall(String ip, String ctmIp) {
		String ips[] = ip.split("\\.");
		String ctmIps[] = ctmIp.split("\\.");
		for (int i = 0; i < ips.length; i++) {
			if (Integer.parseInt(ips[i]) > Integer.parseInt(ctmIps[i])) {
				return false;
			}
		}
		return true;
	}
}
