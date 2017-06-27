/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.dict;

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
 *   <li>{@link org.gocom.components.coframe.dict.EosDictTypeI18n#getDicttypeid <em>Dicttypeid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictTypeI18n#getDicttypename <em>Dicttypename</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictTypeI18n#getLocale <em>Locale</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface EosDictTypeI18n extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.dict.dict.EosDictTypeI18n";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.dict.dict", "EosDictTypeI18n");

	public final static IObjectFactory<EosDictTypeI18n> FACTORY = new IObjectFactory<EosDictTypeI18n>() {
		public EosDictTypeI18n create() {
			return (EosDictTypeI18n) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Dicttypeid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dicttypeid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dicttypeid</em>' attribute.
	 * @see #setDicttypeid(java.lang.String)
	 */
	public String getDicttypeid();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictTypeI18n#getDicttypeid <em>Dicttypeid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypeid</em>' attribute.
	 * @see #getDicttypeid()
	 */
	public void setDicttypeid(String dicttypeid);

	/**
	 * Returns the value of the '<em><b>Dicttypename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dicttypename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dicttypename</em>' attribute.
	 * @see #setDicttypename(java.lang.String)
	 */
	public String getDicttypename();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictTypeI18n#getDicttypename <em>Dicttypename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypename</em>' attribute.
	 * @see #getDicttypename()
	 */
	public void setDicttypename(String dicttypename);

	/**
	 * Returns the value of the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locale</em>' attribute.
	 * @see #setLocale(java.lang.String)
	 */
	public String getLocale();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictTypeI18n#getLocale <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locale</em>' attribute.
	 * @see #getLocale()
	 */
	public void setLocale(String locale);


}