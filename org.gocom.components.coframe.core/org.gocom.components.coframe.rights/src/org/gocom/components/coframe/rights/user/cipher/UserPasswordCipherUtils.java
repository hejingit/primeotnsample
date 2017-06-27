/*******************************************************************************
 * $Header: /cvsroot/OPENSOURCE10/develop/opensources/coframe/source/org.gocom.components.coframe/org.gocom.components.coframe.rights/src/org/gocom/components/coframe/rights/user/cipher/UserPasswordCipherUtils.java,v 1.1 2013/07/15 03:55:29 wangwb Exp $
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

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class UserPasswordCipherUtils {
	private static IUserPasswordCipher cipher;

	public static String encrypt(String password) throws Exception {
		if (cipher == null) {
			throw new NullPointerException("The UserPasswordCipher is null.");
		}
		return cipher.encrypt(password);
	}

	public static String decrypt(String password) throws Exception {
		if (cipher == null) {
			throw new NullPointerException("The UserPasswordCipher is null.");
		}
		return cipher.decrypt(password);
	}

	public static IUserPasswordCipher getCipher() {
		return cipher;
	}

	public static void setCipher(IUserPasswordCipher cipher) {
		UserPasswordCipherUtils.cipher = cipher;
	}
}

/*
 * 修改历史 
 * $Log: UserPasswordCipherUtils.java,v $
 * Revision 1.1  2013/07/15 03:55:29  wangwb
 * Update:支持用户密码加密方式可修改
 *
 */