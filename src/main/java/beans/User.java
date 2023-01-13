package beans;

public class User {
	
	private String email;
	private String password;
	private String name;
	private int id;
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(String name, int id) {
		this.name = name;
		this.setId(id);
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean validate(String email) {
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
