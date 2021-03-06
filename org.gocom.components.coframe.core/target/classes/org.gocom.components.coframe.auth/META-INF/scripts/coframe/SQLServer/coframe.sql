DROP TABLE CAP_PARTYAUTH;
DROP TABLE CAP_RESAUTH;
DROP TABLE CAP_ROLE;
DROP TABLE COMP_IP_ACCESS_RULES;
DROP TABLE COMP_WIN7_AUTO_START;
DROP TABLE COMP_WIN7_CONFIG;
DROP TABLE COMP_WIN7_CUSTOM_PICTURES;
DROP TABLE COMP_WIN7_ICONS;
DROP TABLE APP_FUNCRESOURCE;
DROP TABLE APP_FUNCTION;
DROP TABLE APP_FUNCGROUP;
DROP TABLE APP_APPLICATION;
DROP TABLE APP_MENU;
DROP TABLE CAP_SSOUSER;
DROP TABLE CAP_USER;
DROP TABLE ORG_EMPORG;
DROP TABLE ORG_EMPPOSITION;
DROP TABLE ORG_EMPGROUP;
DROP TABLE ORG_EMPLOYEE;
DROP TABLE ORG_GROUP;
DROP TABLE ORG_GROUPPOSI;
DROP TABLE ORG_POSITION;
DROP TABLE ORG_DUTY;
DROP TABLE ORG_ORGANIZATION;
DROP TABLE ORG_RECENT_VISIT;


CREATE TABLE CAP_RESAUTH (
	PARTY_ID VARCHAR(64) NOT NULL,
	PARTY_TYPE VARCHAR(64) NOT NULL,
	RES_ID VARCHAR(255) NOT NULL,
	RES_TYPE VARCHAR(64) NOT NULL,
	TENANT_ID VARCHAR(64) NULL,
	RES_STATE VARCHAR(512) NOT NULL,
	PARTY_SCOPE VARCHAR(1) DEFAULT '0' NULL,
	CREATEUSER VARCHAR(64) NULL,
	CREATETIME DATETIME NULL,
	PRIMARY KEY (PARTY_ID,PARTY_TYPE,RES_ID,RES_TYPE)); 
CREATE TABLE CAP_ROLE (
	ROLE_ID VARCHAR(64) NOT NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	ROLE_CODE VARCHAR(64) NOT NULL,
	ROLE_NAME VARCHAR(64) NULL,
	ROLE_DESC VARCHAR(255) NULL,
	CREATEUSER VARCHAR(64) NULL,
	CREATETIME DATETIME NULL,
	PRIMARY KEY (ROLE_ID)); 
CREATE TABLE COMP_IP_ACCESS_RULES (
	RULES_ID VARCHAR(255) NOT NULL,
	START_IP VARCHAR(255) NULL,
	END_IP VARCHAR(255) NULL,
	RULES_TYPE VARCHAR(255) NULL,
	REMARK VARCHAR(255) NULL,
	MAKERS_ID VARCHAR(255) NULL,
	ADD_DATE VARCHAR(255) NULL,
	ENABLED VARCHAR(255) NULL,
	PRIMARY KEY (RULES_ID)); 
CREATE TABLE COMP_WIN7_AUTO_START (
	START_ID VARCHAR(255) NOT NULL,
	MENU_ID VARCHAR(255) NULL,
	START_DESC VARCHAR(255) NULL,
	USER_ID VARCHAR(255) NULL,
	PRIMARY KEY (START_ID)); 
CREATE TABLE COMP_WIN7_CONFIG (
	CONFIG_ID VARCHAR(255) NOT NULL,
	BG_PICTURE_PATH VARCHAR(255) NULL,
	USER_ID VARCHAR(255) NULL,
	CONFIG_DATA TEXT NULL,
	OPEN_TYPE VARCHAR(255) NULL,
	DEFAULT_MAX TINYINT NULL,
	DEFAULT_WIDTH INT NULL,
	DEFAULT_HEIGHT INT NULL,
	DESK_STYLE VARCHAR(255) NULL,
	EXT1 VARCHAR(255) NULL,
	EXT2 VARCHAR(255) NULL,
	EXT3 VARCHAR(255) NULL,
	PRIMARY KEY (CONFIG_ID)); 
