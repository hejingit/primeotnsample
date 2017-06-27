/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.org.groupdataset;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

import java.math.BigDecimal;

import org.gocom.components.coframe.org.dataset.OrgEmployee;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getGroupid <em>Groupid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getIsmain <em>Ismain</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getTenantId <em>TenantId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getAppId <em>AppId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getOrgEmployee <em>OrgEmployee</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OrgEmpgroup extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.org.groupdataset.OrgEmpgroup";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.org.groupdataset", "OrgEmpgroup");

	public final static IObjectFactory<OrgEmpgroup> FACTORY = new IObjectFactory<OrgEmpgroup>() {
		public OrgEmpgroup create() {
			return (OrgEmpgroup) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public BigDecimal getGroupid();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getGroupid <em>Groupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupid</em>' attribute.
	 * @see #getGroupid()
	 */
	public void setGroupid(BigDecimal groupid);

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
	public String getIsmain();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getIsmain <em>Ismain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismain</em>' attribute.
	 * @see #getIsmain()
	 */
	public void setIsmain(String ismain);

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
	public String getTenantId();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getTenantId <em>TenantId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TenantId</em>' attribute.
	 * @see #getTenantId()
	 */
	public void setTenantId(String tenantId);

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
	public String getAppId();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getAppId <em>AppId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AppId</em>' attribute.
	 * @see #getAppId()
	 */
	public void setAppId(String appId);

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
	public OrgEmployee getOrgEmployee();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgEmpgroup#getOrgEmployee <em>OrgEmployee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgEmployee</em>' attribute.
	 * @see #getOrgEmployee()
	 */
	public void setOrgEmployee(OrgEmployee orgEmployee);


}