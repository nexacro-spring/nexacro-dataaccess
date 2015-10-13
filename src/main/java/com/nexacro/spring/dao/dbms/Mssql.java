package com.nexacro.spring.dao.dbms;

import com.nexacro.spring.dao.AbstractDbms;
import com.nexacro.spring.dao.DbColumn;
import com.nexacro.xapi.data.datatype.PlatformDataType;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : Mssql.java
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

public class Mssql extends AbstractDbms {

    @Override
    public void handleColumnDataType(DbColumn column) {

        if (column==null) { 
            return; 
        }
        
        if ("xml".equals(column.getVendorsTypeName())) {
            column.setDataType(PlatformDataType.STRING);
        }  else if ("image".equals(column.getVendorsTypeName())) {
            column.setDataType(PlatformDataType.BLOB);
        }  else if ("int".equals(column.getVendorsTypeName())) {
            column.setDataType(PlatformDataType.INT);
        } else if (column.getVendorsTypeName().endsWith("time")) { //datetime, smalldatetime
            column.setDataType(PlatformDataType.DATE_TIME);
        } else if (column.getVendorsTypeName().startsWith("time")) { //datetime2, datetimeoffset
            column.setDataType(PlatformDataType.DATE_TIME);
        } else if (column.getVendorsTypeName().endsWith("money")){ //money, smallmoney
            column.setDataType(PlatformDataType.BIG_DECIMAL);
        } else if (column.getVendorsTypeName().endsWith("text")){ //text, ntext
            column.setDataType(PlatformDataType.STRING);
        } 
        
    }


}
