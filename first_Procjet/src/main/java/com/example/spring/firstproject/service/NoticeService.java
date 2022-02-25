package com.example.spring.firstproject.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.spring.firstproject.config.FileDTO;
import com.example.spring.firstproject.config.NoticeDTO;
import com.example.spring.firstproject.config.pageDTO;
import com.example.spring.firstproject.config.Criteria;

//제어할일은 없지만 MVC 패턴 적용을위해 사용
public interface NoticeService {

	public List<NoticeDTO> SelectNoticeList(NoticeDTO noticeDto,Criteria cri) throws Exception;

	public int SelectNoticeListCount(NoticeDTO noticeDto,Criteria cri) throws Exception;

	public List<NoticeDTO>SelectMainNoticeList(NoticeDTO noticeDto) throws Exception;
	
	public void ProWriteNotice(NoticeDTO noticeDto);	
	
	public void noticeInsertReply(NoticeDTO noticeDto);

	public void ProFileWriteNotice(NoticeDTO noticeDto);

	public NoticeDTO noticeInfoView(int idx);

	public void noticeUpdate(NoticeDTO noticeDto);

	public void noticeDelte(int idx);

	public void noticeReadCount(int idx);

	public void FileUpload(FileDTO fileDto);
	
	public List<FileDTO> fileDetail(int idx);
	
	
	public FileDTO fileDown(int filenum);
	
	public void fileDelete(int idx);


	
	

}
