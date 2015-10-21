package nexacro.sample.service.dao.mybatis;

import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;

@Repository("largeDataMybatisMapper")
public interface LargeDataMybatisMapper {

	public void selectLargeData(ResultHandler resultHandler);
}
