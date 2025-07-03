package org.zerock.springex.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
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
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("todo list........");
//		model.addAttribute("dtoList", todoService.getAll());
//	}
	
	@GetMapping("/list")
	public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		log.info("todo list........");
		
		if(bindingResult.hasErrors()) {
			log.info(">>>>>>>>>>>>>>>>>>> error 걸림!!!");
			pageRequestDTO = PageRequestDTO.builder().build(); // 잘못된 page/size값이 넘어온 경우 page,size 초기값으로 셋팅
		}
		
		model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
	}
	
	@GetMapping({"/read","/modify"})
	public void read(Long tno, PageRequestDTO pageRequestDTO, Model model) {
		log.info(pageRequestDTO);
		
		TodoDTO dto = todoService.getOne(tno);
		log.info(dto);
		
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/modify")
	public String modifyPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {
		log.info(todoDTO);
		
		if(bindingResult.hasErrors()) {
			log.info(">>>>>>>>>>>>>>> error 걸림!!!!!");
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			redirectAttributes.addAttribute("tno", todoDTO.getTno());
			
			log.info(bindingResult.getAllErrors());
			
			return "redirect:/todo/modify";
		}
		
		todoService.modify(todoDTO);
		
		redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
		redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
		return "redirect:/todo/list";
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("todo controller.....");
	}	
	
	@PostMapping("/register")
	public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
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
	
	@PostMapping("/remove")
	public String remove(Long tno, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {
		log.info("todo Remove...........");
		log.info(tno);
		todoService.remove(tno);
		
		redirectAttributes.addAttribute("page", 1);
		redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
		return "redirect:/todo/list"; 
	}
}
