package org.zerock.springex.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

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
		StringBuilder builder = new StringBuilder();
		
		builder.append("page=" + this.page);
		builder.append("&size=" + this.size);
		
		if(finished) {
			builder.append("&finished=on");
		}
		
		if(types != null && types.length > 0) {
			for(int i = 0; i < types.length; i++) {
				builder.append("&types=" + types[i]);
			}
		}
		
		if(keyword != null) {
			try {
				// 한글 깨짐 처리
				builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(from != null) {
			builder.append("&from=" + from.toString());
		}
		
		if(to != null) {
			builder.append("&to=" + to.toString());
		}
		
		return builder.toString();
	}
	
	public boolean checkType(String type) {
		
		if(types == null || types.length == 0) {
			return false;
		}
		
		return Arrays.stream(types).anyMatch(type::equals);
	}
}
