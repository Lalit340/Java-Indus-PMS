package in.co.indusnet.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "indus")
public class UserModal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EID")
	private int  id;
	
	@Column(name = "Ename")
	@NotNull
	private String  name;
	
	@Column(name = "Designation")
	@NotNull
	private String desg;
	
	@Column(name = "Email")
	@NotNull(message = "please provide a valid email")
	@Email(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$")
	private String mail;
	
	@Column(name = "MobNumber")
	@NotNull(message = "please provide a valid number")
	private String mobileNo;

	@Column(name = "Password")
	@NotNull(message = "please provide a valid password")
	private String password;

	
	public UserModal() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public UserModal(int id, @NotNull String name, @NotNull String desg, @NotNull String mail, @NotNull String mobileNo,
			@NotNull String password) {
		super();
		this.id = id;
		this.name = name;
		this.desg = desg;
		this.mail = mail;
		this.mobileNo = mobileNo;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesg() {
		return desg;
	}


	public void setDesg(String desg) {
		this.desg = desg;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


}