CREATE TABLE COMP_WIN7_CUSTOM_PICTURES (
	CUSTOM_ID VARCHAR(255) NOT NULL,
	FILE_NAME VARCHAR(255) NULL,
	USER_ID VARCHAR(255) NULL,
	UPLOAD_TIME VARCHAR(255) NULL,
	PRIMARY KEY (CUSTOM_ID)); 
CREATE TABLE COMP_WIN7_ICONS (
	ICON_ID VARCHAR(255) NOT NULL,
	ICON_NAME VARCHAR(255) NULL,
	ICON_TEXT VARCHAR(255) NULL,
	ICON_PATH VARCHAR(255) NULL,
	ICON_TITLE VARCHAR(255) NULL,
	MENU_ID VARCHAR(255) NULL,
	ICON_INDEX VARCHAR(255) NULL,
	ICON_DESC VARCHAR(255) NULL,
	USER_ID VARCHAR(255) NULL,
	PRIMARY KEY (ICON_ID)); 
CREATE TABLE APP_APPLICATION (
	APPID NUMERIC(10,0) NOT NULL,
	APPCODE VARCHAR(32) NULL,
	APPNAME VARCHAR(50) NULL,
	APPTYPE VARCHAR(255) NULL,
	ISOPEN VARCHAR(1) NULL,
	OPENDATE DATETIME NULL,
	URL VARCHAR(256) NULL,
	APPDESC VARCHAR(512) NULL,
	MAINTENANCE NUMERIC(10,0) NULL,
	MANAROLE VARCHAR(64) NULL,
	DEMO VARCHAR(512) NULL,
	INIWP VARCHAR(1) NULL,
	INTASKCENTER VARCHAR(1) NULL,
	IPADDR VARCHAR(50) NULL,
	IPPORT VARCHAR(10) NULL,
	APP_ID VARCHAR(64) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	PROTOCOL_TYPE VARCHAR(64) NULL,
	PRIMARY KEY (APPID)); 
CREATE TABLE APP_FUNCGROUP (
	FUNCGROUPID NUMERIC(10,0) NOT NULL,
	FUNCGROUPNAME VARCHAR(40) NULL,
	GROUPLEVEL INT NULL,
	FUNCGROUPSEQ VARCHAR(256) NULL,
	ISLEAF VARCHAR(1) NULL,
	SUBCOUNT NUMERIC(10,0) NULL,
	APP_ID VARCHAR(64) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	PARENTGROUP NUMERIC(10,0) NULL,
	APPID NUMERIC(10,0) NOT NULL,
	PRIMARY KEY (FUNCGROUPID)); 
CREATE TABLE APP_FUNCRESOURCE (
	RESID NUMERIC(10,0) NOT NULL,
	RESTYPE VARCHAR(255) NULL,
	RESPATH VARCHAR(256) NULL,
	COMPACKNAME VARCHAR(40) NULL,
	RESNAME VARCHAR(40) NULL,
	APP_ID VARCHAR(64) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	FUNCCODE VARCHAR(255) NULL,
	PRIMARY KEY (RESID)); 
CREATE TABLE APP_FUNCTION (
	FUNCCODE VARCHAR(255) NOT NULL,
	FUNCNAME VARCHAR(128) NOT NULL,
	FUNCDESC VARCHAR(512) NULL,
	FUNCACTION VARCHAR(256) NULL,
	PARAINFO VARCHAR(256) NULL,
	ISCHECK VARCHAR(1) NULL,
	FUNCTYPE VARCHAR(255) DEFAULT '1' NULL,
	ISMENU VARCHAR(1) NULL,
	APP_ID VARCHAR(64) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	FUNCGROUPID NUMERIC(10,0) NULL,
	PRIMARY KEY (FUNCCODE)); 
