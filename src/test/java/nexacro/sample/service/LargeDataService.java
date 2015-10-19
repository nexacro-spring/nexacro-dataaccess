package nexacro.sample.service;

import com.nexacro.spring.data.NexacroFirstRowHandler;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : LargeService.java
 * @Description : 클래스 설명을 기술합니다.
 * @author Park SeongMin
 * @since 2015. 8. 17.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015. 8. 17.     Park SeongMin     최초 생성
 * </pre>
 */

public interface LargeDataService {

    void selectLargeData(NexacroFirstRowHandler firstRowHandler, String sendDataSetName, int firstRowCount, int initDataCount);
    
}
