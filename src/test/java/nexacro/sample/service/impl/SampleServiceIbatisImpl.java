package nexacro.sample.service.impl;

import java.util.List;

import nexacro.sample.service.SampleService;
import nexacro.sample.service.dao.ibatis.SampleIbatisDAO;
import nexacro.sample.vo.SampleVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Test를 위한 ServiceImpl Sample Class
 * 
 * @author Park Seongmin
 * @since 08.23.2015
 * @version 1.0
 * @see
 */
@Service("sampleServiceIbatis")
public class SampleServiceIbatisImpl implements SampleService {

    @Autowired
    private SampleIbatisDAO sampleDAO;

    @Override
    public List<SampleVO> selectSampleVOList(SampleVO searchVO) {
        return sampleDAO.selectSampleVoList(searchVO);
    }

}
