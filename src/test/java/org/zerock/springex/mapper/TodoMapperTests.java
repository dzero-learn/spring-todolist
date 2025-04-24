package org.zerock.springex.mapper;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;
import org.zerock.springex.service.TodoServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {
	@Autowired
	private TodoMapper todoMapper;

	@Test
	public void testGetTime() {
		log.info(todoMapper.getTime());
	}

	@Test
	public void testInsert() {
		TodoVO todoVO = TodoVO.builder()
				.title("test insert....")
				.dueDate(LocalDate.of(2025,03,21))
				.writer("gong")
				.build();
		
		todoMapper.insert(todoVO);
	}
	
	@Test
	public void testselectOne() {
		long tno = 5L;
		
		log.info(todoMapper.selectOne(tno));
	}
	
	@Test
	public void testRemove() {
		long tno = 5L;
		
		todoMapper.delete(tno);
	}
}
