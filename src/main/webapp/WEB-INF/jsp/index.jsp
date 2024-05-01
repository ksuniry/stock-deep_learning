<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="/resources/js/jquery-1.9.1.min.js"></script>
		<script src="/resources/js/comm.js"></script>
		<script src="/resources/js/index.js"></script>
	</head>
	<body>
	    <div id="nowInfo" style="">
	     	chapter :<input type="text" id="lastChapter" disabled="true" maxlength="5" size="2"  ></input>
	     	num1 :<input type="text" id="lastNum1" disabled="true" maxlength="2" size="1"></input>
	     	num2 :<input type="text" id="lastNum2" disabled="true" maxlength="2" size="1"></input>
	     	num3 :<input type="text" id="lastNum3" disabled="true" maxlength="2" size="1"></input>
	     	num4 :<input type="text" id="lastNum4" disabled="true" maxlength="2" size="1"></input>
	     	num5 :<input type="text" id="lastNum5" disabled="true" maxlength="2" size="1"></input>
	     	num6 :<input type="text" id="lastNum6" disabled="true" maxlength="2" size="1"></input>
	     	bonus :<input type="text" id="lastBonus" disabled="true" maxlength="2" size="1"></input>
	     	date :<input type="text" id="lastDt" disabled="true" maxlength="10" size="6"></input>
	    	<input type="button" onclick="crawlingChapter()" value="update" style="margin-left: 15px;" id="chapter_update_btn" />
	    	<label style="margin-left: 15px;" id="chapter_update_label">최신<label>
	    </div>
	    <div id="insInfo" style="border: ">
	    	new chapter insert ing....<label id="insChapter"></label>
	    </div>
	    <div>
	    </div>
	   
	    <div style="border: 1px solid red;display:none" id="inputDiv">
	     	chapter :<input type="text" id="insChapter" disabled="true" maxlength="5" size="2"></input>
	     	num1 :<input type="text" id="insNum1" maxlength="2" size="1" ></input>
	     	num2 :<input type="text" id="insNum2" maxlength="2" size="1"></input>
	     	num3 :<input type="text" id="insNum3" maxlength="2" size="1"></input>
	     	num4 :<input type="text" id="insNum4" maxlength="2" size="1"></input>
	     	num5 :<input type="text" id="insNum5" maxlength="2" size="1"></input>
	     	num6 :<input type="text" id="insNum6" maxlength="2" size="1"></input>
	     	bonus :<input type="text" id="insNumBonus" maxlength="2" size="1"></input>
	     	date :<input type="text" id="insDt" disabled="true"  maxlength="10" size="6"></input>
	     	<input type="button" onclick="insNewChapter()" value="저장" />
	    </div>
	    
	    <div>
	    	<input type="button" onclick="makeStairStart()" value="계단식 만들기" style="margin-left: 15px;" id="chapter_update_btn" />
	    	<input type="button" onclick="btnVerifyStart()" value="검증시작" style="margin-left: 15px;" id="chapter_update_btn" />
	    	<input type="button" onclick="btnChallengeStart()" value="도전!!!" style="margin-left: 15px;" id="chapter_update_btn" />
	    </div>
	</body>
</html>

