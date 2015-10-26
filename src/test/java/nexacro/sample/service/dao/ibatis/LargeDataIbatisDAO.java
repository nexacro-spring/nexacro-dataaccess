package nexacro.sample.service.dao.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nexacro.spring.dao.ibatis.SqlMapClientRowHandler;
import com.nexacro.spring.data.NexacroFirstRowHandler;

/**
 *
 * @author Park SeongMin
 * @since 08.17.2015
 * @version 1.0
 * @see
 */
@Repository("largeDataIbatisDAO")
public class LargeDataIbatisDAO extends SqlMapClientDaoSupport {

	@Resource(name = "sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
    public void initData(int initDataCount) throws SQLException {
        List<String> batchArgs = new ArrayList<String>();
        for(int i=0; i<initDataCount; i++) {
            String value = "name-" + i;
            batchArgs.add(value);
        }

        // run batch..
        batch("largeDataDAO.initData", batchArgs);
        
    }
    
    public void selectLargeData(NexacroFirstRowHandler firstRowHandler, String sendName, int firstRowCount) {
        
        SqlMapClientRowHandler rowHandler = new SqlMapClientRowHandler(firstRowHandler, sendName, firstRowCount);
        getSqlMapClientTemplate().queryWithRowHandler("largeDataDAO.selectLargeData", null, rowHandler);
        
        // send remain data..
        rowHandler.sendRemainData();
    }
    
    private int batch(String queryId, List batchArgs) throws SQLException {
    	SqlMapClient sqlMapClient = getSqlMapClient();
    	sqlMapClient.startBatch();
    	for(Object args: batchArgs) {
    		sqlMapClient.insert(queryId, args);
    	}
		return sqlMapClient.executeBatch();
    }
    
}
