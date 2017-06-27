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

import org.gocom.components.coframe.org.dataset.OrgEmpposition;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroupposi;
import org.gocom.components.coframe.org.util.OrgHelper;
import org.gocom.components.coframe.org.util.OrgResponse;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.primeton.cap.TenantManager;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
/**
 * 岗位工作组服务类
 * @author gouyl (mailto:gouyl@primeton.com)
 */

public class OrgGroupposiService extends CoframeDASDaoSupport implements IOrgGroupposiService{

	public OrgResponse addOrgGroupposi(OrgGroupposi orgGroupposi) {
		if(!validatePosicode(orgGroupposi.getOrgPosition())){
			return new OrgResponse(false, "岗位编号："+orgGroupposi.getOrgPosition().getPosicode()+"已存在");
		}
		orgGroupposi.getOrgPosition().setTenantid(TenantManager.getCurrentTenantID());
		orgGroupposi.setTenantId(TenantManager.getCurrentTenantID());
		OrgPosition parentPosition = orgGroupposi.getOrgPosition().getOrgPosition();
		if(parentPosition != null && parentPosition.getPositionid() != null){
			parentPosition = getOrgPositionWithDetail(parentPosition);
		}
		getDASTemplate().getPrimaryKey(orgGroupposi.getOrgPosition());
		OrgHelper.expandPositionPropertyByParent(orgGroupposi.getOrgPosition(), parentPosition);
		getDASTemplate().insertEntity(orgGroupposi.getOrgPosition());
		if(parentPosition != null && parentPosition.getPositionid() != null){
			OrgHelper.expandParentPositionProperty(parentPosition);
			getDASTemplate().updateEntity(parentPosition);
		}
		getDASTemplate().insertEntity(orgGroupposi);
		return new OrgResponse(true, "添加成功");
		
	}
	/**
	 * 检验岗位code是否合格
	 * @param posicode
	 * @return true合格，false不合格
	 */
	public boolean validatePosicode(OrgPosition position) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq("posicode", position.getPosicode()));
		OrgPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgPosition.class, dasCriteria);
		if(positions == null || positions.length == 0){
			return true;
		}
		if(position.getPositionid() != null && positions[0].getPositionid().equals(position.getPositionid())){//修改的情况，只能为1个
			return positions.length == 1;
		}else{//新增,必定没有
			return positions.length == 0;
		}
	}
	private OrgPosition getOrgPositionWithDetail(OrgPosition position){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgPosition.QNAME);
		dasCriteria.addAssociation("orgPosition");
		dasCriteria.add(ExpressionHelper.eq("positionid", position.getPositionid()));
		OrgPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgPosition.class, dasCriteria);
		if(positions != null && positions.length == 1){
			return positions[0];
		}
		return OrgPosition.FACTORY.create();
	}
	
	public int countGroupposis(CriteriaType criteria) {
		criteria.set_entity(OrgGroupposi.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}
	public OrgPosition[] queryOrgPositions(CriteriaType criteriaType, PageCond pageCond) {
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		dasCriteria.addAssociation("orgPosition");
		OrgGroupposi[] groupposis= getDASTemplate().queryEntitiesByCriteriaEntityWithPage(OrgGroupposi.class, dasCriteria, pageCond);
		if(groupposis!=null&&groupposis.length>0){
			OrgPosition[] ret=new OrgPosition[groupposis.length];
			for(int i=0;i<groupposis.length;i++){
				ret[i]=groupposis[i].getOrgPosition();
				ret[i].setString("orgGroup", groupposis[i].getOrgGroup().getGroupname());
			}
			return ret;
		}
		return null;
	}


	public void deleteOrgPositionCascadeByParent(OrgGroup parentgroup) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroupposi.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgGroup.groupid", parentgroup.getGroupid()));
		OrgGroupposi[] grouppositions =getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroupposi.class, dasCriteria);
		for(OrgGroupposi groupposi:grouppositions){
			getDASTemplate().deleteEntity(groupposi);
		}
	}
	public void deleteOrgGroupposiCascade(String orgPositionId) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroupposi.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgPosition.positionid", orgPositionId));
		OrgGroupposi[] grouppositions =getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroupposi.class, dasCriteria);
		for(OrgGroupposi groupposi:grouppositions){
			getDASTemplate().deleteEntity(groupposi);
		}
		deleteCascade(orgPositionId);
	}

	private void deleteCascade(String orgPositionId){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgPosition.QNAME);
		dasCriteria.addAssociation("orgPosition");
		dasCriteria.add(ExpressionHelper.eq("orgPosition.positionid", orgPositionId));
		OrgPosition[] positions=getDASTemplate().queryEntitiesByCriteriaEntity(OrgPosition.class, dasCriteria);
		for(OrgPosition postion:positions){
			deleteCascade(postion.getPositionid()+"");
		}
		IDASCriteria dasCriteria_del = getDASTemplate().createCriteria(OrgEmpposition.QNAME);
		dasCriteria_del.add(ExpressionHelper.eq("orgPosition.positionid", orgPositionId));
		getDASTemplate().deleteByCriteriaEntity(dasCriteria_del);
		
		IDASCriteria dasCriteria_del2 = getDASTemplate().createCriteria(OrgPosition.QNAME);
		dasCriteria_del2.add(ExpressionHelper.eq("positionid", orgPositionId));
		getDASTemplate().deleteByCriteriaEntity(dasCriteria_del2);
	}

}
