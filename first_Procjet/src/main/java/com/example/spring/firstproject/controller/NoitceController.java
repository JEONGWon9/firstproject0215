package com.example.spring.firstproject.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.firstproject.service.NoticeService;

@RestController
public class NoitceController {
	
	
	@RequestMapping("/")
	public ModelAndView Index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	
	@Resource
	private NoticeService noticeService;
	
	@RequestMapping("/list")
	public ModelAndView AlllistView(Map<String, Object>map) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<Map<String, Object>> AllList = noticeService.SelectAllList();
		mav.addObject("AllList",AllList);		
		mav.setViewName("list");
		return mav;
	}
	
	
	@RequestMapping("/writenotice")
	public ModelAndView NoticeWrite(ModelAndView mav) {
		mav.setViewName("noticeWrite");
		return mav;
	}
	
//	
//	@RequestMapping(value="list")  // => list url 요청
//	public ModelAndView AllListView(Map<String, Object>map) throws Exception{ //db와 view 동시 송출
//		ModelAndView mav = new ModelAndView();
//		
//		List<Map<String, Object>> AllList = noticeService.SelectAllList(); //전체 쿼리 조회.
//		
//		mav.addObject("AllList",AllList);// ModelAndView 에 전달할 key/Value 값 저장
//		mav.setViewName("list");// 전달할 이름
//		
//		return mav;
//		
	}


