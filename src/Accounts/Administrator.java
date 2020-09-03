package Accounts;

public class Administrator{

	private String firstNameAdmin;
	private String lastNameAdmin;
	private String userNameAdmin;
	private String passwordAdmin;
	
	
	public Administrator() {
		
	}
	
	public Administrator(String firstNameAdmin, String lastNameAdmin, String userNameAdmin, String passwordAdmin) {
		super();
		this.firstNameAdmin = firstNameAdmin;
		this.lastNameAdmin = lastNameAdmin;
		this.userNameAdmin = userNameAdmin;
		this.passwordAdmin = passwordAdmin;
	}
	public String getFirstNameAdmin() {
		return firstNameAdmin;
	}
	public void setFirstNameAdmin(String firstNameAdmin) {
		this.firstNameAdmin = firstNameAdmin;
	}
	public String getLastNameAdmin() {
		return lastNameAdmin;
	}
	public void setLastNameAdmin(String lastNameAdmin) {
		this.lastNameAdmin = lastNameAdmin;
	}
	public String getUserNameAdmin() {
		return userNameAdmin;
	}
	public void setUserNameAdmin(String userNameAdmin) {
		this.userNameAdmin = userNameAdmin;
	}
	public String getPasswordAdmin() {
		return passwordAdmin;
	}
	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	
	
	
	
	
}
