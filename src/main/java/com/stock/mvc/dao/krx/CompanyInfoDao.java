package com.stock.mvc.dao.krx;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stock.mvc.vo.krx.CompanyBagicInfoVO;


@Service
public class CompanyInfoDao {
	
	@Qualifier("sqlSession")
	@Autowired(required=true)
	SqlSessionTemplate sqlSession;
	
	String name_space = "com.stock.mvc.dao.krx.CompanyInfoDao.";

	public List<CompanyBagicInfoVO>  companyBagicInfoList(){		
		return sqlSession.selectList(name_space.concat("companyBagicInfoList"));
	}
	
	public List<CompanyBagicInfoVO>  companyBagicInfoHoga20ListByisuSrtCdList(List<String> isuSrtCdArray){		
		return sqlSession.selectList(name_space.concat("companyBagicInfoHoga20ListByisuSrtCdList"), isuSrtCdArray);
	}
	
	public List<CompanyBagicInfoVO>  companyBagicInfoHoga20List(){		
		return sqlSession.selectList(name_space.concat("companyBagicInfoHoga20List"));
	}
	
	public void insertCompanyBagicInfoList(List<CompanyBagicInfoVO> list){
		sqlSession.insert(name_space.concat("insertCompanyBagicInfoList"), list);
	}
	
	public void updateUseYnCompanyBagicInfoList(List<CompanyBagicInfoVO> list){
		sqlSession.update(name_space.concat("updateUseYnCompanyBagicInfoList"), list);
	}
	
	
}
