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
                    <div class="ibox-content ">
                   	 <#list list as bean> 
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                   ${bean.time?string("HH:mm")}
                                </div>
                                <div class="col-xs-8 content no-top-border">
                                    <p>${bean.summary}</p>
                                    <#-- <div class="pull-left text-danger">
                                    	${bean.stock?replace(',','命中')?replace(';','&nbsp;&nbsp;&nbsp;')}
                                    </div> -->
                                    <div class="pull-right">
                                       <a href="#faq${bean_index}" class="text-danger opening" data-toggle="collapse"><i class="fa fa-twitch"></i>评论${bean.comment}</a>
                                        <a href="#" class="text-danger">已阅${bean.view}</a>
                                        <a href="#" class="text-danger">分享到</a>
                                    </div>
                                    <div id="faq${bean_index}" class="panel-collapse collapse" style="margin-top:60px;margin-bottom: 10px;">
                                        <div class="feed-activity-list">
                                                <div class="feed-element">
                                                    <a href="profile.html" class="pull-left">
                                                        <img alt="image" class="img-circle" src="${ctx}/img/defaultUser.png">
                                                    </a>
                                                    <div class="media-body ">
                                                        <span class="pull-right"><strong>2017-02-20 13:19</strong></span>
                                                        <strong>隔壁</strong> <br>
                                                        <span>大宵午评：大盘劲涨25点，成交2745亿，大盘蓝筹股崛起，特别是银行股走强，临近收盘，两桶油拉抬导致大盘迅速走强。恒生指数盘中一度突破24200点创6个月新高，准备挑战243**点，如果成功则创18月以来的新高，港股银行股继续偏强带动A股银行股走强动力十足。</span>
                                                    </div>
                                                </div>
                                                <textarea rows="4" cols="60" style="width: 100%;"></textarea>
                                          </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </#list>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
<script type="text/javascript">
	$(function(){
		
	});
</script>