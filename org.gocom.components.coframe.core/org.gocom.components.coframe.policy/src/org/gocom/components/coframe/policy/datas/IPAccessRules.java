/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.policy.datas;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getRulesId <em>RulesId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getStartIP <em>StartIP</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getEndIP <em>EndIP</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getRulesType <em>RulesType</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getRemark <em>Remark</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getMakersId <em>MakersId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getAddDate <em>AddDate</em>}</li>
 *   <li>{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface IPAccessRules extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.policy.datas.IPAccessRules";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.policy.datas", "IPAccessRules");

	public final static IObjectFactory<IPAccessRules> FACTORY = new IObjectFactory<IPAccessRules>() {
		public IPAccessRules create() {
			return (IPAccessRules) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getRulesId();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getRulesId <em>RulesId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RulesId</em>' attribute.
	 * @see #getRulesId()
	 */
	public void setRulesId(String rulesId);

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
	public String getStartIP();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getStartIP <em>StartIP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>StartIP</em>' attribute.
	 * @see #getStartIP()
	 */
	public void setStartIP(String startIP);

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
	public String getEndIP();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getEndIP <em>EndIP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EndIP</em>' attribute.
	 * @see #getEndIP()
	 */
	public void setEndIP(String endIP);

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
	public String getRulesType();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getRulesType <em>RulesType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RulesType</em>' attribute.
	 * @see #getRulesType()
	 */
	public void setRulesType(String rulesType);

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
	public String getRemark();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getRemark <em>Remark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remark</em>' attribute.
	 * @see #getRemark()
	 */
	public void setRemark(String remark);

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
	public String getMakersId();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getMakersId <em>MakersId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MakersId</em>' attribute.
	 * @see #getMakersId()
	 */
	public void setMakersId(String makersId);

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
	public String getAddDate();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getAddDate <em>AddDate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AddDate</em>' attribute.
	 * @see #getAddDate()
	 */
	public void setAddDate(String addDate);

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
	public String getEnabled();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.policy.datas.IPAccessRules#getEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #getEnabled()
	 */
	public void setEnabled(String enabled);


}