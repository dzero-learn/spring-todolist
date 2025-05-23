package org.zerock.springex.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {
	@Autowired
	private TodoService todoService;
	@Autowired
	private TodoMapper todoMapper;
	
	@Test
	public void testResgister() {
		TodoDTO todoDTO = TodoDTO.builder()
				.title("test service2....")
				.dueDate(LocalDate.of(2025,03,21))
				.writer("gong")
				.build();
		
		todoService.register(todoDTO);
	}
	
	@Test
	public void testSelectAll() {
		List<TodoVO> voList = todoMapper.selectAll();
		
		voList.forEach(vo -> log.info(vo));;
	}
	
	@Test
	public void TestPaging() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);
		
		log.info(pageResponseDTO);
		
		pageResponseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));
	}
}
