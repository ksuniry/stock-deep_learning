function fnAjax(inputParam){
	var fn_callBack = inputParam.call_back;
	var req_type = inputParam.req_type;
	$.ajax({
		  url		: inputParam.url
		, type		: req_type
		, jsonp		: false 
		, data		: JSON.stringify(inputParam.data)
		, dataType	: "json"
		, contentType: "application/x-www-form-urlencoded; charset=UTF-8"
		//, contentType: "application/json; charset=UTF-8"
		, async		: ComUtil.null(inputParam.bAsync, true)
		
		,beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-type","application/json");
        }
		, success	: function(result, textStatus, data) {
			/*
			console.log("result ---------------------------------");
			console.log(result);
			console.log(data);
			*/
	   		//if(!gfn_isNull(result.pageInfo)){
				//result.pageInfo.responseURL = data.responseURL + "?" + inputParam.data;
		    //}
			if( typeof fn_callBack == "function"){
				fn_callBack({sid: inputParam.sid, data: result, status: textStatus});
				/*  
				if ( window.fn_callBack ) {
					fn_callBack(inputParam.sid, result, textStatus);    
				}
				*/
			} else {
				//alert('nofunction')
				//callback(inputParam.sid, result, textStatus);
			}
		}
		, error		: function(xhr, errorName, error) {
			console.log("error ---------------------------------");
			//debugger;
			//gfn_endTran(inputParam);
			try{
				//gfn_errorCallback(xhr.status, xhr.responseText, inputParam);
			}
			catch(e) {
				//gfn_log("gfn_errorCallback error!!");
			}
		}
		, complete  : function(result, textStatus, data) {
			//console.log(result.responseText);
		}
	});
}

const ComUtil = {
	
	isNull	: function(sParam){
		if(typeof(sParam) == "number" || typeof(sParam) == "boolean")
			return false;
		
		if(sParam == '' || sParam == null || sParam == undefined || sParam == 'undefined'){
			return true;
		}else{
			return false;
		}
	},
	
	null	: function(sParam, sDefalut){
		if(this.isNull(sParam))
			return sDefalut;
		else 
			return sParam;
	}
}

const KeyDownEvent = {
	initColor : function(el, sColor){
		$(el).val('');
		$(el).css('color', sColor)
	}
}
