
public class Member {
	private String name;
	private boolean isMember;
	private String contact;
	
	Member(String name, boolean isMember, String contact) {
		this.name = name;
		this.isMember = isMember;
		this.contact = contact;
	}
	
	
	public String getName() {
		return name;
	}
	
	public boolean getIsMember() {
		return isMember;
	}
	
	public String getContact() {
		return contact;
	}
	
	public String toString() {
		return "Name: " + name + " Contact: " + contact;
	}

}
