package com.qurich.external.service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cn.wangxs.excel.ExcelHelper;

import com.qurich.external.mapper.StockNoticeMapper;
import com.qurich.external.model.StockNotice;
import com.qurich.external.utils.PageUtil;
import com.qurich.external.utils.QuarterReportDayUtil;

@Service
public class StockNoticeService {
	
	private static Logger log = Logger.getLogger(StockNoticeService.class.getClass());

	@Autowired
    private StockNoticeMapper stockNoticeMapper;
	

	private List<String>  getMoneys(String str){   
		str=str.replaceAll(",", "");
	     Pattern pattern=Pattern.compile("(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d*))?(?=(万元|$))"); // 判断小数点后2位的数字的正则表达式  
	     Matcher match=pattern.matcher(str);   
	     List<String> list=new ArrayList<>();
	     while(match.find()){   
	    	 list.add(match.group());
	     } 
	     return list;
	 }  
	
	private void yjbdjy(List<StockNotice> list){
		for(StockNotice stock:list){
			try{
				float yjup=stock.getYjbd_up();
				String msg=stock.getYjbd();
				if(msg.contains("万元")&&yjup!=0){
					List<String> moneys=this.getMoneys(msg);
					String target="";
					if(yjup<0){ //亏损
						if(moneys.size()>0){
							target=moneys.get(0);
						}
					}else{
						if(moneys.size()>1){
							target=moneys.get(1);
						}else if(moneys.size()>0){
							target=moneys.get(0);
						}
					}
					if(StringUtils.isNotBlank(target)){
						float f=Float.valueOf(target);
						if(f>0){
							double wc=Math.abs(f-Math.abs(yjup))/f;
							if(wc>0.3){
								stock.setYjbdjy(String.valueOf(wc*100)+"%");
							}
						}
					}
				}
			}catch(Exception e){
				log.error("业绩变动校验出错");
			}
		}
	}
	
	public String list(String date, String field, String sort, int page,
			Model model, HttpServletRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if("new".equals(date)){
				date=dateFormat.format(QuarterReportDayUtil.getCurrentQuarterStartTime());
			}
			List<StockNotice> list = stockNoticeMapper.getBeanList(date,field,sort,(page-1)*50, 50);
			this.yjbdjy(list);
    		int totalRecord = stockNoticeMapper.getBeanAllCount(date);
    		List<StockNotice> quarterDayAll= stockNoticeMapper.getBeanQuarterDayAll();
			
    		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("performance/notice/"+date+"/"+field+"/"+sort);
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
    		log.error("StockPerformanceService.list异常",e);
    	}
    	return "performance/notice/list";
	}


	public String download(String date, Model model, HttpServletResponse response) {
		try{
			List<StockNotice> list=new ArrayList<StockNotice>();
			for(int i=1;i<100;i++){
				List<StockNotice> sp = stockNoticeMapper.getBeanList(date,"pday","desc",(i-1)*50, 50);
				list.addAll(sp);
				if(null==sp||sp.size()<50)break;
			}
			this.yjbdjy(list);
			 response.reset();
	         response.setContentType("application/vnd.ms-excel;charset=utf-8");
	         response.setHeader("Content-Disposition", "attachment;filename="+ new String((date + ".xls").getBytes(), "iso-8859-1"));
	         OutputStream ouputStream=response.getOutputStream();
	         byte[]  bytes=ExcelHelper.write(list, StockNotice.class);
	         ouputStream.write(bytes);
	         ouputStream.flush();
	         ouputStream.close();
		}catch(Exception e){
    		log.error("excel导出异常:",e);
    	}
		return null;
	}
	
}
