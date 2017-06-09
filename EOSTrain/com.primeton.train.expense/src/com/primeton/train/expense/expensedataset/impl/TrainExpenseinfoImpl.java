/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.primeton.train.expense.expensedataset.impl;

import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;
import com.primeton.train.expense.expensedataset.OrgOrganization;
import com.primeton.train.expense.expensedataset.TrainExpenseinfo;

import commonj.sdo.Type;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getExpid <em>Expid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getExpcode <em>Expcode</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getEmpid <em>Empid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getExpname <em>Expname</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getExpdate <em>Expdate</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getExpmoney <em>Expmoney</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getReason <em>Reason</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getIsbudgetitem <em>Isbudgetitem</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getManagerAdvice <em>ManagerAdvice</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getAuditor <em>Auditor</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getAuditorAdvice <em>AuditorAdvice</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getProcessinstid <em>Processinstid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.impl.TrainExpenseinfoImpl#getOrgOrganization <em>OrgOrganization</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements TrainExpenseinfo;
 */

public class TrainExpenseinfoImpl extends ExtendedDataObjectImpl implements TrainExpenseinfo {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_EXPID = 0;
	public final static int INDEX_EXPCODE = 1;
	public final static int INDEX_EMPID = 2;
	public final static int INDEX_EXPNAME = 3;
	public final static int INDEX_EXPDATE = 4;
	public final static int INDEX_EXPMONEY = 5;
	public final static int INDEX_REASON = 6;
	public final static int INDEX_ISBUDGETITEM = 7;
	public final static int INDEX_MANAGER = 8;
	public final static int INDEX_MANAGERADVICE = 9;
	public final static int INDEX_AUDITOR = 10;
	public final static int INDEX_AUDITORADVICE = 11;
	public final static int INDEX_PROCESSINSTID = 12;
	public final static int INDEX_ORGORGANIZATION = 13;
	public static final int SDO_PROPERTY_COUNT = 14;

