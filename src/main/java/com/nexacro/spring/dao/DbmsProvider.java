package com.nexacro.spring.dao;

import java.sql.Connection;

import javax.sql.DataSource;

public interface DbmsProvider {
	
	Dbms getDbms(DataSource dataSource);
	
	Dbms getDbms(Connection conn);
	
}
