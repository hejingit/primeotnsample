
/*organization*/
/*
INSERT INTO ORG_ORGANIZATION (ORGID,ORGCODE,ORGNAME,ORGLEVEL,ORGDEGREE,ORGSEQ,ORGTYPE,ORGADDR,ZIPCODE,MANAPOSITION,MANAGERID,ORGMANAGER,LINKMAN,LINKTEL,EMAIL,WEBURL,STARTDATE,ENDDATE,STATUS,AREA,CREATETIME,LASTUPDATE,UPDATOR,SORTNO,ISLEAF,SUBCOUNT,REMARK,TENANT_ID,APP_ID,PARENTORGID) VALUES ('-1', '10000', 'Company', '1', null, '.1.', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-07-03 13:48:28', '2015-07-03 13:48:28', null, null, 'n', '2', null, 'default', null, null);
INSERT INTO ORG_ORGANIZATION (ORGID,ORGCODE,ORGNAME,ORGLEVEL,ORGDEGREE,ORGSEQ,ORGTYPE,ORGADDR,ZIPCODE,MANAPOSITION,MANAGERID,ORGMANAGER,LINKMAN,LINKTEL,EMAIL,WEBURL,STARTDATE,ENDDATE,STATUS,AREA,CREATETIME,LASTUPDATE,UPDATOR,SORTNO,ISLEAF,SUBCOUNT,REMARK,TENANT_ID,APP_ID,PARENTORGID) VALUES ('-2', '10001', 'DeptA', '2', null, '.1.2.', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-07-03 13:49:12', '2015-07-03 13:49:12', null, null, 'y', '0', null, 'default', null, '-1');
INSERT INTO ORG_ORGANIZATION (ORGID,ORGCODE,ORGNAME,ORGLEVEL,ORGDEGREE,ORGSEQ,ORGTYPE,ORGADDR,ZIPCODE,MANAPOSITION,MANAGERID,ORGMANAGER,LINKMAN,LINKTEL,EMAIL,WEBURL,STARTDATE,ENDDATE,STATUS,AREA,CREATETIME,LASTUPDATE,UPDATOR,SORTNO,ISLEAF,SUBCOUNT,REMARK,TENANT_ID,APP_ID,PARENTORGID) VALUES ('-3', '10002', 'DeptB', '2', null, '.1.3.', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-07-03 13:49:44', '2015-07-03 13:49:44', null, null, 'n', '1', null, 'default', null, '-1');
INSERT INTO ORG_ORGANIZATION (ORGID,ORGCODE,ORGNAME,ORGLEVEL,ORGDEGREE,ORGSEQ,ORGTYPE,ORGADDR,ZIPCODE,MANAPOSITION,MANAGERID,ORGMANAGER,LINKMAN,LINKTEL,EMAIL,WEBURL,STARTDATE,ENDDATE,STATUS,AREA,CREATETIME,LASTUPDATE,UPDATOR,SORTNO,ISLEAF,SUBCOUNT,REMARK,TENANT_ID,APP_ID,PARENTORGID) VALUES ('-4', '10003', 'DeptC', '3', null, '.1.3.4.', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-07-03 13:50:02', '2015-07-03 13:50:02', null, null, 'y', '0', null, 'default', null, '-3');
*/
/*role*/
/*
INSERT INTO CAP_ROLE (ROLE_ID,TENANT_ID,ROLE_CODE,ROLE_NAME,ROLE_DESC,CREATEUSER,CREATETIME) VALUES ('-2','default','rolea','RoleA','示例角色',NULL,NULL);
INSERT INTO CAP_ROLE (ROLE_ID,TENANT_ID,ROLE_CODE,ROLE_NAME,ROLE_DESC,CREATEUSER,CREATETIME) VALUES ('-3','default','roleb','RoleB','示例角色',NULL,NULL);
INSERT INTO CAP_ROLE (ROLE_ID,TENANT_ID,ROLE_CODE,ROLE_NAME,ROLE_DESC,CREATEUSER,CREATETIME) VALUES ('-4','default','rolec','RoleC','示例角色',NULL,NULL);
*/
/*user*/
/*
INSERT INTO CAP_USER (OPERATOR_ID,TENANT_ID,USER_ID,PASSWORD,INVALDATE,USER_NAME,AUTHMODE,STATUS,UNLOCKTIME,MENUTYPE,LASTLOGIN,ERRCOUNT,STARTDATE,ENDDATE,VALIDTIME,MACCODE,IPADDRESS,EMAIL,CREATEUSER,CREATETIME) VALUES (-2,'default','tiger','k2xvHUmCHWw=',NULL,'Tiger','local','1','2013-03-16 11:58:31','default','2015-07-05 11:58:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sysadmin','2015-07-05 11:58:31');
INSERT INTO CAP_USER (OPERATOR_ID,TENANT_ID,USER_ID,PASSWORD,INVALDATE,USER_NAME,AUTHMODE,STATUS,UNLOCKTIME,MENUTYPE,LASTLOGIN,ERRCOUNT,STARTDATE,ENDDATE,VALIDTIME,MACCODE,IPADDRESS,EMAIL,CREATEUSER,CREATETIME) VALUES (-3,'default','goose','k2xvHUmCHWw=',NULL,'Goose','local','1','2013-03-16 11:58:31','default','2015-07-05 11:58:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sysadmin','2015-07-05 11:58:31');
INSERT INTO CAP_USER (OPERATOR_ID,TENANT_ID,USER_ID,PASSWORD,INVALDATE,USER_NAME,AUTHMODE,STATUS,UNLOCKTIME,MENUTYPE,LASTLOGIN,ERRCOUNT,STARTDATE,ENDDATE,VALIDTIME,MACCODE,IPADDRESS,EMAIL,CREATEUSER,CREATETIME) VALUES (-4,'default','snoppy','k2xvHUmCHWw=',NULL,'Snoppy','local','1','2013-03-16 11:58:31','default','2015-07-05 11:58:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sysadmin','2015-07-05 11:58:31');
INSERT INTO CAP_USER (OPERATOR_ID,TENANT_ID,USER_ID,PASSWORD,INVALDATE,USER_NAME,AUTHMODE,STATUS,UNLOCKTIME,MENUTYPE,LASTLOGIN,ERRCOUNT,STARTDATE,ENDDATE,VALIDTIME,MACCODE,IPADDRESS,EMAIL,CREATEUSER,CREATETIME) VALUES (-5,'default','micky','k2xvHUmCHWw=',NULL,'Micky','local','1','2013-03-16 11:58:31','default','2015-07-05 11:58:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sysadmin','2015-07-05 11:58:31');
INSERT INTO CAP_USER (OPERATOR_ID,TENANT_ID,USER_ID,PASSWORD,INVALDATE,USER_NAME,AUTHMODE,STATUS,UNLOCKTIME,MENUTYPE,LASTLOGIN,ERRCOUNT,STARTDATE,ENDDATE,VALIDTIME,MACCODE,IPADDRESS,EMAIL,CREATEUSER,CREATETIME) VALUES (-6,'default','kitty','k2xvHUmCHWw=',NULL,'Kitty','local','1','2013-03-16 11:58:31','default','2015-07-05 11:58:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sysadmin','2015-07-05 11:58:31');
INSERT INTO CAP_USER (OPERATOR_ID,TENANT_ID,USER_ID,PASSWORD,INVALDATE,USER_NAME,AUTHMODE,STATUS,UNLOCKTIME,MENUTYPE,LASTLOGIN,ERRCOUNT,STARTDATE,ENDDATE,VALIDTIME,MACCODE,IPADDRESS,EMAIL,CREATEUSER,CREATETIME) VALUES (-7,'default','fish','k2xvHUmCHWw=',NULL,'Fish','local','1','2013-03-16 11:58:31','default','2015-07-05 11:58:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sysadmin','2015-07-05 11:58:31');
*/
/*employee*/

