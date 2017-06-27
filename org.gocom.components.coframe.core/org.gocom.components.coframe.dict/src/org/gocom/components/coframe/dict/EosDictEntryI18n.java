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
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getDicttypeid <em>Dicttypeid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getDictid <em>Dictid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getDictname <em>Dictname</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getLocale <em>Locale</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface EosDictEntryI18n extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.dict.dict.EosDictEntryI18n";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.dict.dict", "EosDictEntryI18n");

	public final static IObjectFactory<EosDictEntryI18n> FACTORY = new IObjectFactory<EosDictEntryI18n>() {
		public EosDictEntryI18n create() {
			return (EosDictEntryI18n) DataFactory.INSTANCE.create(TYPE);
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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getDicttypeid <em>Dicttypeid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypeid</em>' attribute.
	 * @see #getDicttypeid()
	 */
	public void setDicttypeid(String dicttypeid);

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
	public String getDictid();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getDictid <em>Dictid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dictid</em>' attribute.
	 * @see #getDictid()
	 */
	public void setDictid(String dictid);

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
	public String getDictname();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getDictname <em>Dictname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dictname</em>' attribute.
	 * @see #getDictname()
	 */
	public void setDictname(String dictname);

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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntryI18n#getLocale <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locale</em>' attribute.
	 * @see #getLocale()
	 */
	public void setLocale(String locale);


}