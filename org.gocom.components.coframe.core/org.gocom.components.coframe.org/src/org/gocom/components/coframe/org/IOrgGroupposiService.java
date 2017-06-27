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

import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroupposi;
import org.gocom.components.coframe.org.util.OrgResponse;

import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;


/**
 * 岗位工作组服务类
 * @author gouyl (mailto:gouyl@primeton.com)
 */

public interface IOrgGroupposiService {
	
	/**
	 * 添加
	 * @param orgGroupposi
	 */
	OrgResponse addOrgGroupposi(OrgGroupposi orgGroupposi);
	
	/**
	 * 删除
	 * @param groupId
	 */
	void deleteOrgGroupposiCascade(String orgPositionId);
	
	
	/**
	 * 查询数量
	 * @param criteria
	 * @return
	 */
	int countGroupposis(CriteriaType criteria);
	/**
	 * 查询
	 * @param criteriaType
	 * @param pageCond
	 * @return
	 */
	OrgPosition[] queryOrgPositions(CriteriaType criteriaType,
			PageCond pageCond);

	/**
	 * 删除子工作组岗位，删除子岗位之前先删除子岗位关联的人员关系
	 * @param parentgroup
	 */
	void deleteOrgPositionCascadeByParent(OrgGroup parentgroup);
}
