package org.zerock.springex.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	@GetMapping("/list")
	public void list() {
		log.info("todo list.....");
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("todo controller.....");
	}	
	
	@PostMapping("/register")
	public String register(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		log.info(todoDTO);
		
		if(bindingResult.hasErrors()) {
			log.info(">>>>>>>>>>>>>>> error 걸림!!!!!");
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			log.info(bindingResult.getAllErrors());
			
			return "redirect:/todo/register";
		}
		
		todoService.register(todoDTO);
		
		return "redirect:/todo/list";
	}
}
