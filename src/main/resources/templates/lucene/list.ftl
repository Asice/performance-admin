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
                <div class="ibox float-e-margins">
                	<form id="conditionForm" action="${BASE_PATH}/lucene/analyze" autocomplete="off" method="post">
                		<div class="row">
                		<div class="col-sm-9">
							<div class="form-group">
								<textarea rows="4" name="content" cols="60" style="width: 100%;">${content}</textarea>
							</div>
						</div>
						<div class="col-sm-10">
						<div class="pull-left text-danger">
                                ${result?replace(',','命中')?replace(';','&nbsp;&nbsp;&nbsp;')}
                         </div>
                         </div>
                		<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label" for="status">&nbsp;</label> <span
									class="input-group-btn">
									<button type="submit"  class="btn btn-primary">查询</button>
								</span>
							</div>
						</div>
						</div>
                	</form>
                    <div class="ibox-content ">
                   	 <#list list as bean> 
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-11 content no-top-border">
                                    <span class="text-danger">${bean.time?string("YYYY-MM-dd HH:mm")}</span><p>${bean.content}</p>
                                </div>
                            </div>
                        </div>
                        </#list>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
