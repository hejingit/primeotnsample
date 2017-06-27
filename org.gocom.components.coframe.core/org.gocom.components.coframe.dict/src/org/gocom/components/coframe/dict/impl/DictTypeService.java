/**
 * 
 */
package org.gocom.components.coframe.dict.impl;

import org.gocom.components.coframe.dict.EosDictType;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
import commonj.sdo.DataObject;

/**
 * <pre>
 *      Title: 业务字典类型服务类
 *      Description: 业务字典类型服务类
 * </pre>
 * 
 * @author 陈鹏
 * @version 1.00.00
 * 
 */
public class DictTypeService extends CoframeDASDaoSupport {

	public DictTypeService() {
	}

	/**
	 * 检查叶子节点
	 * 
	 * @param data
	 */
	public void checkLeaf(DataObject[] data) {
		for (DataObject obj : data) {
			EosDictType type = (EosDictType) obj;
			IDASCriteria dasCriteria = getDASTemplate().createCriteria(EosDictType.QNAME);
			dasCriteria.add(ExpressionHelper.eq("parentid", type.getDicttypeid()));
			EosDictType[] children = getDASTemplate().queryEntitiesByCriteriaEntity(EosDictType.class, dasCriteria);
			if (children.length > 0) {
				type.setBoolean("isLeaf", false);
				type.setBoolean("expanded", false);
			}
		}
	}

}
