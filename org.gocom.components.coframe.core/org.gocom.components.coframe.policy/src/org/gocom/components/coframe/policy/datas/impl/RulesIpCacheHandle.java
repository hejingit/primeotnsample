/**
 * 
 */
package org.gocom.components.coframe.policy.datas.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.tools.DasEntityHelper;

import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.CacheProperty;
import com.eos.common.cache.ICache;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.internal.system.domain.DomainManager;
import com.eos.system.annotation.Bizlet;
import com.primeton.cap.auth.AuthResource;
import com.primeton.cap.party.Party;
import com.primeton.ext.common.cache.CacheHelper;

import commonj.sdo.DataObject;

@Bizlet("Ip规则缓存处理")
public class RulesIpCacheHandle {

	private final static String CACHE_NAME = "RULES_IP_CACHE";

	private final static String ENTITY_NAME = "org.gocom.components.coframe.policy.datas.IPAccessRules";

	private static ICache<String, Object> rulesIpCache = null;

	static {

		rulesIpCache = (ICache<String, Object>) CacheFactory.getInstance()
				.findCache(CACHE_NAME);
		if (rulesIpCache == null) {
			String cluserName = DomainManager.getInstance().getCurrentServer().getGroupName();
			if (StringUtils.isBlank(cluserName)){
				CacheProperty cacheProperty = CacheProperty.newLocalCacheProperty(CACHE_NAME);
				rulesIpCache = (ICache<String, Object>) CacheFactory.getInstance().createCache(cacheProperty);
			}else{
				CacheProperty cacheProperty = CacheProperty.newClusteredCacheProperty(CACHE_NAME, null);
				//同步复制模式
				cacheProperty.setOtherProperties("CacheMode", CacheHelper.REPL_SYNC);
				rulesIpCache = (ICache<String, Object>) CacheFactory.getInstance().createCache(cacheProperty);
			}
		}
	}

	public static void initCache() {

		rulesIpCache.clear();

		DataObject template = DataObjectUtil.createDataObject(ENTITY_NAME);
		DataObject[] datas = DasEntityHelper.queryEntitiesByTemplate(template);
		rulesIpCache.put("rulesIpCache", datas);
	}

	@Bizlet("更新缓存")
	public void updateCache(DataObject[] rules) {
		rulesIpCache.put("rulesIpCache", rules);
	}
}
