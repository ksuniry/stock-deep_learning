package com.stock.mvc.controller.admin;

import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.mvc.service.MainService;
import com.stock.mvc.vo.admin.AdminUtilVO;
import com.stock.utils.Aes;
import com.stock.utils.KoreaInvestmentApiLink;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private Logger logger = Logger.getLogger(AdminController.class.getName());
	
	@Autowired
	MainService mainService;
	
	@GetMapping(value = "")
	public String viewAdminPage(HttpServletRequest request) throws Exception {
		return "index";
	}
	
	@GetMapping(value = "/util")
	public String viewAdminUtil(HttpServletRequest request) throws Exception {
		//String result = KoreaInvestMentApiLink.get("/uapi/domestic-stock/v1/quotations/inquire-daily-price", "?fid_cond_mrkt_div_code=J&fid_input_iscd=005930&fid_period_div_code=D&fid_org_adj_prc=1fid_cond_mrkt_div_code=J&fid_input_iscd=005930&fid_period_div_code=D&fid_org_adj_prc=1");
		//logger.info("result : "+result);		
		return "admin/util";
	}
	//KEY Encoding Util
	@PostMapping(value = "/aesEncoding")
	public ResponseEntity<AdminUtilVO> aesEncoding(HttpServletRequest request, @RequestBody AdminUtilVO adminUtilVO) throws Exception {
		HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		AdminUtilVO rtnAdminUtilVO = new AdminUtilVO();
		rtnAdminUtilVO.setEncodeText(Aes.aesCBCEndoce(adminUtilVO.getPlainText()));
		return new ResponseEntity<AdminUtilVO>(rtnAdminUtilVO, header, HttpStatus.OK);
	}
	

	
}
