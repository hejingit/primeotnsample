ALTER TABLE ORG_GROUP DROP CONSTRAINT FK_F_GROUP_GROUP;
ALTER TABLE ORG_GROUP DROP CONSTRAINT FK_F_ORG_GROUP;
ALTER TABLE ORG_GROUPPOSI DROP CONSTRAINT FK_F_GROUP_POS;
ALTER TABLE ORG_GROUPPOSI DROP CONSTRAINT FK_F_POS_GROUP;
ALTER TABLE ORG_EMPGROUP DROP CONSTRAINT FK_F_EMP_GROUP;
ALTER TABLE ORG_EMPGROUP DROP CONSTRAINT FK_F_GROUP_EMP;

drop table ORG_GROUP;

/*==============================================================*/
/* Table: ORG_GROUP                                             */
/*==============================================================*/
create table ORG_GROUP
(
   GROUPID              decimal(10) not null,
   ORGID                decimal(10),
   PARENTGROUPID        decimal(10),
   GROUPLEVEL           integer,
   GROUPNAME            varchar2(50),
   GROUPDESC            varchar2(512),
   GROUPTYPE            varchar2(255),
   GROUPSEQ             varchar2(256),
   STARTDATE            date,
   ENDDATE              date,
   GROUPSTATUS          varchar2(255),
   MANAGER              varchar2(30),
   CREATETIME           timestamp,
   LASTUPDATE           date,
   UPDATOR              decimal(10),
   ISLEAF               char(1),
   SUBCOUNT             decimal(10),
   TENANT_ID            varchar2(64) not null,
   APP_ID               varchar2(64),
   primary key (GROUPID)
);

comment on column ORG_GROUP.GROUPID is '工作组编号';
comment on column ORG_GROUP.ORGID is '隶属机构编号';
comment on column ORG_GROUP.PARENTGROUPID is '父工作组编号';
comment on column ORG_GROUP.GROUPLEVEL is '工作组层次';
comment on column ORG_GROUP.GROUPNAME is '工作组名称';
comment on column ORG_GROUP.GROUPDESC is '工作组描述';
comment on column ORG_GROUP.GROUPTYPE is '工作组类型';
comment on column ORG_GROUP.GROUPSEQ is '工作组路径序列';
comment on column ORG_GROUP.STARTDATE is '工作组有效开始日期';
comment on column ORG_GROUP.ENDDATE is '工作组有效截止日期';
comment on column ORG_GROUP.GROUPSTATUS is '工作组状态';
comment on column ORG_GROUP.MANAGER is '负责人';
comment on column ORG_GROUP.CREATETIME is '创建时间';
comment on column ORG_GROUP.LASTUPDATE is '最近更新时间';
comment on column ORG_GROUP.UPDATOR is '最近更新人员';
comment on column ORG_GROUP.ISLEAF is '是否叶子节点';
comment on column ORG_GROUP.SUBCOUNT is '子节点数';
comment on table ORG_GROUP is '工作组';


alter table ORG_GROUP add constraint FK_F_GROUP_GROUP foreign key (PARENTGROUPID)
      references ORG_GROUP (GROUPID) ON DELETE CASCADE;

alter table ORG_GROUP add constraint FK_F_ORG_GROUP foreign key (ORGID)
      references ORG_ORGANIZATION (ORGID) ON DELETE CASCADE;




drop table ORG_EMPGROUP;

/*==============================================================*/
/* Table: ORG_EMPGROUP                                          */
/*==============================================================*/
create table ORG_EMPGROUP
(
   GROUPID              decimal(10) not null ,
   EMPID                decimal(10) not null,
   ISMAIN               varchar(1),
   TENANT_ID            varchar(64) not null,
   APP_ID               varchar(64),
   primary key(GROUPID)
);

comment on column ORG_EMPGROUP.GROUPID is '工作组编号';
comment on column ORG_EMPGROUP.EMPID is '人员编号';
comment on table ORG_EMPGROUP is '人员工作组对应关系';

alter table ORG_EMPGROUP add constraint FK_F_EMP_GROUP foreign key (EMPID)
      references ORG_EMPLOYEE (EMPID) on delete cascade;

alter table ORG_EMPGROUP add constraint FK_F_GROUP_EMP foreign key (GROUPID)
      references ORG_GROUP (GROUPID) on delete cascade;


drop table ORG_GROUPPOSI;

/*==============================================================*/
/* Table: ORG_GROUPPOSI                                         */
/*==============================================================*/
create table ORG_GROUPPOSI
(
   GROUPID              decimal(10) not null,
   POSITIONID           decimal(10) not null,
   ISMAIN               varchar2(1),
   TENANT_ID            varchar2(64) not null,
   APP_ID               varchar2(64),
   primary key (GROUPID)
);
comment on column ORG_GROUPPOSI.GROUPID is '工作组编号';
comment on column ORG_GROUPPOSI.POSITIONID is '岗位编号';
comment on table ORG_GROUPPOSI is '工作组岗位列表';

alter table ORG_GROUPPOSI add constraint FK_F_GROUP_POS foreign key (GROUPID)
      references ORG_GROUP (GROUPID) on delete cascade;

alter table ORG_GROUPPOSI add constraint FK_F_POS_GROUP foreign key (POSITIONID)
      references ORG_POSITION (POSITIONID) on delete cascade;
