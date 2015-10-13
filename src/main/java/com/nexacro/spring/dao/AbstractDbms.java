package com.nexacro.spring.dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nexacro.xapi.data.datatype.DataType;
import com.nexacro.xapi.data.datatype.DataTypeFactory;
import com.nexacro.xapi.data.datatype.PlatformDataType;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : DBMS.java
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

public abstract class AbstractDbms implements Dbms {
    
    /**
     * DBMS type handle
     * Statements
     *
     * @param column
     * @return
     */
    public abstract void handleColumnDataType(DbColumn column);
    
    public List<DbColumn> getDbColumns(ResultSetMetaData resultSetMetaData) throws SQLException {
        
        List<DbColumn> columnList = new ArrayList<DbColumn>();
        
        int columnCount = resultSetMetaData.getColumnCount();
        // rs..
        for(int i=1; i<=columnCount; i++) {
            
            String columnName = resultSetMetaData.getColumnLabel(i);
            if (columnName == null || columnName.equals("")) {
                columnName = resultSetMetaData.getColumnName(i);
            }
            
            String vendorsTypeName = resultSetMetaData.getColumnTypeName(i);
            String typeJavaClassName = resultSetMetaData.getColumnClassName(i);
            
            DataType dataType = DataTypeFactory.getSqlDataType(vendorsTypeName);
            if(dataType==null) {
                // when dbms vendor's specific column type name
                int javaSqlTypeNumber = resultSetMetaData.getColumnType(i);
                dataType = DataTypeFactory.getSqlDataType(javaSqlTypeNumber);
            }

            // find platform datatype
            dataType = DataTypeFactory.getPlatformDataType(dataType);
            if(dataType == null) {
                dataType = PlatformDataType.UNDEFINED;
            }
            
            int precision = resultSetMetaData.getPrecision(i);
            int scale = resultSetMetaData.getScale(i);
            int columnSize = resultSetMetaData.getColumnDisplaySize(i);
            DbColumn column = new DbColumn(columnName, dataType, columnSize, vendorsTypeName);
            column.setTypeJavaClassName(typeJavaClassName);
            column.setPrecision(precision);
            column.setScale(scale);
            
            // handle column for dbms
            handleColumnDataType(column);
            
            columnList.add(column);
        }
        
        return columnList;
    }
    
    protected DataType findPlatformDataType(int javaSqlTypeNumber, String vendorsTypeName) {
        
        DataType dataType = DataTypeFactory.getSqlDataType(vendorsTypeName);
        if(dataType==null) {
            // when dbms vendor's specific column type name
            dataType = DataTypeFactory.getSqlDataType(javaSqlTypeNumber);
        }

        dataType = DataTypeFactory.getPlatformDataType(dataType);
        if(dataType == null) {
            dataType = PlatformDataType.UNDEFINED;
        }
        
        return dataType;
    }
    
}
