package com.example.spring.firstproject.service;

import java.util.List;
import java.util.Map;
//제어할일은 없지만 MVC 패턴 적용을위해 사용
public interface NoticeService {
	
	public List<Map<String, Object>> SelectAllList() throws Exception;
	
	

}
