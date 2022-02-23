package com.example.spring.firstproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;

import com.example.spring.firstproject.config.FileDTO;
import com.example.spring.firstproject.config.NoticeDTO;
import com.example.spring.firstproject.config.pageDTO;
import com.example.spring.firstproject.config.Criteria;
import com.example.spring.firstproject.service.NoticeService;

@Controller
public class NoitceController {

	@Autowired
	private NoticeService noticeService;
	
	
//-------------------------------------------------------------
//						메인 화면
//-------------------------------------------------------------
	@RequestMapping("/")
	public ModelAndView Index(ModelAndView mav) throws Exception {
		System.out.println("Controller - index");
		mav.setViewName("index");
		return mav;
	}
	
//-------------------------------------------------------------
//						공지 리스트
//-------------------------------------------------------------
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("cri") Criteria cri , Model model,NoticeDTO noticeDto) throws Exception {
		
		model.addAttribute("noitceList",noticeService.SelectNoticeList(noticeDto, cri));		
		model.addAttribute("pageMaker", new pageDTO(noticeService.SelectNoticeListCount(noticeDto), 10, cri));
		model.addAttribute("main", noticeService.SelectMainNoticeList(noticeDto));
		
		return "/list";
	}
	

//-------------------------------------------------------------
//						공지답글 쓰기(web)
//-------------------------------------------------------------
	@RequestMapping(value = "/noticeReply",method = RequestMethod.GET)
	public ModelAndView noticeReply(@RequestParam int idx) {
		ModelAndView mav = new ModelAndView();
		
		NoticeDTO noticeinfo = this.noticeService.noticeInfoView(idx);
		mav.addObject("noticeInfo", noticeinfo);
		
		mav.setViewName("noticeReply");
		return mav;		
	}

//-------------------------------------------------------------
//						공지 답글 쓰기(db)
//-------------------------------------------------------------
	@RequestMapping(value = "/noitceInsertReply",method = RequestMethod.POST)
	public ModelAndView noticeInsertReply(@ModelAttribute NoticeDTO noticeDto) {
		ModelAndView mav = new ModelAndView();
		
		noticeService.noticeInsertReply(noticeDto);
		
		mav.setViewName("redirect:list");
				
		return mav;
	}
	
//-------------------------------------------------------------
//						공지 삭제(데이터 파일 삭제)
//-------------------------------------------------------------	

	@RequestMapping(value = "noticeDelete", method = RequestMethod.GET)
	public ModelAndView noticeDelete(@RequestParam int idx) throws Exception {
		System.out.println("Controller - noticeDelete");
		ModelAndView mav = new ModelAndView();
		
		List<FileDTO> fileDto = noticeService.fileDetail(idx);
		for(int i=0;i<fileDto.size(); i++) {
			String path = fileDto.get(i).getFileurl()+fileDto.get(i).getFilename();
			
			File delteFile = new File(path);
			
			if(delteFile.exists()) {
				
				delteFile.delete();
				System.out.println(i+"번째 파일삭제 ");
			}else {
				System.out.println("파일이 없습니다.");
			}
			
		}
		noticeService.fileDelete(idx);
		
		noticeService.noticeDelte(idx);

		mav.setViewName("redirect:list");

		return mav;
	}
//-------------------------------------------------------------
//					게시물 상세보기 
//-------------------------------------------------------------

	@RequestMapping(value = "noticeInfoView", method = RequestMethod.GET)
	public ModelAndView noticeInfoView(@RequestParam int idx) throws Exception {
		System.out.println("Controller - noticeInfoView");
		ModelAndView mav = new ModelAndView();

		noticeService.noticeReadCount(idx);
		NoticeDTO noticeInfoView = this.noticeService.noticeInfoView(idx);

		mav.addObject("noticeInfoView", noticeInfoView);
		mav.addObject("idx", noticeService.noticeInfoView(idx));
		mav.setViewName("noticeInfoView");

		if (noticeService.fileDetail(idx) == null) {
			return mav;
		} else {
			mav.addObject("file", noticeService.fileDetail(idx));
			return mav;
		}

	}
	
//-------------------------------------------------------------
//					공지 게시물 쓰기(web)
//-------------------------------------------------------------
	@RequestMapping(value = "/formWriteNotice", method = RequestMethod.GET)
	public ModelAndView fromWriteNotice() {
		System.out.println("Controller - formWriteNotice");
		ModelAndView mav = new ModelAndView();

		mav.setViewName("formWriteNotice");
		return mav;
	}
	
//-------------------------------------------------------------
//					공지 게시물 수정(web)
//-------------------------------------------------------------

	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public ModelAndView noticeUpdate(@RequestParam int idx) throws Exception {
		System.out.println("Controller - noticeUpdate");
		ModelAndView mav = new ModelAndView();

		NoticeDTO noticeUpdate = this.noticeService.noticeInfoView(idx);
		mav.addObject("noticeUpdate", noticeUpdate);
		mav.setViewName("noticeUpdate");

		return mav;
	}
	
