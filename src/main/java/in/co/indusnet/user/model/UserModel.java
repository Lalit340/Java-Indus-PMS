package in.co.indusnet.user.model;

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

import in.co.indusnet.project.model.ProjectModel;
import in.co.indusnet.task.model.TaskModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "indus")
@Setter
@Getter
@AllArgsConstructor
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EID")
	private int id;

	@Column(name = "Ename")
	@NotNull
	private String name;

	@Column(name = "Designation")
	@NotNull
	private String desg;

	@Column(name = "Email")
	@NotNull(message = "please provide a valid email")
	private String mail;

	@Column(name = "MobNumber")
	@NotNull(message = "please provide a  number")
	private String mobileNo;

	@Column(name = "Password")
	@NotNull(message = "please provide a valid password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "indus_project",joinColumns = {@JoinColumn(name ="EID") },inverseJoinColumns = {@JoinColumn(name ="PID")})
	@JsonIgnore
	private List<ProjectModel> projectModel;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "indus_task",joinColumns = {@JoinColumn(name="EID")},inverseJoinColumns = {@JoinColumn(name ="TID")})
	@JsonIgnore
	private List<TaskModel> task;

	public UserModel() {
		super();
	}

}