CREATE TABLE APP_MENU (
	MENUID VARCHAR(40) NOT NULL,
	MENUNAME VARCHAR(40) NOT NULL,
	MENULABEL VARCHAR(40) NOT NULL,
	MENUCODE VARCHAR(40) NULL,
	ISLEAF VARCHAR(1) NULL,
	PARAMETER VARCHAR(256) NULL,
	UIENTRY VARCHAR(256) NULL,
	MENULEVEL SMALLINT NULL,
	ROOTID VARCHAR(40) NULL,
	DISPLAYORDER SMALLINT NULL,
	IMAGEPATH VARCHAR(100) NULL,
	EXPANDPATH VARCHAR(100) NULL,
	MENUSEQ VARCHAR(256) NULL,
	OPENMODE VARCHAR(255) NULL,
	SUBCOUNT NUMERIC(10,0) NULL,
	APPID NUMERIC(10,0) NULL,
	FUNCCODE VARCHAR(255) NULL,
	APP_ID VARCHAR(64) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	PARENTSID VARCHAR(40) NULL,
	PRIMARY KEY (MENUID)); 
CREATE TABLE CAP_PARTYAUTH (
	ROLE_TYPE VARCHAR(64) NOT NULL,
	PARTY_ID VARCHAR(64) NOT NULL,
	PARTY_TYPE VARCHAR(64) NOT NULL,
	ROLE_ID VARCHAR(64) NOT NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	CREATEUSER VARCHAR(64) NULL,
	CREATETIME DATETIME NOT NULL,
	PRIMARY KEY (ROLE_TYPE,PARTY_ID,PARTY_TYPE,ROLE_ID)); 
CREATE TABLE CAP_SSOUSER (
	OPERATOR_ID VARCHAR(64) NOT NULL,
	TENANT_ID VARCHAR(64) NULL,
	USER_ID VARCHAR(64) NOT NULL,
	PASSWORD VARCHAR(100) NULL,
	USER_NAME VARCHAR(64) NULL,
	EMAIL VARCHAR(128) NULL,
	STATUS VARCHAR(16) NULL,
	UNLOCKTIME DATETIME NULL,
	LASTLOGIN DATETIME NOT NULL,
	ERRCOUNT NUMERIC(10,0) NULL,
	MACCODE VARCHAR(255) NULL,
	IPADDRESS VARCHAR(255) NULL,
	CREATEUSER VARCHAR(64) NULL,
	CREATETIME DATETIME NOT NULL,
	PRIMARY KEY (OPERATOR_ID)); 
CREATE TABLE CAP_USER (
	OPERATOR_ID NUMERIC(18,0) NOT NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	USER_ID VARCHAR(64) NOT NULL,
	PASSWORD VARCHAR(100) NULL,
	INVALDATE DATETIME NULL,
	USER_NAME VARCHAR(64) NULL,
	AUTHMODE VARCHAR(255) NULL,
	STATUS VARCHAR(16) NULL,
	UNLOCKTIME DATETIME NOT NULL,
	MENUTYPE VARCHAR(255) NULL,
	LASTLOGIN DATETIME NOT NULL,
	ERRCOUNT NUMERIC(10,0) NULL,
	STARTDATE DATETIME NULL,
	ENDDATE DATETIME NULL,
	VALIDTIME VARCHAR(255) NULL,
	MACCODE VARCHAR(128) NULL,
	IPADDRESS VARCHAR(128) NULL,
	EMAIL VARCHAR(255) NULL,
	CREATEUSER VARCHAR(64) NULL,
	CREATETIME DATETIME NOT NULL,
	PRIMARY KEY (OPERATOR_ID)); 
CREATE TABLE ORG_DUTY (
	DUTYID NUMERIC(10,0) NOT NULL,
	DUTYCODE VARCHAR(20) NULL,
	DUTYNAME VARCHAR(30) NULL,
	PARENTDUTY NUMERIC(10,0) NULL,
	DUTYLEVEL INT NULL,
	DUTYSEQ VARCHAR(256) NULL,
	DUTYTYPE VARCHAR(255) NULL,
	ISLEAF VARCHAR(10) NULL,
	SUBCOUNT NUMERIC(10,0) NULL,
	REMARK VARCHAR(256) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	PRIMARY KEY (DUTYID)); 
CREATE TABLE ORG_EMPGROUP (
	GROUPID NUMERIC(10,0) NOT NULL,
	EMPID NUMERIC(10,0) NOT NULL,
	ISMAIN VARCHAR(1) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	PRIMARY KEY (GROUPID,EMPID)); 
