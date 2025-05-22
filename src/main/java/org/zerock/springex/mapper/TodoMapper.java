package org.zerock.springex.mapper;

import java.util.List;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

public interface TodoMapper {
	String getTime();
	
	// todo 등록
	void insert(TodoVO todoVO);
	
	// todo 전체 목록 조회(페이징x)
	List<TodoVO> selectAll();
	
	// todo 상세 조회
	TodoVO selectOne(Long tno);
	
	// todo 삭제
	void delete(Long tno);
	
	// todo 수정
	void update(TodoVO todoVO);
	
	// todo 리스트 조회(페이징 처리)
	List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
	
	// 전체 페이지 수 계산(검색 필터가 있을 경우 대비 -> pageRequestDTO객체 파라미터 받음)
	int getCount(PageRequestDTO pageRequestDTO);
}
