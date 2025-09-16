package org.zerock.springex.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

	@Builder.Default // @builder와 함께 사용할 떄, 기본값 유지를 위해 사용 -> @builder는 초기값을 무시하고 0,null로 세팅할 수 있음
	@Min(value = 1)
	@Positive // 양수(>0) 검증
	private int page = 1;
	
	@Builder.Default
	@Min(value = 10)
	@Max(value = 100)
	@Positive
	private int size = 10; // 한페이지에 보여줄 게시물 수
	private int totalCount; // 전체 게시물 수
	
	// 검색 필터링 관련
	private String[] types; // t:제목,w:작가
	private String keyword; // 검색어
	private boolean finished; // todo완료여부
	private LocalDate from; // 기간
	private LocalDate to; // 기간
	
	private String link;
	
	public int getSkip() {
		return (page-1)*size;
	}
	
	public String getLink() {
		if(link == null) {
			StringBuilder builder = new StringBuilder();
			builder.append("page=" + this.page);
			builder.append("&size=" + this.size);
			link = builder.toString();
		}
		
		return link;
	}
}
