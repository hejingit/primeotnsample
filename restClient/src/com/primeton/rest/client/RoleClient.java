package com.primeton.rest.client;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class RoleClient {

	public static void main(String[] args) throws Exception{
		//提供rest服务的应用地址
		String host = "http://127.0.0.1:8080/default";
		//rest服务
		String service = "/rest/services/RoleService";
		
		RoleClient client = new RoleClient();
		
		//增加两个角色 
		client.addRole(host+service+"/addRole", "{role:{roleId:'role1',roleCode:'role1',roleName:'角色1',roleDesc:'测试',tenantId:'default'}}");
		client.addRole(host+service+"/addRole", "{role:{roleId:'role2',roleCode:'role2',roleName:'角色2',roleDesc:'测试',tenantId:'default'}}");
		
		//修改角色信息
		client.updateRole(host+service+"/updateRole", "{role:{roleId:'role1',roleName:'角色一',roleDesc:'测试角色'}}");
		
		//根据角色ID取角色信息
		String json1 = client.getRoleById(host+service+"/getRoleById/"+"role1");
		System.out.println(json1);
		//转换成HashMap
		HashMap map1 = JSON.parseObject(json1,HashMap.class);

		//根据角色名模拟查询角色信息
		String json2 = client.queryRolesByName(host+service+"/queryRolesByName/"+java.net.URLEncoder.encode("角色","utf-8"));
		System.out.println(json2);
		//数组转换成队列，角色转换成HashMap
		List list1 = JSON.parseArray(json2,HashMap.class);
		
		//根据角色Id删除角色信息
		client.deleteRoleById(host+service+"/deleteRoleById/"+"role1");
		client.deleteRoleById(host+service+"/deleteRoleById/"+"role2");
		
		System.out.println("end!");
	}
	
	public void addRole(String serviceUrl,String jsonData)throws Exception{
		RemoteServiceUtil.post(serviceUrl, jsonData);
	}
	
	public void updateRole(String serviceUrl,String jsonData)throws Exception{
		RemoteServiceUtil.put(serviceUrl, jsonData);
	}
	
	public String getRoleById(String serviceUrl)throws Exception{
		return RemoteServiceUtil.get(serviceUrl);
	}
	
	public String queryRolesByName(String serviceUrl)throws Exception{
		return RemoteServiceUtil.get(serviceUrl);
	}
	
	public String deleteRoleById(String serviceUrl)throws Exception{
		return RemoteServiceUtil.delete(serviceUrl);
	}

}
