package model.tool;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import util.DBManager;

public class MemberTool extends Tool<Member> {
	@Override
	public boolean isExist(Member obj) {
		String query = "SELECT * FROM " + DBManager.TABLE_MEMBER + " WHERE id='" + obj.getUserID() + "'";
		if (obj.getPassword() != null) {
			query += " and password='" + obj.getPassword() + "'";
		}
		if (obj.getPrivilege() >= 0) {
			query += " and privilege='" + obj.getPrivilege() + "'";
		}
		query += ";";

		ResultSet result = null;
		try {
			result = DBManager.getInstance().query(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		try {
			if (!result.next()) {
				return false;
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int add(Member obj) {
		String query = "INSERT INTO " + DBManager.TABLE_MEMBER + " (id, password, name, privilege) VALUES ( '"
				+ obj.getUserID() + "', '" + obj.getPassword() + "', '" + obj.getName() + "', " + obj.getPrivilege()
				+ ");";

		int result = -1;
		try {
			result = DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean remove(Member obj) {
		String query = "DELETE FROM " + DBManager.TABLE_MEMBER + " WHERE idx='" + obj.getUserNum() + "';";

		try {
			DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(Member obj) {
		String query = "UPDATE " + DBManager.TABLE_MEMBER + " SET id='" + obj.getUserID() + "', password='"
				+ obj.getPassword() + "', name='" + obj.getName() + "', privilege='" + obj.getPrivilege()
				+ "' WHERE idx='" + obj.getUserNum() + "';";

		try {
			DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public Member find(int index) {
		String query = "SELECT * FROM " + DBManager.TABLE_MEMBER + " WHERE idx='" + index + "';";
		ResultSet result = null;
		Member member = new Member();
		try {
			result = DBManager.getInstance().query(query);
			if (result.next()) {
				member.setUserNum(result.getInt("idx"));
				member.setUserID(result.getString("id"));
				member.setPassword(result.getString("password"));
				member.setName(result.getString("name"));
				member.setPrivilege(result.getInt("privilege"));
				member.setTimestamp(result.getString("timestamp"));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return member;
	}
	
	@Override
	public Member find(Member obj){
		String query = "SELECT * FROM " + DBManager.TABLE_MEMBER + " WHERE id='" + obj.getUserID() + "'";
		if (obj.getPassword() != null) {
			query += " and password='" + obj.getPassword() + "'";
		}
		if (obj.getPrivilege() >= 0) {
			query += " and privilege='" + obj.getPrivilege() + "'";
		}
		query += ";";
		ResultSet result = null;
		Member member = new Member();
		try {
			result = DBManager.getInstance().query(query);
			if (result.next()) {
				member.setUserNum(result.getInt("idx"));
				member.setUserID(result.getString("id"));
				member.setPassword(result.getString("password"));
				member.setName(result.getString("name"));
				member.setPrivilege(result.getInt("privilege"));
				member.setTimestamp(result.getString("timestamp"));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return member;
	}

	@Override
	public List<Member> findAll() {
		String query = "SELECT * FROM " + DBManager.TABLE_MEMBER + ";";
		ResultSet result = null;
		List<Member> list = new ArrayList<Member>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Member member = new Member();
				member.setUserNum(result.getInt("idx"));
				member.setUserID(result.getString("id"));
				member.setPassword(result.getString("password"));
				member.setName(result.getString("name"));
				member.setPrivilege(result.getInt("privilege"));
				member.setTimestamp(result.getString("timestamp"));

				list.add(member);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
}
