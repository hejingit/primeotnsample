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
 *   <li>{@link org.gocom.components.coframe.dict.EosDictType#getDicttypeid <em>Dicttypeid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictType#getDicttypename <em>Dicttypename</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictType#getRank <em>Rank</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictType#getParentid <em>Parentid</em>}</li>
 *   <li>{@link org.gocom.components.coframe.dict.EosDictType#getSeqno <em>Seqno</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface EosDictType extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.dict.dict.EosDictType";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.dict.dict", "EosDictType");

	public final static IObjectFactory<EosDictType> FACTORY = new IObjectFactory<EosDictType>() {
		public EosDictType create() {
			return (EosDictType) DataFactory.INSTANCE.create(TYPE);
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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictType#getDicttypeid <em>Dicttypeid</em>}' attribute.
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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictType#getDicttypename <em>Dicttypename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypename</em>' attribute.
	 * @see #getDicttypename()
	 */
	public void setDicttypename(String dicttypename);

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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictType#getRank <em>Rank</em>}' attribute.
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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictType#getParentid <em>Parentid</em>}' attribute.
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
	 * Sets the value of the '{@link org.gocom.components.coframe.dict.EosDictType#getSeqno <em>Seqno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seqno</em>' attribute.
	 * @see #getSeqno()
	 */
	public void setSeqno(String seqno);


}