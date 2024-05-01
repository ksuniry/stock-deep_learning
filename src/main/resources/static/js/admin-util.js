function fnAesEncoding(){
	var planText = $('#planText').val();
	if(ComUtil.isNull(planText)){
		$('#planText').val("값을 넣어주세요");
		$('#planText').css('color',"red");
		return false;
	}
	var inputParam = {};
	inputParam.url = '/admin/aesEncoding';
	inputParam.data	= {"plainText": planText};
	inputParam.req_type = "POST";
	inputParam.call_back = fnAesEncodingCallBack;
	fnAjax(inputParam);
}

function fnAesEncodingCallBack(resultInfo){
	$('#div_aesIncoding_result').text(resultInfo.data.encodeText);
}
