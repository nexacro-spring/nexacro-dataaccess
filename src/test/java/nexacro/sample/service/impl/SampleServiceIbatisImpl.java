package nexacro.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nexacro.sample.service.SampleService;
import nexacro.sample.service.dao.ibatis.SampleIbatisDAO;
import nexacro.sample.vo.SampleVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Test를 위한 ServiceImpl Sample Class
 * </pre>
 * 
 * @ClassName   : SampleServiceImpl.java
 * @Description : service impl
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
@Service("sampleServiceIbatis")
public class SampleServiceIbatisImpl implements SampleService {

    @Autowired
    private SampleIbatisDAO sampleDAO;

    @Override
    public List<SampleVO> selectSampleVOList(SampleVO searchVO) {
        return sampleDAO.selectSampleVoList(searchVO);
    }

}
