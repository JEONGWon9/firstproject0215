package com.example.spring.firstproject.service;

//비지니스 로직 처리 

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.firstproject.config.NoticeDTO;
import com.example.spring.firstproject.mapper.NoticeMapper;

import lombok.extern.log4j.Log4j;




@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticemapper;


	@Override
	public List<NoticeDTO> SelectNoticeList(HttpServletRequest request, NoticeDTO noticeDto) throws Exception {
		// TODO Auto-generated method stub
		return noticemapper.SelectNoticeList();
	}

	@Override
	public int SelectNoticeListCount(HttpServletRequest request, NoticeDTO noticeDto) throws Exception {
		// TODO Auto-generated method stub
		return noticemapper.SelectNoticeListCount();
	}

	@Override
	public void ProWriteNotice(NoticeDTO noticeDto) {
	
		String title = noticeDto.getTitle();
		String writer = noticeDto.getWriter();
		String pw = noticeDto.getPw();
		String content = noticeDto.getContent();
		
		noticeDto.setTitle(title);
		noticeDto.setWriter(writer);
		noticeDto.setPw(pw);
		noticeDto.setContent(content);	
		
		noticemapper.ProWriteNotice(noticeDto);		
	}


	@Override
	public NoticeDTO noticeInfoView(int idx) {		
		return noticemapper.noticeInfoView(idx);
	}


	@Override
	public void noticeUpdate(NoticeDTO noticeDto) {
		int idx = noticeDto.getIdx();		
		String title = noticeDto.getTitle();
		String writer = noticeDto.getWriter();	
		String content = noticeDto.getContent();
		
		noticeDto.setIdx(idx);
		noticeDto.setTitle(title);
		noticeDto.setWriter(writer);		
		noticeDto.setContent(content);	
		
		
		 noticemapper.ProUpdateNotice(noticeDto);	
		
	}

	@Override
	public void noticeDelte(int idx) {
		
		noticemapper.DelteNotice(idx);
		
	}

	@Override
	public void noticeReadCount(int idx) {
		
		noticemapper.noticeReadCount(idx);
		
	}

}
	
