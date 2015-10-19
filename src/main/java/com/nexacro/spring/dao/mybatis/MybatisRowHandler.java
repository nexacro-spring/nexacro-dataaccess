package com.nexacro.spring.dao.mybatis;

import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import com.nexacro.spring.dao.NexacroFirstRowException;
import com.nexacro.spring.data.NexacroFirstRowHandler;
import com.nexacro.spring.data.convert.NexacroConvertException;
import com.nexacro.spring.data.support.ObjectToDataSetConverter;
import com.nexacro.xapi.data.DataSet;
import com.nexacro.xapi.tx.PlatformException;

public class MybatisRowHandler implements ResultHandler {

	private static final int DEFAULT_FIRSTROW_COUNT = 1000;
    
    private ObjectToDataSetConverter converter;
    private NexacroFirstRowHandler firstRowHandler;
    private String resultName;
    private int firstRowCount;
    
    private DataSet currentDataSet;
    private int currentCount = 0;
    
    public MybatisRowHandler(NexacroFirstRowHandler firstRowHandler, String resultName, int firstRowCount) {
        this.firstRowHandler = firstRowHandler;
        this.resultName = resultName;
        this.firstRowCount = firstRowCount;
        if(this.firstRowCount <= 0) {
            this.firstRowCount = DEFAULT_FIRSTROW_COUNT;
        }
        // TODO getting NexacroConverterFactory.getConverter();
        this.converter = new ObjectToDataSetConverter();
    }

    @Override
    public void handleResult(ResultContext context) {
    	
    	Object valueObject = context.getResultObject();
    	
        try {
            prepareDataSet(valueObject);
            addRow(valueObject);
            currentCount++;
            if(currentCount % firstRowCount == 0) {
                sendDataSet();
            }
        } catch (PlatformException e) {
            throw new NexacroFirstRowException("could not send data. e="+e.getMessage(), e);
        } catch (NexacroConvertException e) {
            throw new NexacroFirstRowException("object to dataset convert failed. e="+e.getMessage(), e);
        }
    }

    public void sendRemainData() {
    	 // send remain data..
        DataSet remainDataSet = getDataSet();
        if(remainDataSet != null && remainDataSet.getRowCount() > 0) {
            try {
                firstRowHandler.sendDataSet(remainDataSet);
            } catch (PlatformException e) {
//                throw new NexacroException("could not send remain data. query="+queryId+" e="+e.getMessage(), e);
                throw new NexacroFirstRowException("could not send remain data. e="+e.getMessage(), e);
            }
        }
    }

    
    
    private void sendDataSet() throws PlatformException {
        firstRowHandler.sendDataSet(currentDataSet);
    }

    private void addRow(Object valueObject) throws NexacroConvertException {
        if(valueObject instanceof Map) {
            converter.addRowIntoDataSet(currentDataSet, (Map) valueObject);
        } else {
            converter.addRowIntoDataSet(currentDataSet, valueObject);    
        }
        
    }

    private void prepareDataSet(Object valueObject) throws NexacroConvertException {
        if(this.currentDataSet != null) {
            return;
        }
        this.currentDataSet = new DataSet(resultName != null? resultName: "RESULT0");
        
        if(valueObject instanceof Map) {
            converter.addColumnIntoDataSet(currentDataSet, (Map) valueObject);
        } else {
            converter.addColumnIntoDataSet(currentDataSet, valueObject);    
        }
    }
    
    public DataSet getDataSet() {
        return this.currentDataSet;
    }
	
	
}
