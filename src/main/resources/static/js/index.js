
function crawlingChapter(){
	var inputParam = {};
	inputParam.url = '/crawlingChapter';
	inputParam.data 	= {"":""};
	inputParam.req_type = "POST";
	fnAjax(inputParam);
}

function makeStairStart(){
	
	var inputParam 		= {};
	//inputParam.sid 		= "makeStair";
	//inputParam.target 	= "local";
	inputParam.url 		= "/makeStair";
	inputParam.data 	= {"":""};
	inputParam.req_type = "POST";
	//inputParam.callback	= INDEX.callBack;
	fnAjax( inputParam );
	
}

function btnVerifyStart(){
	
	var inputParam 		= {};
	//inputParam.sid 		= "verifyStart";
	//inputParam.target 	= "local";
	inputParam.url 		= "/verifyStart";
	inputParam.data 	= {"":""};
	inputParam.req_type = "POST";
	//inputParam.callback	= INDEX.callBack; 
	fnAjax( inputParam );
	
}

function btnChallengeStart(){
	
	var inputParam 		= {};
	//inputParam.sid 		= "verifyStart";
	//inputParam.target 	= "local";
	inputParam.url 		= "/challengeStart";
	inputParam.data 	= {"":""};
	inputParam.req_type = "POST";
	//inputParam.callback	= INDEX.callBack; 
	fnAjax( inputParam );
	
}

