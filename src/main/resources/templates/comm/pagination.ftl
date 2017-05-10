
<tfoot>
	<tr>
		<td colspan="${showPage.columns+4}" class="footable-visible">
			<ul class="pagination pull-right">
			 <#if (showPage.totalPage>0)>
				<#if showPage.currentPage!=1>
						<li class="footable-page-arrow "><a data-page="first"
								href="${BASE_PATH}/${showPage.urlName}/1?${query}">«</a></li>
						<li class="footable-page-arrow "><a data-page="prev"
								href="${BASE_PATH}/${showPage.urlName}/${showPage.prePage}?${query}">‹</a></li>
				<#else>
					<li class="footable-page-arrow disabled"><a data-page="first"
								href="#">«</a></li>
						<li class="footable-page-arrow disabled"><a data-page="prev"
								href="#">‹</a></li>
				</#if>
				<#list showPage.pageNumStart..showPage.pageNumEnd as pageIndex>
					<#if showPage.currentPage==pageIndex>
						<li class="footable-page active"><a data-page="0" href="#">${pageIndex}</a></li>
					</#if>
					<#if showPage.currentPage!=pageIndex>
						<li class="footable-page ">
						<a data-page="0" href="${BASE_PATH}/${showPage.urlName}/${pageIndex}?${query}">${pageIndex}</a></li>
					</#if>
				</#list>
				<#if showPage.currentPage!=showPage.totalPage>
					<li class="footable-page-arrow "><a data-page="next"
							href="${BASE_PATH}/${showPage.urlName}/${showPage.nextPage}?${query}">›</a></li>
						<li class="footable-page-arrow "><a data-page="last"
							href="${BASE_PATH}/${showPage.urlName}/${showPage.totalPage}?${query}">»</a></li>
				<#else>
					<li class="footable-page-arrow disabled"><a data-page="next"
							href="#">›</a></li>
						<li class="footable-page-arrow disabled"><a data-page="last"
							href="#">»</a></li>
				</#if>
				</#if>
			</ul>
		</td>
	</tr>

</tfoot>


<script type="text/javascript">
	function jumpPage() {
		var pageNum = $("#pageNum").val();
		if (pageNum != '')
			window.location.href = '${BASE_PATH}/${showPage.urlName}/' + pageNum + '${query}';
		else
			window.location.reload();
	}
</script>