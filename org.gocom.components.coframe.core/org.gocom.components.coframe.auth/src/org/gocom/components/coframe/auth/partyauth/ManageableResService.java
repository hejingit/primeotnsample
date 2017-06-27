/**
 * 
 */
package org.gocom.components.coframe.auth.partyauth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gocom.components.coframe.auth.service.AuthPartyRuntimeService;
import org.gocom.components.coframe.auth.service.PartyAuthModel;
import org.gocom.components.coframe.org.GroupAuthService;
import org.gocom.components.coframe.org.OrgGroupService;
import org.gocom.components.coframe.org.OrgOrganizationService;
import org.gocom.components.coframe.org.OrganizationAuthService;
import org.gocom.components.coframe.org.dataset.OrgOrganization;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.util.OrgTreeNodeHelper;
import org.gocom.components.coframe.rights.dataset.CapUser;
import org.gocom.components.coframe.rights.user.CapUserService;
import org.gocom.components.coframe.tools.IConstants;
import org.gocom.components.coframe.tools.superadmin.SuperAdminService;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.IUserObject;
import com.eos.spring.BeanFactory;
import com.primeton.cap.party.Party;
import com.primeton.cap.party.manager.PartyRuntimeManager;
import commonj.sdo.DataObject;


public class ManageableResService {
	
