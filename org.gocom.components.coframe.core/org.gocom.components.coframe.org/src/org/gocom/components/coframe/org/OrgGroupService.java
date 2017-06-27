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

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.groupdataset.OrgEmpgroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroupposi;
import org.gocom.components.coframe.org.util.OrgHelper;
import org.gocom.components.coframe.org.util.OrgResponse;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;

import commonj.sdo.DataObject;

import org.gocom.components.coframe.tools.CoframeDASDaoSupport;

/**
 * 工作组管理
 * @author gouyl (mailto:gouyl@primeton.com)
 * 
 */
public class OrgGroupService extends CoframeDASDaoSupport implements IOrgGroupService {

	public static final String SPRING_BEAN_NAME = "OrgGroupServiceBean";
	private IOrgGroupposiService groupposiService = null;
	private IOrgEmpgroupService empgroupService = null;
	
	public OrgResponse addOrgGroup(OrgGroup group) {
		//设置主键
		getDASTemplate().getPrimaryKey(group);
		OrgGroup parentGroup = group.getOrgGroup();
		if(parentGroup != null && parentGroup.getGroupid() != null){
			getOrgGroup(parentGroup);
		}
		OrgHelper.expandGroupPropertyByParent(group, parentGroup);
		getDASTemplate().insertEntity(group);
		//更新父工作组
		if(parentGroup != null && parentGroup.getGroupid() != null){
			OrgHelper.expandParentGroupProperty(parentGroup);
			getDASTemplate().updateEntity(parentGroup);
		}
		return new OrgResponse(true, "添加成功");
	}
	public int countOrgGroups(CriteriaType criteria) {
		criteria.set_entity(OrgGroup.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}

	public void deleteOrgGroup(OrgGroup[] orgGroups) {
		if(orgGroups == null) return;
		for(OrgGroup group : orgGroups){
			//删除工作组和人员的关联关系
			if(empgroupService != null){
				empgroupService.deleteOrgEmpgroup(empgroupService.queryOrgEmpgroupsByGroup(group));
			}
			//删除子工作组，子岗位
			deleteSubGroupByParent(group);
			getDASTemplate().deleteEntity(group);
		}
	}

	/**
	 * 根据父工作组删除子工作组
	 * 删除子工作组规则：
	 * 	   1.删除子岗位，删除子岗位之前必须先删除子岗位关联的人员关系
	 * 	   2.删除子工作组：删除子工作组之前，先级联删除其对应的子工作组相关内容
	 * @param parentOrg 父工作组
	 */
	private void deleteSubGroupByParent(OrgGroup parentGroup){
		//删除子工作组岗位，删除子岗位之前先删除子岗位关联的人员关系
		if(groupposiService != null){
			groupposiService.deleteOrgPositionCascadeByParent(parentGroup);
		}
		//删除子工作组
		if(parentGroup!=null&&parentGroup.getGroupid()!=null){
			OrgGroup[] children = querySubOrgGroups(parentGroup.getGroupid()+"");
			if(children != null){
				deleteOrgGroup(children);
			}
		}
		
	}
	
	public void deleteOrgGroup(String id) {
		if(!StringUtils.isBlank(id)){
    		OrgGroup group = OrgGroup.FACTORY.create();
    		group.setGroupid(new BigDecimal(id));
    		deleteOrgGroup(new OrgGroup[]{group});
    	}

	}
	public void getOrgGroup(OrgGroup orgGroup) {
		getDASTemplate().expandEntity(orgGroup);
	}


	public OrgGroup[] queryOrgGroups(CriteriaType criteriaType,
			PageCond pageCond) {
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		dasCriteria.asc("grouplevel");
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(OrgGroup.class, dasCriteria, pageCond);
	}

	public OrgPosition[] queryPositionsOfGroup(String groupid) {
		if(!StringUtils.isBlank(groupid)) {
			IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroupposi.QNAME);
			dasCriteria.add(ExpressionHelper.eq("orgGroup.groupid", groupid));
			dasCriteria.addAssociation("orgPosition");
			OrgGroupposi[] groupposis=getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroupposi.class,dasCriteria);
			OrgPosition[] ret=new OrgPosition[groupposis.length];
			for(int i=0;i<groupposis.length;i++){
				ret[i]=groupposis[i].getOrgPosition();
			}
			return ret;
		}
		return null;
	}

	public OrgGroup[] querySubOrgGroups(String groupid) {
//		 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		if(StringUtils.isBlank(groupid)) {
			// groupid为空则查询顶级工作组
			dasCriteria.add(ExpressionHelper.isNull("orgGroup"));
		} else {
			dasCriteria.add(ExpressionHelper.eq("orgGroup.groupid", new BigDecimal(groupid)));
		}
		
		OrgGroup[] groups = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, dasCriteria);
		return groups;
	}
	
	public OrgResponse updateOrgGroup(OrgGroup orgGroup) {
		getDASTemplate().updateEntity(orgGroup);
		return new OrgResponse(true, "修改成功");
	}

	public OrgGroup getOrgGroupnWithParent(OrgGroup child){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		dasCriteria.add(ExpressionHelper.eq("groupid", child.getGroupid()));
		dasCriteria.addAssociation("orgGroup");
		OrgGroup[] groups = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, dasCriteria);
		if(groups != null && groups.length == 1){
			return groups[0];
		}
		return OrgGroup.FACTORY.create();
	}
	
	public DataObject getOrgGroupnParent(String groupid){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		dasCriteria.add(ExpressionHelper.eq("groupid", new BigDecimal(groupid)));
		OrgGroup[] groups = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, dasCriteria);
		if(groups != null && groups.length == 1){
			DataObject parent=groups[0].getDataObject(18);
			if(parent!=null){
				return parent;
			}
		}
		return null;
	}
	
	
	public void setEmpgroupService(IOrgEmpgroupService empgroupService) {
		this.empgroupService = empgroupService;
	}
	public void setGroupposiService(IOrgGroupposiService groupposiService) {
		this.groupposiService = groupposiService;
	}
	public OrgEmployee[] queryEmployeesOfGroup(String groupid) {
		if(!StringUtils.isBlank(groupid)){
			OrgGroup group=OrgGroup.FACTORY.create();
			group.setGroupid(new BigDecimal(groupid));
			OrgEmpgroup[] empgroups=empgroupService.queryOrgEmpgroupsByGroup(group);
			if(empgroups!=null&&empgroups.length>0){
				OrgEmployee[] ret=new OrgEmployee[empgroups.length];
				for(int i=0;i<empgroups.length;i++){
					ret[i]=empgroups[i].getOrgEmployee();
				}
				return ret;
			}
		}
		return null;
	}
}
