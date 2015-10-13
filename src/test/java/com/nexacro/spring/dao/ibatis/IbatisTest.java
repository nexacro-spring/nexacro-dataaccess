package com.nexacro.spring.dao.ibatis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nexacro.sample.service.dao.ibatis.SampleIbatisDAO;
import nexacro.sample.vo.SampleVO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexacro.spring.data.metadata.NexacroMetaData;
import com.nexacro.spring.data.metadata.support.BeanMetaData;
import com.nexacro.spring.data.metadata.support.MapMetaData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/context-*.xml" } )
public class IbatisTest {

	@Autowired
	private SampleIbatisDAO dao;
	
	@Test
	public void testFirstRow() {
		
	}
	
	@Test
	public void testMetaDataBean() {
		
		SampleVO sample = new SampleVO();
		sample.setTitle("invalid");
		
		List<SampleVO> selectSampleVoList = dao.selectSampleVoList(sample);
		
		Assert.assertNotNull("result should not be null.", selectSampleVoList);
		
		if(!(selectSampleVoList instanceof NexacroMetaData)) {
			Assert.fail("query data is to look up the metadata information of zero.");
		}
		
		if(!(selectSampleVoList instanceof BeanMetaData)) {
			Assert.fail("results should be BeanMetaData bean is implemented.");
		}
		
		BeanMetaData metaData = (BeanMetaData) selectSampleVoList;
		
		Object metaDataClass = metaData.getMetaData();
		
		Assert.assertSame(SampleVO.class, metaDataClass.getClass());
	}
	
	@Test
	public void testMetaDataMap() {
		
		// data type 체크도 해야 한다.
		
		SampleVO sample = new SampleVO();
		sample.setTitle("invalid");
		
		List<Map> selectSampleVoList = dao.selectSampleVoMap(sample);
		
		Assert.assertNotNull("result should not be null.", selectSampleVoList);
		
		if(!(selectSampleVoList instanceof NexacroMetaData)) {
			Assert.fail("query data is to look up the metadata information of zero.");
		}
		
		if(!(selectSampleVoList instanceof MapMetaData)) {
			Assert.fail("results should be MapMetaData bean is implemented.");
		}
		
		MapMetaData metaData = (MapMetaData) selectSampleVoList;
		
		Map metaDataMap = (Map) metaData.getMetaData();
		
		// Map안의 데이터 타입을 확인하자.
		
	}
	
	
}