	public List<Party> getManagedGorupList(){
		List<Party> partyList = new ArrayList<Party>();
		

		if (SuperAdminService.currUserIsSupserAdmin()) {
			return PartyRuntimeManager.getInstance().getRootPartyList(
					IConstants.GROUP_PARTY_TYPE_ID);
		}

		if (!judgeIsEmp()) {
			return partyList;
		}
		String userId = getUserId();
		String capuserId=getCAPUserID();
		Party empParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				userId, IConstants.EMP_PARTY_TYPE_ID);
		PartyAuthModel authModel = AuthPartyRuntimeService.getPartyModel(
				empParty.getId(), empParty.getPartyTypeID());
		Party userParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				capuserId, IConstants.USER_PARTY_TYPE_ID);
		PartyAuthModel userauthModel = AuthPartyRuntimeService.getPartyModel(
				userParty.getId(), userParty.getPartyTypeID());
		List<Party> roles = new ArrayList<Party>();
		if (authModel != null) {
			roles.addAll(authModel.getRoles());
		}
		if (userauthModel != null) {
			roles.addAll(userauthModel.getRoles());
		}
		List<Party> result=new ArrayList<Party>();
		GroupAuthService GroupAuthService=BeanFactory.newInstance().getBean(new GroupAuthService().SPRING_BEAN_NAME);
		for(Party role:roles){
			String roleId=role.getId();
			OrgGroup[] groups=GroupAuthService.getManageGroupbyRoleId(roleId);
			for(OrgGroup group:groups){
				Party party=new Party();
				party.setCode(group.getGroupname());
				party.setId(group.getGroupid().toString());
				party.setName(group.getGroupname());
				party.setPartyTypeID("group");
				party.setTenantID(group.getTenantId());
				if(!result.contains(party)){
					result.add(party);
				}
				
			}
		}
		result=deleteRedundantGroup(result);
		return result;
	}

	public List<Party> getManagedOrgList() {
		List<Party> partyList = new ArrayList<Party>();
		
		if (SuperAdminService.currUserIsSupserAdmin()) {
			return PartyRuntimeManager.getInstance().getRootPartyList(
					IConstants.ORG_PARTY_TYPE_ID);
		}
		
		if(!judgeIsEmp()) {
			return partyList;
		}
		String userId = getUserId();
		String capuserId=getCAPUserID();
		List<Party> roles = new ArrayList<Party>();
		Party empParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				userId, IConstants.EMP_PARTY_TYPE_ID);
		if(empParty!=null){
			PartyAuthModel authModel = AuthPartyRuntimeService.getPartyModel(
					empParty.getId(), empParty.getPartyTypeID());
			
			if (authModel != null) {
				roles.addAll(authModel.getRoles());
			}
		}
		Party userParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				capuserId, IConstants.USER_PARTY_TYPE_ID);
		if(userParty!=null){
			PartyAuthModel userauthModel = AuthPartyRuntimeService.getPartyModel(
					userParty.getId(), userParty.getPartyTypeID());
			if (userauthModel != null) {
				roles.addAll(userauthModel.getRoles());
			}
		}
		List<Party> result=new ArrayList<Party>();
		OrganizationAuthService organizationAuthService=BeanFactory.newInstance().getBean(OrganizationAuthService.SPRING_BEAN_NAME);
		for(Party role:roles){
			String roleId=role.getId();
			OrgOrganization[] orgs=organizationAuthService.getManageOrgbyRoleId(roleId);
			for(OrgOrganization org:orgs){
				Party party=new Party();
				party.setCode(org.getOrgcode());
				party.setId(org.getOrgid().toString());
				party.setName(org.getOrgname());
				party.setPartyTypeID(org.getOrgtype());
				party.setTenantID(org.getTenantid());
				if(!result.contains(party)){
					result.add(party);
				}
				
			}
		}
		result=deleteRedundantOrg(result);
		return result;
	}
	
	private String getUserId() {
		IMUODataContext muoContext = DataContextManager.current()
				.getMUODataContext();
		IUserObject userObject = muoContext.getUserObject();
		if (userObject != null) {
			return userObject.getUserId();
		}
		return null;
	}
	
	private String getCAPUserID() {
		IMUODataContext muoContext = DataContextManager.current()
				.getMUODataContext();
		CapUserService userservice=BeanFactory.newInstance().getBean(CapUserService.SPRING_MENU_BEAN);
		CapUser user=CapUser.FACTORY.create();
		IUserObject userObject = muoContext.getUserObject();
		if (userObject != null) {
			String userid=userObject.getUserId();
			user.setOperatorId(new BigDecimal(userid));
			userservice.getCapUser(user);
			return user.getUserId();
		}
		return null;
	}
	
	private boolean judgeIsEmp() {
		IMUODataContext muoContext = DataContextManager.current()
				.getMUODataContext();
		IUserObject userObject = muoContext.getUserObject();
		if (userObject != null) {
			if(userObject.getUserOrgId() == null || "".equals(userObject.getUserOrgId())) {
				return false;
			}
		}
		return true;
	}
	
	public List<Party> deleteRedundantOrg(List<Party> orgs){
		OrgOrganizationService orgOrganizationService=BeanFactory.newInstance().getBean(OrgOrganizationService.SPRING_BEAN_NAME);
		List<Party> deleteList=new ArrayList<Party>();
		Set set=new HashSet();
		for(Party org:orgs){
			set.add(org.getId());
		}
		for(Party org:orgs){
			DataObject parent=orgOrganizationService.getOrgOrganizationParent(org.getId());
			if(parent!=null){
				while(parent!=null){
					String parentid=parent.getString("orgid");
					if(set.contains(parentid)){
						deleteList.add(org);
					}
					parent=orgOrganizationService.getOrgOrganizationParent(parentid);
				}
			}else{
				continue;
			}
		}
		orgs.removeAll(deleteList);	
		return orgs;
	}
	
	public List<Party> deleteRedundantGroup(List<Party> groups){
		OrgGroupService orgGroupService=BeanFactory.newInstance().getBean(OrgGroupService.SPRING_BEAN_NAME);
		List<Party> deleteList=new ArrayList<Party>();
		Set set=new HashSet();
		for(Party group:groups){
			set.add(group.getId());
		}
		for(Party group:groups){
			DataObject parent=orgGroupService.getOrgGroupnParent(group.getId());
			if(parent!=null){
				while(parent!=null){
					String parentid=parent.getString("groupid");
					if(set.contains(parentid)){
						deleteList.add(group);
					}
					parent=orgGroupService.getOrgGroupnParent(parentid);
				}
			}else{
				continue;
			}
		}
		groups.removeAll(deleteList);	
		return groups;
	}
	
	public static DataObject[] buildOrgTreeNodes(List<Party> orgPartyList){
	 return OrgTreeNodeHelper.buildOrgTreeNodes(orgPartyList);
	}
	
	public static DataObject[] buildGroupTreeNodes(List<Party> PartyList){
		 return OrgTreeNodeHelper.buildGroupTreeNodes(PartyList);
		}

}
