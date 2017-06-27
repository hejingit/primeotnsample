/*******************************************************************************
 * $Header: /cvsroot/OPENSOURCE10/develop/opensources/coframe/source/org.gocom.components.coframe/org.gocom.components.coframe.rights/src/org/gocom/components/coframe/rights/user/cipher/DESedeUserPasswordCipher.java,v 1.1 2013/07/15 03:55:29 wangwb Exp $
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

import com.eos.system.utility.CryptoUtil;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class DESedeUserPasswordCipher implements IUserPasswordCipher {
	private final static String ENCRYPT_KEY = "cap_user";

	public String encrypt(String plaintext) throws Exception {
		return CryptoUtil.encrypt(plaintext, ENCRYPT_KEY);
	}

	public String decrypt(String cryptograph) throws Exception {
		return CryptoUtil.decrypt(cryptograph, ENCRYPT_KEY);
	}
}

/*
 * 修改历史
 * $Log: DESedeUserPasswordCipher.java,v $
 * Revision 1.1  2013/07/15 03:55:29  wangwb
 * Update:支持用户密码加密方式可修改
 * 
 */