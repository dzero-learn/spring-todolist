package org.zerock.springex.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{	

	private final TodoMapper todoMapper; // 스프링이 생성자를 통해 자동으로 의존성 주입
	private final ModelMapper modelMapper;
	
	public void register(TodoDTO todoDTO) {
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
		log.info(todoVO);
		
		todoMapper.insert(todoVO);
	}
}
