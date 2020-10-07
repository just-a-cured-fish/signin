package com.yxh.mapper;

import java.util.List;

import com.yxh.pojo.Student;
import com.yxh.pojo.allhave;

public interface allhaveMapper {
	public int updateallhave(allhave ah);
	public List<allhave> selectvalue();
	public List<allhave> selectdan(allhave ah);
	public List<allhave> selectvaluetoday();
}
