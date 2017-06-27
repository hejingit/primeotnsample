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
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getDictid <em>Dictid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getDictname <em>Dictname</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getStatus <em>Status</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getSortno <em>Sortno</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getRank <em>Rank</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getParentid <em>Parentid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getSeqno <em>Seqno</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getFilter1 <em>Filter1</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getFilter2 <em>Filter2</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictEntry#getEosDictType <em>EosDictType</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface EosDictEntry extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.dict.dict.EosDictEntry";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.dict.dict", "EosDictEntry");

	public final static IObjectFactory<EosDictEntry> FACTORY = new IObjectFactory<EosDictEntry>() {
		public EosDictEntry create() {
			return (EosDictEntry) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getDictid <em>Dictid</em>}' attribute.
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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getDictname <em>Dictname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dictname</em>' attribute.
	 * @see #getDictname()
	 */
	public void setDictname(String dictname);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(int)
	 */
	public int getStatus();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 */
	public void setStatus(int status);

	/**
	 * Returns the value of the '<em><b>Sortno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sortno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sortno</em>' attribute.
	 * @see #setSortno(int)
	 */
	public int getSortno();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getSortno <em>Sortno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sortno</em>' attribute.
	 * @see #getSortno()
	 */
	public void setSortno(int sortno);

	/**
	 * Returns the value of the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rank</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rank</em>' attribute.
	 * @see #setRank(int)
	 */
	public int getRank();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getRank <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rank</em>' attribute.
	 * @see #getRank()
	 */
	public void setRank(int rank);

	/**
	 * Returns the value of the '<em><b>Parentid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parentid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parentid</em>' attribute.
	 * @see #setParentid(java.lang.String)
	 */
	public String getParentid();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getParentid <em>Parentid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parentid</em>' attribute.
	 * @see #getParentid()
	 */
	public void setParentid(String parentid);

	/**
	 * Returns the value of the '<em><b>Seqno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seqno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seqno</em>' attribute.
	 * @see #setSeqno(java.lang.String)
	 */
	public String getSeqno();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getSeqno <em>Seqno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seqno</em>' attribute.
	 * @see #getSeqno()
	 */
	public void setSeqno(String seqno);

	/**
	 * Returns the value of the '<em><b>Filter1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter1</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter1</em>' attribute.
	 * @see #setFilter1(java.lang.String)
	 */
	public String getFilter1();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getFilter1 <em>Filter1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter1</em>' attribute.
	 * @see #getFilter1()
	 */
	public void setFilter1(String filter1);

	/**
	 * Returns the value of the '<em><b>Filter2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter2</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter2</em>' attribute.
	 * @see #setFilter2(java.lang.String)
	 */
	public String getFilter2();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getFilter2 <em>Filter2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter2</em>' attribute.
	 * @see #getFilter2()
	 */
	public void setFilter2(String filter2);

	/**
	 * Returns the value of the '<em><b>EosDictType</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EosDictType</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EosDictType</em>' attribute.
	 * @see #setEosDictType(org.gocom.components.coframe.dict.EosDictType)
	 */
	public EosDictType getEosDictType();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictEntry#getEosDictType <em>EosDictType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EosDictType</em>' attribute.
	 * @see #getEosDictType()
	 */
	public void setEosDictType(EosDictType eosDictType);


}