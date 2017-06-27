package org.gocom.components.coframe.policy;

import org.gocom.components.coframe.policy.datas.impl.RulesIpCacheHandle;

import com.eos.access.http.WebInterceptorConfig;
import com.eos.access.http.WebInterceptorManager;
import com.eos.runtime.resource.IContributionEvent;
import com.eos.runtime.resource.IContributionListener;

public class CoframePolicyContributionListener implements IContributionListener {

	public void load(IContributionEvent arg0) {
		// TODO 自动生成方法存根

	}

	public void loadFinished(IContributionEvent arg0) {
		// 初始化缓存
		RulesIpCacheHandle.initCache();
		// 注册过滤器
		WebInterceptorConfig config = new WebInterceptorConfig();
		config.setFilterId("RulesIpFilter");
		config.setSortIdx(0);
		config
				.setClassName("org.gocom.components.coframe.policy.RulesIpFilter");
		config.setPattern("*.jsp");
		WebInterceptorManager.INSTANCE.addInterceptorConfig(config);
	}

	public void unLoad(IContributionEvent arg0) {
		// TODO 自动生成方法存根

	}

}
