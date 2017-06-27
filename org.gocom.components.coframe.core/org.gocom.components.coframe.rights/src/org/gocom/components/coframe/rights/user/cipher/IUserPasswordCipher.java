/*******************************************************************************
 * $Header: /cvsroot/OPENSOURCE10/develop/opensources/coframe/source/org.gocom.components.coframe/org.gocom.components.coframe.rights/src/org/gocom/components/coframe/rights/user/cipher/IUserPasswordCipher.java,v 1.1 2013/07/15 03:55:29 wangwb Exp $
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

public interface IUserPasswordCipher {
	String encrypt(String plaintext) throws Exception;

	String decrypt(String cryptograph) throws Exception;
}

/*
 * 修改历史
 * $Log: IUserPasswordCipher.java,v $
 * Revision 1.1  2013/07/15 03:55:29  wangwb
 * Update:支持用户密码加密方式可修改
 * 
 */