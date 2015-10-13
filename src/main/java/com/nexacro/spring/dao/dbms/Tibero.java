package com.nexacro.spring.dao.dbms;

import com.nexacro.spring.dao.AbstractDbms;
import com.nexacro.spring.dao.DbColumn;
import com.nexacro.xapi.data.datatype.PlatformDataType;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : Tibero.java
 * @Description : 클래스 설명을 기술합니다.
 * @author Park SeongMin
 * @since 2015. 9. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015. 9. 23.     Park SeongMin     최초 생성
 * </pre>
 */

public class Tibero extends AbstractDbms {

    @Override
    public void handleColumnDataType(DbColumn column) {
        if (column == null) {
            return;
        }
        
        String vendorsTypeName = column.getVendorsTypeName();
        
        // 모든 numeric 타입이 number로 반환되며, value는 BigDecimal로 처리된다..
        if("NUMBER".equals(vendorsTypeName)) {
            String typeJavaClassName = column.getTypeJavaClassName();
            if("java.math.BigDecimal".equals(typeJavaClassName)) {
                column.setDataType(PlatformDataType.BIG_DECIMAL);
            }
//            int precision = column.getPrecision();
//            int scale = column.getScale();
            
        } else if ("GEOMETRY".equals(vendorsTypeName)) {
            column.setDataType(PlatformDataType.BLOB);
//        } else if ("INTERVAL YEAR TO MONTH".equals(column.getVendorsTypeName())) {
//            column.setDataType(PlatformDataType.UNDEFINED);
//        } else if ("INTERVAL DAY TO SECOND".equals(column.getVendorsTypeName())) {
//            column.setDataType(PlatformDataType.UNDEFINED);
        } else if ("ROWID".equals(vendorsTypeName)) {
            column.setDataType(PlatformDataType.UNDEFINED);
        }
        // 그 외 tibero 타입의 경우 자동 변환은 수행하지 않는다.
        
    }

}
