<html>  
  <#include "/comm/head.ftl">
    <body> 
    <#include "/comm/nav.ftl">
       
        <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                	<h2>采购管理</h2>
                    <ol class="breadcrumb">
                        <li class="active">
                            <strong><a href="/purchaseself/list">采购管理</a></strong>
                        </li>
                    </ol>
                </div>
            </div>
	<div class="wrapper wrapper-content animated fadeInRight ecommerce">
		<#assign query = "&type=${type}">
		<div class="ibox-content m-b-sm border-bottom">
		<form  action="/purchaseself/list"
						autocomplete="off" method="get">
			<div class="row">
					 <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label">备注名称</label>
                            <input class="form-control" type="text" value="" name="remark_name" />
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label">采购商订单</label>
                            <input class="form-control" type="text" value="" name="buyer_order_no" />
                        </div>
                    </div>
                   
                   <div class="col-sm-1">
                        <div class="form-group">
                         <label class="control-label">采购商</label><select name="buyer_id" class="form-control" onchange="$('form').submit()">
							<option value="0">全部</option>
								<option value="28" >剁手帮</option>
								<option value="27" >当当海外</option>
								<option value="26" >密惠</option>
								<option value="25" >丰趣</option>
								<option value="24" >奥买家</option>
								<option value="23" >宝贝格子</option>
								<option value="22" >云猴</option>
								<option value="21" >京东</option>
								<option value="20" >网易考拉</option>
								</select>
                        </div>
                    </div>
                     <div class="col-sm-1">
                        <div class="form-group">
                         <label class="control-label">采购商</label><select name="status" class="form-control" onchange="$('form').submit()">
							<option value="0" selected="selected">全部</option>
							<option value="1" >待入库</option>
							</select>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label" for="status">&nbsp;</label>
                            <span class="input-group-btn">
                                    <button type="submit" class="btn btn-primary">搜索</button>
                                </span>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label" for="status">&nbsp;</label>
                            <a class="input-group"
								href="/purchaseself/add"> 
								<button type="button" class="btn btn-w-m btn-primary">添加</button></a>
                        </div>
                    </div>
                </div>
             </form>
		</div>
		<div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content" style="overflow-x: scroll;">
                           <table class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default" data-page-size="15">
                              <thead>
                                <tr>
                                    <th class="text-center footable-visible footable-first-column">采购商ID</th>
                                    <th class="text-center footable-visible" >备注名称</th>
                                    <th class="text-center footable-visible" >采购商</th>
                                    <th class="text-center footable-visible" >采购商订单</th>
                                    <th class="text-center footable-visible " >采购时间</th>
                                    <th class="text-center footable-visible " >操作人员</th>
                                    <th class="text-center footable-visible " >更新时间</th>
                                    <th class="text-right footable-visible " >操作</th>
                                </tr>
                                </thead>
                                <tbody>
								<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        25</td>
                                    <td class="footable-visible">
                                        日本ROSETTE海泥洗面奶 黄色湖底泥 祛角质120g*2</td>
                                    <td class="footable-visible">丰趣</td>
                                    <td class="footable-visible">3201611091400106577</td>
                               		<td class="footable-visible">2016-11-09 11:41:20</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 23:05:12</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=191">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        MARVIS 玛尔斯 紫色茉莉薄荷牙膏 75毫升/支</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016110118470004610053140</td>
                               		<td class="footable-visible">2015-10-30 10:59:43</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 23:02:59</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=87">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        MARVIS 玛尔斯 紫色茉莉薄荷牙膏 75毫升/支</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016110317190004610096937</td>
                               		<td class="footable-visible">2016-11-03 11:02:51</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 23:01:40</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=89">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        MARVIS 玛尔斯 紫色茉莉薄荷牙膏 75毫升/支</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016110316460004610079332</td>
                               		<td class="footable-visible">2016-11-03 11:01:11</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 22:59:58</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=88">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        SHISEIDO/资生堂 UNO男士清爽型全效肌能三合一调理乳液 160毫升</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016103119360000710077139</td>
                               		<td class="footable-visible">2016-11-03 14:08:02</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 15:34:07</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=201">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        21</td>
                                    <td class="footable-visible">
                                        石泽研究所（ishizawa）毛穴扶子小苏打泡沫洗面奶 去黑头角质洗面奶 100g/瓶 "</td>
                                    <td class="footable-visible">京东</td>
                                    <td class="footable-visible">46148978067</td>
                               		<td class="footable-visible">2016-12-08 12:43:13</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 12:45:21</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=200">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                         GENKI KAKUMEI 元气革命 G-革命片 男性保健 2粒装+4粒装 </td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016120218510000510001374</td>
                               		<td class="footable-visible">2016-12-02 12:41:29</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 12:42:28</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=199">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        MONTBLANC 万宝龙 星辰男士淡香水 75毫升 0.01kg</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016120218070000920089447</td>
                               		<td class="footable-visible">2016-12-02 12:36:43</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 12:37:36</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=198">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        BURBERRY 博柏利 经典伦敦男士淡香水 50毫升 绅士的品格</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016120217550008630085856</td>
                               		<td class="footable-visible">2016-12-02 12:32:48</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 12:33:35</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=197">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        28</td>
                                    <td class="footable-visible">
                                        fx眼药水</td>
                                    <td class="footable-visible">剁手帮</td>
                                    <td class="footable-visible">3003001</td>
                               		<td class="footable-visible">2016-11-26 12:01:47</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 12:03:15</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=196">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        25</td>
                                    <td class="footable-visible">
                                         澳大利亚Nu-lax 乐康膏纯天然果蔬膏500g</td>
                                    <td class="footable-visible">丰趣</td>
                                    <td class="footable-visible">3201611102200128870</td>
                               		<td class="footable-visible">2016-11-10 11:55:06</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 11:56:40</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=195">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        22</td>
                                    <td class="footable-visible">
                                        美国GNC健安喜玛咖精氨酸男士复合配方片 60粒</td>
                                    <td class="footable-visible">云猴</td>
                                    <td class="footable-visible">163152500189</td>
                               		<td class="footable-visible">2016-11-10 11:50:39</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 11:54:40</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=194">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        27</td>
                                    <td class="footable-visible">
                                        当当自营】 GNC健安喜三倍效力浓缩深海鱼油软胶囊1000mg 60粒 海外购</td>
                                    <td class="footable-visible">当当海外</td>
                                    <td class="footable-visible">34450106759</td>
                               		<td class="footable-visible">2016-11-10 11:48:53</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 11:50:12</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=193">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        21</td>
                                    <td class="footable-visible">
                                        zippo打火机 礼盒套装（缎纱镀铬205+133ml电油+六粒装火石</td>
                                    <td class="footable-visible">京东</td>
                                    <td class="footable-visible">44230191775</td>
                               		<td class="footable-visible">2016-11-09 11:45:38</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 11:46:40</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=192">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        26</td>
                                    <td class="footable-visible">
                                        Propolinse 比那氏 蜂胶除口臭漱口水 600毫升</td>
                                    <td class="footable-visible">密惠</td>
                                    <td class="footable-visible">1001002</td>
                               		<td class="footable-visible">2016-11-09 11:38:53</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 11:40:59</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=190">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        26</td>
                                    <td class="footable-visible">
                                        Shiseido资生堂洗颜专科洗面奶柔澈泡沫洁面乳洗面奶120g</td>
                                    <td class="footable-visible">密惠</td>
                                    <td class="footable-visible">1001001</td>
                               		<td class="footable-visible">2016-11-06 11:19:56</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-27 11:38:09</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=189">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        25</td>
                                    <td class="footable-visible">
                                        日本 BIORE碧柔 男士控油去角质洗面奶130g</td>
                                    <td class="footable-visible">丰趣</td>
                                    <td class="footable-visible">3201611031500121180</td>
                               		<td class="footable-visible">2016-11-11 20:34:55</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-21 20:40:04</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=188">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        Kracie/肌美精 渗透保湿紧致毛孔黑面膜 18毫升*4片</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016110221230000710072617</td>
                               		<td class="footable-visible">2016-11-11 20:32:09</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-21 20:34:38</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=186">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        20</td>
                                    <td class="footable-visible">
                                        Kracie/肌美精 渗透保湿紧致毛孔黑面膜 18毫升*4片</td>
                                    <td class="footable-visible">网易考拉</td>
                                    <td class="footable-visible">2016110221260000710074611</td>
                               		<td class="footable-visible">2016-11-11 20:33:28</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-21 20:34:26</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=187">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							<tr class="text-center footable-even" style="display: table-row;">
                                    <td class="footable-visible footable-first-column">
                                        25</td>
                                    <td class="footable-visible">
                                        SHISEIDO/资生堂 UNO男士清爽型全效肌能三合一调理乳液 160毫升</td>
                                    <td class="footable-visible">丰趣</td>
                                    <td class="footable-visible">3201611031500121180</td>
                               		<td class="footable-visible">2016-11-03 20:27:45</td>
                                    <td class="footable-visible">胡椿霖</td>
                                    <td class="footable-visible">2016-12-21 20:29:57</td>
                                    <td class="text-right footable-visible footable-last-column">
                                        <div class="btn-group">
                                            <a href="/purchaseself/edit?id=185">
                                            	<button type="button" class="btn btn-xs btn-outline btn-primary">编辑</button></a>
                                        </div>
                                    </td>
                                </tr>
							</tbody>
								<tfoot>
	<tr>
		<td colspan="12" class="footable-visible">
			<ul class="pagination pull-right">
			<li class="footable-page-arrow disabled"><a data-page="first"
								href="#">«</a></li>
						<li class="footable-page-arrow disabled"><a data-page="prev"
								href="#">‹</a></li>
					<li class="footable-page active"><a data-page="0" href="#">1</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=2&buyer_order_no=&buyer_id=0&remark_name=&status=0#">2</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=3&buyer_order_no=&buyer_id=0&remark_name=&status=0#">3</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=4&buyer_order_no=&buyer_id=0&remark_name=&status=0#">4</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=5&buyer_order_no=&buyer_id=0&remark_name=&status=0#">5</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=6&buyer_order_no=&buyer_id=0&remark_name=&status=0#">6</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=7&buyer_order_no=&buyer_id=0&remark_name=&status=0#">7</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=8&buyer_order_no=&buyer_id=0&remark_name=&status=0#">8</a></li>
					<li class="footable-page ">
						<a data-page="0" href="/purchaseself/list?page=9&buyer_order_no=&buyer_id=0&remark_name=&status=0#">9</a></li>
					<li class="footable-page-arrow "><a data-page="next"
							href="/purchaseself/list?page=2&buyer_order_no=&buyer_id=0&remark_name=&status=0#">›</a></li>
						<li class="footable-page-arrow "><a data-page="last"
							href="/purchaseself/list?page=9&buyer_order_no=&buyer_id=0&remark_name=&status=0#">»</a></li>
					</ul>
		</td>
	</tr>

</tfoot>

</table>
                        </div>
                    </div>
                </div>
            </div>
	</div>
	
        	
<#include "/comm/foot.ftl">
 