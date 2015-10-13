package com.nexacro.spring.dao.mybatis;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;


@Intercepts({ @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class NexacroMybatisMetaDataProvider implements Interceptor {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		Object proceed = invocation.proceed();
		
		if(proceed instanceof List) {
			
			List list = (List) proceed;
			if(list.size() == 0) {
				
				Executor executor = (Executor) invocation.getTarget();
				
				Object[]        args     = invocation.getArgs();
				MappedStatement ms       = (MappedStatement)args[0];
				Object          param    = (Object)args[1];
				BoundSql        boundSql = ms.getBoundSql(param);
				System.out.println("====================================");
				System.out.println(invocation.getMethod().getName());
				System.out.println("====================================");
				System.out.println(ms.getId());
				System.out.println("====================================");
				System.out.println(boundSql.getSql());
				System.out.println("====================================");
				System.out.println(param);
				System.out.println("====================================");
				
				
				Transaction transaction = executor.getTransaction();
				
				Connection connection = transaction.getConnection();
				
				List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
				for(ParameterMapping mapping: parameterMappings) {
					
					// statement에 값을 설정한다.
					
					System.out.println(mapping.getMode());
					System.out.println(mapping.getProperty());
				}
				
				List<ResultMap> resultMaps = ms.getResultMaps();
				for(ResultMap resultMap: resultMaps) {
					System.out.println(resultMap.getType());
				}
				
				
				proceed = list;
			}
			
		}
		
		return proceed;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}
	
}
