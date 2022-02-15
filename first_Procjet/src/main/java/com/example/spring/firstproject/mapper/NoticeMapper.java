package com.example.spring.firstproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.firstproject.config.NoticeDTO;

//DB접근을위한 패키지(DAO) - Inserface

@Mapper // 해당 인터페이스가 Mybatis 인터페이스 임을 명시 해주는 어노테이션.
public interface NoticeMapper {

	// uu 메소드 명은 .xml select id명과 완전히 동일해야한다.

	List<NoticeDTO> SelectNoticeList();

	void ProWriteNotice(NoticeDTO noticeDto);

	NoticeDTO noticeInfoView(int idx);

	int SelectNoticeListCount();

}
