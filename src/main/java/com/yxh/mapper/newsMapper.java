package com.yxh.mapper;

import java.util.List;

import com.yxh.pojo.allhave;
import com.yxh.pojo.news;

public interface newsMapper {
	public int insertnews(List<news> news);
	public List<news> selectvalue();
}
