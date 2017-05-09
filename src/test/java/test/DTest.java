package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.qurich.external.model.StockPerformance;
import com.qurich.external.utils.HttpUtils;

public class DTest {
	
	// 业绩公告
	@Test
	public void yjgg() {
		String result=HttpUtils.doGet("https://api-prod.wallstreetcn.com/apiv1/content/lives/pc?limit=20");
		/*Pattern pat =  Pattern.compile("(?<=var ajaxResult=)[\\s\\S]*?(?=($))");
		Matcher mat = pat.matcher(result);
		while (mat.find()) {
			System.out.println(mat.group());
		}*/
		System.out.println(result);
		
	}

	
	
}
