package com.example.spring.firstproject.service;

//비지니스 로직 처리 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.firstproject.mapper.NoticeMapper;



@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeMapper noticemapper;

	@Override
	public List<Map<String, Object>> SelectAllList() throws Exception {
		// TODO Auto-generated method stub
		return noticemapper.SelectAllList();
	}

}
