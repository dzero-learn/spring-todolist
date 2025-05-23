package org.zerock.springex.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter // 페이징 처리 관련 값을 읽기만 하므로 
@ToString
public class PageResponseDTO<E> {

	private int page;
	private int size;
	private int total;

	// 현재 페이지 시작번호
	private int start;
	// 현재 페이지 끝번호
	private int end;
	
	// 이전 페이지 존재여부
	private boolean prev;
	// 다음 페이지 존재여부
	private boolean next;
	
	private List<E> dtoList;
	
	@Builder(builderMethodName = "withAll")
	public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
		this.page = pageRequestDTO.getPage();
		this.size = pageRequestDTO.getSize();
		
		this.total = total;
		this.dtoList = dtoList;
		
		this.end = (int)(Math.ceil(this.page/10.0))*10;
		this.start = this.end-9;
		int last = (int)(Math.ceil(total/(double)this.size));
		
		this.end = end > last ? last : end;
		
		// prev 값 셋팅
		this.prev = this.start > 1;
		
		// next 값 셋팅
		this.next = this.total > this.end*this.size;		
	}
}
