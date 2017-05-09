package com.qurich.external.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qurich.external.service.MessageService;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
	
	private Logger logger = Logger.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	
	/**
	 * 公司利好
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bull", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list(@RequestParam(value="page", required=false, defaultValue="1")int page,
			Model model){
		return messageService.listType(page,model);
	}
	@RequestMapping(value="/bull/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String listPage(@PathVariable("page")int page,
			Model model){
		return messageService.listType(page,model);
	}
	
	@RequestMapping(value="/page/{id}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String pageOne(@PathVariable("id")int id,
			Model model){
		return messageService.pageOne(id,model);
	}
	
	@RequestMapping(value="/updatebull", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updatebull(@RequestParam(value="id", required=false, defaultValue="0")int id,
			@RequestParam("content[]") String[] content,
			Model model){
		return messageService.updatebull(id,content,model);
	}
	 
}
