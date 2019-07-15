package in.co.indusnet.task.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TaskDto {
	
	@NotNull(message = "Enter Task Name")
	@NotBlank
	private String name;
	
	@NotNull(message = "Enter Task Desciption")
	private String description;

	private boolean status;
}
