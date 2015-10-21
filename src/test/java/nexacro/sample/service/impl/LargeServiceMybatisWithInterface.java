package nexacro.sample.service.impl;

import javax.annotation.Resource;

import nexacro.sample.service.LargeDataService;
import nexacro.sample.service.dao.jdbc.LargeDataJdbcDAO;
import nexacro.sample.service.dao.mybatis.LargeDataMybatisMapper;

import org.springframework.stereotype.Service;

import com.nexacro.spring.dao.mybatis.MybatisRowHandler;
import com.nexacro.spring.data.NexacroFirstRowHandler;

@Service("largeMybatisWithInterfaceService")
public class LargeServiceMybatisWithInterface implements LargeDataService {

    @Resource(name = "largeDataMybatisMapper")
    private LargeDataMybatisMapper largeDataMybatisMapper;

    @Resource(name = "largeDataJdbcDAO")
    private LargeDataJdbcDAO largeDataJdbcDAO;
    
    private static boolean isInited = false;
    
    @Override
    public void selectLargeData(NexacroFirstRowHandler firstRowHandler, String sendDataSetName, int firstRowCount, int initDataCount) {
        
        if(!isInited) {
            largeDataJdbcDAO.initData(initDataCount);
        }
        isInited = true;
        
    	MybatisRowHandler rowHandler = new MybatisRowHandler(firstRowHandler, sendDataSetName, firstRowCount);
    	largeDataMybatisMapper.selectLargeData(rowHandler);
		rowHandler.sendRemainData();
        
    }

}