//-------------------------------------------------------------
//					공지 게시물 수정(db)
//-------------------------------------------------------------

	@RequestMapping(value = "/ProUpdateNotice", method = RequestMethod.POST)
	public ModelAndView ProUpdateNotice(@ModelAttribute NoticeDTO noticeDto) throws Exception {
		System.out.println("Controller - ProUpdateNotice");
		ModelAndView mav = new ModelAndView();

		noticeService.noticeUpdate(noticeDto);

		int idx = noticeDto.getIdx();

		mav.setViewName("redirect:noticeInfoView?idx=" + idx);
		return mav;

	}
	
//-------------------------------------------------------------
//					공지 게시물 쓰기(db)
//-------------------------------------------------------------

	@RequestMapping(value = "/ProWriteNotice", method = RequestMethod.POST)
	public String ProWriteNotice(@RequestParam("fileUpload") List<MultipartFile> mul,NoticeDTO noticeDto,HttpServletRequest request) {
				System.out.println("controller prowriteNotice");
		FileDTO fileDto = new FileDTO();
		String path;
		int insertSucess = 0;
		try {			
			if(insertSucess == 0) {
				noticeService.ProWriteNotice(noticeDto);
				System.out.println("글작성완료");
				if(mul.size()<0 && mul.get(0).getOriginalFilename().equals("")) {
					return "redirect:list";
				}
				else { //(mul.size()>0 && !mul.get(0).getOriginalFilename().equals("")) 
				
						for(MultipartFile file:mul) {
							path=request.getRealPath("upload/");
							System.out.println(path);
							
							String originFileName = file.getOriginalFilename();
							String fileExtention = originFileName.substring(originFileName.lastIndexOf("."));
							String saveFileName = UUID.randomUUID()+fileExtention;
							
							File targetFile = new File(path+saveFileName);
							try {
								InputStream fileStream = file.getInputStream();
								FileUtils.copyInputStreamToFile(fileStream, targetFile);
								
							}catch (Exception e) {
								//파일삭제
								FileUtils.deleteQuietly(targetFile);
								e.printStackTrace();
								break;
							}						
							
							fileDto.setIdx(noticeDto.getIdx());
							fileDto.setFilename(saveFileName);
							fileDto.setFileorignname(originFileName);
							fileDto.setFileurl(path);
							
							noticeService.FileUpload(fileDto);							
						}
					}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:list";
	}
	
//-------------------------------------------------------------
//					첨부파일 다운로드
//-------------------------------------------------------------
	

	@RequestMapping("/fileDown")
	public void fileDown(@RequestParam int filenum, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, Exception {
		System.out.println("Controller - fileDown");

		request.setCharacterEncoding("UTF-8");
		FileDTO fileDto = noticeService.fileDown(filenum);
		
		try {
			
					String fileURL = fileDto.getFileurl();							
					String savePath = fileURL;
					System.out.println("=======================================");
					System.out.println("savePath :: "+savePath);
					System.out.println("=======================================");
					
					String fileName = fileDto.getFilename();
					
					// 실제 내보낼 파일명
					String fileOriginName = fileDto.getFileorignname();					
					InputStream in = null;
					OutputStream out = null;
					File file = null;
					Boolean skip = false;
					String client = "";
					
					try {
						file = new File(savePath, fileName);
						in = new FileInputStream(file);
						
					} catch (FileNotFoundException fe) {
						skip = true;
					}
					
					client = request.getHeader("User-Agent");
					
					response.reset();
					response.setContentType("application/octest-stream");
					response.setHeader("Content-Dscription", "HTML Generated Data");
					
					if (!skip) {
						
						if (client.indexOf("MSIE") != 1) {
							System.out.println("-------------IE-------------");					
							response.setHeader("Content-Disposition", "attachment; filename=\""
									+ java.net.URLEncoder.encode(fileOriginName, "UTF-8").replaceAll("\\+", "\\") + "\"");
							
						} else if (client.indexOf("Trident") != -1) {
							System.out.println("-------------IE11 이상-------------");
							response.setHeader("Content-Disposition", "attachment; filename=\""
									+ java.net.URLEncoder.encode(fileOriginName, "UTF-8").replaceAll("\\+", "\\") + "\"");
						} else {
							System.out.println("------------그외-------------------");
							response.setHeader("Content-Disposition", "attachment; filename=\""
									+ new String(fileOriginName.getBytes("UTF-8"), "ISO8859_1") + "\"");
							response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
						}
						
						response.setHeader("Content-Length", "" + file.length());
						out = response.getOutputStream();
						byte b[] = new byte[(int) file.length()];
						int leng = 0;
						
						while ((leng = in.read(b)) > 0) {
							out.write(b, 0, leng);
						}
						
					} else {
						response.setContentType("text/html; charset=UTF-8");						
						PrintWriter pout = response.getWriter();
						pout.println("<script>alert('파일을 찾을수 없습니다.'); histroy.back();</script>");
						pout.flush();
					}
					
					in.close();
					out.close();
					
					
			
			
		} catch (Exception e) {
			System.out.println("error :: " + e.getStackTrace());
		}

	}

	/*
	 * // 드래그 모달창.
	 * 
	 * @RequestMapping(value = "dragUpload", method = RequestMethod.GET) public
	 * ModelAndView dragUpload() { ModelAndView mav = new ModelAndView();
	 * 
	 * mav.setViewName("dragUpload"); return mav;
	 * 
	 * }
	 */

}
