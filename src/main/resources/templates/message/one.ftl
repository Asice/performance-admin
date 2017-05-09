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
                    <div class="ibox-content" style="min-height: 400px;">
                    	<h2>${message.title}</h2>
                        <p>${message.content}
                        </p>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
<script type="text/javascript">
	$(function(){
		
	});
</script>