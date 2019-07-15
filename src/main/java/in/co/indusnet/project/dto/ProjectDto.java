package in.co.indusnet.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProjectDto {
	
	@NotNull(message = "Enter project Name")
	@NotBlank
	private String name;
	
	@NotNull(message = "Enter project Desciption")
	private String description;

}
