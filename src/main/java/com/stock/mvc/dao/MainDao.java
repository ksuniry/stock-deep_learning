package com.stock.mvc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class MainDao {
	
	@Qualifier("sqlSession")
	@Autowired(required=true)
	SqlSessionTemplate sqlSession;
	
	String name_space = "com.stock.mvc.MainDao";

	public List test(){
			
		return sqlSession.selectList("com.stock.mvc.MainDao.commpanyList");
	}
	
}
