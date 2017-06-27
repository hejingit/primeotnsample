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

import org.gocom.components.coframe.dict.EosDictEntryI18n;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.dict.impl.EosDictEntryI18nImpl#getDicttypeid <em>Dicttypeid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.impl.EosDictEntryI18nImpl#getDictid <em>Dictid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.impl.EosDictEntryI18nImpl#getDictname <em>Dictname</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.impl.EosDictEntryI18nImpl#getLocale <em>Locale</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements EosDictEntryI18n;
 */

public class EosDictEntryI18nImpl extends ExtendedDataObjectImpl implements EosDictEntryI18n {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_DICTTYPEID = 0;
	public final static int INDEX_DICTID = 1;
	public final static int INDEX_DICTNAME = 2;
	public final static int INDEX_LOCALE = 3;
	public final static int SDO_PROPERTY_COUNT = 4;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EosDictEntryI18nImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EosDictEntryI18nImpl(Type type) {
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
	 * Returns the value of the '<em><b>Dictid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dictid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dictid</em>' attribute.
	 * @see #setDictid(java.lang.String)
	 */
	public String getDictid() {
		return DataUtil.toString(super.getByIndex(INDEX_DICTID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDictid <em>Dictid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dictid</em>' attribute.
	 * @see #getDictid()
	 */
	public void setDictid(String dictid) {
		super.setByIndex(INDEX_DICTID, dictid);
	}

	/**
	 * Returns the value of the '<em><b>Dictname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dictname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dictname</em>' attribute.
	 * @see #setDictname(java.lang.String)
	 */
	public String getDictname() {
		return DataUtil.toString(super.getByIndex(INDEX_DICTNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDictname <em>Dictname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dictname</em>' attribute.
	 * @see #getDictname()
	 */
	public void setDictname(String dictname) {
		super.setByIndex(INDEX_DICTNAME, dictname);
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