CREATE TABLE ORG_EMPLOYEE (
	EMPID NUMERIC(10,0) NOT NULL,
	EMPCODE VARCHAR(30) NULL,
	OPERATORID NUMERIC(18,0) NULL,
	USERID VARCHAR(30) NULL,
	EMPNAME VARCHAR(50) NULL,
	REALNAME VARCHAR(50) NULL,
	GENDER VARCHAR(255) NULL,
	BIRTHDATE DATETIME NULL,
	POSITION NUMERIC(10,0) NULL,
	EMPSTATUS VARCHAR(255) NULL,
	CARDTYPE VARCHAR(255) NULL,
	CARDNO VARCHAR(20) NULL,
	INDATE DATETIME NULL,
	OUTDATE DATETIME NULL,
	OTEL VARCHAR(12) NULL,
	OADDRESS VARCHAR(255) NULL,
	OZIPCODE VARCHAR(10) NULL,
	OEMAIL VARCHAR(128) NULL,
	FAXNO VARCHAR(14) NULL,
	MOBILENO VARCHAR(14) NULL,
	QQ VARCHAR(16) NULL,
	HTEL VARCHAR(12) NULL,
	HADDRESS VARCHAR(128) NULL,
	HZIPCODE VARCHAR(10) NULL,
	PEMAIL VARCHAR(128) NULL,
	PARTY VARCHAR(255) NULL,
	DEGREE VARCHAR(255) NULL,
	SORTNO INT NULL,
	MAJOR NUMERIC(10,0) NULL,
	SPECIALTY VARCHAR(1024) NULL,
	WORKEXP VARCHAR(512) NULL,
	REGDATE DATETIME NULL,
	CREATETIME DATETIME NOT NULL,
	LASTMODYTIME DATETIME NOT NULL,
	ORGIDLIST VARCHAR(128) NULL,
	ORGID NUMERIC(10,0) NULL,
	REMARK VARCHAR(512) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	WEIBO VARCHAR(255) NULL,
	PRIMARY KEY (EMPID)); 
CREATE TABLE ORG_EMPORG (
	ORGID NUMERIC(10,0) NOT NULL,
	EMPID NUMERIC(10,0) NOT NULL,
	ISMAIN VARCHAR(1) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	PRIMARY KEY (ORGID,	EMPID)); 
CREATE TABLE ORG_EMPPOSITION (
	POSITIONID NUMERIC(10,0) NOT NULL,
	EMPID NUMERIC(10,0) NOT NULL,
	ISMAIN VARCHAR(1) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	PRIMARY KEY (POSITIONID,EMPID)); 
CREATE TABLE ORG_GROUP (
	GROUPID NUMERIC(10,0) NOT NULL,
	ORGID NUMERIC(10,0) NULL,
	GROUPLEVEL INT NULL,
	GROUPNAME VARCHAR(50) NULL,
	GROUPDESC VARCHAR(512) NULL,
	GROUPTYPE VARCHAR(255) NULL,
	GROUPSEQ VARCHAR(256) NULL,
	STARTDATE DATETIME NULL,
	ENDDATE DATETIME NULL,
	GROUPSTATUS VARCHAR(255) NULL,
	MANAGER VARCHAR(30) NULL,
	CREATETIME DATETIME NOT NULL,
	LASTUPDATE DATETIME NULL,
	UPDATOR NUMERIC(10,0) NULL,
	ISLEAF VARCHAR(1) NULL,
	SUBCOUNT NUMERIC(10,0) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	PARENTGROUPID NUMERIC(10,0) NULL,
	PRIMARY KEY (GROUPID)); 
CREATE TABLE ORG_GROUPPOSI (
	GROUPID NUMERIC(10,0) NOT NULL,
	POSITIONID NUMERIC(10,0) NOT NULL,
	ISMAIN VARCHAR(1) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	PRIMARY KEY (GROUPID,POSITIONID)); 
