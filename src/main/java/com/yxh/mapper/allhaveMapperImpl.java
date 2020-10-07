package com.yxh.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.yxh.pojo.Student;
import com.yxh.pojo.allhave;

public class allhaveMapperImpl implements allhaveMapper{
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public int updateallhave(allhave ah) {

		allhaveMapper mapper=sqlSession.getMapper(allhaveMapper.class);
		int a=mapper.updateallhave(ah);
		return a;
	}
	@Override
	public List<allhave> selectvalue() {
		allhaveMapper mapper=sqlSession.getMapper(allhaveMapper.class);
		return mapper.selectvalue();
		
	}
	@Override
	public List<allhave> selectvaluetoday() {
		allhaveMapper mapper=sqlSession.getMapper(allhaveMapper.class);
		return mapper.selectvaluetoday();
	}
	@Override
	public List<allhave> selectdan(allhave ah) {
		allhaveMapper mapper=sqlSession.getMapper(allhaveMapper.class);
		return mapper.selectdan(ah);
	}

}
