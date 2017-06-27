alter table ORG_GROUP
   drop primary key;

drop table if exists ORG_GROUP;

/*==============================================================*/
/* Table: ORG_GROUP                                             */
/*==============================================================*/
create table ORG_GROUP
(
   GROUPID              decimal(10) not null comment '工作组编号',
   ORGID                decimal(10) comment '隶属机构编号',
   PARENTGROUPID        decimal(10) comment '父工作组编号',
   GROUPLEVEL           int comment '工作组层次',
   GROUPNAME            varchar(50) comment '工作组名称',
   GROUPDESC            varchar(512) comment '工作组描述',
   GROUPTYPE            varchar(255) comment '工作组类型',
   GROUPSEQ             varchar(256) comment '工作组路径序列',
   STARTDATE            datetime comment '工作组有效开始日期',
   ENDDATE              datetime comment '工作组有效截止日期',
   GROUPSTATUS          varchar(255) comment '工作组状态',
   MANAGER              varchar(30) comment '负责人',
   CREATETIME           timestamp comment '创建时间',
   LASTUPDATE           datetime comment '最近更新时间',
   UPDATOR              decimal(10) comment '最近更新人员',
   ISLEAF               char(1) comment '是否叶子节点',
   SUBCOUNT             decimal(10) comment '子节点数',
   TENANT_ID            varchar(64) not null,
   APP_ID               varchar(64)
);

alter table ORG_GROUP comment '工作组';

alter table ORG_GROUP
   add primary key (GROUPID);

alter table ORG_GROUP add constraint FK_F_GROUP_GROUP foreign key (PARENTGROUPID)
      references ORG_GROUP (GROUPID) on delete restrict on update restrict;

alter table ORG_GROUP add constraint FK_F_ORG_GROUP foreign key (ORGID)
      references ORG_ORGANIZATION (ORGID) on delete restrict on update restrict;



alter table ORG_EMPGROUP
   drop primary key;

drop table if exists ORG_EMPGROUP;

/*==============================================================*/
/* Table: ORG_EMPGROUP                                          */
/*==============================================================*/
create table ORG_EMPGROUP
(
   GROUPID              decimal(10) not null comment '工作组编号',
   EMPID                decimal(10) not null comment '人员编号',
   ISMAIN               varchar(1),
   TENANT_ID            varchar(64) not null,
   APP_ID               varchar(64)
);

alter table ORG_EMPGROUP comment '人员工作组对应关系';

alter table ORG_EMPGROUP
   add primary key (GROUPID, EMPID);

alter table ORG_EMPGROUP add constraint FK_F_EMP_GROUP foreign key (EMPID)
      references ORG_EMPLOYEE (EMPID) on delete restrict on update restrict;

alter table ORG_EMPGROUP add constraint FK_F_GROUP_EMP foreign key (GROUPID)
      references ORG_GROUP (GROUPID) on delete restrict on update restrict;


alter table ORG_GROUPPOSI
   drop primary key;

drop table if exists ORG_GROUPPOSI;

/*==============================================================*/
/* Table: ORG_GROUPPOSI                                         */
/*==============================================================*/
create table ORG_GROUPPOSI
(
   GROUPID              decimal(10) not null comment '工作组编号',
   POSITIONID           decimal(10) not null comment '岗位编号',
   ISMAIN               varchar(1),
   TENANT_ID            varchar(64) not null,
   APP_ID               varchar(64)
);

alter table ORG_GROUPPOSI comment '工作组岗位列表';

alter table ORG_GROUPPOSI
   add primary key (GROUPID, POSITIONID);

alter table ORG_GROUPPOSI add constraint FK_F_GROUP_POS foreign key (GROUPID)
      references ORG_GROUP (GROUPID) on delete restrict on update restrict;

alter table ORG_GROUPPOSI add constraint FK_F_POS_GROUP foreign key (POSITIONID)
      references ORG_POSITION (POSITIONID) on delete restrict on update restrict;
