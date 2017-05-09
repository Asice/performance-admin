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
								<a href="${BASE_PATH}/performance/download/notice/${date}" class="btn btn-primary">导出Excel</a>
							</span>
						</div>
					</div>
                    <div class="ibox-content" style="overflow-x:scroll;">
                   		<table class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default"
							data-page-size="15">
							<thead>
								<tr>
									<th class="text-center footable-visible footable-first-column">序号</th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/stock/${sortdest}/1">股票代码</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/name/${sortdest}/1">股票名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yjbd/${sortdest}/1">业绩变动</a></th>
									<th class="text-center footable-visible">业绩变动误差(30%以上)</th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/syl/${sortdest}/1">市盈率</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yqsyl_low/${sortdest}/1">预期市盈率下区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yqsyl_up/${sortdest}/1">预期市盈率上区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/peratio/${sortdest}/1">市盈率比</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yjbdfd/${sortdest}/1">业绩变动幅度</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yjbd_low/${sortdest}/1">业绩变动下区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yjbd_up/${sortdest}/1">业绩变动上区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/year_low/${sortdest}/1">同比增长下区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/year_up/${sortdest}/1">同比增长上区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/quarter_low/${sortdest}/1">环比增长下区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/quarter_up/${sortdest}/1">环比增长上区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yqmgsy_low/${sortdest}/1">预期每股收益下区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/yqmgsy_up/${sortdest}/1">预期每股收益上区间</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/nitoce/${sortdest}/1">预告</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/jrl_last/${sortdest}/1">上年同期净利润（万元）</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/performance/notice/${date}/pday/${sortdest}/1">公告日期</a></th>
								</tr>
							</thead>
							<tbody>
								<#list list as bean>
								<tr class="text-center footable-even"
									style="display: table-row;">
									<td class="footable-visible">${bean_index+1}</td>
									<td class="footable-visible">${bean.stock}</td>
									<td class="footable-visible">${bean.name}</td>
									<td class="footable-visible">${bean.yjbd}</td>
									<td class="footable-visible">
									<span style="font-weight: bold;color: #f00;">${bean.yjbdjy}</span>
									</td>
									<td class="footable-visible">${bean.syl}</td>
									<td class="footable-visible">${bean.yqsyl_low}</td>
									<td class="footable-visible">${bean.yqsyl_up}</td>
									<td class="footable-visible">${bean.peratio}</td>
									<td class="footable-visible">${bean.yjbdfd}</td>
									<td class="footable-visible">${bean.yjbd_low}</td>
									<td class="footable-visible">${bean.yjbd_up}</td>
									<td class="footable-visible">${bean.year_low}</td>
									<td class="footable-visible">${bean.year_up}</td>
									<td class="footable-visible">${bean.quarter_low}</td>
									<td class="footable-visible">${bean.quarter_up}</td>
									<td class="footable-visible">${bean.yqmgsy_low}</td>
									<td class="footable-visible">${bean.yqmgsy_up}</td>
									<td class="footable-visible">${bean.nitoce}</td>
									<td class="footable-visible">${bean.jrl_last}</td>
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
			location.href="${BASE_PATH}/performance/notice/"+day+"/pday";
		});
	});
</script>