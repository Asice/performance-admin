package com.qurich.external.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qurich.external.service.MessageService;

@Controller
public class IndexController {
	
	private Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = {"/","index"}, method = RequestMethod.GET)
	public String index(@RequestParam(value="page", required=false, defaultValue="1")int page
			,HttpServletRequest request, HttpServletResponse response, Model model) {
		return messageService.list(page, model); 
	}
	
	
	/*
	@RequestMapping(value="/index", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list(@RequestParam(value="page", required=false, defaultValue="1")int page,
			Model model){
		return indexService.toList(page,model);
	}*/
	 
}
