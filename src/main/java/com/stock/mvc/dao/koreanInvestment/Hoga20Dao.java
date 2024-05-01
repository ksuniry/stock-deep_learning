package com.stock.mvc.dao.koreanInvestment;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stock.mvc.vo.koreanInvestment.Hoga20VO;
import com.stock.mvc.vo.krx.CompanyBagicInfoVO;

@Service
public class Hoga20Dao {
	
	@Qualifier("sqlSession")
	@Autowired(required=true)
	SqlSessionTemplate sqlSession;
	
	String name_space = "com.stock.mvc.dao.koreanInvestment.Hoga20Dao";

	public Hoga20VO selectHoga20TableCheck(CompanyBagicInfoVO companyBagicInfoVO){
		return sqlSession.selectOne("com.stock.mvc.dao.koreanInvestment.Hoga20Dao.selectHoga20TableCheck", companyBagicInfoVO);
	}
	
	public Hoga20VO createhoga20Table(String createSQL){
		return sqlSession.selectOne("com.stock.mvc.dao.koreanInvestment.Hoga20Dao.createHoga20Table", createSQL);
	}
	
	public void insertHoga20(Hoga20VO hoga20VO){
		 sqlSession.insert("com.stock.mvc.dao.koreanInvestment.Hoga20Dao.insertHoga20", hoga20VO);
	}
	
}
