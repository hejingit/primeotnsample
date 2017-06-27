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

import org.gocom.components.coframe.org.dataset.OrgPosition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getIsmain <em>Ismain</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getTenantId <em>TenantId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getAppId <em>AppId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getOrgGroup <em>OrgGroup</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getOrgPosition <em>OrgPosition</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OrgGroupposi extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.org.groupdataset.OrgGroupposi";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.org.groupdataset", "OrgGroupposi");

	public final static IObjectFactory<OrgGroupposi> FACTORY = new IObjectFactory<OrgGroupposi>() {
		public OrgGroupposi create() {
			return (OrgGroupposi) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getIsmain <em>Ismain</em>}' attribute.
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
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getTenantId <em>TenantId</em>}' attribute.
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
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getAppId <em>AppId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AppId</em>' attribute.
	 * @see #getAppId()
	 */
	public void setAppId(String appId);

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
	public OrgGroup getOrgGroup();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getOrgGroup <em>OrgGroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgGroup</em>' attribute.
	 * @see #getOrgGroup()
	 */
	public void setOrgGroup(OrgGroup orgGroup);

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
	public OrgPosition getOrgPosition();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.groupdataset.OrgGroupposi#getOrgPosition <em>OrgPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgPosition</em>' attribute.
	 * @see #getOrgPosition()
	 */
	public void setOrgPosition(OrgPosition orgPosition);


}