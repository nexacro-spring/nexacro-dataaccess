package com.nexacro.spring.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

/**
 *
 * 
 * Dbms 별 데이터 타입 처리를 위한 Provider 이다.
 * 아래는 설정 관련 sample 이다.
 *
 * <pre>
 * &lt;bean id="hsqlDbms" class="com.nexacro.spring.dao.dbms.Hsql" /&gt;
 * &lt;bean id="oracleDbms" class="com.nexacro.spring.dao.dbms.Oracle" /&gt;
 * &lt;bean id="mssqlDbms" class="com.nexacro.spring.dao.dbms.Mssql" /&gt;
 * &lt;bean id="mysqlDbms" class="com.nexacro.spring.dao.dbms.Mysql" /&gt;
 * &lt;bean id="tiberoDbms" class="com.nexacro.spring.dao.dbms.Tibero" /&gt;
 *		
 * &lt;bean id="dbmsProvider" class="com.nexacro.spring.dao.DbVendorsProvider"&gt;
 *   &lt;property name="dbverdors"&gt;
 *     &lt;map&gt;
 *	     &lt;entry key="HSQL Database Engine" value-ref="hsqlDbms"/&gt;
 *	     &lt;entry key="SQL Server" value-ref="mssqlDbms"/&gt;
 *	     &lt;entry key="Oracle" value-ref="oracleDbms"/&gt;
 *	   &lt;/map&gt;
 *	 &lt;/property&gt;
 * &lt;/bean&gt;
 *
 * </pre>
 * 
 * @author Park SeongMin
 *
 */
public class DbVendorsProvider implements DbmsProvider {

	/*
<bean id="hsqlDbms" class="com.nexacro.spring.dao.dbms.Hsql" />
<bean id="oracleDbms" class="com.nexacro.spring.dao.dbms.Oracle" />
<bean id="mssqlDbms" class="com.nexacro.spring.dao.dbms.Mssql" />
<bean id="mysqlDbms" class="com.nexacro.spring.dao.dbms.Mysql" />
<bean id="tiberoDbms" class="com.nexacro.spring.dao.dbms.Tibero" />

<bean id="dbmsProvider" class="com.nexacro.spring.dao.DbVendorsProvider">
    <property name="dbverdors">
        <map>
     		<entry key="SQL Server" value-ref="mssqlDbms"/>
            <entry key="Oracle" value-ref="oracleDbms"/>
        </map>
    </property>
</bean>
	*/
	
	// dbms 이름, dbms에 맞는 Dbms class 이름을 list 형태로 받아야 한다.

	private static final Log log = LogFactory.getLog(BaseExecutor.class);

	private Map<String, Dbms> dbverdors;
	
	public Map<String, Dbms> getDbverdors() {
		return dbverdors;
	}

	public void setDbverdors(Map<String, Dbms> dbverdors) {
		this.dbverdors = dbverdors;
	}

	public Dbms getDbms(DataSource dataSource) {
		if (dataSource == null)
			throw new NullPointerException("dataSource cannot be null");
		try {
			String productName = getDatabaseProductName(dataSource);
			if (this.dbverdors != null) {
				Dbms dbms = dbverdors.get(productName);
//				for (Map.Entry<Object, Object> property : properties.entrySet()) {
//					if (productName.contains((String) property.getKey())) {
//						return (String) property.getValue();
//					}
//				}
				return dbms; // no match, return null
			}
		} catch (Exception e) {
			log.error("Could not get a Dbms from dataSource", e);
		}
		
		return null;
	}

	private String getDatabaseProductName(DataSource dataSource)
			throws SQLException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			DatabaseMetaData metaData = con.getMetaData();
			return metaData.getDatabaseProductName();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// ignored
				}
			}
		}
	}

}
