package com.example.spring.firstproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.firstproject.config.NoticeDTO;
import com.example.spring.firstproject.service.NoticeService;

@RestController
public class NoitceController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/")
	public ModelAndView Index(ModelAndView mav) throws Exception {
		System.out.println("Controller - index");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/list")
	public ModelAndView noticeList(HttpServletRequest request, NoticeDTO noticeDto) throws Exception {
		System.out.println("Controller - list");
		ModelAndView mav = new ModelAndView();

		int totalCount = this.noticeService.SelectNoticeListCount(request, noticeDto);

		List<NoticeDTO> noticeList = null;
		if (totalCount > 0) {
			noticeList = this.noticeService.SelectNoticeList(request, noticeDto);
		}

		mav.addObject("totalCount", totalCount);
		mav.addObject("noitceList", noticeList);
		mav.addObject("noticeDto", noticeDto);

		mav.setViewName("list");
		return mav;
	}
	
	@RequestMapping(value="noticeDelete", method = RequestMethod.GET)
	public ModelAndView noticeDelete(@RequestParam int idx) throws Exception{
		System.out.println("Controller - noticeDelete");
		ModelAndView mav = new ModelAndView();
		
		noticeService.noticeDelte(idx);
		
		
		mav.setViewName("redirect:list");
		
		return mav;
	}


	@RequestMapping(value = "noticeInfoView", method = RequestMethod.GET)
	public ModelAndView noticeInfoView(@RequestParam int idx) throws Exception {
		System.out.println("Controller - noticeInfoView");
		ModelAndView mav = new ModelAndView();

		noticeService.noticeReadCount(idx);
		NoticeDTO noticeInfoView = this.noticeService.noticeInfoView(idx);

		mav.addObject("noticeInfoView", noticeInfoView);
		mav.addObject("idx", noticeService.noticeInfoView(idx));
		mav.setViewName("noticeInfoView");
		return mav;

	}

	@RequestMapping(value = "/formWriteNotice", method = RequestMethod.GET)
	public ModelAndView fromWriteNotice() {
		System.out.println("Controller - formWriteNotice");
		ModelAndView mav = new ModelAndView();

		mav.setViewName("formWriteNotice");
		return mav;
	}
	
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public ModelAndView noticeUpdate(@RequestParam int idx) throws Exception {
		System.out.println("Controller - noticeUpdate");
		ModelAndView mav = new ModelAndView();
		
		NoticeDTO noticeUpdate = this.noticeService.noticeInfoView(idx);
		mav.addObject("noticeUpdate", noticeUpdate);
		mav.setViewName("noticeUpdate");
		
		return mav;
	}

	@RequestMapping(value = "/ProUpdateNotice", method = RequestMethod.POST)
	public ModelAndView ProUpdateNotice(@ModelAttribute NoticeDTO noticeDto) throws Exception {
		System.out.println("Controller - ProUpdateNotice");
		ModelAndView mav = new ModelAndView();

		noticeService.noticeUpdate(noticeDto);

		System.out.println("toString ===================\n" + noticeDto.toString() + "\n" + "====================");

		int idx = noticeDto.getIdx();

		mav.setViewName("redirect:noticeInfoView?idx=" + idx);
		return mav;

	}

	@RequestMapping(value = "/ProWriteNotice", method = RequestMethod.POST)
	public ModelAndView ProWriteNotice(@ModelAttribute NoticeDTO noticeDto) throws Exception {
		System.out.println("Controller - ProWriteNotice");
		ModelAndView mav = new ModelAndView();

		noticeService.ProWriteNotice(noticeDto);

		mav.setViewName("redirect:list");
		return mav;
	}

}