	public static final int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TrainExpenseinfoImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TrainExpenseinfoImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Expid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expid</em>' attribute.
	 * @see #setExpid(int)
	 */
	public int getExpid() {
		return DataUtil.toInt(super.getByIndex(INDEX_EXPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getExpid <em>Expid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expid</em>' attribute.
	 * @see #getExpid()
	 */
	public void setExpid(int expid) {
		super.setByIndex(INDEX_EXPID, expid);
	}

	/**
	 * Returns the value of the '<em><b>Expcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expcode</em>' attribute.
	 * @see #setExpcode(java.lang.String)
	 */
	public String getExpcode() {
		return DataUtil.toString(super.getByIndex(INDEX_EXPCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getExpcode <em>Expcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expcode</em>' attribute.
	 * @see #getExpcode()
	 */
	public void setExpcode(String expcode) {
		super.setByIndex(INDEX_EXPCODE, expcode);
	}

	/**
	 * Returns the value of the '<em><b>Empid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Empid</em>' attribute.
	 * @see #setEmpid(int)
	 */
	public int getEmpid() {
		return DataUtil.toInt(super.getByIndex(INDEX_EMPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmpid <em>Empid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empid</em>' attribute.
	 * @see #getEmpid()
	 */
	public void setEmpid(int empid) {
		super.setByIndex(INDEX_EMPID, empid);
	}

	/**
	 * Returns the value of the '<em><b>Expname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expname</em>' attribute.
	 * @see #setExpname(java.lang.String)
	 */
	public String getExpname() {
		return DataUtil.toString(super.getByIndex(INDEX_EXPNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getExpname <em>Expname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expname</em>' attribute.
	 * @see #getExpname()
	 */
	public void setExpname(String expname) {
		super.setByIndex(INDEX_EXPNAME, expname);
	}

	/**
	 * Returns the value of the '<em><b>Expdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expdate</em>' attribute.
	 * @see #setExpdate(java.util.Date)
	 */
	public Date getExpdate() {
		return DataUtil.toDate(super.getByIndex(INDEX_EXPDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getExpdate <em>Expdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expdate</em>' attribute.
	 * @see #getExpdate()
	 */
	public void setExpdate(Date expdate) {
		super.setByIndex(INDEX_EXPDATE, expdate);
	}

	/**
	 * Returns the value of the '<em><b>Expmoney</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expmoney</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expmoney</em>' attribute.
	 * @see #setExpmoney(java.math.BigDecimal)
	 */
	public BigDecimal getExpmoney() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_EXPMONEY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getExpmoney <em>Expmoney</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expmoney</em>' attribute.
	 * @see #getExpmoney()
	 */
	public void setExpmoney(BigDecimal expmoney) {
		super.setByIndex(INDEX_EXPMONEY, expmoney);
	}

	/**
	 * Returns the value of the '<em><b>Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reason</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reason</em>' attribute.
	 * @see #setReason(java.lang.String)
	 */
	public String getReason() {
		return DataUtil.toString(super.getByIndex(INDEX_REASON, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getReason <em>Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reason</em>' attribute.
	 * @see #getReason()
	 */
	public void setReason(String reason) {
		super.setByIndex(INDEX_REASON, reason);
	}

	/**
	 * Returns the value of the '<em><b>Isbudgetitem</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isbudgetitem</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isbudgetitem</em>' attribute.
	 * @see #setIsbudgetitem(java.lang.String)
	 */
	public String getIsbudgetitem() {
		return DataUtil.toString(super.getByIndex(INDEX_ISBUDGETITEM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsbudgetitem <em>Isbudgetitem</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isbudgetitem</em>' attribute.
	 * @see #getIsbudgetitem()
	 */
	public void setIsbudgetitem(String isbudgetitem) {
		super.setByIndex(INDEX_ISBUDGETITEM, isbudgetitem);
	}

	/**
	 * Returns the value of the '<em><b>Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manager</em>' attribute.
	 * @see #setManager(java.lang.String)
	 */
	public String getManager() {
		return DataUtil.toString(super.getByIndex(INDEX_MANAGER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getManager <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' attribute.
	 * @see #getManager()
	 */
	public void setManager(String manager) {
		super.setByIndex(INDEX_MANAGER, manager);
	}

	/**
	 * Returns the value of the '<em><b>ManagerAdvice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ManagerAdvice</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ManagerAdvice</em>' attribute.
	 * @see #setManagerAdvice(java.lang.String)
	 */
	public String getManagerAdvice() {
		return DataUtil.toString(super.getByIndex(INDEX_MANAGERADVICE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getManagerAdvice <em>ManagerAdvice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ManagerAdvice</em>' attribute.
	 * @see #getManagerAdvice()
	 */
	public void setManagerAdvice(String managerAdvice) {
		super.setByIndex(INDEX_MANAGERADVICE, managerAdvice);
	}

	/**
	 * Returns the value of the '<em><b>Auditor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auditor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auditor</em>' attribute.
	 * @see #setAuditor(java.lang.String)
	 */
	public String getAuditor() {
		return DataUtil.toString(super.getByIndex(INDEX_AUDITOR, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAuditor <em>Auditor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auditor</em>' attribute.
	 * @see #getAuditor()
	 */
	public void setAuditor(String auditor) {
		super.setByIndex(INDEX_AUDITOR, auditor);
	}

	/**
	 * Returns the value of the '<em><b>AuditorAdvice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AuditorAdvice</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AuditorAdvice</em>' attribute.
	 * @see #setAuditorAdvice(java.lang.String)
	 */
	public String getAuditorAdvice() {
		return DataUtil.toString(super.getByIndex(INDEX_AUDITORADVICE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAuditorAdvice <em>AuditorAdvice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AuditorAdvice</em>' attribute.
	 * @see #getAuditorAdvice()
	 */
	public void setAuditorAdvice(String auditorAdvice) {
		super.setByIndex(INDEX_AUDITORADVICE, auditorAdvice);
	}

	/**
	 * Returns the value of the '<em><b>Processinstid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Processinstid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processinstid</em>' attribute.
	 * @see #setProcessinstid(long)
	 */
	public long getProcessinstid() {
		return DataUtil.toLong(super.getByIndex(INDEX_PROCESSINSTID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getProcessinstid <em>Processinstid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Processinstid</em>' attribute.
	 * @see #getProcessinstid()
	 */
	public void setProcessinstid(long processinstid) {
		super.setByIndex(INDEX_PROCESSINSTID, processinstid);
	}

	/**
	 * Returns the value of the '<em><b>OrgOrganization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OrgOrganization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OrgOrganization</em>' attribute.
	 * @see #setOrgOrganization(com.primeton.train.expense.expensedataset.OrgOrganization)
	 */
	public OrgOrganization getOrgOrganization() {
		return (OrgOrganization) DataUtil.toDataObject(super.getByIndex(INDEX_ORGORGANIZATION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgOrganization <em>OrgOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgOrganization</em>' attribute.
	 * @see #getOrgOrganization()
	 */
	public void setOrgOrganization(OrgOrganization orgOrganization) {
		super.setByIndex(INDEX_ORGORGANIZATION, orgOrganization);
	}


}