CREATE TABLE ORG_ORGANIZATION (
	ORGID NUMERIC(10,0) NOT NULL,
	ORGCODE VARCHAR(32) NOT NULL,
	ORGNAME VARCHAR(64) NULL,
	ORGLEVEL NUMERIC(2,0) DEFAULT 1 NULL,
	ORGDEGREE VARCHAR(255) NULL,
	ORGSEQ VARCHAR(512) NULL,
	ORGTYPE VARCHAR(12) NULL,
	ORGADDR VARCHAR(256) NULL,
	ZIPCODE VARCHAR(10) NULL,
	MANAPOSITION NUMERIC(10,0) NULL,
	MANAGERID NUMERIC(10,0) NULL,
	ORGMANAGER VARCHAR(128) NULL,
	LINKMAN VARCHAR(30) NULL,
	LINKTEL VARCHAR(20) NULL,
	EMAIL VARCHAR(128) NULL,
	WEBURL VARCHAR(512) NULL,
	STARTDATE DATETIME NULL,
	ENDDATE DATETIME NULL,
	STATUS VARCHAR(255) NULL,
	AREA VARCHAR(30) NULL,
	CREATETIME DATETIME NOT NULL,
	LASTUPDATE DATETIME NOT NULL,
	UPDATOR NUMERIC(10,0) NULL,
	SORTNO INT NULL,
	ISLEAF VARCHAR(1) NULL,
	SUBCOUNT NUMERIC(10,0) NULL,
	REMARK VARCHAR(512) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	PARENTORGID NUMERIC(10,0) NULL,
	PRIMARY KEY (ORGID)); 
CREATE TABLE ORG_POSITION (
	POSITIONID NUMERIC(10,0) NOT NULL,
	POSICODE VARCHAR(20) NULL,
	POSINAME VARCHAR(128) NOT NULL,
	POSILEVEL NUMERIC(2,0) NULL,
	POSITIONSEQ VARCHAR(512) NOT NULL,
	POSITYPE VARCHAR(255) NULL,
	CREATETIME DATETIME NOT NULL,
	LASTUPDATE DATETIME NOT NULL,
	UPDATOR NUMERIC(10,0) NULL,
	STARTDATE DATETIME NULL,
	ENDDATE DATETIME NULL,
	STATUS VARCHAR(255) NULL,
	ISLEAF VARCHAR(1) NULL,
	SUBCOUNT NUMERIC(10,0) NULL,
	TENANT_ID VARCHAR(64) NOT NULL,
	APP_ID VARCHAR(64) NULL,
	DUTYID NUMERIC(10,0) NULL,
	MANAPOSI NUMERIC(10,0) NULL,
	ORGID NUMERIC(10,0) NULL,
	PRIMARY KEY (POSITIONID)); 
CREATE TABLE ORG_RECENT_VISIT (
	ID VARCHAR(32) NOT NULL,
	TARGET_ID VARCHAR(32) NOT NULL,
	USERID VARCHAR(32) NOT NULL,
	FREQUENCY INT DEFAULT 1 NOT NULL,
	LASTTIME DATETIME NOT NULL,
	TARGET_TYPE VARCHAR(32) NOT NULL,
	PRIMARY KEY (ID)); 

/*==============================================================*/
/* TABLE: CAP_RULEAUTH                                          */
/*==============================================================*/
DROP TABLE CAP_RULEAUTH;
CREATE TABLE CAP_RULEAUTH  (
   RULEAUTH_ID          VARCHAR(64)                    NOT NULL,
   TENANT_ID            VARCHAR(64)                    NOT NULL,
   RULE_ID              VARCHAR(64)                    NOT NULL,
   RES_ID               VARCHAR(255)                   NOT NULL,
   RES_TYPE             VARCHAR(64)                    NOT NULL,
   RES_STATE            VARCHAR(512)                   NOT NULL,
   CREATEUSER           VARCHAR(64),
   CREATETIME           DATETIME,
   PRIMARY KEY (RULEAUTH_ID)
);
CREATE INDEX IDX_CAP_RULEAUTH_RULE_ID ON CAP_RULEAUTH(RULE_ID);

DROP TABLE CAP_RULE;
CREATE TABLE CAP_RULE (
	RULE_ID VARCHAR(64) DEFAULT '' NOT NULL,
	RULE_NAME VARCHAR(64) DEFAULT '' NOT NULL,
	TENANT_ID VARCHAR(64) DEFAULT '' NOT NULL,
	RULE_TYPE VARCHAR(64) DEFAULT '' NOT NULL,
	NAMESPACE VARCHAR(512) DEFAULT '' NOT NULL,
	RULE_EXPRESSION IMAGE NULL,
	CREATEUSER VARCHAR(64) NULL,
	CREATETIME DATETIME NULL,
	PRIMARY KEY (RULE_ID)); 