package org.zerock.springex.controller.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public String exceptCommen(Exception exception) { // 모든 예외 발생
		StringBuffer buffer = new StringBuffer("<ul>");
		
		buffer.append("<li>" + exception.getMessage() + "</li>");
		
		Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
			buffer.append("<li>"+stackTraceElement+"</li>");
		});
		
		buffer.append("</ul>");
		
		return buffer.toString();
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound() { // 존재하지 않는 페이지(경로) 접근
		return "custom404";
	}
}
