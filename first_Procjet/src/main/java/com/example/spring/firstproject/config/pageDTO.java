package com.example.spring.firstproject.config;



import lombok.Data;


@Data
public class pageDTO {
	
	private int pageCount;// 페이지의 사이즈
	private int startPage; // 	게시판 시작번호
	private int endPage;   // 게시판 
	
	private int realEnd;// 마지막 페이지
	private boolean prev, next;
	private int total;// 전체 데이터 갯수
	
	private Criteria cri; // 페이지 번호 계산에 대한 필요한 클래스
	
	public pageDTO() {;}
	
	
	public pageDTO(int _total,int _pageCount, Criteria _cri) {
	
		this.total = _total;
		this.cri = _cri;
		this.pageCount= _pageCount;
		
		
		
		this.endPage = (int)(Math.ceil(_cri.getPageNum()*1.0/_pageCount))*_pageCount; // 화면상에 보이는 끝번호 공식
		this.startPage = this.endPage - (pageCount-1);// 시작번호 계산 (10개씩 보여준다면 시작번호는 무조건 끝에서 9를 뺀값)
		
		realEnd = (int)(Math.ceil((_total * 1.0) / _cri.getAmount()));
		
		
		
		if(endPage>realEnd) {
			endPage = realEnd== 0 ? 1 : realEnd;
		}// 전체 데이터 수에 영향 받는 끝번호에 대해 다시 계산 하는 작업.
		
		
		
		this.prev = this.startPage > 1;//이전 페이지
		this.next = this.endPage < realEnd;// 다음페이지
	}
	
	

}
