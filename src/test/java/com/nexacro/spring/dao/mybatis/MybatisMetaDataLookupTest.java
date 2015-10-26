package com.nexacro.spring.dao.mybatis;

import java.util.Date;
import java.util.List;
import java.util.Map;

import nexacro.sample.service.dao.mybatis.SampleMybatisMapper;
import nexacro.sample.vo.SampleVO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexacro.spring.data.metadata.support.BeanMetaData;
import com.nexacro.spring.data.metadata.support.MapMetaData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/context-*.xml" } )
public class MybatisMetaDataLookupTest {

	/*
	 * Test case 종료시 발생하는 Warn log.
	 * mybatis의 SqlSessionTemplate이 발생시키는 로그이다.
WARN [org.springframework.beans.factory.support.DisposableBeanAdapter] Invocation of destroy method 'close' failed on bean with name 'sqlSession': java.lang.UnsupportedOperationException: Manual close is not allowed over a Spring managed SqlSession
	 */
	
	@Autowired
	private SampleMybatisMapper mapper;
	
	@Test
	public void testFirstRow() {
		
	}
	
	@Test
	public void testLookupMetaDataBean() {
		
		SampleVO sample = new SampleVO();
		sample.setTitle("searchMetaData");
		
		List<SampleVO> selectSampleVoList = mapper.getSampleVOList(sample);
		
		Assert.assertNotNull("result should not be null.", selectSampleVoList);
		
		if(!(selectSampleVoList instanceof BeanMetaData)) {
			Assert.fail("results should be BeanMetaData bean is implemented.");
		}
		
		BeanMetaData metaData = (BeanMetaData) selectSampleVoList;
		
		Object metaDataClass = metaData.getMetaData();
		
		Assert.assertSame(SampleVO.class, metaDataClass.getClass());
	}
	
	@Test
	public void testLookupMetaDataMap() {
		
		// data type 체크도 해야 한다.
		SampleVO sample = new SampleVO();
		sample.setTitle("searchMetaData");
		
		List<Map> selectSampleListMap = mapper.getSampleVOMap(sample);
		Assert.assertNotNull("result should not be null.", selectSampleListMap);
		
		if(!(selectSampleListMap instanceof MapMetaData)) {
			Assert.fail("results should be MapMetaData bean is implemented.");
		}
		
		String[] columns = {"title", "regDate", "hitCount"};
		Class[] types = {String.class, Date.class, Integer.class};
		
		MapMetaData metaData = (MapMetaData) selectSampleListMap;
		Map metaDataMap = (Map) metaData.getMetaData();
		
		int actualColumnSize = metaDataMap.keySet().size();
		Assert.assertEquals("number of result columns does not matched.",  columns.length, actualColumnSize);
		
		for(String column: columns) {
			if(!metaDataMap.containsKey(column)) {
				Assert.fail(column+" property must be exist.");
			}
		}
		
		for(int i=0; i<types.length; i++) {
			if(metaDataMap.get(columns[i]).getClass() != types[i]) {
				Assert.fail(columns[i] + " property should be "+types[i]+" implemented.");
			}
		}
		
//		Object title = metaDataMap.get(columns[0]); // defined String type
//		if(!(title instanceof String)) {
//			Assert.fail(columns[0] + " property should be Date implemented.");
//		}
//		
//		Object regDate = metaDataMap.get(columns[1]);
//		if(!(regDate instanceof Date)) {
//			Assert.fail(columns[1] + " property should be Date implemented.");
//		}
//		
//		Object hitCount = metaDataMap.get(columns[2]);
//		if(!(hitCount instanceof Integer)) {
//			Assert.fail(columns[2] + " property should be Integer implemented.");
//		}
		
	}
	
	@Test
	public void testNonCachedLookupMetaDataMap() {
		
		// data type 체크도 해야 한다.
		SampleVO sample = new SampleVO();
		sample.setTitle("searchMetaData");
		
		List<Map> selectSampleListMap = mapper.getSampleVOMap(sample);
		Assert.assertNotNull("result should not be null.", selectSampleListMap);
		
		if(!(selectSampleListMap instanceof MapMetaData)) {
			Assert.fail("results should be MapMetaData bean is implemented.");
		}
		
		// Map안의 데이터 타입을 확인하자.
		sample.setTitle("first");
		List<Map> secondSampleListMap = mapper.getSampleVOMap(sample);
		Assert.assertNotNull("result should not be null.", selectSampleListMap);
		
		int actualSize = secondSampleListMap.size();
		int expectedSize = 1;
		
		Assert.assertEquals(expectedSize, actualSize);
		
	}
	
	@Test
	public void testLookupMetaDataAutoMap() {

		SampleVO sample = new SampleVO();
		sample.setTitle("searchMetaData");
		
		List<Map> selectSampleListMap = mapper.getSampleVOAutoMap(sample);
		
		Assert.assertNotNull("result should not be null.", selectSampleListMap);
		
		if(!(selectSampleListMap instanceof MapMetaData)) {
			Assert.fail("results should be MapMetaData bean is implemented.");
		}
		
		String[] columns = {"TITLE", "REG_ID", "REG_DATE", "POST_ID", "CONTENTS", "COMMUNITY_ID", "HIT_COUNT"};
		Class[] types = {String.class, String.class, Date.class, Integer.class, String.class, String.class, Integer.class};
		
		MapMetaData metaData = (MapMetaData) selectSampleListMap;
		Map metaDataMap = (Map) metaData.getMetaData();
		
		int actualColumnSize = metaDataMap.keySet().size();
		Assert.assertEquals("number of result columns does not matched.",  columns.length, actualColumnSize);
		
		for(String column: columns) {
			if(!metaDataMap.containsKey(column)) {
				Assert.fail(column+" property must be exist.");
			}
		}
		
		for(int i=0; i<types.length; i++) {
			if(metaDataMap.get(columns[i]).getClass() != types[i]) {
				Assert.fail(columns[i] + " property should be "+types[i]+" implemented.");
			}
		}
		
	}
	
	@Test
	public void testDataType4Map() {
		
	}
	
}