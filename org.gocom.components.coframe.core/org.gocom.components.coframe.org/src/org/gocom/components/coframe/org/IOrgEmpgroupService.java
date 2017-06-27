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

import org.gocom.components.coframe.org.groupdataset.OrgEmpgroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;



/**
 * 人员工作组服务类
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public interface IOrgEmpgroupService{

	/**
	 *添加
	 * @param orgEmporg OrgEmporg
	 */
	void addOrgEmpgroup(OrgEmpgroup orgEmpgroup);

	/**
	 * 删除
	 * @param orgEmporgs OrgEmporg[]
	 */
	void deleteOrgEmpgroup(OrgEmpgroup[] orgEmpgroups);
	
	/**
	 * 删除
	 * @param empid String
	 */
	void deleteOrgEmpgroup(String empid);




	/**
	 * 根据工作组查询出关联的Emporg列表
	 * @param org 工作组
	 * @return
	 */
	OrgEmpgroup[] queryOrgEmpgroupsByGroup(OrgGroup group);

}
