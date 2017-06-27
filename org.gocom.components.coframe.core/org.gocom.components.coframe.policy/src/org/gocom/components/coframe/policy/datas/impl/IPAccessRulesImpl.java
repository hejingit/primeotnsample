/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.policy.datas.impl;

import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import org.gocom.components.coframe.policy.datas.IPAccessRules;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getRulesId <em>RulesId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getStartIP <em>StartIP</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getEndIP <em>EndIP</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getRulesType <em>RulesType</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getRemark <em>Remark</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getMakersId <em>MakersId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getAddDate <em>AddDate</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.impl.IPAccessRulesImpl#getEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements IPAccessRules;
 */

public class IPAccessRulesImpl extends ExtendedDataObjectImpl implements IPAccessRules {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_RULESID = 0;
	public final static int INDEX_STARTIP = 1;
	public final static int INDEX_ENDIP = 2;
	public final static int INDEX_RULESTYPE = 3;
	public final static int INDEX_REMARK = 4;
	public final static int INDEX_MAKERSID = 5;
	public final static int INDEX_ADDDATE = 6;
	public final static int INDEX_ENABLED = 7;
	public final static int SDO_PROPERTY_COUNT = 8;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public IPAccessRulesImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public IPAccessRulesImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>RulesId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RulesId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RulesId</em>' attribute.
	 * @see #setRulesId(java.lang.String)
	 */
	public String getRulesId() {
		return DataUtil.toString(super.getByIndex(INDEX_RULESID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRulesId <em>RulesId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RulesId</em>' attribute.
	 * @see #getRulesId()
	 */
	public void setRulesId(String rulesId) {
		super.setByIndex(INDEX_RULESID, rulesId);
	}

	/**
	 * Returns the value of the '<em><b>StartIP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>StartIP</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>StartIP</em>' attribute.
	 * @see #setStartIP(java.lang.String)
	 */
	public String getStartIP() {
		return DataUtil.toString(super.getByIndex(INDEX_STARTIP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getStartIP <em>StartIP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>StartIP</em>' attribute.
	 * @see #getStartIP()
	 */
	public void setStartIP(String startIP) {
		super.setByIndex(INDEX_STARTIP, startIP);
	}

	/**
	 * Returns the value of the '<em><b>EndIP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EndIP</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EndIP</em>' attribute.
	 * @see #setEndIP(java.lang.String)
	 */
	public String getEndIP() {
		return DataUtil.toString(super.getByIndex(INDEX_ENDIP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEndIP <em>EndIP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EndIP</em>' attribute.
	 * @see #getEndIP()
	 */
	public void setEndIP(String endIP) {
		super.setByIndex(INDEX_ENDIP, endIP);
	}

	/**
	 * Returns the value of the '<em><b>RulesType</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RulesType</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RulesType</em>' attribute.
	 * @see #setRulesType(java.lang.String)
	 */
	public String getRulesType() {
		return DataUtil.toString(super.getByIndex(INDEX_RULESTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRulesType <em>RulesType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RulesType</em>' attribute.
	 * @see #getRulesType()
	 */
	public void setRulesType(String rulesType) {
		super.setByIndex(INDEX_RULESTYPE, rulesType);
	}

	/**
	 * Returns the value of the '<em><b>Remark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remark</em>' attribute.
	 * @see #setRemark(java.lang.String)
	 */
	public String getRemark() {
		return DataUtil.toString(super.getByIndex(INDEX_REMARK, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRemark <em>Remark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remark</em>' attribute.
	 * @see #getRemark()
	 */
	public void setRemark(String remark) {
		super.setByIndex(INDEX_REMARK, remark);
	}

	/**
	 * Returns the value of the '<em><b>MakersId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MakersId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MakersId</em>' attribute.
	 * @see #setMakersId(java.lang.String)
	 */
	public String getMakersId() {
		return DataUtil.toString(super.getByIndex(INDEX_MAKERSID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMakersId <em>MakersId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MakersId</em>' attribute.
	 * @see #getMakersId()
	 */
	public void setMakersId(String makersId) {
		super.setByIndex(INDEX_MAKERSID, makersId);
	}

	/**
	 * Returns the value of the '<em><b>AddDate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AddDate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AddDate</em>' attribute.
	 * @see #setAddDate(java.lang.String)
	 */
	public String getAddDate() {
		return DataUtil.toString(super.getByIndex(INDEX_ADDDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAddDate <em>AddDate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AddDate</em>' attribute.
	 * @see #getAddDate()
	 */
	public void setAddDate(String addDate) {
		super.setByIndex(INDEX_ADDDATE, addDate);
	}

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(java.lang.String)
	 */
	public String getEnabled() {
		return DataUtil.toString(super.getByIndex(INDEX_ENABLED, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #getEnabled()
	 */
	public void setEnabled(String enabled) {
		super.setByIndex(INDEX_ENABLED, enabled);
	}


}