INSERT INTO ORG_EMPLOYEE (EMPID,EMPCODE,OPERATORID,USERID,EMPNAME,REALNAME,GENDER,BIRTHDATE,POSITION,EMPSTATUS,CARDTYPE,CARDNO,INDATE,OUTDATE,OTEL,OADDRESS,OZIPCODE,OEMAIL,FAXNO,MOBILENO,QQ,HTEL,HADDRESS,HZIPCODE,PEMAIL,PARTY,DEGREE,MAJOR,SPECIALTY,WORKEXP,REGDATE,CREATETIME,LASTMODYTIME,ORGIDLIST,ORGID,REMARK,TENANT_ID,APP_ID,WEIBO) VALUES (-1,'tiger',-1,'tiger','Tiger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-05 12:21:05','2015-07-05 12:21:05',NULL,'-1',NULL,'default',NULL,NULL);
INSERT INTO ORG_EMPLOYEE (EMPID,EMPCODE,OPERATORID,USERID,EMPNAME,REALNAME,GENDER,BIRTHDATE,POSITION,EMPSTATUS,CARDTYPE,CARDNO,INDATE,OUTDATE,OTEL,OADDRESS,OZIPCODE,OEMAIL,FAXNO,MOBILENO,QQ,HTEL,HADDRESS,HZIPCODE,PEMAIL,PARTY,DEGREE,MAJOR,SPECIALTY,WORKEXP,REGDATE,CREATETIME,LASTMODYTIME,ORGIDLIST,ORGID,REMARK,TENANT_ID,APP_ID,WEIBO) VALUES (-2,'goose',-2,'goose','Goose',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-05 12:21:05','2015-07-05 12:21:05',NULL,'-2',NULL,'default',NULL,NULL);
INSERT INTO ORG_EMPLOYEE (EMPID,EMPCODE,OPERATORID,USERID,EMPNAME,REALNAME,GENDER,BIRTHDATE,POSITION,EMPSTATUS,CARDTYPE,CARDNO,INDATE,OUTDATE,OTEL,OADDRESS,OZIPCODE,OEMAIL,FAXNO,MOBILENO,QQ,HTEL,HADDRESS,HZIPCODE,PEMAIL,PARTY,DEGREE,MAJOR,SPECIALTY,WORKEXP,REGDATE,CREATETIME,LASTMODYTIME,ORGIDLIST,ORGID,REMARK,TENANT_ID,APP_ID,WEIBO) VALUES (-3,'fish',-3,'fish','Fish',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-05 12:21:05','2015-07-05 12:21:05',NULL,'-6',NULL,'default',NULL,NULL);
INSERT INTO ORG_EMPLOYEE (EMPID,EMPCODE,OPERATORID,USERID,EMPNAME,REALNAME,GENDER,BIRTHDATE,POSITION,EMPSTATUS,CARDTYPE,CARDNO,INDATE,OUTDATE,OTEL,OADDRESS,OZIPCODE,OEMAIL,FAXNO,MOBILENO,QQ,HTEL,HADDRESS,HZIPCODE,PEMAIL,PARTY,DEGREE,MAJOR,SPECIALTY,WORKEXP,REGDATE,CREATETIME,LASTMODYTIME,ORGIDLIST,ORGID,REMARK,TENANT_ID,APP_ID,WEIBO) VALUES (-4,'snoppy',-4,'snoppy','Snoppy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-05 12:21:05','2015-07-05 12:21:05',NULL,'-3',NULL,'default',NULL,NULL);
INSERT INTO ORG_EMPLOYEE (EMPID,EMPCODE,OPERATORID,USERID,EMPNAME,REALNAME,GENDER,BIRTHDATE,POSITION,EMPSTATUS,CARDTYPE,CARDNO,INDATE,OUTDATE,OTEL,OADDRESS,OZIPCODE,OEMAIL,FAXNO,MOBILENO,QQ,HTEL,HADDRESS,HZIPCODE,PEMAIL,PARTY,DEGREE,MAJOR,SPECIALTY,WORKEXP,REGDATE,CREATETIME,LASTMODYTIME,ORGIDLIST,ORGID,REMARK,TENANT_ID,APP_ID,WEIBO) VALUES (-5,'micky',-5,'micky','Micky',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-05 12:21:05','2015-07-05 12:21:05',NULL,'-4',NULL,'default',NULL,NULL);
INSERT INTO ORG_EMPLOYEE (EMPID,EMPCODE,OPERATORID,USERID,EMPNAME,REALNAME,GENDER,BIRTHDATE,POSITION,EMPSTATUS,CARDTYPE,CARDNO,INDATE,OUTDATE,OTEL,OADDRESS,OZIPCODE,OEMAIL,FAXNO,MOBILENO,QQ,HTEL,HADDRESS,HZIPCODE,PEMAIL,PARTY,DEGREE,MAJOR,SPECIALTY,WORKEXP,REGDATE,CREATETIME,LASTMODYTIME,ORGIDLIST,ORGID,REMARK,TENANT_ID,APP_ID,WEIBO) VALUES (-6,'kitty',-6,'kitty','Kitty',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-05 12:21:05','2015-07-05 12:21:05',NULL,'-5',NULL,'default',NULL,NULL);


/*emporg*/
/*
INSERT INTO ORG_EMPORG (ORGID,EMPID,ISMAIN,TENANT_ID,APP_ID) VALUES ('-1', '-2', 'y', 'default', null);
INSERT INTO ORG_EMPORG (ORGID,EMPID,ISMAIN,TENANT_ID,APP_ID) VALUES ('-2', '-3', 'y', 'default', null);
INSERT INTO ORG_EMPORG (ORGID,EMPID,ISMAIN,TENANT_ID,APP_ID) VALUES ('-2', '-4', 'y', 'default', null);
INSERT INTO ORG_EMPORG (ORGID,EMPID,ISMAIN,TENANT_ID,APP_ID) VALUES ('-3', '-5', 'y', 'default', null);
INSERT INTO ORG_EMPORG (ORGID,EMPID,ISMAIN,TENANT_ID,APP_ID) VALUES ('-3', '-6', 'y', 'default', null);
INSERT INTO ORG_EMPORG (ORGID,EMPID,ISMAIN,TENANT_ID,APP_ID) VALUES ('-4', '-7', 'y', 'default', null);
*/

/*group*/
/*
INSERT INTO org_group (GROUPID, ORGID, GROUPLEVEL, GROUPNAME, GROUPDESC, GROUPTYPE, GROUPSEQ, STARTDATE, ENDDATE, GROUPSTATUS, MANAGER, CREATETIME, LASTUPDATE, UPDATOR, ISLEAF, SUBCOUNT, TENANT_ID, APP_ID, PARENTGROUPID) VALUES (-1, null, 1, 'GroupA', null, null, '.-1.', null, null, null, null, '2015-08-06 19:04:59', '2015-08-06', null, 'n', 1, 'default', null, null);
INSERT INTO org_group (GROUPID, ORGID, GROUPLEVEL, GROUPNAME, GROUPDESC, GROUPTYPE, GROUPSEQ, STARTDATE, ENDDATE, GROUPSTATUS, MANAGER, CREATETIME, LASTUPDATE, UPDATOR, ISLEAF, SUBCOUNT, TENANT_ID, APP_ID, PARENTGROUPID) VALUES (-2, null, 1, 'GroupB', null, null, '.-2.', null, null, null, null, '2015-08-06 19:05:05', '2015-08-06', null, 'y', 0, 'default', null, null);
INSERT INTO org_group (GROUPID, ORGID, GROUPLEVEL, GROUPNAME, GROUPDESC, GROUPTYPE, GROUPSEQ, STARTDATE, ENDDATE, GROUPSTATUS, MANAGER, CREATETIME, LASTUPDATE, UPDATOR, ISLEAF, SUBCOUNT, TENANT_ID, APP_ID, PARENTGROUPID) VALUES (-3, null, 2, 'GroupC', null, null, '.-1.-3.', null, null, null, null, '2015-08-06 19:05:12', '2015-08-06', null, 'y', 0, 'default', null, -1);
*/
/*empgroup*/
/*
INSERT INTO org_empgroup (GROUPID, EMPID, ISMAIN, TENANT_ID, APP_ID) VALUES (-3, -7, 'y', 'default', null);
INSERT INTO org_empgroup (GROUPID, EMPID, ISMAIN, TENANT_ID, APP_ID) VALUES (-3, -5, 'y', 'default', null);
INSERT INTO org_empgroup (GROUPID, EMPID, ISMAIN, TENANT_ID, APP_ID) VALUES (-2, -4, 'y', 'default', null);
INSERT INTO org_empgroup (GROUPID, EMPID, ISMAIN, TENANT_ID, APP_ID) VALUES (-2, -3, 'y', 'default', null);
INSERT INTO org_empgroup (GROUPID, EMPID, ISMAIN, TENANT_ID, APP_ID) VALUES (-1, -6, 'y', 'default', null);
INSERT INTO org_empgroup (GROUPID, EMPID, ISMAIN, TENANT_ID, APP_ID) VALUES (-1, -2, 'y', 'default', null);
*/
/*role user auth*/
/*
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role','fish','user','-3','default','sysadmin','2015-07-05 12:00:37');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role','goose','user','-3','default','sysadmin','2015-07-05 12:00:37');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role','kitty','user','-4','default','sysadmin','2015-07-05 12:00:37');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role','micky','user','-4','default','sysadmin','2015-07-05 12:00:37');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role','snoppy','user','-4','default','sysadmin','2015-07-05 12:00:37');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role','tiger','user','-2','default','sysadmin','2015-07-05 12:00:37');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role', '-2', 'emp', '-2', 'default', 'sysadmin', '2015-07-05 16:29:19');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role', '-3', 'emp', '-3', 'default', 'sysadmin', '2015-07-05 16:30:00');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role', '-4', 'emp', '-4', 'default', 'sysadmin', '2015-07-05 16:30:11');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role', '-5', 'emp', '-4', 'default', 'sysadmin', '2015-07-05 16:30:11');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role', '-6', 'emp', '-4', 'default', 'sysadmin', '2015-07-05 16:30:11');
INSERT INTO CAP_PARTYAUTH (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID,TENANT_ID,CREATEUSER,CREATETIME) VALUES ('role', '-7', 'emp', '-3', 'default', 'sysadmin', '2015-07-05 16:30:00');
*/