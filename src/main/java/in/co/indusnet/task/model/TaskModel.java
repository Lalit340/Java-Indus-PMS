package in.co.indusnet.task.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import in.co.indusnet.project.model.ProjectModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Task")
@Setter
@Getter
public class TaskModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TID")
	private int tId;
	
	@Column(name = "TaskName")
	private String name;
	
	@Column(name = "Description")
	private String description;

	@Column(name = "Status")
	private boolean status;
	
	@ManyToOne
	private ProjectModel projectModel;

}
