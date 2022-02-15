package com.example.spring.firstproject.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.spring.firstproject.config.NoticeDTO;

//제어할일은 없지만 MVC 패턴 적용을위해 사용
public interface NoticeService {

	public List<NoticeDTO> SelectNoticeList(HttpServletRequest request, NoticeDTO noticeDto) throws Exception;

	public int SelectNoticeListCount(HttpServletRequest request, NoticeDTO noticeDto) throws Exception;

	
	public void ProWriteNotice(NoticeDTO noticeDto);

	public void updateReadCount(int idx, HttpSession session);


	public NoticeDTO noticeInfoView(int idx);
	
	

}
