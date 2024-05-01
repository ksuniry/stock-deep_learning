package com.stock.constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

public final class KoreaInvestmentConst{
	
	public static String BAGIC_URL = "";
	
	public static String APP_KEY ="";
	
	public static String SECRET_KEY = "";
	
	public static String BEARER_TOKEN = "";
	
	public static String TRAIDE_DAY_YN = "";
	//bearer 토큰
	public static final String BEARER_TOKEN_URL = "/oauth2/tokenP";
	public static final String BEARER_TR_ID = "FHKST01010300";
	//기본URL
	public static final String API_V1_BAGIC_URL = "/uapi/domestic-stock/v1";
	//기본구분값
	public static final String FID_COND_MRKT_DIV_CODE = "?fid_cond_mrkt_div_code=J";
	//@GET 거래일 여부
	public static final String TRAIDE_YN_URL = API_V1_BAGIC_URL.concat("/quotations/chk-holiday?BASS_DT=@bassDtYyyymmdd&CTX_AREA_NK=&CTX_AREA_FK=");
	public static final String TRAIDE_YN_TR_ID = "CTCA0903R";
	//20호가
	public static Map<String, ScheduledExecutorService> HOGA20_THREAD_MAP = new HashMap<String, ScheduledExecutorService>();
	public static final String HOGA20_URL = API_V1_BAGIC_URL.concat("/quotations/inquire-asking-price-exp-ccn").concat(FID_COND_MRKT_DIV_CODE).concat("&fid_input_iscd=@iscd");	
	public static final String HOGA20_TR_ID = "FHKST01010200";
	//1분봉
	public static final String MIN_BONG_URL = API_V1_BAGIC_URL.concat("/quotations/inquire-time-itemchartprice").concat(FID_COND_MRKT_DIV_CODE).concat("&FID_ETC_CLS_CODE=&FID_INPUT_ISCD=@iscd&FID_INPUT_HOUR_1=@fidIputHour&FID_PW_DATA_INCU_YN=Y");	
	public static final String MIN_BONG_TR_ID = "FHKST03010200";
	
	//rank
	//fid_rank_sort_cls_code : 순위 정렬 구분 코드 0:상승율순 1:하락율순 2:시가대비상승율 3:시가대비하락율 4:변동율
	//fid_prc_cls_code : 가격 구분 코드: 0 상승율 순일때 (0:저가대비, 1:종가대비),1 하락율 순일때 (0:고가대비, 1:종가대비), 기타 (0:전체)
	// 상승일때는 상승 , 하락일때는 하락 
	//fid_input_price_1 : 범위 입력가격 시작
	//fid_input_price_2 : 범위 입력가격 종료
	public static Map<String, String> NOW_RANK_MAP = new LinkedHashMap<String, String>();
	public static final String NOW_RANK_URL = API_V1_BAGIC_URL.concat("/ranking/fluctuation?").concat(FID_COND_MRKT_DIV_CODE).concat("&fid_cond_scr_div_code=20170&fid_input_iscd=0000&fid_rank_sort_cls_code=@fidRankSortClsCode&fid_input_cnt_1=0&fid_prc_cls_code=@fidPrcClsCode&fid_input_price_1=&fid_input_price_2=&fid_vol_cnt=&fid_trgt_cls_code=0&fid_trgt_exls_cls_code=0&fid_div_cls_code=0&fid_rsfl_rate1=&fid_rsfl_rate2=");	
	public static final String NOW_RANK_TR_ID = "FHPST01700000";
	

	
	
	
}
