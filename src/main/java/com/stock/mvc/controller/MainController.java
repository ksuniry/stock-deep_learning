package com.stock.mvc.controller;

import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.mvc.service.MainService;
import com.stock.mvc.service.koreanInvestment.RankCollectionService;
import com.stock.mvc.service.krx.CompanyInfoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	private Logger logger = Logger.getLogger(MainController.class.getName());
	
	@Autowired
	MainService mainService;
	@Autowired
	CompanyInfoService companyInfoService;
	@Autowired
	RankCollectionService rankCollectionService;
	
	@GetMapping(value = "/")
	public String goThymeleafIndex(HttpServletRequest request) throws Exception {
		return "index";
	}
	
	@RequestMapping(value = "/test")
	public ResponseEntity selectSetSat(HttpServletRequest request) throws Exception {
		HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        rankCollectionService.rankCollection();
		return new ResponseEntity<>("", header, HttpStatus.OK);
	      
	}
	

	
}
