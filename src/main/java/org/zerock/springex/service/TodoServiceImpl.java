package org.zerock.springex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

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

		List<TodoDTO> dtoList = todoMapper.selectAll().stream().map(vo -> modelMapper.map(vo, TodoDTO.class))
				.collect(Collectors.toList());

		return dtoList;
	}

	public TodoDTO getOne(Long tno) {
		TodoVO todoVO = todoMapper.selectOne(tno);
		TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

		return todoDTO;
	}

	@Override
	public void remove(Long tno) {
		todoMapper.delete(tno);
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

		todoMapper.update(todoVO);
	}

	@Override
	public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
		List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
		List<TodoDTO> dtoList = voList.stream()
				.map(vo -> modelMapper.map(vo, TodoDTO.class))
				.collect(Collectors.toList());

		int total = todoMapper.getCount(pageRequestDTO);

		PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total(total)
				.build();

		return pageResponseDTO;
	}
}
