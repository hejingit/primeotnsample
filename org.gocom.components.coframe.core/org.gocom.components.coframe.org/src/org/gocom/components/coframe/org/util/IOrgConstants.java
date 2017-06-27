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
package org.gocom.components.coframe.org.util;

/**
 * 机构常量定义
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public interface IOrgConstants {
	
	
	/**节点类型**/
	String NODE_TYPE_ORG = "OrgOrganization";
	String NODE_TYPE_DUTY = "OrgDuty";
	String NODE_TYPE_ORGEMP = "OrgEmployee";
	String NODE_TYPE_ORGEMPORG = "OrgEmporg";
	String NODE_TYPE_GROUP = "OrgGroup";
	String NODE_TYPE_ORGPOSI = "OrgPosition";
	
	
	/** 叶子节点 */
	String IS_LEAF_YES = "y";
	
	/** 不是叶子节点 */
	String IS_LEAF_NO = "n";
	
	/** 工作组ID标识 */
	String GROUPID_PROPERTY = "groupid";
	
	/** 机构ID标识 */
	String ORGID_PROPERTY = "orgid";
	
	/** 机构名称标识 */
	String ORGNAME_PROPERTY = "orgname";
	
	/** 工作组名称标识 */
	String GROUPNAME_PROPERTY = "groupname";
	
	/** 岗位ID标识 */
	String POSITIONID_PROPERTY = "positionid";
	
	/** 员工ID标识 */
	String EMPID_PROPERTY = "empid";
	
	
	/** 用户ID标识 */
	String USERID_PROPERTY = "userid";
	
	
	/** 员工代码 */
	String EMPCODE_PROPERTY = "empcode";
	
	String EMP_EMAIL_PROPERTY = "oemail";

	/** 岗位关联标识 */
	String POSITION_REF_PROPERTY = "orgPosition";

	/** 员工关联标识 */
	String EMP_REF_PROPERTY = "orgEmployee";

	/** 工作组关联标识 */
	String GROUP_REF_PROPERTY = "orgGroup";
	
	
	/** 机构关联标识 */
	String ORG_REF_PROPERTY = "orgOrganization";
	
	/** 主要的 */
	String IS_MAIN_YES = "y";
	
	/** 次要的 */
	String IS_MAIN_NO = "n";

	/** 操作员ID（用户ID） */
	String OPERATORID = "operatorid";
	
}
