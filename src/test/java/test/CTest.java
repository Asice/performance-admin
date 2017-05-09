package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.qurich.external.model.StockPerformance;

public class CTest {
	
	// 业绩公告
	public void yjgg() {
		String url = "http://data.eastmoney.com/soft_new/bbsj/201703/yjbb/ggrq/desc/1.html";
		try {
			Document dom = Jsoup.parse(new URL(url), 20000);
			Element table = dom.getElementById("dt_1")
					.getElementsByTag("tbody").get(0);
			Elements trs = table.getElementsByTag("tr");
			Iterator<Element> it = trs.iterator();
			while (it.hasNext()) {
				Element tr = it.next();
				for (int i = 0; i < 15; i++) {
					if (i < 3) {
						System.out.println(tr.child(i).html());
					} else {
						System.out.println(tr.child(i).child(0).html());
					}
				}
				System.out.println("===============");
			}
			// System.out.println(table.html());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 业绩预告
	
	public void yjyg() {
		String url = "http://data.eastmoney.com/soft_new/bbsj/201612/yjyg/fsrq/asc/200.html";
		try {
			Document dom = Jsoup.parse(new URL(url), 20000);
			Element table = dom.getElementById("dt_1")
					.getElementsByTag("tbody").get(0);
			Elements trs = table.getElementsByTag("tr");
			System.out.println(trs.size());
			Iterator<Element> it = trs.iterator();
			while (it.hasNext()) {
				Element tr = it.next();
				for (int i = 0; i < 8; i++) {
					if(i==4){
						System.out.println(tr.child(i).text());
						if("-".equals(tr.child(i).html())){
							System.out.println(tr.child(i).html());
						}else
						if(tr.child(i).childNodeSize()==3){
							System.out.println(tr.child(i).child(0).html());
							System.out.println(tr.child(i).child(1).html());
						}else if(tr.child(i).childNodeSize()==1){
							System.out.println(tr.child(i).child(0).html());
						}
						//System.out.println(tr.child(i).childNodeSize());
					}else if ( i == 6 || i == 7) {
						if (tr.child(i).childNode(0).childNodeSize() == 0) {
							System.out.println(tr.child(i).html());
						} else {
							System.out.println(tr.child(i).child(0).html());
						}
					} else {
						System.out.println(tr.child(i).html());
					}
				}
				System.out.println("===============");
			}
			// System.out.println(table.html());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 个股详情
	@Test
	public void gpxq() {
		String url = "http://stockpage.10jqka.com.cn/900935/";
		try {
			Document dom = Jsoup.parse(new URL(url), 20000);

			Element dl = dom.getElementsByClass("company_details").get(0);
			Iterator<Element> it = dl.getElementsByTag("dd").iterator();
			int i = 0;
			while (it.hasNext()) {
				Element dd = it.next();
				switch (i) {
				case 0:
					System.out.println("地域:" + dd.html());
					break;
				case 1:
					System.out.println("概念:" + dd.getElementsByAttribute("title").get(0)
							.attr("title"));
					break;
				case 3:
					System.out.println("主营:"
							+ dd.getElementsByAttribute("title").get(0)
									.attr("title"));
					break;
				case 4:
					System.out.println("上市日期:"+ dd.html());
					break;
				case 5:
					System.out.println("每股净资产："+ dd.html());
					break;
				case 6:
					System.out.println("每股收益:"+ dd.html());
					break;
				case 7:
					System.out.println("净利润："+ dd.html());
					break;
				case 8:
					System.out.println("净利润增长率："+ dd.html());
					break;
				case 9:
					System.out.println("营业收入："+ dd.html());
					break;
				case 10:
					System.out.println("每股现金流："+ dd.html());
					break;
				case 11:
					System.out.println("每股公积金："+ dd.html());
					break;
				case 12:
					System.out.println("每股未分配利润："+ dd.html());
					break;
				case 13:
					System.out.println("总股本："+ dd.html());
					break;
				case 14:
					System.out.println("流通股："+ dd.html());
					break;
				default:
					break;
				}
				i++;
			}
			System.out.println("===============");

			Response res = Jsoup
					.connect(
							"http://d.10jqka.com.cn/v2/realhead/hs_900935/last.js")
					.header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept-Language",
							"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
					.timeout(10000).ignoreContentType(true).execute();// .get();

			// System.out.println(res.body());
			String result = res.body();
			result = result.replaceFirst(".*?(?=(\\(|$))", "").replaceAll(
					"\\)|\\(", "");
			System.out.println(result);
			JSONObject obj = JSONObject.fromObject(result);
			System.out.println("总股本:"
					+ obj.getJSONObject("items").getDouble("402") / 10000);
			System.out.println("现价:"
					+ obj.getJSONObject("items").getDouble("10"));
			System.out.println("流通市值:"
					+ obj.getJSONObject("items").getDouble("3475914") / 10000);
			System.out.println("总市值:"
					+ obj.getJSONObject("items").getDouble("3541450") / 10000);
			System.out.println("现价市盈率:"
					+ obj.getJSONObject("items").getDouble("2034120"));
			
			System.out.println("市净率:"
					+ obj.getJSONObject("items").getDouble("592920"));
			
			
			// System.out.println(dom2.getElementById("stock_quoteinfo_xj").html());

			// System.out.println(dom2.getElementById("sta_2").getElementsByTag("h2").get(0).html());

			// System.out.println(dom.getElementsByClass("company_details").get(0));

			// System.out.println(table.html());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ip(){
		String url="http://1212.ip138.com/ic.asp";
		try {
			Document dom = Jsoup.parse(new URL(url), 20000);
			System.out.println(dom.html());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
