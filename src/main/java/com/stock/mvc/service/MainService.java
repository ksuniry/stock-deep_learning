package com.stock.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stock.mvc.dao.MainDao;


@Service
public class MainService {
	
	@Autowired
	MainDao mainDao;
	
	public List test() {
		try {
			return mainDao.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
