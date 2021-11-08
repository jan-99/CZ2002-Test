import java.util.ArrayList;

public class MemberController {
	private ArrayList<Member> members;
	private static MemberController memberController = null;
	private double discountRate = 0.1;
	
	MemberController() {
		this.members = new ArrayList<Member>();
	}
	
	public static MemberController getInstance() {
        if (memberController == null) {
        	memberController = new MemberController();
        }
        return memberController;
    }
	
	public void createMember(String name, String contact) {
		try {
			if (contact.length() != 8)
				throw new Exception("Invalid contact number!");
			
			Member m = new Member(name, true, contact);
			members.add(m);
			System.out.println("New member created:\n" + m.toString());
		} catch (Exception e) {
			System.out.print("Member not created: ");
			System.out.println(e.getMessage());
		}
		
	}		
	
	
	public boolean checkIsMember(String contact) {
		for (Member m : members) {
			if (m.getContact().contains(contact)) {
				return m.getIsMember();
			}
		}
		return false;
	}
	
	public void removeMember(String contact) {
		try {
			if (contact.length() != 8)
				throw new Exception("Invalid contact number!");
			for (Member m : members) {
				if (m.getContact().contains(contact)) {
					members.remove(m);
					System.out.println("Member removed!");
					return;
				}
			}
			System.out.println("Member not found!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public ArrayList<Member> getAllMembers() {
		return members;
	}
	
	public double getDiscountRate() {
		return discountRate;
	}
}
