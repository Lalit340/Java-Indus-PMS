package in.co.indusnet.user.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterDto {

	
	@NotNull
	private String  name;
	
	@NotNull
	private String desg;
	
	@NotNull(message = "please provide a valid email")
	@Email(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$")
	private String mail;
	
	@NotNull(message = "please provide a valid number")
	@Pattern(regexp = "[0-9]{10}" , message = "provide valid mobile number")
	private String mobileNo;

	@NotNull(message = "please provide a valid password")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})" , message = "provide valid password ")
	private String password;

	public UserRegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegisterDto(@NotNull String name, @NotNull String desg,
			@NotNull(message = "please provide a valid email") @Email(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$") String mail,
			@NotNull(message = "please provide a valid number") String mobileNo,
			@NotNull(message = "please provide a valid password") String password) {
		super();
		this.name = name;
		this.desg = desg;
		this.mail = mail;
		this.mobileNo = mobileNo;
		this.password = password;
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

	@Override
	public String toString() {
		return "UserRegister [name=" + name + ", desg=" + desg + ", mail=" + mail + ", mobileNo=" + mobileNo
				+ ", password=" + password + "]";
	}

	
}
