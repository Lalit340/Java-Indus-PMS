package in.co.indusnet.project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.co.indusnet.task.model.TaskModel;
import in.co.indusnet.user.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="project")
@Getter
@Setter
public class ProjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PID")
	private int pid;
	
	@Column(name = "Pname")
	@NotNull
	private String name;
	
	@Column(name = "Description")
	@NotNull
	private String description;
	
	@ManyToMany(mappedBy = "projectModel")
	private List<UserModel> userModel;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "project_task",joinColumns = {@JoinColumn(name ="PID") },inverseJoinColumns = {@JoinColumn(name ="TID")})
	@JsonIgnore
	private List<TaskModel> taskModel;
}
