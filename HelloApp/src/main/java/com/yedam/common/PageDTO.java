package com.yedam.common;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageDTO {
	//전체 글 수 : 152건 
	//현재 페이지 : 2페이지
	//시작 페이지 : 1페이지
	//마지막 페이지 :10페이지   
	//이전여부 
	//이후여부
	//<< 1 (2) 3 4 5 6 7 8 9 10 >>   이런형태인듯 endPage = 10 startPage = 1
	//<< 11 12 13 14 15 16 17 18 19 20 >>   endPage = 20 startPage = 11
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int pageNum;
	
	public PageDTO(int pageNum,int total) { // pageNum = 현재 페이지 total = 전체 글의 개수 
		this.pageNum = pageNum;
		
		this.endPage = (int)Math.ceil(this.pageNum/10.0)*10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil(total/10.0)); // realEnd = 올림(전체 글 수/10.0) => realEnd 는 총 페이지 수
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage >1;
		this.next = this.endPage <realEnd;
		
	}
	
}
