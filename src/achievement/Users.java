package achievement;

public class Users {
	String name;
	int sc;
	public Users(){
		super();
	}
	public Users(String name, int sc) {
		this.name = name;
		this.sc = sc;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSc(int sc) {
		this.sc = sc;
	}
	public String getName() {
		return name;
	}
	public int getSc() {
		return sc;
	}
}
