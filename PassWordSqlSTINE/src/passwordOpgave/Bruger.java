package passwordOpgave;

public class Bruger {
	private int userid;
	private String Username;
	private String pasword;
	
	public Bruger(int userid, String username, String pasword) {
		this.userid = userid;
		this.Username = username;
		this.pasword = pasword;
	}

	public int getUserid() {
		return userid;
	}

	public String getUsername() {
		return Username;
	}

	public String getPasword() {
		return pasword;
	}	
}

