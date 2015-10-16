package nexacro.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nexacro.sample.service.SampleService;
import nexacro.sample.service.dao.ibatis.SampleIbatisDAO;
import nexacro.sample.service.dao.mybatis.SampleMybatisMapper;
import nexacro.sample.vo.SampleVO;

@Service("sampleServiceMybatis")
public class SampleServiceMybatisImpl implements SampleService {

    @Autowired
    private SampleMybatisMapper sampleMapper;

    @Override
    public List<SampleVO> selectSampleVOList(SampleVO searchVO) {
        return sampleMapper.getSampleVOList(searchVO);
    }

}
