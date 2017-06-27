/*
 * Copyright 2013 Primeton.
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
package org.gocom.components.coframe.tools.superadmin;

import org.apache.commons.lang.StringUtils;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.IUserObject;
import com.primeton.cap.ISystemConstants;

/**
 * 
 * 超级管理员辅助类，判断当前用户是否是超级管理员
 * 
 * @author caozw@primeton.com
 * 
 */
public class SuperAdminService {

	private static final String SUPER_USER_ID = "sysadmin";

	/**
	 * 如果用户名是sysadmin则认为是超级管理员
	 * 
	 * @return
	 */
	public static boolean currUserIsSupserAdmin() {
		IMUODataContext muoContext = DataContextManager.current()
				.getMUODataContext();
		IUserObject userObject = muoContext.getUserObject();
		if (userObject != null) {
			return SUPER_USER_ID.equals(userObject.getAttributes().get(ISystemConstants.USER_ID));
		}
		return false;
	}
}
