package com.nexacro.spring.dao.dbms;

import com.nexacro.spring.dao.AbstractDbms;
import com.nexacro.spring.dao.DbColumn;
import com.nexacro.xapi.data.datatype.PlatformDataType;

/**
 * <pre>
 * Statements
 * </pre>
 * 
 * @ClassName : Mysql.java
 * @Description : 클래스 설명을 기술합니다.
 * @author Park SeongMin
 * @since 2015. 8. 12.
 * @version 1.0
 * @see
 * @Modification Information
 * 
 *               <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015. 8. 12.     Park SeongMin     최초 생성
 * </pre>
 */

public class Mysql extends AbstractDbms {

    @Override
    public void handleColumnDataType(DbColumn column) {
        if (column == null) {
            return;
        }
        if ("MEDIUMINT".equals(column.getVendorsTypeName())) {
            column.setDataType(PlatformDataType.INT);
        } else if ("DATETIME".equals(column.getVendorsTypeName())) {
            column.setDataType(PlatformDataType.DATE_TIME);
        } else if ("YEAR".equals(column.getVendorsTypeName())) {
            column.setDataType(PlatformDataType.DATE);
        }

    }

}
