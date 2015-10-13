package com.nexacro.spring.dao;

import javax.sql.DataSource;

public interface DbmsProvider {
	
	Dbms getDbms(DataSource dataSource);
	
}
