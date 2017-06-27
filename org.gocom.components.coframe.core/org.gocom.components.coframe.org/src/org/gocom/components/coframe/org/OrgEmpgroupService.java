/*
 * Copyright 2013 Primeton Technologies Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gocom.components.coframe.org;


import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.groupdataset.OrgEmpgroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.foundation.PageCond;
import com.primeton.cap.TenantManager;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;


/**
 * 人员机构关系服务类
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgEmpgroupService extends CoframeDASDaoSupport implements IOrgEmpgroupService{

	
	public void addOrgEmpgroup(OrgEmpgroup orgEmpgroup) {
		orgEmpgroup.setTenantId(TenantManager.getCurrentTenantID());
		orgEmpgroup.setIsmain("y");
		getDASTemplate().insertEntity(orgEmpgroup);
	}
	public void addOrgEmpgroup(OrgEmpgroup[] orgEmpgroup) {
		if(orgEmpgroup != null && orgEmpgroup.length > 0){
			for(OrgEmpgroup empposition : orgEmpgroup){
				addOrgEmpgroup(empposition);
			}
		}
		
	}
	public Integer countEmpsInGroup(String userid, String empname, String groupid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmpgroup.QNAME);
		dasCriteria.add(ExpressionHelper.eq("groupid", groupid));
		if(!StringUtils.isBlank(userid)){
			dasCriteria.add(ExpressionHelper.eq("orgEmployee.userid", userid));
		}
		if(!StringUtils.isBlank(empname)){
			dasCriteria.add(ExpressionHelper.eq("orgEmployee.empname", empname));
		}
		return getDASTemplate().count(dasCriteria);
	}
	
	public OrgEmpgroup[] queryEmpsInGroup(String userid, String empname, String groupid, PageCond page) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmpgroup.QNAME);
		dasCriteria.add(ExpressionHelper.eq("groupid", groupid));
		dasCriteria.addAssociation("orgEmployee");
		dasCriteria.asc("orgEmployee.sortno");
		if(!StringUtils.isBlank(userid)){
			dasCriteria.add(ExpressionHelper.eq("orgEmployee.userid", userid));
		}
		if(!StringUtils.isBlank(empname)){
			dasCriteria.add(ExpressionHelper.eq("orgEmployee.empname", empname));
		}
		OrgEmpgroup[] ret =getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmpgroup.class, dasCriteria);
		return ret;	
	}	
	public void deleteEmpgroupByEmp(OrgEmployee emp) {
		getDASTemplate().deleteEntity(emp);
	}

	public void deleteOrgEmpgroup(OrgEmpgroup[] orgEmpgroups) {
		getDASTemplate().deleteEntityBatch(orgEmpgroups);
	}


	public OrgEmpgroup[] queryOrgEmpgroupsByGroup(OrgGroup group) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmpgroup.QNAME);
		dasCriteria.add(ExpressionHelper.eq("groupid", group.getGroupid()));
		dasCriteria.addAssociation("orgEmployee");
		OrgEmpgroup[] ret =getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmpgroup.class, dasCriteria);
		return ret;
	}


	public void deleteOrgEmpgroup(String empid) {
		OrgEmployee emp=OrgEmployee.FACTORY.create();
		deleteEmpgroupByEmp(emp);
	}
}


