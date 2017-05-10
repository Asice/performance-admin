<html>
<style>
.product-box {
	width: 320px;
}

.product-imitation {
	padding: 15px 0;
	width: 320px;
	height: 350px;
}

.product-desc {
	padding: 10px;
}
.timeline-item{
	font-size: 14px;
    font-family: Tahoma,Arial,Helvetica,'Microsoft Yahei';
    color: #333;
}
.timeline-item .date {
	color: #333;
    left: 0;
    font-weight: bold;
    font-size: 15px;
    
}
.timeline-item .content{
	border-bottom: 1px solid #e7eaec;
}
.pull-right a{
	margin-left: 30px;
}

</style>
<#include "/comm/head.ftl">
<div class="container">
		<#assign query = "type=${status}&bull=${bulllist}">
                <div class="ibox float-e-margins">
                <div class="row">
					<div class="col-sm-2 condition_type">
						<div class="form-group">
							<label class="control-label">类型</label><select name="btype"
								class="form-control">
								<option value="0">全部</option> 
								<option value="1" <#if status==1>selected="selected"</#if>>A股</option> 
							</select>
						</div>
					</div>
					<div class="col-sm-4 condition_bull">
						<div class="form-group">
							<label class="control-label">公司利好</label>
							<#include "/scomm/company_good_select2.ftl">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="status">&nbsp;</label> <span
								class="input-group-btn">
								<button type="submit" id="bsearch" class="btn btn-primary">搜索</button>
							</span>
						</div>
					</div>
				</div>
                    <div class="ibox-content ">
                   	 <#list list as bean> 
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                   ${bean.time?string("HH:mm")}
                                </div>
                                <div class="col-xs-8 content no-top-border" style="min-height: 80px;">
                                    <p><a target="_black" href="${bean.href}">${bean.title}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bean.time?string("yyy-MM-dd")}</p>
                                     <div class="pull-left text-danger">
                                     	<#if bean.bull_category!>
                                     	<#list bean.bull_category?split(",") as category>
									        	 【${category}】
									     </#list>
									     </#if>
                                    </div> 
                                    <div class="select2content col-md-4 select2_${bean_index}" data-mid="${bean.id?c}">
										<#include "/scomm/company_good_select2.ftl">
                               		 </div>
                                </div>
                            </div>
                        </div>
                        </#list>
                        <#include "/comm/pagination.ftl">
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
<!-- Select2 -->
<script src="${BASE_PATH}/static/js/plugins/select2/select2.full.min.js"></script>
<link href="${BASE_PATH}/static/css/plugins/select2/select2.min.css" rel="stylesheet">



<script type="text/javascript">
	$(function(){
		
		 $(".select2content .select2_bull").select2();
		 $(".condition_bull .select2_bull").select2().val([${bullArray}]).trigger('change');
		 
		 $(".select2content .select2_bull").on("change",function(){
			 var id=$(this).parent().attr("data-mid");
			 $.ajax({
	             type: "POST",
	             url: "${BASE_PATH}/message/updatebull",
	             data: {id:id, content:$(this).val()},
	             dataType: "json",
	             success: function(result){
	            	/*  swal({
	            		  title: "提示",
	            		  text: result.msg,
	            		  timer: 200,
	            		  showConfirmButton: false
	            		});    */
	         	 }
	        });
		 })
		 //搜索
		 $("#bsearch").click(function(){
			var type=$(".condition_type").find("select").val();
			var bull=$(".condition_bull .select2_bull").val();
			var url="${BASE_PATH}/message/bull?";
			if(type!=undefined&&type!=null&&type!=0){
				url+="type="+type
				if(bull!=undefined&&bull!=null){
					url+="&bull="+bull
				}
			}else{
				if(bull!=undefined&&bull!=null){
					url+="bull="+bull
				}
			}
			location.href=url;
		 });
		 
		 
	});
</script>