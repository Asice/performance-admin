package com.qurich.external.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.conf.PropConfig;
import com.qurich.external.lucene.LuceneUtil;
import com.qurich.external.mapper.MessageMapper;
import com.qurich.external.mapper.StockLimitUpMapper;
import com.qurich.external.model.Message;
import com.qurich.external.model.StockLimitUp;
import com.qurich.external.utils.HttpUtils;

@Service
public class LuceneService {

	private static Logger log = Logger.getLogger(LuceneService.class.getClass());
	
	@Autowired
	private PropConfig propConfig;
	
	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private StockLimitUpMapper stockLimitUpMapper;

	public String list(String content, Model model) {
		try {
			if (StringUtils.isNotBlank(content)) {
				List<Message> list = LuceneUtil.SearchFile(content);
				List<String> dates = new ArrayList<String>();
				for (Message msg : list) {
					dates.add(String.valueOf(msg.getTime().getTime()));
				}
				String result = getStockResult(dates);
				model.addAttribute("result", result);
				model.addAttribute("content", content);
				model.addAttribute("list", list);
			}
		} catch (Exception e) {
			log.equals("LuceneService.list error" + e);
		}
		return "lucene/list";
	}

	// 传入时间list返回命中结果
	public String getStockResult(List<String> dates) {
		Map<String, Integer> stockMap = new HashMap<>();
		for (String date : dates) {
			Date time = LuceneUtil.getWorkDay(date);// 得到交易日
			// 查找涨停信息
			getStockCodes(stockMap, time);
			// message.setStock(stocks); //推荐股票
		}
		String result = "";
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		// map value倒序
		stockMap.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Integer> comparingByValue()
						.reversed())
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		int i = 0;
		for (String key : sortedMap.keySet()) {
			//key是股票代码
			try{
				String stockResutl=HttpUtils.doGet(propConfig.getStockNameUrl()+key,"gbk");
				if(stockResutl.contains(",")){
					result += key + stockResutl.split(",")[4]+"," + sortedMap.get(key) + ";";
				}
			}catch(Exception e){
				log.error(e);
			}
			if("".equals(result)){
				result += key + "," + sortedMap.get(key) + ";";
			}
			i++;
			if (sortedMap.get(key) < 3 && i > 5) {
				break;
			}
		}
		return result;
	}
	private void getStockCodes(Map<String, Integer> stockMap, Date date) {
		List<StockLimitUp> list = stockLimitUpMapper.getBeanByDate(LuceneUtil.sdf.format(date));
		for (StockLimitUp bean : list) {
			if (null!=bean&&StringUtils.isNotBlank(bean.getStock())) {
				if (stockMap.containsKey(bean.getStock())) {
					stockMap.put(bean.getStock(), stockMap.get(bean.getStock()) + 1);
				} else {
					stockMap.put(bean.getStock(), 1);
				}
			}
		}
	}
}
