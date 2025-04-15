package org.zerock.springex.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {
	@Autowired
	private TodoService todoService;
	
	@Test
	public void testResgister() {
		TodoDTO todoDTO = TodoDTO.builder()
				.title("test service2....")
				.dueDate(LocalDate.of(2025,03,21))
				.writer("gong")
				.build();
		
		todoService.register(todoDTO);
	}
}
