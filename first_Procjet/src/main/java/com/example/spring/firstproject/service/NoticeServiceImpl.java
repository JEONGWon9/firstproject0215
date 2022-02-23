package com.example.spring.firstproject.service;

//비지니스 로직 처리 

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.firstproject.config.FileDTO;
import com.example.spring.firstproject.config.NoticeDTO;
import com.example.spring.firstproject.config.Criteria;
import com.example.spring.firstproject.mapper.NoticeMapper;

import lombok.extern.log4j.Log4j;




@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticemapper;


	@Override
	public List<NoticeDTO> SelectNoticeList(NoticeDTO noticeDto,Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return noticemapper.SelectNoticeList(cri);
	}

	@Override
	public int SelectNoticeListCount(NoticeDTO noticeDto) throws Exception {
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

	@Override
	public void ProFileWriteNotice(NoticeDTO noticeDto) {
//		String title = noticeDto.getTitle();
//		String writer = noticeDto.getWriter();
//		String pw = noticeDto.getPw();
//		String content = noticeDto.getContent();
//		
//		noticeDto.setTitle(title);
//		noticeDto.setWriter(writer);
//		noticeDto.setPw(pw);
//		noticeDto.setContent(content);	
//		
//		noticemapper.ProFileWriteNotice(noticeDto);
		
	}

	@Override
	public void FileUpload(FileDTO fileDto) {
		
		noticemapper.fileUpload(fileDto);
		
		
	}

	@Override
	public List<FileDTO> fileDetail(int idx) {
	
		return noticemapper.fileDetail(idx);
	}

	@Override
	public List<NoticeDTO> SelectMainNoticeList(NoticeDTO noticeDto) throws Exception {
		
		return noticemapper.SelectMainNoticeList();
	}

	@Override
	public void noticeInsertReply(NoticeDTO noticeDto) {
		noticemapper.noticeInsertReply(noticeDto);
		
	}

	@Override
	public FileDTO fileDown(int filenum) {
		// TODO Auto-generated method stub
		return noticemapper.fileDown(filenum);
	}

	@Override
	public void fileDelete(int idx) {
		noticemapper.fileDelte(idx);
		
	}

	

}
	
