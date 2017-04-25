package com.primeton.rest.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.primeton.components.rest.annotation.JSONParam;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

@Path("/rest/services/RoleService")
@Produces({"application/json"})
public class RoleService {

	@POST
	@Path("/addRole")
	public void addRole(@JSONParam("role")DataObject role)throws Throwable{
		role = DataObjectUtil.convertDataObject(role,"com.primeton.rest.service.dataset.CapRole",false);
		//调用逻辑流处理
		Object[] inParameters = { role };
		ILogicComponent comp = LogicComponentFactory.create("com.primeton.rest.service.caprolebiz");
		comp.invoke("addCapRole", inParameters);
	}
	
	@PUT
	@Path("/updateRole")
	public void updateRole(@JSONParam("role")DataObject role)throws Throwable{
		role = DataObjectUtil.convertDataObject(role,"com.primeton.rest.service.dataset.CapRole",false);
		//调用逻辑流处理
		Object[] inParameters = { role };
		ILogicComponent comp = LogicComponentFactory.create("com.primeton.rest.service.caprolebiz");
		comp.invoke("updateCapRole", inParameters);
	}

	@GET
	@Path("/getRoleById/{roleId}")
	public DataObject  getRoleById( @PathParam("roleId")String roleId )throws Throwable{
		DataObject role = DataObjectUtil.createDataObject("com.primeton.rest.service.dataset.CapRole");
		role.set("roleId", roleId);
		
		//调用逻辑流处理
		Object[] inParameters = { role };
		ILogicComponent comp = LogicComponentFactory.create("com.primeton.rest.service.caprolebiz");
		Object[] outParameters  = comp.invoke("getCapRole", inParameters);
		
		DataObject ret = (DataObject)outParameters[0];
		return ret;

	}

	@GET
	@Path("/queryRolesByName/{roleName}")
	public DataObject[]  queryRolesByName( @PathParam("roleName")String roleName )throws Throwable{
		DataObject criteria = DataObjectUtil.createDataObject("com.primeton.das.criteria.criteriaType");
		criteria.set("_entity","com.primeton.rest.service.dataset.CapRole");
		criteria.set("_expr[1]/roleName", roleName);
		criteria.set("_expr[1]/_op", "like");
		
		DataObject page = DataObjectUtil.createDataObject("com.eos.foundation.PageCond");
		
		//调用逻辑流处理
		Object[] inParameters = { criteria, page };
		ILogicComponent comp = LogicComponentFactory.create("com.primeton.rest.service.caprolebiz");
		Object[] outParameters  = comp.invoke("queryCapRoles", inParameters);
		
		DataObject[] ret = (DataObject[])outParameters[0];;
		return ret;

	}
	
	@DELETE
	@Path("/deleteRoleById/{roleId}")
	public void deleteRoleById( @PathParam("roleId")String roleId )throws Throwable{
		
		DataObject role = DataObjectUtil.createDataObject("com.primeton.rest.service.dataset.CapRole");
		role.set("roleId", roleId);
		
		//调用逻辑流处理
		Object[] inParameters = { new DataObject[]{role} };
		ILogicComponent comp = LogicComponentFactory.create("com.primeton.rest.service.caprolebiz");
		comp.invoke("deleteCapRoles", inParameters);

	}

}
