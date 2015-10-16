package nexacro.sample.service.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import nexacro.sample.vo.SampleVO;

@Repository("sampleMibatisMapper")
public interface SampleMybatisMapper {

	public List<SampleVO> getSampleVOList(SampleVO searchVO);
	
	public List<Map> getSampleVOMap(SampleVO searchVO);
	
	public List<Map> getSampleVOAutoMap(SampleVO searchVO);
	
}
