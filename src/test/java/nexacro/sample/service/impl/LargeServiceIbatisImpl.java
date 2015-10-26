package nexacro.sample.service.impl;

import javax.annotation.Resource;

import nexacro.sample.service.LargeDataService;
import nexacro.sample.service.dao.ibatis.LargeDataIbatisDAO;
import nexacro.sample.service.dao.jdbc.LargeDataJdbcDAO;

import org.springframework.stereotype.Service;

import com.nexacro.spring.data.NexacroFirstRowHandler;

/**
 * 
 * @author Park SeongMin
 * @since 08.17.2015
 * @version 1.0
 * @see
 */
@Service("largeDataIbatisService")
public class LargeServiceIbatisImpl implements LargeDataService {

    @Resource(name = "largeDataIbatisDAO")
    private LargeDataIbatisDAO largeDataIbatisDAO;

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
        largeDataIbatisDAO.selectLargeData(firstRowHandler, sendDataSetName, firstRowCount);
        
    }

}
