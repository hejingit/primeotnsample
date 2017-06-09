/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.primeton.train.expense.expensedataset;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

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
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpid <em>Expid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpcode <em>Expcode</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getEmpid <em>Empid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpname <em>Expname</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpdate <em>Expdate</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpmoney <em>Expmoney</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getReason <em>Reason</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getIsbudgetitem <em>Isbudgetitem</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getManager <em>Manager</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getManagerAdvice <em>ManagerAdvice</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getAuditor <em>Auditor</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getAuditorAdvice <em>AuditorAdvice</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getProcessinstid <em>Processinstid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getOrgOrganization <em>OrgOrganization</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface TrainExpenseinfo extends DataObject {

	public static final String QNAME = "com.primeton.train.expense.expensedataset.TrainExpenseinfo";

	public static final Type TYPE = TypeHelper.INSTANCE.getType("com.primeton.train.expense.expensedataset", "TrainExpenseinfo");

	public static final IObjectFactory<TrainExpenseinfo> FACTORY = new IObjectFactory<TrainExpenseinfo>() {
		public TrainExpenseinfo create() {
			return (TrainExpenseinfo) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public int getExpid();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpid <em>Expid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expid</em>' attribute.
	 * @see #getExpid()
	 */
	public void setExpid(int expid);

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
	public String getExpcode();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpcode <em>Expcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expcode</em>' attribute.
	 * @see #getExpcode()
	 */
	public void setExpcode(String expcode);

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
	public int getEmpid();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getEmpid <em>Empid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empid</em>' attribute.
	 * @see #getEmpid()
	 */
	public void setEmpid(int empid);

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
	public String getExpname();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpname <em>Expname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expname</em>' attribute.
	 * @see #getExpname()
	 */
	public void setExpname(String expname);

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
	public Date getExpdate();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpdate <em>Expdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expdate</em>' attribute.
	 * @see #getExpdate()
	 */
	public void setExpdate(Date expdate);

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
	public BigDecimal getExpmoney();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getExpmoney <em>Expmoney</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expmoney</em>' attribute.
	 * @see #getExpmoney()
	 */
	public void setExpmoney(BigDecimal expmoney);

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
	public String getReason();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getReason <em>Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reason</em>' attribute.
	 * @see #getReason()
	 */
	public void setReason(String reason);

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
	public String getIsbudgetitem();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getIsbudgetitem <em>Isbudgetitem</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isbudgetitem</em>' attribute.
	 * @see #getIsbudgetitem()
	 */
	public void setIsbudgetitem(String isbudgetitem);

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
	public String getManager();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getManager <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' attribute.
	 * @see #getManager()
	 */
	public void setManager(String manager);

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
	public String getManagerAdvice();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getManagerAdvice <em>ManagerAdvice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ManagerAdvice</em>' attribute.
	 * @see #getManagerAdvice()
	 */
	public void setManagerAdvice(String managerAdvice);

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
	public String getAuditor();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getAuditor <em>Auditor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auditor</em>' attribute.
	 * @see #getAuditor()
	 */
	public void setAuditor(String auditor);

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
	public String getAuditorAdvice();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getAuditorAdvice <em>AuditorAdvice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AuditorAdvice</em>' attribute.
	 * @see #getAuditorAdvice()
	 */
	public void setAuditorAdvice(String auditorAdvice);

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
	public long getProcessinstid();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getProcessinstid <em>Processinstid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Processinstid</em>' attribute.
	 * @see #getProcessinstid()
	 */
	public void setProcessinstid(long processinstid);

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
	public OrgOrganization getOrgOrganization();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.TrainExpenseinfo#getOrgOrganization <em>OrgOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgOrganization</em>' attribute.
	 * @see #getOrgOrganization()
	 */
	public void setOrgOrganization(OrgOrganization orgOrganization);


}