package nexacro.sample.service.impl;

import javax.annotation.Resource;

import nexacro.sample.service.LargeDataService;
import nexacro.sample.service.dao.ibatis.LargeDataIbatisDAO;
import nexacro.sample.service.dao.jdbc.LargeDataJdbcDAO;

import org.springframework.stereotype.Service;

import com.nexacro.spring.data.NexacroFirstRowHandler;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : LargeServiceImpl.java
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
@Service("largeDataService")
public class LargeServiceImpl implements LargeDataService {

    @Resource(name = "largeDataDAO")
    private LargeDataIbatisDAO largeDataDAO;
    
    @Resource(name = "largeDataJdbcDAO")
    private LargeDataJdbcDAO largeDataJdbcDAO;
    
    private static boolean isInited = false;
    
    @Override
    public void selectLargeData(NexacroFirstRowHandler firstRowHandler, String sendDataSetName, int firstRowCount, int initDataCount) {
        
        if(!isInited) {
//            largeDataDAO.initData(initDataCount);
            largeDataJdbcDAO.initData(initDataCount);
        }
        isInited = true;
        largeDataDAO.selectLargeData(firstRowHandler, sendDataSetName, firstRowCount);
        
    }

    public void selectJdbcLargeData(NexacroFirstRowHandler firstRowHandler, String sendDataSetName, int firstRowCount, int initDataCount) {
        
        if(!isInited) {
            largeDataJdbcDAO.initData(initDataCount);
        }
        isInited = true;
        largeDataJdbcDAO.selectLargeData(firstRowHandler, sendDataSetName, firstRowCount);
        
    }

}
