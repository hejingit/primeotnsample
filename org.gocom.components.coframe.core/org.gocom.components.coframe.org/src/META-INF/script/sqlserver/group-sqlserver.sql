drop table ORG_GROUP;

/*==============================================================*/
/* Table: ORG_GROUP                                             */
/*==============================================================*/
create table ORG_GROUP
(
   GROUPID              numeric(10, 0) not null,
   ORGID                numeric(10, 0) ,
   PARENTGROUPID        numeric(10, 0),
   GROUPLEVEL           int,
   GROUPNAME            varchar(50),
   GROUPDESC            varchar(512),
   GROUPTYPE            varchar(255),
   GROUPSEQ             varchar(256) ,
   STARTDATE            datetime,
   ENDDATE              datetime ,
   GROUPSTATUS          varchar(255) ,
   MANAGER              varchar(30),
   CREATETIME           timestamp ,
   LASTUPDATE           datetime,
   UPDATOR              numeric(10, 0),
   ISLEAF               char(1),
   SUBCOUNT             numeric(10, 0),
   TENANT_ID            varchar(64) not null,
   APP_ID               varchar(64)
);

alter table ORG_GROUP
   add primary key (GROUPID);

alter table ORG_GROUP add constraint FK_F_GROUP_GROUP foreign key (PARENTGROUPID)
      references ORG_GROUP (GROUPID) ON Delete NO ACTION ON UPDATE NO ACTION;

alter table ORG_GROUP add constraint FK_F_ORG_GROUP foreign key (ORGID)
      references ORG_ORGANIZATION (ORGID) ON Update CASCADE ON Delete CASCADE;


drop table ORG_EMPGROUP;

/*==============================================================*/
/* Table: ORG_EMPGROUP                                          */
/*==============================================================*/
create table ORG_EMPGROUP
(
   GROUPID              numeric(10, 0) not null ,
   EMPID                numeric(10, 0) not null,
   ISMAIN               varchar(1),
   TENANT_ID            varchar(64) not null,
   APP_ID               varchar(64)
);


alter table ORG_EMPGROUP
   add primary key (GROUPID, EMPID);

alter table ORG_EMPGROUP add constraint FK_F_EMP_GROUP foreign key (EMPID)
      references ORG_EMPLOYEE (EMPID) ON Update CASCADE ON Delete CASCADE;

alter table ORG_EMPGROUP add constraint FK_F_GROUP_EMP foreign key (GROUPID)
      references ORG_GROUP (GROUPID) ON Update CASCADE ON Delete CASCADE;


drop table ORG_GROUPPOSI;

/*==============================================================*/
/* Table: ORG_GROUPPOSI                                         */
/*==============================================================*/
create table ORG_GROUPPOSI
(
   GROUPID              numeric(10, 0) not null,
   POSITIONID           numeric(10, 0) not null,
   ISMAIN               varchar(1),
   TENANT_ID            varchar(64) not null,
   APP_ID               varchar(64)
);

alter table ORG_GROUPPOSI
   add primary key (GROUPID, POSITIONID);

alter table ORG_GROUPPOSI add constraint FK_F_GROUP_POS foreign key (GROUPID)
      references ORG_GROUP (GROUPID) ON Update CASCADE ON Delete CASCADE;

alter table ORG_GROUPPOSI add constraint FK_F_POS_GROUP foreign key (POSITIONID)
      references ORG_POSITION (POSITIONID) ON Update CASCADE ON Delete CASCADE;
