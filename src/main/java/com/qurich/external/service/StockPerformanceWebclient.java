package com.qurich.external.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.qurich.external.mapper.StockInfoMapper;
import com.qurich.external.mapper.StockNoticeMapper;
import com.qurich.external.mapper.StockPerformanceMapper;
import com.qurich.external.model.StockInfo;
import com.qurich.external.model.StockNotice;
import com.qurich.external.model.StockPerformance;
import com.qurich.external.utils.HttpUtils;

/**
 * 没有经过计算的数据都是直接抓取，%的都是没有除与100的
 * @author Asice
 *
 */
@Service
public class StockPerformanceWebclient {
	
	private static Logger log = Logger.getLogger(StockPerformanceWebclient.class.getClass());
	
	
	@Autowired
    private StockPerformanceMapper stockPerformanceMapper;
	
	@Autowired
    private StockInfoMapper stockInfoMapper;
	
	@Autowired
    private StockNoticeMapper stockNoticeMapper;
	/**
	 * 预期业绩
	 * @param year
	 * @param mon
	 * @param day
	 * @param yestDay
	 */
	public void quarterNotice(String year,String mon,String day,Date yestDay){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String url = "http://data.eastmoney.com/soft_new/bbsj/"+year+mon;
		for(int page=0;page<200;page++){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				Document dom = Jsoup.parse(new URL(url+"/yjyg/fsrq/desc/"+page+".html"), 20000);
				Element table = dom.getElementById("dt_1")
						.getElementsByTag("tbody").get(0);
				Elements trs = table.getElementsByTag("tr");
				if(trs.size()<50)break;
				Iterator<Element> it = trs.iterator();
				boolean ex=false;
				while (it.hasNext()) {
					StockNotice bean=new StockNotice();
					Element tr = it.next();
					try{
						bean.setQuarter_day(dateFormat.parse(year+"-"+mon+"-"+day));
						if(tr.childNodeSize()>=8)
						for (int i = 0; i < 8; i++) {
							switch (i) {
							case 1:
								bean.setStock(tr.child(i).html());
								break;
							case 2:
								bean.setName(tr.child(i).html());
								break;
							case 3:
								bean.setYjbd(tr.child(i).html());
								break;
							case 4:
								bean.setYjbdfd(tr.child(i).text());
								if("-".equals(tr.child(i).html())){
									
								}else if(tr.child(i).childNodeSize()==3){
									bean.setYear_low(Float.valueOf(tr.child(i).child(0).html().replaceAll("%", "")));
									bean.setYear_up(Float.valueOf(tr.child(i).child(1).html().replaceAll("%", "")));
								}else if(tr.child(i).childNodeSize()==1){
									bean.setYear_low(Float.valueOf(tr.child(i).child(0).html().replaceAll("%", "")));
									bean.setYear_up(Float.valueOf(tr.child(i).child(0).html().replaceAll("%", "")));
								}
								break;
							case 5:
								bean.setNitoce(tr.child(i).html());
								break;
							case 6:
								if (tr.child(i).childNode(0).childNodeSize() == 0) {
									if(!"-".equals(tr.child(i).html()))
										bean.setJrl_last(Double.valueOf(tr.child(i).html()));
								} else {
									if(!"-".equals(tr.child(i).child(0).html()))
										bean.setJrl_last(Double.valueOf(tr.child(i).child(0).html()));
								}
								break;
							case 7:
								try {
									if (tr.child(i).childNode(0).childNodeSize() == 0) {
										bean.setPday(dateFormat.parse(tr.child(i).html()));
									} else {
										bean.setPday(dateFormat.parse(tr.child(i).child(0).html()));
									}
									if(yestDay.after(bean.getPday())){
										ex=true;
									}
								} catch (Exception e) {
									log.error("获取公告日期出错：",e);
								}
								break;
							default:
								break;
							}
							if(ex)break;
						}
					}catch(Exception e){
						log.error("bean保存出错：",e);
					}
					if(ex)break;
					//获取市盈率
					this.noticeSave(bean,year,mon,dateFormat);
				}
				if(ex)break;
			} catch (MalformedURLException e) {
				log.error("MalformedURLException",e);
			} catch (IOException e) {
				log.error("IOException",e);
			}
		}
	}
	
	/**
	 * @param year 季度报表的年
	 * @param mon 季度报表的月
	 * @param day 季度报表的日
	 * @param yestDay 昨天的日期 04-27
	 */
	public void quarterReport(String year,String mon,String day,Date yestDay){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String url = "http://data.eastmoney.com/soft_new/bbsj/"+year+mon;
		for(int page=1;page<200;page++){
		try {
				Document dom = Jsoup.parse(new URL(url+"/yjbb/ggrq/desc/"+page+".html"), 20000);
				Element table = dom.getElementById("dt_1")
						.getElementsByTag("tbody").get(0);
				Elements trs = table.getElementsByTag("tr");
				if(trs.size()<50)break;
				Iterator<Element> it = trs.iterator();
				boolean ey=false;
				while (it.hasNext()) {
					Element tr = it.next();
					try{
					StockPerformance bean=new StockPerformance();
					bean.setQuarter_day(dateFormat.parse(year+"-"+mon+"-"+day));
					if(tr.childNodeSize()>=15)
					for (int i = 0; i < 15; i++) {
						switch (i) {
						case 1:
							bean.setStock(tr.child(i).html());
							break;
						case 2:
							bean.setName(HtmlUtils.htmlUnescape(tr.child(i).html()));//有偏僻字乱码
							break;
						case 3:
							bean.setMgsy(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 4:
							bean.setYysr(Double.valueOf(tr.child(i).child(0).html()));
							break;
						case 5:
							bean.setYysr_year(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 6:
							bean.setYysr_quarter(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 7:
							bean.setJrl(Double.valueOf(tr.child(i).child(0).html()));
							break;
						case 8:
							bean.setJrl_year(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 9:
							bean.setJrl_quarter(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 10:
							bean.setMgjzc(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 11:
							bean.setJzcsyl(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 12:
							bean.setMgxjll(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 13:
							bean.setMlv(Float.valueOf(tr.child(i).child(0).html().contains("-")?"0":tr.child(i).child(0).html()));
							break;
						case 14:
							if("06".equals(mon)||"12".equals(mon))break;//利润分配
							try {
								bean.setQuarter_day(dateFormat.parse(year+"-"+mon+"-"+day));
								bean.setPday(dateFormat.parse(year+"-"+tr.child(i).child(0).html()));
							} catch (ParseException e) {
								log.error("日期转换差错:"+year+"-"+tr.child(i).child(0).html());
							}
							if(null!=bean.getPday()&&yestDay.after(bean.getPday())){
								ey=true;
								break;
							}
							break;
						default:
							break;
						}
						if(ey)break;
					}
					if("06".equals(mon)||"12".equals(mon)){
						try {
							bean.setQuarter_day(dateFormat.parse(year+"-"+mon+"-"+day));
							bean.setPday(dateFormat.parse(year+"-"+tr.child(15).child(0).html()));
						} catch (Exception e) {
							log.error("日期转换差错or利润分配:"+year+"-"+tr.child(15).html());
						}
					}
					if(ey)break;
					this.savePerformance(bean,dateFormat);
					}catch (Exception e) {
						log.error("解析出错，单个bean没保存:",e);
					}
				}
				if(ey)break;
			} catch (MalformedURLException e) {
				log.error("MalformedURLException",e);
			} catch (IOException e) {
				log.error("IOException",e);
			}
		}
	}
	
	public void quarterReport2(String year,String mon,String day){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String url = "http://data.eastmoney.com/soft_new/bbsj/"+year+mon;
		for(int page=1;page<100;page++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StockPerformance bean=new StockPerformance();
		   try {
				Document dom = Jsoup.parse(new URL(url+"/yjbb/ggrq/desc/"+page+".html"), 20000);
				Element table = dom.getElementById("dt_1")
						.getElementsByTag("tbody").get(0);
				Elements trs = table.getElementsByTag("tr");
				if(trs.size()<50)break;
				Iterator<Element> it = trs.iterator();
				while (it.hasNext()) {
					Element tr = it.next();
					try{
					bean=new StockPerformance();
					bean.setQuarter_day(dateFormat.parse(year+"-"+mon+"-"+day));
					if(tr.childNodeSize()>=15)
					for (int i = 0; i < 15; i++) {
						switch (i) {
						case 1:
							bean.setStock(tr.child(i).html());
							break;
						case 2:
							bean.setName(HtmlUtils.htmlUnescape(tr.child(i).html()));//有偏僻字乱码
							break;
						case 3:
							bean.setMgsy(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 4:
							bean.setYysr(Double.valueOf(tr.child(i).child(0).html()));
							break;
						case 5:
							bean.setYysr_year(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 6:
							bean.setYysr_quarter(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 7:
							bean.setJrl(Double.valueOf(tr.child(i).child(0).html()));
							break;
						case 8:
							bean.setJrl_year(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 9:
							bean.setJrl_quarter(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 10:
							bean.setMgjzc(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 11:
							bean.setJzcsyl(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 12:
							bean.setMgxjll(Float.valueOf(tr.child(i).child(0).html()));
							break;
						case 13:
							bean.setMlv(Float.valueOf(tr.child(i).child(0).html().contains("-")?"0":tr.child(i).child(0).html()));
							break;
						case 14:
							//公告日期 04-27
							if("06".equals(mon)||"12".equals(mon))break;//利润分配
							try {
								bean.setQuarter_day(dateFormat.parse(year+"-"+mon+"-"+day));
								bean.setPday(dateFormat.parse(year+"-"+tr.child(i).child(0).html()));
							} catch (ParseException e) {
								log.error("日期转换差错or利润分配:"+year+"-"+tr.child(i).html());
							}
							break;
						default:
							break;
						}
					}
					if("06".equals(mon)||"12".equals(mon)){
						try {
							bean.setQuarter_day(dateFormat.parse(year+"-"+mon+"-"+day));
							bean.setPday(dateFormat.parse(year+"-"+tr.child(15).child(0).html()));
						} catch (Exception e) {
							log.error("日期转换差错or利润分配:"+year+"-"+tr.child(15).html());
						}
					}
					this.savePerformance(bean,dateFormat);
					}catch (Exception e) {
						log.error("解析出错，单个bean没保存:"+bean.getStock()+";"+bean.getName(),e);
					}
				}
			} catch (MalformedURLException e) {
				log.error("quarterReport2 MalformedURLException",e);
			} catch (IOException e) {
				log.error("quarterReport2 IOException",e);
			}
		}
	}
	//保存预期业绩和个股
	private void noticeSave(StockNotice bean,String year,String mon,SimpleDateFormat dateFormat){
		StockInfo stockInfo=saveStockInfo(bean.getStock(),bean.getName(),dateFormat);
		//预期净利润上下区间
		if(!"-".equals(bean.getYjbdfd())){
			if(bean.getJrl_last()<0){
				bean.setYjbd_low((float)bean.getJrl_last()*(1-bean.getYear_low()/100));
				bean.setYjbd_up((float)bean.getJrl_last()*(1-bean.getYear_up()/100));
			}else{
				bean.setYjbd_low((float)bean.getJrl_last()*(1+bean.getYear_low()/100));
				bean.setYjbd_up((float)bean.getJrl_last()*(1+bean.getYear_up()/100));
			}
			if(bean.getYjbd_low()>bean.getYjbd_up()){
				float temp=bean.getYjbd_low();
				bean.setYjbd_low(bean.getYjbd_up());
				bean.setYjbd_up(temp);
			}
		}
		//bean.setYjbd_low((float)bean.getJrl_last()*bean.getYear_low());
		//bean.setYjbd_up((float)bean.getJrl_last()*bean.getYear_up());
		try{
			//环比增长
			String yearDay="",yearDay2="";
			double njlr_low=0d,njlr_up=0d;
			switch (mon) {
			case "03":
				yearDay2=Integer.valueOf(year)-1+"-12-31";
				yearDay=Integer.valueOf(year)-1+"-09-30";
				njlr_low=bean.getYjbd_low()*4;
				njlr_up=bean.getYjbd_up()*4;
				break;
			case "06":
				yearDay2=year+"-03-31";
				njlr_low=bean.getYjbd_low()*2;
				njlr_up=bean.getYjbd_up()*2;
				break;
			case "09":
				yearDay2=year+"-06-30";
				yearDay=year+"-03-31";
				njlr_low=bean.getYjbd_low()*4/3;
				njlr_up=bean.getYjbd_up()*4/3;
				break;
			case "12":
				yearDay2=year+"-09-30";
				yearDay=year+"-06-30";
				njlr_low=bean.getYjbd_low();
				njlr_up=bean.getYjbd_up();
				break;
			default:
				break;
			}
			double jlr=0d;
			if("".equals(yearDay)){
				StockPerformance sp1=stockPerformanceMapper.getBeanByStockQuarterDay(bean.getStock(),yearDay2);
				if(null!=sp1){
					jlr=sp1.getJrl();
				}
			}else{
				StockPerformance sp1=stockPerformanceMapper.getBeanByStockQuarterDay(bean.getStock(),yearDay2);
				StockPerformance sp2=stockPerformanceMapper.getBeanByStockQuarterDay(bean.getStock(),yearDay);
				if(null!=sp1&&null!=sp2){
					jlr=sp1.getJrl()-sp2.getJrl();
				}
			}
			if(jlr!=0){ //环比
				bean.setQuarter_low((bean.getYjbd_low()/jlr-1)*100);
				bean.setQuarter_up((bean.getYjbd_up()/jlr-1)*100);
			}
			if(null!=stockInfo){//预期市盈率
				bean.setSyl(stockInfo.getSyl());
				bean.setYqmgsy_low((float)(njlr_low/stockInfo.getZgb()));
				bean.setYqmgsy_up((float)(njlr_up/stockInfo.getZgb()));
				if(njlr_low!=0)
					bean.setYqsyl_low((float)(stockInfo.getPrice()*stockInfo.getZgb()/njlr_low));
				if(njlr_up!=0)
					bean.setYqsyl_up((float)(stockInfo.getPrice()*stockInfo.getZgb()/njlr_up));
			}
			if(bean.getYqsyl_up()!=0){
				if(bean.getSyl()<0&&bean.getYqsyl_up()>0){
					bean.setPeratio(Math.abs(bean.getSyl()/bean.getYqsyl_up())); //负变正
				}else{
					bean.setPeratio(bean.getSyl()/bean.getYqsyl_up()); //市盈率比
				}
			}
			stockNoticeMapper.saveBean(bean);
		}catch(Exception e){
			log.error("保存单个stockNotice错误：",e);
		}
		
	}	
	//保存已披露业绩和个股
	private void savePerformance(StockPerformance bean,SimpleDateFormat dateFormat){
		saveStockInfo(bean.getStock(),bean.getName(),dateFormat);
		stockPerformanceMapper.saveBean(bean);
	}

	//保存股票基本信息
	private StockInfo saveStockInfo(String stock,String name,SimpleDateFormat dateFormat){
		String url = "http://stockpage.10jqka.com.cn/"+stock+"/";
		StockInfo stockInfo=new StockInfo();
		stockInfo.setStock(stock);
		stockInfo.setName(name);
		try {
			Document dom = Jsoup.parse(new URL(url), 20000);
			if(dom.getElementsByClass("company_details").size()==0)return null; //找不到该股票
			Element dl = dom.getElementsByClass("company_details").get(0);
			Iterator<Element> it = dl.getElementsByTag("dd").iterator();
			int i = 0;
			while (it.hasNext()) {
				Element dd = it.next();
				switch (i) {
				case 0:
					stockInfo.setArea(dd.html());
					break;
				case 1:
					stockInfo.setConcept(dd.getElementsByAttribute("title").get(0)
							.attr("title"));
					break;
				case 3:
					stockInfo.setMain_business(dd.getElementsByAttribute("title").get(0).attr("title"));
					break;
				case 4:
					if(StringUtils.isNotBlank(dd.html()))
						stockInfo.setPday(dateFormat.parse(dd.html()));
					break;
				case 5:
					if(!"-元".equals(dd.html()))
						stockInfo.setMgjzc(Float.valueOf(dd.html().replaceAll("元", "")));
					break;
				case 6:
					if(!"-元".equals(dd.html()))
						stockInfo.setMgsy(Float.valueOf(dd.html().replaceAll("元", "")));
					break;
				case 7:
					if("-亿元".equals(dd.html())||"-万元".equals(dd.html()))break;
					if(dd.html().contains("亿"))
						stockInfo.setJlr(Float.valueOf(dd.html().replaceAll("亿|元", ""))*10000);
					else
						stockInfo.setJlr(Float.valueOf(dd.html().replaceAll("万|元", "")));
					break;
				case 8:
					if(!"-%".equals(dd.html()))
						stockInfo.setJlrzzl(Float.valueOf(dd.html().replace("%", "")));
					break;
				case 9:
					if("-亿元".equals(dd.html())||"-万元".equals(dd.html()))break;
					if(dd.html().contains("亿"))
						stockInfo.setYysr(Float.valueOf(dd.html().replaceAll("亿|元", ""))*10000);
					else
						stockInfo.setYysr(Float.valueOf(dd.html().replaceAll("万|元", "")));
					break;
				case 10:
					if(!"-元".equals(dd.html()))
						stockInfo.setMgxjl(Float.valueOf(dd.html().replaceAll("元", "")));
					break;
				case 11:
					if(!"-元".equals(dd.html()))
						stockInfo.setMggjj(Float.valueOf(dd.html().replaceAll("元", "")));
					break;
				case 12:
					if(!"-元".equals(dd.html()))
						stockInfo.setMgwfplr(Float.valueOf(dd.html().replaceAll("元", "")));
					break;
				case 13:
					if("-亿".equals(dd.html())||"-万".equals(dd.html()))break;
					if(dd.html().contains("亿"))
						stockInfo.setZgb(Float.valueOf(dd.html().replaceAll("亿|元", ""))*10000);
					else
						stockInfo.setZgb(Float.valueOf(dd.html().replaceAll("万|元", "")));
					break;
				case 14:
					if("-亿".equals(dd.html())||"-万".equals(dd.html()))break;
					if(dd.html().contains("亿"))
						stockInfo.setLtg(Float.valueOf(dd.html().replaceAll("亿|元", ""))*10000);
					else
						stockInfo.setLtg(Float.valueOf(dd.html().replaceAll("万|元", "")));
					break;
				default:
					break;
				}
				i++;
			}
			Response res = Jsoup
					.connect("http://d.10jqka.com.cn/v2/realhead/hs_"+stock+"/last.js")
					.header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
					.timeout(10000).ignoreContentType(true).execute();// .get();
			String result = res.body();
			result = result.replaceFirst(".*?(?=(\\(|$))", "").replaceAll(
					"\\)|\\(", "");
			JSONObject obj = JSONObject.fromObject(result);
			stockInfo.setZgb((float)("".equals(obj.getJSONObject("items").getString("402"))?0d:obj.getJSONObject("items").getDouble("402"))/10000);
			stockInfo.setPrice((float)("".equals(obj.getJSONObject("items").getString("10"))?0d:obj.getJSONObject("items").getDouble("10")));
			stockInfo.setLtsz("".equals(obj.getJSONObject("items").getString("3475914"))?0d:obj.getJSONObject("items").getDouble("3475914")/ 10000);
			stockInfo.setZsz("".equals(obj.getJSONObject("items").getString("3475914"))?0d:obj.getJSONObject("items").getDouble("3475914")/ 10000);
			
			stockInfo.setSjl((float)("".equals(obj.getJSONObject("items").getString("592920"))?0d:obj.getJSONObject("items").getDouble("592920")));
			stockInfo.setSyl((float)("".equals(obj.getJSONObject("items").getString("2034120"))?0d:obj.getJSONObject("items").getDouble("2034120")));
			stockInfo.setTime(new Date());
			stockInfoMapper.saveBean(stockInfo);
		} catch (MalformedURLException e) {
			log.error("MalformedURLException"+stock,e);
		} catch (Exception e) {
			log.error("Exception"+stock,e);
		}
		return stockInfo;
	}	

}
