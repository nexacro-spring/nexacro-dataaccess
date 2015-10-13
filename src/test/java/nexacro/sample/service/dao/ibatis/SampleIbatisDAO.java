package nexacro.sample.service.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nexacro.sample.vo.SampleVO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * <pre>
 * Test를 위한 DAO Sample Class
 * </pre>
 * 
 * @ClassName   : SampleDAO.java
 * @Description : Sample DAO Class
 * @author djkim
 * @since 2012. 1. 31.
 * @version 1.0
 * @see
 * @Modification Information
 * 
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2012. 1. 31.     djkim     최초 생성
 * </pre>
 */
@Repository("sampleDAO")
public class SampleIbatisDAO extends SqlMapClientDaoSupport {

	// meta data 획득. proxy 처리.
	@Resource(name = "sampleDAO")
	private SampleIbatisDAO self;
	
	@Resource(name = "sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public List<SampleVO> selectSampleVoList(SampleVO searchVO) {
		return self.list("sampleDAO.selectSampleVOList", searchVO);
	}

	public List<Map> selectSampleVoMap(SampleVO searchVO) {
//		return self.list("sampleDAO.selectSampleVOMap", searchVO);
		return self.list("sampleDAO.selectSampleVOMap", searchVO);
	}
	
	public List list(String queryId, Object obj) {
		return getSqlMapClientTemplate().queryForList(queryId, obj);
	}

}
