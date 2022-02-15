package com.example.spring.firstproject.config;

import java.sql.Date;

import lombok.Data;

//@Data//get setter tostring equalsAndHashCode requiredArgsconstructor 종합 

@Data
public class NoticeDTO {
	
	
	private int idx;
	private String title;
	private String writer;
	private String pw;
	private String content;
	private int readCount;
	private Date writeDate;
	private int grpno;
	private int grpord;
	private int depth;
	
	
	
	

}
