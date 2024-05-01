CREATE TABLE stock.company_bagic_info (
	ISU_CD varchar NOT NULL,
	ISU_SRT_CD varchar NOT NULL,
	ISU_NM varchar  NULL,
	ISU_ABBRV varchar  NULL,
	ISU_ENG_NM varchar  NULL,
	LIST_DD varchar  NULL,
	MKT_TP_NM varchar  NULL,
	SECUGRP_NM varchar  NULL,
	SECT_TP_NM varchar  NULL,
	KIND_STKCERT_TP_NM varchar NULL,
	PARVAL varchar ,
	LIST_SHRS varchar ,
	USE_YN VARCHAR(1)default ('Y'), 	
	CONSTRAINT s_c_cbi PRIMARY KEY (ISU_CD)
);