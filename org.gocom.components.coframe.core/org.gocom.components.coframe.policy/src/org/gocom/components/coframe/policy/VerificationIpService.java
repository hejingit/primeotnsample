package org.gocom.components.coframe.policy;

import org.gocom.components.coframe.policy.datas.IPAccessRules;

import com.eos.foundation.data.DataObjectUtil;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
import commonj.sdo.DataObject;

public class VerificationIpService extends CoframeDASDaoSupport {

	/**
	 * 验证IP段是否重复
	 * 
	 * @param rule
	 *            IP规则实体对象
	 * @return IP段有重复则返回true，否则返回false
	 */
	public Boolean VerificationIp(IPAccessRules rule) {
		DataObject info = DataObjectUtil.createDataObject(IPAccessRules.QNAME);
		DataObject[] datas = getDASTemplate().queryEntitiesByTemplate(DataObject.class, info);
		String startIp = "";
		String endIp = "";
		String newStartIp = rule.getStartIP();
		String newEndIp = rule.getEndIP();
		for (int i = 0; i < datas.length; i++) {
			if (datas[i].getString("rulesId").equals(rule.getRulesId())) {
				continue;
			}
			startIp = datas[i].getString("startIP");
			endIp = datas[i].getString("endIP");
			if ((!CompareIp(newStartIp, startIp) && CompareIp(newEndIp, startIp))
					|| (CompareIp(newStartIp, startIp) && !CompareIp(newEndIp,
							endIp))
					|| (!CompareIp(newStartIp, endIp) && CompareIp(newEndIp,
							endIp))) {
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
	 * @return 比较的IP大于被比较的IP则返回true,否则返回false
	 */
	private Boolean CompareIp(String ip, String ctmIp) {
		String ips[] = ip.split("\\.");
		String ctmIps[] = ctmIp.split("\\.");
		for (int i = 0; i < ips.length; i++) {
			if (Integer.parseInt(ips[i]) > Integer.parseInt(ctmIps[i])) {
				return true;
			}
		}
		return false;
	}
}
