package toyproject;

public class Member {

	private String memberId;
	private String name;
	private String phoneNum;

	public Member() {

	}
	
	public Member(String memberId, String name, String phoneNum) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.phoneNum = phoneNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", phoneNum=" + phoneNum + "]";
	}

	

}
