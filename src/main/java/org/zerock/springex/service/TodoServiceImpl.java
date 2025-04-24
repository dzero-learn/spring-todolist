package org.zerock.springex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	
	public List<TodoDTO> getAll() {
//		List<TodoVO> voList = todoMapper.selectAll();
//		List<TodoDTO> dtoList = new ArrayList<TodoDTO>();
//		
//		for(TodoVO vo : voList) {
//			dtoList.add(modelMapper.map(vo, TodoDTO.class));
//			log.info(dtoList);
//		}
		
		List<TodoDTO> dtoList = todoMapper.selectAll().stream()
				.map(vo -> modelMapper.map(vo, TodoDTO.class))
				.collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	public TodoDTO getOne(Long tno) {
		TodoVO todoVO = todoMapper.selectOne(tno);
		TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
		
		return todoDTO;
	}

	@Override
	public void remove(Long tno) {
		todoMapper.delete(tno);
	}
}
