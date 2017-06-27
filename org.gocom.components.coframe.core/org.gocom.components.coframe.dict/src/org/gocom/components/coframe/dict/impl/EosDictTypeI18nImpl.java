/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.dict.impl;

import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import org.gocom.components.coframe.dict.EosDictTypeI18n;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.dict.impl.EosDictTypeI18nImpl#getDicttypeid <em>Dicttypeid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.impl.EosDictTypeI18nImpl#getDicttypename <em>Dicttypename</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.impl.EosDictTypeI18nImpl#getLocale <em>Locale</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements EosDictTypeI18n;
 */

public class EosDictTypeI18nImpl extends ExtendedDataObjectImpl implements EosDictTypeI18n {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_DICTTYPEID = 0;
	public final static int INDEX_DICTTYPENAME = 1;
	public final static int INDEX_LOCALE = 2;
	public final static int SDO_PROPERTY_COUNT = 3;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EosDictTypeI18nImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EosDictTypeI18nImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

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
	public String getDicttypeid() {
		return DataUtil.toString(super.getByIndex(INDEX_DICTTYPEID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDicttypeid <em>Dicttypeid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypeid</em>' attribute.
	 * @see #getDicttypeid()
	 */
	public void setDicttypeid(String dicttypeid) {
		super.setByIndex(INDEX_DICTTYPEID, dicttypeid);
	}

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
	public String getDicttypename() {
		return DataUtil.toString(super.getByIndex(INDEX_DICTTYPENAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDicttypename <em>Dicttypename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypename</em>' attribute.
	 * @see #getDicttypename()
	 */
	public void setDicttypename(String dicttypename) {
		super.setByIndex(INDEX_DICTTYPENAME, dicttypename);
	}

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
	public String getLocale() {
		return DataUtil.toString(super.getByIndex(INDEX_LOCALE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLocale <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locale</em>' attribute.
	 * @see #getLocale()
	 */
	public void setLocale(String locale) {
		super.setByIndex(INDEX_LOCALE, locale);
	}


}