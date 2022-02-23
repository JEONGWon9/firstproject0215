package com.example.spring.firstproject.config;


import lombok.Data;

@Data
public class FileDTO {
	
	private int idx;// 글번호
	private String filename;     //db파일명
	private String fileorignname; // 파일명
	private String fileurl;		  // 파일경로
	private int filenum;		  // 파일 번호

}
