<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="/resources/js/jquery-1.9.1.min.js"></script>
		<script src="/resources/js/comm.js"></script>
		<script src="/resources/js/admin-util.js"></script>
		 
	</head>
	<body>
	    <div id="div_aesIncoding" style="">
	     	AES ENCODING <input type="text" id="planText" onkeydown="KeyDownEvent.initColor(this, 'black')" maxlength="1000" size="250"></input>
	    	<input type="button" onclick="fnAesEncoding(fnAesEncodingCallBack)"  value="변환"  />
	    </div>
	    <div id="" style="border: ">
	    	<label id="div_aesIncoding_result" style="padding-left: 5%;color: red"></label>
	    </div>
	</body>
</html>

