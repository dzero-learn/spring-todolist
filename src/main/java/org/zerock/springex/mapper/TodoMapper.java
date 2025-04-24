package org.zerock.springex.mapper;

import java.util.List;

import org.zerock.springex.domain.TodoVO;

public interface TodoMapper {
	String getTime();
	
	void insert(TodoVO todoVO);
	
	List<TodoVO> selectAll();
	
	TodoVO selectOne(Long tno);
	
	void delete(Long tno);
}
