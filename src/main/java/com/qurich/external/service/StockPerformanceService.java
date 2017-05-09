package com.qurich.external.service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cn.wangxs.excel.ExcelHelper;

import com.qurich.external.mapper.StockPerformanceMapper;
import com.qurich.external.model.Message;
import com.qurich.external.model.StockPerformance;
import com.qurich.external.utils.PageUtil;
import com.qurich.external.utils.QuarterReportDayUtil;

@Service
public class StockPerformanceService {
	
	private static Logger log = Logger.getLogger(StockPerformanceService.class.getClass());

	@Autowired
    private StockPerformanceMapper stockPerformanceMapper;
	

	public String list(String date, String field, String sort, int page,
			Model model, HttpServletRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if("new".equals(date)){
				date=dateFormat.format(QuarterReportDayUtil.getCurrentQuarterStartTime());
			}
			List<StockPerformance> list = stockPerformanceMapper.getBeanList(date,field,sort,(page-1)*50, 50);
    		int totalRecord = stockPerformanceMapper.getBeanAllCount(date);
    		List<StockPerformance> quarterDayAll= stockPerformanceMapper.getBeanQuarterDayAll();
			
    		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("performance/report/"+date+"/"+field+"/"+sort);
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
			
    		model.addAttribute("field", field);
    		model.addAttribute("date", date);
    		model.addAttribute("sort", sort);
    		model.addAttribute("quarterDayAll", quarterDayAll);
    		if("desc".equals(sort)){
    			model.addAttribute("sortdest", "asc");
    		}else{
    			model.addAttribute("sortdest", "desc");
    		}
    		
		}catch(Exception e){
    		log.error("StockPerformanceService.list异常"+e);
    	}
    	return "performance/list";
	}


	public String download(String date, Model model, HttpServletResponse response) {
		try{
			List<StockPerformance> list=new ArrayList<StockPerformance>();
			for(int i=1;i<100;i++){
				List<StockPerformance> sp = stockPerformanceMapper.getBeanList(date,"pday","desc",(i-1)*50, 50);
				list.addAll(sp);
				if(null==sp||sp.size()<50)break;
			}
			 response.reset();
	         response.setContentType("application/vnd.ms-excel;charset=utf-8");
	         response.setHeader("Content-Disposition", "attachment;filename="+ new String((date + ".xls").getBytes(), "iso-8859-1"));
	         OutputStream ouputStream=response.getOutputStream();
	         byte[]  bytes=ExcelHelper.write(list, StockPerformance.class);
	         ouputStream.write(bytes);
	         ouputStream.flush();
	         ouputStream.close();
		}catch(Exception e){
    		log.error("excel导出异常:"+e);
    	}
		return null;
	}
	
}
