/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package org.gocom.components.coframe.org.dataset;

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
 *   <li>{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getNodeId <em>NodeId</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getNodeType <em>NodeType</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getNodeName <em>NodeName</em>}</li>
 *   <li>{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getIconCls <em>IconCls</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OrgTreeNode extends DataObject {

	public final static String QNAME = "org.gocom.components.coframe.org.dataset.OrgTreeNode";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("org.gocom.components.coframe.org.dataset", "OrgTreeNode");

	public final static IObjectFactory<OrgTreeNode> FACTORY = new IObjectFactory<OrgTreeNode>() {
		public OrgTreeNode create() {
			return (OrgTreeNode) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getNodeId();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getNodeId <em>NodeId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NodeId</em>' attribute.
	 * @see #getNodeId()
	 */
	public void setNodeId(String nodeId);

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
	public String getNodeType();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getNodeType <em>NodeType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NodeType</em>' attribute.
	 * @see #getNodeType()
	 */
	public void setNodeType(String nodeType);

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
	public String getNodeName();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getNodeName <em>NodeName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NodeName</em>' attribute.
	 * @see #getNodeName()
	 */
	public void setNodeName(String nodeName);

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
	public String getIconCls();

	/**
	 * Sets the value of the '{@link org.gocom.components.coframe.org.dataset.OrgTreeNode#getIconCls <em>IconCls</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IconCls</em>' attribute.
	 * @see #getIconCls()
	 */
	public void setIconCls(String iconCls);


}