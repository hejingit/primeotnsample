/*******************************************************************************
 * $Header: /cvsroot/OPENSOURCE10/develop/opensources/coframe/source/org.gocom.components.coframe/org.gocom.components.coframe.rights/src/org/gocom/components/coframe/rights/user/cipher/SHAUserPasswordCipher.java,v 1.1 2013/07/15 03:55:29 wangwb Exp $
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

import com.eos.foundation.common.utils.CryptoUtil;


/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class SHAUserPasswordCipher implements IUserPasswordCipher {

	public String encrypt(String plaintext) throws Exception {
		return CryptoUtil.digestBySHA(plaintext);
	}

	public String decrypt(String cryptograph) throws Exception {
		throw new UnsupportedOperationException("The SHAUserPasswordCipher not support decrypt.");
	}
	
}

/*
 * 修改历史 
 * $Log: SHAUserPasswordCipher.java,v $
 * Revision 1.1  2013/07/15 03:55:29  wangwb
 * Update:支持用户密码加密方式可修改
 *
 */