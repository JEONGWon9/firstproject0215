package com.example.spring.firstproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.firstproject.config.FileDTO;
import com.example.spring.firstproject.config.NoticeDTO;
import com.example.spring.firstproject.config.pageDTO;
import com.example.spring.firstproject.config.Criteria;

//DB접근을위한 패키지(DAO) - Inserface

@Mapper // 해당 인터페이스가 Mybatis 인터페이스 임을 명시 해주는 어노테이션.
public interface NoticeMapper {

	// uu 메소드 명은 .xml select id명과 완전히 동일해야한다.

	List<NoticeDTO> SelectNoticeList(Criteria cri); // 리스트
	
	List<NoticeDTO> SelectMainNoticeList();
	
	int SelectNoticeListCount(Criteria cri);        // 카운트 

	void ProWriteNotice(NoticeDTO noticeDto);      // 글쓰기 
	
	void noticeInsertReply(NoticeDTO noticeDto);  // 답글

	NoticeDTO noticeInfoView(int idx);             //detail

	void ProUpdateNotice(NoticeDTO noticeDto);    //수정

	void DelteNotice(int idx);					// 삭제

	void noticeReadCount(int idx);				// 조회수

	void ProFileWriteNotice(NoticeDTO noticeDto); //파일 업로드 +글쓰기(안씀)

	void fileUpload(FileDTO fileDto); // 파일 업로드
	
	List<FileDTO> fileDetail(int idx); // 파일 디테일
	
	FileDTO fileDown(int filenum); // 파일다운로드
	
	void fileDelte(int idx);  // 파일 삭제 


	
	


}
