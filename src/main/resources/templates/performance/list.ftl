<html>

<#include "/comm/head.ftl">
<div class="container">
                <div class="ibox float-e-margins">
                	<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label">数据日期</label> 
							<select id="reportDay"  class="form-control">
							<#list quarterDayAll as bean>
								<#if (bean.quarter_day?string("yyyy-MM-dd"))==date>
									<option selected="selected" value="${(bean.quarter_day?string("yyyy-MM-dd"))!}">${(bean.quarter_day?string("yyyy-MM-dd"))!}</option> 
								<#else>
									<option  value="${(bean.quarter_day?string("yyyy-MM-dd"))!}">${(bean.quarter_day?string("yyyy-MM-dd"))!}</option> 
								</#if>
							</#list>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="status">&nbsp;</label> <span class="input-group-btn">
								<a href="${BASE_PATH}/performance/download/report/${date}" class="btn btn-primary">导出Excel</a>
							</span>
						</div>
					</div>
                    <div class="ibox-content ">
                   		<table class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default"
							data-page-size="15">
							<thead>
								<tr>
									<th class="text-center footable-visible footable-first-column">序号</th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/stock/${sortdest}/1">股票代码</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/name/${sortdest}/1">股票名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/mgsy/${sortdest}/1">没股收益</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/yysr/${sortdest}/1">营业收入(万元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/yysr_year/${sortdest}/1">同比增长</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/yysr_quarter/${sortdest}/1">环比增长</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/jrl/${sortdest}/1">净利润(万元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/jrl_year/${sortdest}/1">同比增长</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/jrl_quarter/${sortdest}/1">环比增长</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/mgjzc/${sortdest}/1">每股净资产(元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/jzcsyl/${sortdest}/1">净资产收益率</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/mgxjll/${sortdest}/1">每股现金流量(元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/mlv/${sortdest}/1">销售毛利率</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/report/${date}/pday/${sortdest}/1">公告日期</a></th>
								</tr>
							</thead>
							<tbody>
								<#list list as bean>
								<tr class="text-center footable-even"
									style="display: table-row;">
									<td class="footable-visible">${bean_index+1}</td>
									<td class="footable-visible">${bean.stock}</td>
									<td class="footable-visible">${bean.name}</td>
									<td class="footable-visible">${bean.mgsy}</td>
									<td class="footable-visible ">${bean.yysr}</td>
									<td class="footable-visible">${bean.yysr_year}</td>
									<td class="footable-visible">${bean.yysr_quarter}</td>
									<td class="footable-visible">${bean.jrl}</td>
									<td class="footable-visible">${bean.jrl_year}</td>
									<td class="footable-visible">${bean.jrl_quarter}</td>
									<td class="footable-visible">${bean.mgjzc}</td>
									<td class="footable-visible">${bean.jzcsyl}</td>
									<td class="footable-visible">${bean.mgxjll}</td>
									<td class="footable-visible">${bean.mlv}</td>
									<td class="footable-visible">${(bean.pday?string("yyyy-MM-dd"))!}</td>
								</tr>
								</#list>
							</tbody>
							<#include "/comm/pagination.ftl">
						</table>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
<script type="text/javascript">
	$(function(){
		$("#reportDay").change(function(){
			var day=$(this).val();
			location.href="${BASE_PATH}/performance/report/"+day+"/pday";
		});
	});
</script>