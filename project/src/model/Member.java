package model;

public class Member {
	public final static String[] AUTH_STRINGS = { "일반 회원", "관리자" };
	public final static int AUTH_MEMBER = 0;
	public final static int AUTH_ADMIN = 1;
	public final static int AUTH_NONE = -1;

	private int userNum;
	private String userID;
	private String password;
	private String userName;
	private int privilege;
	private String timestamp;
	
	public Member() {
		this.userNum = -1;
		this.userID = null;
		this.password = null;
		this.userName = null;
		this.privilege = AUTH_NONE;
	}

	public Member(String id, String pass, int priv) {
		this.userID = id;
		this.password = pass;
		this.privilege = priv;
	}

	public Member(String id, String pass, String name, int priv) {
		this.userID = id;
		this.password = pass;
		this.userName = name;
		this.privilege = priv;
	}
	
	public Member(int num, String id, String pass, String name, int priv) {
		this.userNum = num;
		this.userID = id;
		this.password = pass;
		this.userName = name;
		this.privilege = priv;
	}

	public int getUserNum() {
		return this.userNum;
	}

	public String getUserID() {
		return this.userID;
	}

	public String getPassword() {

		return this.password;
	}

	public String getName() {
		return this.userName;
	}

	public int getPrivilege() {
		return this.privilege;
	}

	public void setUserNum(int idx){
		this.userNum = idx;
	}
	
	public void setUserID(String id) {
		this.userID = id;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public void setPrivilege(int pri) {
		this.privilege = pri;
	}
	
	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
	
	public String getTimestamp(){
		return this.timestamp;
	}
}
