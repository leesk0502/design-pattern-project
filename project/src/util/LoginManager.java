package util;

import model.Member;

public class LoginManager {
	private Member member = new Member();

	private LoginManager() {
	};

	private static class SingleHolder {
		public static LoginManager single = new LoginManager();
	}

	public static LoginManager getInstance() {
		return SingleHolder.single;
	}

	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
}
