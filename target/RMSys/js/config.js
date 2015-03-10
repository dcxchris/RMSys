

var gridConfig = {
	loadOnStart:false,
	url: "list.action",
	root: "data",
	record: "totalRecord",
	pageParmName: "page",
	pagesizeParmName: "pageSize",
	sortnameParmName: "sortName",
	sortorderParmName: "sortOrder",
	pageSize: 30,
	switchPageSizeApplyComboBox:true,
	pageSizeOptions:[10,30,50,100],
	rowHeight: 26,
	checkbox: true,
	async :true,
	resizable : true,
	// selectRowButtonOnly: true,
	rownumbers: true,
	height: '99%',
	onError: function(XMLHttpRequest, textStatus, errorThrown){
		$.ligerDialog.error('数据加载出错,请检查网络或者重新登录!');
	}
};


$.metadata.setType("attr", "validate");
// 身份证号码验证
jQuery.validator.addMethod("idcardno", function(value, element) {
	return this.optional(element) || _isIdCardNo(value);   
}, "请正确输入身份证号码");
// 汉字
jQuery.validator.addMethod("chcharacter", function(value, element) {
	var tel = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (tel.test(value));
}, "请输入汉字");
// 手机号码验证
jQuery.validator.addMethod("cellphone", function(value, element) {
	var length = value.length;
	return this.optional(element) || (length == 11 && /^(1\d{10})$/.test(value));
}, "请正确填写手机号码"); 
 // 电话号码验证
jQuery.validator.addMethod("telephone", function(value, element) {
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
	return this.optional(element) || (tel.test(value));
}, "请正确填写电话号码");
// 邮政编码验证
jQuery.validator.addMethod("zipcode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写邮政编码");
// 字母数字
jQuery.validator.addMethod("alnum", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "只能包括英文字母和数字");
// 长度
jQuery.validator.addMethod("textlength", function(value, element, param) {
	var test = false;
	for(var i=0; i<param.length; i++){
		test = test || value.length == parseInt(param[i]);
	}
	return this.optional(element) || test;
}, "长度错误");
function _isIdCardNo(num) {
	 
	var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
	var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		return false;
	}
	// check and set value
	for(i=0;i<intStrLen;i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	      
	if (intStrLen == 18) {
		// check date
		var date8 = idNumber.substring(6,14);
		if (isDate8(date8) == false) {
			return false;
		}
		// calculate the sum of the products
		for(i=0;i<17;i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = parityBit[lngProduct % 11];
		// check last digit
		if (varArray[17] != intCheckDigit) {
			return false;
		}
	} else {
		// length is 15
		// check date
		var date6 = idNumber.substring(6,12);
		if (isDate6(date6) == false) {
			return false;
		}
	}
	return true;
}
function isDate6(sDate) {
	if(!/^[0-9]{6}$/.test(sDate)) {
	   return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	if (year < 1700 || year > 2500) return false;
	if (month < 1 || month > 12) return false;
	return true;
 }
function isDate8(sDate) {
	if(!/^[0-9]{8}$/.test(sDate)) {
	   return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	day = sDate.substring(6, 8);
	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
	if (year < 1700 || year > 2500) return false;
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12) return false;
	if (day < 1 || day > iaMonthDays[month - 1]) return false;
	return true;
} 

var validateConfig = {
	errorPlacement: function (lable, element) {
		var next = element.next();
        next.addClass("validate-error");
        next.html(lable.html());
        lable.insertAfter(next);
        lable.removeAttr("class");
        lable.html("");
    },
    success: function (element) {
    	var prev = element.prev();
    	prev.removeAttr("class");
    	prev.html("");
    	element.remove();
    }
};

function reloadGrid(g, o){
	var p = g.options;
	var param = p.parms;
	if(o){
		for(var x in o){
			var name = x;
			var value = o[x];
			var replaceed = false;
			for(var i=0; i<param.length; i++){
				if(param[i].name == name){
					param[i].value = value;
					replaceed = true;
					break;
				}
			}
			if(!replaceed){
				param.push({name:name, value:value});
			}
		}
	}else{
		param = [];
	}
	g.options.parms = param;
	
	var pm = [];
	for(var i=0; i<param.length; i++){
		pm.push({name:param[i].name, value:param[i].value});
	}
	
	if (p.usePager) {
		pm.push({
			name : p.pageParmName,
			value : 1
		});
		pm.push({
			name : p.pagesizeParmName,
			value : p.pageSize
		});
	}
	if (p.sortName) {
		pm.push({
			name : p.sortnameParmName,
			value : p.sortName
		});
		pm.push({
			name : p.sortorderParmName,
			value : p.sortOrder
		});
	}
	
	g.options.dataAction == "local";
	g.changePage("first");
	g.options.dataAction == "server";
	g.loadServerData(pm);
}

function closeWindow(w, refreashData){
	var win = w || "w";
	try {
		try {
			if(refreashData){
				refreashData();
			}else{
				parent.g.loadData(true);
			}
		} catch (e) {
		}
		parent.window[win].hide();
	} catch (e) {
	}
}


function getDict(type, value){
	var group = dict[type];
	if(group)
		return group[value];
	else
		return "";
}


function validateFileSize(fileuploads, maxsize) {
	var size = 0;
	for ( var i = 0; fileuploads != null && i < fileuploads.length; i++) {
		var fileupload = fileuploads[i];
		if (fileupload.value.length < 5) {
			continue;
		}
		var agent = window.navigator.userAgent;
		if (document.all) {
			var isIE7 = agent.indexOf('MSIE 7.0') != -1;
			var isIE8 = agent.indexOf('MSIE 8.0') != -1;
			var isIE9 = agent.indexOf('MSIE 9.0') != -1;
			//IE9获得文件路径
			if (isIE9) {
				fileupload.select();
				fileupload.blur();
				filepath = document.selection.createRange().text;
				//IE7和IE8获得文件路径 
			} else if (isIE7 || isIE8) {
				fileupload.select();
				filepath = document.selection.createRange().text;
			} else {
				//IE6获得文件路径
				filepath = fileupload.value;
			}
			//var fileSize = window.external.GetLocalFileSize(filepath);
			//size = parseInt(size) + parseInt(fileSize);
			try {
				var fileobject = new ActiveXObject(
						"Scripting.FileSystemObject");//获取上传文件的对象  
			} catch (ex) {//不能创建该对象时，不作js文件大小检验
				//alert(ex.message);
				return true;
			}
			var file = fileobject.GetFile(filepath);//获取上传的文件  
			var fileSize = file.Size;//文件大小
			size = parseInt(size) + parseInt(fileSize);
		}
		if (agent.indexOf("Firefox") >= 1) {
			if (fileupload.files) {
				size = parseInt(size)
						+ parseInt(fileupload.files[0].size);
			}
		}
	}
	if (size > parseInt(maxsize)) {
		return false;
	} else {
		return true;
	}
	return false;
}



