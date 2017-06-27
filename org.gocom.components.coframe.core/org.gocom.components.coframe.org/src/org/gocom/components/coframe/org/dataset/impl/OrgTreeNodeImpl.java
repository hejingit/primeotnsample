/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.org.dataset.impl;

import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import org.gocom.components.coframe.org.dataset.OrgTreeNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gocom.components.coframe.org.dataset.impl.OrgTreeNodeImpl#getNodeId <em>NodeId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.dataset.impl.OrgTreeNodeImpl#getNodeType <em>NodeType</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.dataset.impl.OrgTreeNodeImpl#getNodeName <em>NodeName</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.dataset.impl.OrgTreeNodeImpl#getIconCls <em>IconCls</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OrgTreeNode;
 */

public class OrgTreeNodeImpl extends ExtendedDataObjectImpl implements OrgTreeNode {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_NODEID = 0;
	public final static int INDEX_NODETYPE = 1;
	public final static int INDEX_NODENAME = 2;
	public final static int INDEX_ICONCLS = 3;
	public final static int SDO_PROPERTY_COUNT = 4;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgTreeNodeImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgTreeNodeImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>NodeId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NodeId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NodeId</em>' attribute.
	 * @see #setNodeId(java.lang.String)
	 */
	public String getNodeId() {
		return DataUtil.toString(super.getByIndex(INDEX_NODEID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getNodeId <em>NodeId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NodeId</em>' attribute.
	 * @see #getNodeId()
	 */
	public void setNodeId(String nodeId) {
		super.setByIndex(INDEX_NODEID, nodeId);
	}

	/**
	 * Returns the value of the '<em><b>NodeType</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NodeType</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NodeType</em>' attribute.
	 * @see #setNodeType(java.lang.String)
	 */
	public String getNodeType() {
		return DataUtil.toString(super.getByIndex(INDEX_NODETYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getNodeType <em>NodeType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NodeType</em>' attribute.
	 * @see #getNodeType()
	 */
	public void setNodeType(String nodeType) {
		super.setByIndex(INDEX_NODETYPE, nodeType);
	}

	/**
	 * Returns the value of the '<em><b>NodeName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NodeName</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NodeName</em>' attribute.
	 * @see #setNodeName(java.lang.String)
	 */
	public String getNodeName() {
		return DataUtil.toString(super.getByIndex(INDEX_NODENAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getNodeName <em>NodeName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NodeName</em>' attribute.
	 * @see #getNodeName()
	 */
	public void setNodeName(String nodeName) {
		super.setByIndex(INDEX_NODENAME, nodeName);
	}

	/**
	 * Returns the value of the '<em><b>IconCls</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IconCls</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IconCls</em>' attribute.
	 * @see #setIconCls(java.lang.String)
	 */
	public String getIconCls() {
		return DataUtil.toString(super.getByIndex(INDEX_ICONCLS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIconCls <em>IconCls</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IconCls</em>' attribute.
	 * @see #getIconCls()
	 */
	public void setIconCls(String iconCls) {
		super.setByIndex(INDEX_ICONCLS, iconCls);
	}


}