package com.yxh.mapper;

import java.util.List;

import com.yxh.pojo.Student;
import com.yxh.pojo.signin;

public interface signinMapper {
	public int insertinto(signin signin);
	public List<signin> selecttrue(signin signin);
	public List<signin> selectnottrue(signin signin);
	public List<signin> selectnottrue2(signin signin);
	public List<signin> selectnottrue3(signin signin);
	public List<signin> selectchoose(signin signin);
	public List<Student> selectstunot(signin signin);
}
