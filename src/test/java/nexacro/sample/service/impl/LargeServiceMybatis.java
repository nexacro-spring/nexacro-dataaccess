package nexacro.sample.service.impl;

import javax.annotation.Resource;

import nexacro.sample.service.LargeDataService;
import nexacro.sample.service.dao.jdbc.LargeDataJdbcDAO;
import nexacro.sample.service.dao.mybatis.LargeDataMybatisDAO;

import org.springframework.stereotype.Service;

import com.nexacro.spring.data.NexacroFirstRowHandler;

/**
 * <pre>
 * Mybatis의 경우 SqlSessionTemplate에서 transaction을 명시적으로 관리하게 되어 있다.
 * 그래서 Transaction을 처리 하지 않도록 sufix로 imple을 삭제 한다.
 * </pre>
 *
 * @author Park SeongMin
 * @since 08.17.2015
 * @version 1.0
 * @see
 */
@Service("largeDataMybatisService")
public class LargeServiceMybatis implements LargeDataService {

    @Resource(name = "largeDataMybatisDAO")
    private LargeDataMybatisDAO largeDataMybatisDAO;

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
        largeDataMybatisDAO.selectLargeData(firstRowHandler, sendDataSetName, firstRowCount);
        
    }

}
