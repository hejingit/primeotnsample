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

import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroupposi;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgGroupposiImpl#getIsmain <em>Ismain</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgGroupposiImpl#getTenantId <em>TenantId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgGroupposiImpl#getAppId <em>AppId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgGroupposiImpl#getOrgGroup <em>OrgGroup</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.impl.OrgGroupposiImpl#getOrgPosition <em>OrgPosition</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OrgGroupposi;
 */

public class OrgGroupposiImpl extends ExtendedDataObjectImpl implements OrgGroupposi {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_ISMAIN = 0;
	public final static int INDEX_TENANTID = 1;
	public final static int INDEX_APPID = 2;
	public final static int INDEX_ORGGROUP = 3;
	public final static int INDEX_ORGPOSITION = 4;
	public final static int SDO_PROPERTY_COUNT = 5;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgGroupposiImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgGroupposiImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
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
	 * Returns the value of the '<em><b>OrgGroup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OrgGroup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OrgGroup</em>' attribute.
	 * @see #setOrgGroup(org.gocom.components.coframe.org.groupdataset.OrgGroup)
	 */
	public OrgGroup getOrgGroup() {
		return (OrgGroup) DataUtil.toDataObject(super.getByIndex(INDEX_ORGGROUP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgGroup <em>OrgGroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgGroup</em>' attribute.
	 * @see #getOrgGroup()
	 */
	public void setOrgGroup(OrgGroup orgGroup) {
		super.setByIndex(INDEX_ORGGROUP, orgGroup);
	}

	/**
	 * Returns the value of the '<em><b>OrgPosition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OrgPosition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OrgPosition</em>' attribute.
	 * @see #setOrgPosition(org.gocom.components.coframe.org.dataset.OrgPosition)
	 */
	public OrgPosition getOrgPosition() {
		return (OrgPosition) DataUtil.toDataObject(super.getByIndex(INDEX_ORGPOSITION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgPosition <em>OrgPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgPosition</em>' attribute.
	 * @see #getOrgPosition()
	 */
	public void setOrgPosition(OrgPosition orgPosition) {
		super.setByIndex(INDEX_ORGPOSITION, orgPosition);
	}


}