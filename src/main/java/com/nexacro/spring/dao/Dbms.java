package com.nexacro.spring.dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : Dbms.java
 * @Description : 클래스 설명을 기술합니다.
 * @author Park SeongMin
 * @since 2015. 8. 7.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015. 8. 7.     Park SeongMin     최초 생성
 * </pre>
 */

public interface Dbms {

    List<DbColumn> getDbColumns(ResultSetMetaData resultSetMetaData) throws SQLException;
    
}
