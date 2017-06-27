package org.gocom.components.coframe.init;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.eos.common.connection.SimpleConnectionHelper;
import com.eos.das.sql.INamedSqlSession;
import com.eos.das.sql.NamedSqlSessionFactory;
import com.eos.spring.DASDataSource;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
import com.primeton.ext.infra.connection.ConnectionWrapper;
import commonj.sdo.DataObject;

/**
 * 用户登录初始化查询用户及权限信息
 *  
 * @author linfeng (mailto:linfeng@primeton.com)
 * 
 */
public class InitUserObjectService extends CoframeDASDaoSupport {

	public static String SPRING_BEAN_NAME = "InitUserObjectServiceBean";
	/**
	 * 查询用户信息
	 * @param userid
	 * @return 用户属于多机构时，会返回多条数据
	 */
	public DataObject[] getUserBaseInfo(String userId) {
		DataObject[] datas = getDASTemplate().queryByNamedSql(DataObject.class, 
				"org.gocom.components.coframe.auth.coframeUserInit.select_userbaseinfo", userId);
		return datas;
	}
	
	/**
	 * 查询用户的权限
	 * @param userid
	 * @return 返回多条数据
	 */
	public DataObject[] getUserPartyAuth(String userid,String empid) {
		Map<String,String> params=new HashMap<String, String>();
		params.put("userid", userid);
		params.put("empid", empid);
		
		Connection conn = null;
		INamedSqlSession session = null;
		try {
			conn = getConnection(getDASTemplate().getDataSource());
			String databaseProductName = getProductName(conn);
			session = NamedSqlSessionFactory.createSQLMapSession(conn);
			String nameSqlId = null;
			if (databaseProductName != null && databaseProductName.toLowerCase().indexOf("db2") != -1) {
				nameSqlId = "org.gocom.components.coframe.auth.coframeUserInit.select_partyauth_db2";
			} else {
				nameSqlId = "org.gocom.components.coframe.auth.coframeUserInit.select_partyauth";
			}
			List<?> list = session.queryForList(nameSqlId, params);
			return list.toArray(new DataObject[0]);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Throwable e) {

				}
			}
			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Throwable e) {

			}
		}
	}
	
	private static Connection getConnection(DataSource dataSource) {
		if(dataSource instanceof DASDataSource){
			String dsName = ((DASDataSource)dataSource).getDataSourceName();
			return getDefaultConnection(dsName);
		}else{
			try {
				return DataSourceUtils.getConnection(dataSource);
			} catch (Exception e) {
				return null;
			}
		}

	}
	
	private static Connection getDefaultConnection(String dsName) {
		String value = dsName;
		if (value == null || value.trim().equals(""))
			value = "default";

		return SimpleConnectionHelper.getConnection(dsName);
	}
	
	private static String getProductName(Connection conn) {
		try {
			return ((ConnectionWrapper) conn).getDatabaseProductName();
		} catch (Exception e) {
			try {
				return conn.getMetaData().getDatabaseProductName();
			} catch (Exception e1) {
				return null;
			}
		}
	}
	

}
