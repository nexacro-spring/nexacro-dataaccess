package nexacro.sample.service.dao.mybatis;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nexacro.spring.dao.mybatis.MybatisRowHandler;
import com.nexacro.spring.data.NexacroFirstRowHandler;

@Repository("largeDataMybatisMapper")
public class LargeDataMybatisMapper extends SqlSessionDaoSupport {

    @Resource(name="sqlSessionFactory")
    void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        setSqlSessionFactory(sqlSessionFactory);
    }
	
	public void selectLargeData(NexacroFirstRowHandler firstRowHandler, String sendDataSetName, int firstRowCount) {
		
		SqlSession sqlSession = getSqlSession();
		
		String statement = "nexacro.sample.service.dao.mybatis.LargeDataMybatisMapper.selectLargeData";
		Object parameter = null;
		
		MybatisRowHandler rowHandler = new MybatisRowHandler(firstRowHandler, sendDataSetName, firstRowCount);
		sqlSession.select(statement, parameter, rowHandler);
		rowHandler.sendRemainData();
	}

}
