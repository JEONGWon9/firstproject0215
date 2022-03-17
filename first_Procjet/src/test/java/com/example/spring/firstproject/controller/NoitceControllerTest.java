package com.example.spring.firstproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring.firstproject.service.NoticeService;

@WebMvcTest(NoitceController.class)
class NoitceControllerTest {
	
	
	@Autowired
	private MockMvc mvc; // 테스트 객체 주입
	
	@MockBean
	NoticeService noticeService;
	


	
	@Test
	@DisplayName("글조회")
	void testNoticeList() {
				
		
		
		fail("Not yet implemented");
	}
	/*
	 * @Test void testNoticeReply() { fail("Not yet implemented"); }
	 * 
	 * @Test void testNoticeInsertReply() { fail("Not yet implemented"); }
	 * 
	 * @Test void testNoticeDelete() { fail("Not yet implemented"); }
	 * 
	 * @Test void testNoticeInfoView() { fail("Not yet implemented"); }
	 * 
	 * @Test void testNoticeWriteForm() { fail("Not yet implemented"); }
	 * 
	 * @Test void testNoticeUpdate() { fail("Not yet implemented"); }
	 * 
	 * @Test void testProUpdateNotice() { fail("Not yet implemented"); }
	 * 
	 * @Test void testProWriteNotice() { fail("Not yet implemented"); }
	 * 
	 * @Test void testFileDown() { fail("Not yet implemented"); }
	 */

}
