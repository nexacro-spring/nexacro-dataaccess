package nexacro.sample.service;

import java.util.List;

import nexacro.sample.vo.SampleVO;

public interface SampleService {

	List<SampleVO> selectSampleVOList(SampleVO searchVO);
	
}
