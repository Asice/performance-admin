function singleImage(id, folder, image, imageHidden, queueID, filename)
{
	if(typeof(image) == "undefined")
		image = "#image";
	if(typeof(imageHidden) == "undefined")
		imageHidden = "#imageHidden";
	if(typeof(queueID) == "undefined")
		queueID = "singleDiv";
	if(typeof(filename) == "undefined")
		filename = "";
	$(id).uploadify({
		'uploader' : '/common/upload',
		'formData' : {'folder' : folder},
		'queueID' : queueID,
		'fileSizeLimit' : 1024,
		'overrideEvents': ['onSelectError'],
		'onSelectError':function(file, errorCode, errorMsg){
	    	switch(errorCode) {
	        case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
	        	this.queueData.errorMsg = "图片大小不能超过1M";
	          break;
	    	}
	    	return false;
		},
		'onUploadStart': function (file) {    
			if(filename!='')
			{
				if($(filename).val()=='')
				{
					$(id).uploadify('stop');
					$(filename).after("<label id=\""+filename+"-error\" class=\"error\" for=\""+filename+"\">请先上传视频</label>");
				}
				else
				{
					$(filename).next(".error").remove();
				}
				$(id).uploadify("settings", "uploader", '/common/upload?f='+$(filename).val());
			}   
            //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。    
        },
		'onUploadSuccess' : function(file, data, response) {
			var data = eval('(' + data + ')')
			$(imageHidden).val(data.nname);
	/*		if (data.video=="1") {
				$("#myVideo").show();$(image).css("display", 'none');
			}else{*/
				$(image).attr("src", data.src+"?imageView2/2/w/200");
			/*	$("#myVideo").css("display",'none');
			}*/
			if(typeof(data.size) != "undefined")
				$(imageHidden).siblings("input[name*='picsize']").val(data.size);
			 $(image).css("margin-top","10px");
			 $(image).show();
			 $(image).removeClass("hide");
		},
		'onUploadComplete' : function(){
			/*$(image).load(function(){
				parent.document.getElementById("iframepage").height=$(".content").height()+$(image).height()+20;
			});*/
		}
	});
}
function multipleImage(id, folder, name, control, queueID)
{
	 
	if(typeof(control) == "undefined")
		control = "#picControl";
	if(typeof(queueID) == "undefined")
		queueID = "queueDiv";
	$(id).uploadify({
		'uploader' : '/common/upload',
		'formData' : {'folder' : folder},
		'queueID' : queueID,
		'fileSizeLimit' : 1024,
		'overrideEvents': ['onSelectError'],
		'onSelectError':function(file, errorCode, errorMsg){
			switch(errorCode) {
	        	case -110:
	        		this.queueData.errorMsg = "图片大小不能超过1M";
	        	break;
			}
			return false;
		},
		'onUploadSuccess' : function(file, data, response) {
			var data = eval('(' + data + ')')
			$(name).val($(name).val() + "," + data.nname);
			$(control).append("<img "+(typeof(data.size)=="undefined"?"":"size=\"" +data.size+ "\"")+" data=\"" + data.nname + "\" src=\"" + data.src + "\" class=\"fl wh200-150 mr10 mt10\" /><i class=\"fl remove mtr2-18\"></i>");
			$(name).siblings(".error").remove();
		},
		'onUploadComplete' : function(){
			parent.document.getElementById("iframepage").height=$(".content").height();
		}
	});
	$(document).on("click",control+" i",function(){
		removeImg(this, name);
	});
	new Sortable(picControl, {
		group: "photo",
		draggable:"img",
		onStart:function(evt){$(evt.item).siblings("i").remove();},
		onEnd: function(evt){ 
			$(control+" img").after("<i class=\"fl remove mtr2-18\"></i>");
			$(name).val("");
			$(control+" img").each(function(index){
				$(name).val($(name).val()+(index==0?"":",")+$(this).attr("data"));
			});
		}
	});
}
function removeImg(target, input)
{
	$(target).prev("img").remove();
	$(input).val("");
	$(target).siblings("img").each(function(i){
		$(input).val((i == 0 ? "" : $(input).val() + ",") + $(this).attr("data"));
	});
	$(target).remove();
}