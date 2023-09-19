package application;

public class Account {

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String userplan;

	public Account() {
	};

	public Account(String username, String password, String firstname, String lastname, String userplan) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.userplan = userplan;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUserPlan() {
		return userplan;
	}

	public void setRole(String userplan) {
		this.userplan = userplan;
	}

}
