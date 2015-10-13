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
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : LargeDAO.java
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
@Repository("largeDataDAO")
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
