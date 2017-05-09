package com.qurich.external.service;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.MessageMapper;
import com.qurich.external.model.Message;
import com.qurich.external.utils.PageUtil;

@Service
public class MessageService {
	
	private static Logger log = Logger.getLogger(MessageService.class.getClass());

	@Autowired
    private MessageMapper messageMapper;
	
	//首页资讯
	public String list(int page,Model model){
    	try{
    		List<Message> list = messageMapper.getBeanList((page-1)*20, 20);
    		int totalRecord = messageMapper.getBeanAllCount(0);
    		//添加查看数
    		messageMapper.updateViewList(list);
    		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("message/list");
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    	}catch(Exception e){
    		log.error("MessageService.list异常",e);
    	}
    	return "message/list";
    }
	
	//公司利好
	public String listType(int page,Model model){
    	try{
    		List<Message> list = messageMapper.getBeanListType(3,(page-1)*30, 30);
    		int totalRecord = messageMapper.getBeanAllCountType(3);
    		messageMapper.updateViewList(list);
    		PageUtil pageUtil = new PageUtil(30, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("message/bull");
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    	}catch(Exception e){
    		log.error("MessageService.listType异常",e);
    	}
    	return "message/bull";
    }

	public String pageOne(int id, Model model) {
		try{
			Message message=messageMapper.getBeanById(id);
    		model.addAttribute("message", message);
    	}catch(Exception e){
    		log.error("MessageService.pageOne异常",e);
    	}
    	return "message/one";
	}

	public String updatebull(int id, String[] content, Model model) {
		try{
			int re=messageMapper.updateBullCategoryById(id,ArrayUtils.toString(content).replaceAll("\\{|\\}", ""));
			if(re==1){
				return "{code:1,meg:\"更新成功\"}";
			}
    		
    	}catch(Exception e){
    		log.error("MessageService.pageOne异常",e);
    	}
		return "{code:0,meg:\"操作失败\"}";
	}
	
}
