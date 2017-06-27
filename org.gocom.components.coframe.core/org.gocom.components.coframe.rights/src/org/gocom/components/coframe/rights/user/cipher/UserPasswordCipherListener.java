/*******************************************************************************
 * $Header: /cvsroot/OPENSOURCE10/develop/opensources/coframe/source/org.gocom.components.coframe/org.gocom.components.coframe.rights/src/org/gocom/components/coframe/rights/user/cipher/UserPasswordCipherListener.java,v 1.1 2013/07/15 03:55:29 wangwb Exp $
 * $Revision: 1.1 $
 * $Date: 2013/07/15 03:55:29 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2012 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2013-7-8
 *******************************************************************************/

package org.gocom.components.coframe.rights.user.cipher;

import com.eos.infra.config.Configuration;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.runtime.resource.IContributionEvent;
import com.eos.runtime.resource.IContributionListener;
import com.eos.system.logging.Logger;
import com.eos.system.utility.ClassUtil;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class UserPasswordCipherListener implements IContributionListener {
	private Logger logger = TraceLoggerFactory.getLogger(UserPasswordCipherListener.class); 

	public void load(IContributionEvent event) {
	}

	public void loadFinished(IContributionEvent event) {
		Configuration configuration = event.getContributionConfiguration();
		String className = configuration.getConfigValue("Right", "Cipher", "Class");
		try {
			IUserPasswordCipher cipher = (IUserPasswordCipher) ClassUtil.newInstance(className, new Object[0]);
			UserPasswordCipherUtils.setCipher(cipher);
		} catch (Throwable e) {
			logger.error(e);
		}
	}


	public void unLoad(IContributionEvent event) {
	}
}

/*
 * 修改历史
 * $Log: UserPasswordCipherListener.java,v $
 * Revision 1.1  2013/07/15 03:55:29  wangwb
 * Update:支持用户密码加密方式可修改
 * 
 */