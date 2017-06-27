/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.auth.queryentity.impl;

import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.math.BigDecimal;

import org.gocom.components.coframe.auth.queryentity.QueryGroupByRole;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.auth.queryentity.impl.QueryGroupByRoleImpl#getRoleId <em>RoleId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.auth.queryentity.impl.QueryGroupByRoleImpl#getPartyType <em>PartyType</em>}</li>
 *   <li>{@link org.gocom.components.coframe.auth.queryentity.impl.QueryGroupByRoleImpl#getGroupid <em>Groupid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.auth.queryentity.impl.QueryGroupByRoleImpl#getGroupname <em>Groupname</em>}</li>
 *   <li>{@link org.gocom.components.coframe.auth.queryentity.impl.QueryGroupByRoleImpl#getTenantId <em>TenantId</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements QueryGroupByRole;
 */

public class QueryGroupByRoleImpl extends ExtendedDataObjectImpl implements QueryGroupByRole {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_ROLEID = 0;
	public final static int INDEX_PARTYTYPE = 1;
	public final static int INDEX_GROUPID = 2;
	public final static int INDEX_GROUPNAME = 3;
	public final static int INDEX_TENANTID = 4;
	public final static int SDO_PROPERTY_COUNT = 5;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public QueryGroupByRoleImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public QueryGroupByRoleImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>RoleId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RoleId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RoleId</em>' attribute.
	 * @see #setRoleId(java.lang.String)
	 */
	public String getRoleId() {
		return DataUtil.toString(super.getByIndex(INDEX_ROLEID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRoleId <em>RoleId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RoleId</em>' attribute.
	 * @see #getRoleId()
	 */
	public void setRoleId(String roleId) {
		super.setByIndex(INDEX_ROLEID, roleId);
	}

	/**
	 * Returns the value of the '<em><b>PartyType</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PartyType</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PartyType</em>' attribute.
	 * @see #setPartyType(java.lang.String)
	 */
	public String getPartyType() {
		return DataUtil.toString(super.getByIndex(INDEX_PARTYTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPartyType <em>PartyType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PartyType</em>' attribute.
	 * @see #getPartyType()
	 */
	public void setPartyType(String partyType) {
		super.setByIndex(INDEX_PARTYTYPE, partyType);
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
	 * Returns the value of the '<em><b>Groupname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groupname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groupname</em>' attribute.
	 * @see #setGroupname(java.lang.String)
	 */
	public String getGroupname() {
		return DataUtil.toString(super.getByIndex(INDEX_GROUPNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGroupname <em>Groupname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupname</em>' attribute.
	 * @see #getGroupname()
	 */
	public void setGroupname(String groupname) {
		super.setByIndex(INDEX_GROUPNAME, groupname);
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


}