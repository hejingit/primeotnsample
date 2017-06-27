/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.org.groupdataset.impl;

import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.math.BigDecimal;

import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.groupdataset.OrgEmpgroup;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgEmpgroupImpl#getGroupid <em>Groupid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgEmpgroupImpl#getIsmain <em>Ismain</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgEmpgroupImpl#getTenantId <em>TenantId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgEmpgroupImpl#getAppId <em>AppId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgEmpgroupImpl#getOrgEmployee <em>OrgEmployee</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OrgEmpgroup;
 */

public class OrgEmpgroupImpl extends ExtendedDataObjectImpl implements OrgEmpgroup {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_GROUPID = 0;
	public final static int INDEX_ISMAIN = 1;
	public final static int INDEX_TENANTID = 2;
	public final static int INDEX_APPID = 3;
	public final static int INDEX_ORGEMPLOYEE = 4;
	public final static int SDO_PROPERTY_COUNT = 5;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgEmpgroupImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgEmpgroupImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Groupid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groupid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groupid</em>' attribute.
	 * @see #setGroupid(java.math.BigDecimal)
	 */
	public BigDecimal getGroupid() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_GROUPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGroupid <em>Groupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupid</em>' attribute.
	 * @see #getGroupid()
	 */
	public void setGroupid(BigDecimal groupid) {
		super.setByIndex(INDEX_GROUPID, groupid);
	}

	/**
	 * Returns the value of the '<em><b>Ismain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ismain</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ismain</em>' attribute.
	 * @see #setIsmain(java.lang.String)
	 */
	public String getIsmain() {
		return DataUtil.toString(super.getByIndex(INDEX_ISMAIN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsmain <em>Ismain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismain</em>' attribute.
	 * @see #getIsmain()
	 */
	public void setIsmain(String ismain) {
		super.setByIndex(INDEX_ISMAIN, ismain);
	}

	/**
	 * Returns the value of the '<em><b>TenantId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TenantId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TenantId</em>' attribute.
	 * @see #setTenantId(java.lang.String)
	 */
	public String getTenantId() {
		return DataUtil.toString(super.getByIndex(INDEX_TENANTID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getTenantId <em>TenantId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TenantId</em>' attribute.
	 * @see #getTenantId()
	 */
	public void setTenantId(String tenantId) {
		super.setByIndex(INDEX_TENANTID, tenantId);
	}

	/**
	 * Returns the value of the '<em><b>AppId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AppId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AppId</em>' attribute.
	 * @see #setAppId(java.lang.String)
	 */
	public String getAppId() {
		return DataUtil.toString(super.getByIndex(INDEX_APPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppId <em>AppId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AppId</em>' attribute.
	 * @see #getAppId()
	 */
	public void setAppId(String appId) {
		super.setByIndex(INDEX_APPID, appId);
	}

	/**
	 * Returns the value of the '<em><b>OrgEmployee</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OrgEmployee</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OrgEmployee</em>' attribute.
	 * @see #setOrgEmployee(org.gocom.components.coframe.org.dataset.OrgEmployee)
	 */
	public OrgEmployee getOrgEmployee() {
		return (OrgEmployee) DataUtil.toDataObject(super.getByIndex(INDEX_ORGEMPLOYEE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgEmployee <em>OrgEmployee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgEmployee</em>' attribute.
	 * @see #getOrgEmployee()
	 */
	public void setOrgEmployee(OrgEmployee orgEmployee) {
		super.setByIndex(INDEX_ORGEMPLOYEE, orgEmployee);
	}


}