package org.zerock.springex.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {
	
	private Long tno;
	
	@NotEmpty
	private String title;
	
	@Future
	private LocalDate dueDate;
	
	@NotEmpty
	private String writer;
	
	private boolean finished;
}
