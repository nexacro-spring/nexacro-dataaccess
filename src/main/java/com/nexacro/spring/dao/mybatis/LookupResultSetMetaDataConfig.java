package com.nexacro.spring.dao.mybatis;

import org.apache.ibatis.mapping.MappedStatement;

class LookupResultSetMetaDataConfig {

	private final boolean isSearchMetaData;
	private final MappedStatement ms;
	
	LookupResultSetMetaDataConfig(final boolean isSearchMetaData, final MappedStatement ms) {
		this.isSearchMetaData = isSearchMetaData;
		this.ms = ms;
	}

	boolean isSearchMetaData() {
		return isSearchMetaData;
	}

	MappedStatement getMappedStatement() {
		return ms;
	}
	
}
