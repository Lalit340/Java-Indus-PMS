package in.co.indusnet.project.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import in.co.indusnet.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "proj")
@Setter
@Getter
public class ProjectModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PID")
	private int pId;
	
	@Column(name = "ProjectName")
	private String name;
	
	@Column(name = "Description")
	private String description;

	@ManyToMany
	private List<UserModel> userModel;

}
