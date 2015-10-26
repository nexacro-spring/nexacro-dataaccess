package com.nexacro.spring.dao.ibatis;

import java.util.Date;
import java.util.List;
import java.util.Map;

import nexacro.sample.service.dao.ibatis.SampleIbatisDAO;
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
public class IbatisMetaDataLookupTest {

	@Autowired
	private SampleIbatisDAO dao;
	
	@Test
	public void testLookupMetaDataBean() {
		
		SampleVO sample = new SampleVO();
		sample.setTitle("searchMetaData");
		
		List<SampleVO> selectSampleVoList = dao.selectSampleVoList(sample);
		
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
		
		List<Map> selectSampleListMap = dao.selectSampleVoMap(sample);
		
		Assert.assertNotNull("result should not be null.", selectSampleListMap);
		
		if(!(selectSampleListMap instanceof MapMetaData)) {
			Assert.fail("results should be MapMetaData bean is implemented.");
		}
		
		String[] columns = {"title", "regId", "regDate", "postId", "contents", "communityId", "hitCount"};
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
		
//		Object title = metaDataMap.get(columns[0]); // defined String type
//		if(!(title instanceof String)) {
//			Assert.fail(columns[0] + " property should be Date implemented.");
//		}
//		Object regId = metaDataMap.get(columns[1]);
//		if(!(regId instanceof Date)) {
//			Assert.fail(columns[1] + " property should be Date implemented.");
//		}
//		Object regDate = metaDataMap.get(columns[2]);
//		if(!(regId instanceof Date)) {
//			Assert.fail(columns[2] + " property should be Date implemented.");
//		}
//		Object postId = metaDataMap.get(columns[3]);
//		if(!(regId instanceof Date)) {
//			Assert.fail(columns[3] + " property should be Date implemented.");
//		}
//		Object contents = metaDataMap.get(columns[1]);
//		if(!(regId instanceof Date)) {
//			Assert.fail(columns[1] + " property should be Date implemented.");
//		}
//		Object communityId = metaDataMap.get(columns[1]);
//		if(!(regDate instanceof Date)) {
//			Assert.fail(columns[1] + " property should be Date implemented.");
//		}
//		Object hitCount = metaDataMap.get(columns[2]);
//		if(!(hitCount instanceof Integer)) {
//			Assert.fail(columns[2] + " property should be Integer implemented.");
//		}
		
	}
	
	
}
