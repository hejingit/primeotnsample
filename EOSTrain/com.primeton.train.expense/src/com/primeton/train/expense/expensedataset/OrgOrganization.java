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

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgid <em>Orgid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgcode <em>Orgcode</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgname <em>Orgname</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgdegree <em>Orgdegree</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgseq <em>Orgseq</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgtype <em>Orgtype</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgaddr <em>Orgaddr</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getZipcode <em>Zipcode</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getManaposition <em>Manaposition</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getManagerid <em>Managerid</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgmanager <em>Orgmanager</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getLinkman <em>Linkman</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getLinktel <em>Linktel</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getEmail <em>Email</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getWeburl <em>Weburl</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getStatus <em>Status</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getArea <em>Area</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getLastupdate <em>Lastupdate</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getUpdator <em>Updator</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getSortno <em>Sortno</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getRemark <em>Remark</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getTenantId <em>TenantId</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getAppId <em>AppId</em>}</li>
 *   <li>{@link com.primeton.train.expense.expensedataset.OrgOrganization#getParentorgid <em>Parentorgid</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OrgOrganization extends DataObject {

	public static final String QNAME = "com.primeton.train.expense.expensedataset.OrgOrganization";

	public static final Type TYPE = TypeHelper.INSTANCE.getType("com.primeton.train.expense.expensedataset", "OrgOrganization");

	public static final IObjectFactory<OrgOrganization> FACTORY = new IObjectFactory<OrgOrganization>() {
		public OrgOrganization create() {
			return (OrgOrganization) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Orgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgid</em>' attribute.
	 * @see #setOrgid(int)
	 */
	public int getOrgid();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgid <em>Orgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgid</em>' attribute.
	 * @see #getOrgid()
	 */
	public void setOrgid(int orgid);

	/**
	 * Returns the value of the '<em><b>Orgcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgcode</em>' attribute.
	 * @see #setOrgcode(java.lang.String)
	 */
	public String getOrgcode();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgcode <em>Orgcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgcode</em>' attribute.
	 * @see #getOrgcode()
	 */
	public void setOrgcode(String orgcode);

	/**
	 * Returns the value of the '<em><b>Orgname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgname</em>' attribute.
	 * @see #setOrgname(java.lang.String)
	 */
	public String getOrgname();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgname <em>Orgname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgname</em>' attribute.
	 * @see #getOrgname()
	 */
	public void setOrgname(String orgname);

	/**
	 * Returns the value of the '<em><b>Orglevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orglevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orglevel</em>' attribute.
	 * @see #setOrglevel(int)
	 */
	public int getOrglevel();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrglevel <em>Orglevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orglevel</em>' attribute.
	 * @see #getOrglevel()
	 */
	public void setOrglevel(int orglevel);

	/**
	 * Returns the value of the '<em><b>Orgdegree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgdegree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgdegree</em>' attribute.
	 * @see #setOrgdegree(java.lang.String)
	 */
	public String getOrgdegree();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgdegree <em>Orgdegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgdegree</em>' attribute.
	 * @see #getOrgdegree()
	 */
	public void setOrgdegree(String orgdegree);

	/**
	 * Returns the value of the '<em><b>Orgseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgseq</em>' attribute.
	 * @see #setOrgseq(java.lang.String)
	 */
	public String getOrgseq();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgseq <em>Orgseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgseq</em>' attribute.
	 * @see #getOrgseq()
	 */
	public void setOrgseq(String orgseq);

	/**
	 * Returns the value of the '<em><b>Orgtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgtype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgtype</em>' attribute.
	 * @see #setOrgtype(java.lang.String)
	 */
	public String getOrgtype();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgtype <em>Orgtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgtype</em>' attribute.
	 * @see #getOrgtype()
	 */
	public void setOrgtype(String orgtype);

	/**
	 * Returns the value of the '<em><b>Orgaddr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgaddr</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgaddr</em>' attribute.
	 * @see #setOrgaddr(java.lang.String)
	 */
	public String getOrgaddr();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgaddr <em>Orgaddr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgaddr</em>' attribute.
	 * @see #getOrgaddr()
	 */
	public void setOrgaddr(String orgaddr);

	/**
	 * Returns the value of the '<em><b>Zipcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Zipcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zipcode</em>' attribute.
	 * @see #setZipcode(java.lang.String)
	 */
	public String getZipcode();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getZipcode <em>Zipcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zipcode</em>' attribute.
	 * @see #getZipcode()
	 */
	public void setZipcode(String zipcode);

	/**
	 * Returns the value of the '<em><b>Manaposition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manaposition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manaposition</em>' attribute.
	 * @see #setManaposition(int)
	 */
	public int getManaposition();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getManaposition <em>Manaposition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manaposition</em>' attribute.
	 * @see #getManaposition()
	 */
	public void setManaposition(int manaposition);

	/**
	 * Returns the value of the '<em><b>Managerid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managerid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Managerid</em>' attribute.
	 * @see #setManagerid(int)
	 */
	public int getManagerid();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getManagerid <em>Managerid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managerid</em>' attribute.
	 * @see #getManagerid()
	 */
	public void setManagerid(int managerid);

	/**
	 * Returns the value of the '<em><b>Orgmanager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgmanager</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgmanager</em>' attribute.
	 * @see #setOrgmanager(java.lang.String)
	 */
	public String getOrgmanager();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getOrgmanager <em>Orgmanager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgmanager</em>' attribute.
	 * @see #getOrgmanager()
	 */
	public void setOrgmanager(String orgmanager);

	/**
	 * Returns the value of the '<em><b>Linkman</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linkman</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linkman</em>' attribute.
	 * @see #setLinkman(java.lang.String)
	 */
	public String getLinkman();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getLinkman <em>Linkman</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linkman</em>' attribute.
	 * @see #getLinkman()
	 */
	public void setLinkman(String linkman);

	/**
	 * Returns the value of the '<em><b>Linktel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linktel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linktel</em>' attribute.
	 * @see #setLinktel(java.lang.String)
	 */
	public String getLinktel();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getLinktel <em>Linktel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linktel</em>' attribute.
	 * @see #getLinktel()
	 */
	public void setLinktel(String linktel);

	/**
	 * Returns the value of the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Email</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Email</em>' attribute.
	 * @see #setEmail(java.lang.String)
	 */
	public String getEmail();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 */
	public void setEmail(String email);

	/**
	 * Returns the value of the '<em><b>Weburl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weburl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weburl</em>' attribute.
	 * @see #setWeburl(java.lang.String)
	 */
	public String getWeburl();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getWeburl <em>Weburl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weburl</em>' attribute.
	 * @see #getWeburl()
	 */
	public void setWeburl(String weburl);

	/**
	 * Returns the value of the '<em><b>Startdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Startdate</em>' attribute.
	 * @see #setStartdate(java.util.Date)
	 */
	public Date getStartdate();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getStartdate <em>Startdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startdate</em>' attribute.
	 * @see #getStartdate()
	 */
	public void setStartdate(Date startdate);

	/**
	 * Returns the value of the '<em><b>Enddate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enddate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enddate</em>' attribute.
	 * @see #setEnddate(java.util.Date)
	 */
	public Date getEnddate();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getEnddate <em>Enddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enddate</em>' attribute.
	 * @see #getEnddate()
	 */
	public void setEnddate(Date enddate);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(java.lang.String)
	 */
	public String getStatus();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 */
	public void setStatus(String status);

	/**
	 * Returns the value of the '<em><b>Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Area</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Area</em>' attribute.
	 * @see #setArea(java.lang.String)
	 */
	public String getArea();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getArea <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Area</em>' attribute.
	 * @see #getArea()
	 */
	public void setArea(String area);

	/**
	 * Returns the value of the '<em><b>Createtime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Createtime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Createtime</em>' attribute.
	 * @see #setCreatetime(java.util.Date)
	 */
	public Date getCreatetime();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getCreatetime <em>Createtime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Createtime</em>' attribute.
	 * @see #getCreatetime()
	 */
	public void setCreatetime(Date createtime);

	/**
	 * Returns the value of the '<em><b>Lastupdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastupdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastupdate</em>' attribute.
	 * @see #setLastupdate(java.util.Date)
	 */
	public Date getLastupdate();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getLastupdate <em>Lastupdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastupdate</em>' attribute.
	 * @see #getLastupdate()
	 */
	public void setLastupdate(Date lastupdate);

	/**
	 * Returns the value of the '<em><b>Updator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Updator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Updator</em>' attribute.
	 * @see #setUpdator(int)
	 */
	public int getUpdator();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getUpdator <em>Updator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updator</em>' attribute.
	 * @see #getUpdator()
	 */
	public void setUpdator(int updator);

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
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getSortno <em>Sortno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sortno</em>' attribute.
	 * @see #getSortno()
	 */
	public void setSortno(int sortno);

	/**
	 * Returns the value of the '<em><b>Isleaf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isleaf</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isleaf</em>' attribute.
	 * @see #setIsleaf(java.lang.String)
	 */
	public String getIsleaf();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getIsleaf <em>Isleaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isleaf</em>' attribute.
	 * @see #getIsleaf()
	 */
	public void setIsleaf(String isleaf);

	/**
	 * Returns the value of the '<em><b>Subcount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subcount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subcount</em>' attribute.
	 * @see #setSubcount(int)
	 */
	public int getSubcount();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount);

	/**
	 * Returns the value of the '<em><b>Remark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remark</em>' attribute.
	 * @see #setRemark(java.lang.String)
	 */
	public String getRemark();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getRemark <em>Remark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remark</em>' attribute.
	 * @see #getRemark()
	 */
	public void setRemark(String remark);

	/**
	 * Returns the value of the '<em><b>TenantId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TenantId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TenantId</em>' attribute.
	 * @see #setTenantId(java.lang.String)
	 */
	public String getTenantId();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getTenantId <em>TenantId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TenantId</em>' attribute.
	 * @see #getTenantId()
	 */
	public void setTenantId(String tenantId);

	/**
	 * Returns the value of the '<em><b>AppId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AppId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AppId</em>' attribute.
	 * @see #setAppId(java.lang.String)
	 */
	public String getAppId();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getAppId <em>AppId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AppId</em>' attribute.
	 * @see #getAppId()
	 */
	public void setAppId(String appId);

	/**
	 * Returns the value of the '<em><b>Parentorgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parentorgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parentorgid</em>' attribute.
	 * @see #setParentorgid(int)
	 */
	public int getParentorgid();

	/**
	 * Sets the value of the '{@link com.primeton.train.expense.expensedataset.OrgOrganization#getParentorgid <em>Parentorgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parentorgid</em>' attribute.
	 * @see #getParentorgid()
	 */
	public void setParentorgid(int parentorgid);


}