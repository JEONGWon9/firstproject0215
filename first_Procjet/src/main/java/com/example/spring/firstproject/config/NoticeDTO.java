package com.example.spring.firstproject.config;

import java.sql.Date;

import lombok.Data;

//@Data//get setter tostring equalsAndHashCode requiredArgsconstructor 종합 

@Data
public class NoticeDTO {
	 
	
	private int idx;		//	번호
	private String title;	//  제목	
	private String writer;	//  작성자
	private String pw;		// 	비밀번호
	private String content; // 	내용
	private int readCount; 	// 	조회수
	private Date writeDate; // 	작성일
	private int grpno;
	private int grpord;
	private int depth;		//  띄움
	
	private String startDate;
	private String endDate;
	
	
	
	//-----------------------------------------//
	
	
	
	

}
