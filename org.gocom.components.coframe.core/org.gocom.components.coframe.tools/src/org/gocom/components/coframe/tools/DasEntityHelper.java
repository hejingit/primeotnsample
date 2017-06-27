package org.gocom.components.coframe.tools;

import com.eos.spring.DASTemplate;
import com.eos.system.annotation.Bizlet;
import com.primeton.cap.spi.DASTemplateFactory;
import commonj.sdo.DataObject;

/**
 * 数据实体辅助类
 * 
 * @author Administrator
 *
 */
@Bizlet("数据实体辅助类")
public class DasEntityHelper {
	
	public static DASTemplate getDASTemplate() {
		return DASTemplateFactory.getSystemDASTemplate();
	}
	
	@Bizlet("getPrimaryKey")
	public static void getPrimaryKey(DataObject entity) {
		getDASTemplate().getPrimaryKey(entity);
	}
	
	@Bizlet("count")
	public static int count(DataObject criteria) {
		return getDASTemplate().count(criteria);
	}
	
	@Bizlet("insertEntity")
	public static void insertEntity(DataObject entity) {
		getDASTemplate().insertEntity(entity);
	}
	
	@Bizlet("insertEntityBatch")
	public static void insertEntityBatch(DataObject[] entities) {
		getDASTemplate().insertEntityBatch(entities);
	}
	
	@Bizlet("updateEntity")
	public static void updateEntity(DataObject entity) {
		getDASTemplate().updateEntity(entity);
	}
	
	@Bizlet("updateEntityByCriteriaEntity")
	public static int updateEntityByCriteriaEntity(DataObject updateValue, DataObject criteria) {
		return getDASTemplate().updateEntityByCriteriaEntity(updateValue, criteria);
	}
	
	@Bizlet("saveEntity")
	public static void saveEntity(DataObject entity) {
		getDASTemplate().saveEntity(entity);
	}
	
	@Bizlet("saveEntities")
	public static void saveEntities(DataObject[] entities) {
		getDASTemplate().saveEntities(entities);
	}
	
	@Bizlet("deleteEntity")
	public static void deleteEntity(DataObject entity) {
		getDASTemplate().deleteEntity(entity);
	}
	
	@Bizlet("deleteEntityBatch")
	public static void deleteEntityBatch(DataObject[] entities) {
		getDASTemplate().deleteEntityBatch(entities);
	}
	
	@Bizlet("deleteByTemplate")
	public static int deleteByTemplate(DataObject template) {
		return getDASTemplate().deleteByTemplate(template);
	}
	
	@Bizlet("deleteByCriteriaEntity")
	public static int deleteByCriteriaEntity(DataObject criteria) {
		return getDASTemplate().deleteByCriteriaEntity(criteria);
	}
	
	@Bizlet("queryByNamedSql")
	public static Object[] queryByNamedSql(String nameSqlId, Object parameterObject) {
		return getDASTemplate().queryByNamedSql(Object.class, nameSqlId, parameterObject);
	}
	
	@Bizlet("queryEntitiesByTemplate")
	public static DataObject[] queryEntitiesByTemplate(DataObject template) {
		return getDASTemplate().queryEntitiesByTemplate(DataObject.class, template);
	}
	
	@Bizlet("queryEntitiesByCriteriaEntity")
	public static DataObject[] queryEntitiesByCriteriaEntity(DataObject criteria) {
		return getDASTemplate().queryEntitiesByCriteriaEntity(DataObject.class, criteria);
	}
	
	@Bizlet("queryEntitiesByTemplateWithPage")
	public static DataObject[] queryEntitiesByTemplateWithPage(DataObject template, DataObject pagecond) {
		return getDASTemplate().queryEntitiesByTemplateWithPage(DataObject.class, template, pagecond);
	}
	
	@Bizlet("queryEntitiesByCriteriaEntityWithPage")
	public static DataObject[] queryEntitiesByCriteriaEntityWithPage(DataObject criteria, DataObject pagecond) {
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(DataObject.class, criteria, pagecond);
	}
	
	@Bizlet("expandEntitiesRelation")
	public static void expandEntitiesRelation(DataObject[] dataObjects, String property) {
		getDASTemplate().expandEntitiesRelation(dataObjects, property);
	}
	
	@Bizlet("expandEntityByTemplate")
	public static int expandEntityByTemplate(DataObject template, DataObject entity) {
		return getDASTemplate().expandEntityByTemplate(template, entity);
	}
	
	@Bizlet("expandEntity")
	public static int expandEntity(DataObject entity) {
		return getDASTemplate().expandEntity(entity);
	}
}
