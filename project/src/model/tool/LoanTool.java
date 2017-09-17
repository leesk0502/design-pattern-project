package model.tool;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Loan;
import util.DBManager;

public class LoanTool extends Tool<Loan>{
	
	@Override
	public boolean isExist(Loan obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int add(Loan obj) {
		String query = "INSERT INTO " + DBManager.TABLE_LOAN + " (item_idx, item_type, member_idx, borrowed_date, return_date) "
				+ "VALUES ('"+ obj.getItemNum() + "', '" + obj.getType() + "', '" + obj.getMemberIndex() + "', '" 
				+ obj.getBorrowedDate()	+ "', '" + obj.getReturnDate() + "');";

		//int result = -1;
		try {
			DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public boolean remove(Loan obj) {
		String query = "DELETE FROM " + DBManager.TABLE_LOAN + " WHERE member_idx='" + obj.getMemberIndex() 
		+ "' and item_idx='" + obj.getItemNum() + "' and item_type='" + obj.getType()+ "';";
 
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
	public boolean edit(Loan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Loan find(int index) {
		String query = "SELECT * FROM " + DBManager.TABLE_LOAN + " WHERE idx='" + index + "';";
		ResultSet result = null;
		Loan loan = new Loan();
		try {
			result = DBManager.getInstance().query(query);
			if (result.next()) {
				loan.setItemNum(result.getInt("item_idx"));
				loan.setMemberIndex(result.getInt("member_idx"));
				loan.setType(result.getInt("item_type"));
				loan.setBorrowedDate(result.getString("borrowed_date"));
				loan.setReturnDate(result.getString("return_date"));
				loan.setTimestamp(result.getString("timestamp"));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return loan;
	}

	@Override
	public Loan find(Loan obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> findAll()
	{
		String query = "SELECT * FROM " + DBManager.TABLE_LOAN + ";";
		ResultSet result = null;
		List<Loan> list = new ArrayList<Loan>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Loan loan = new Loan();
				loan.setItemNum(result.getInt("item_idx"));
				loan.setMemberIndex(result.getInt("member_idx"));
				loan.setType(result.getInt("item_type"));
				loan.setBorrowedDate(result.getString("borrowed_date"));
				loan.setReturnDate(result.getString("return_date"));
				loan.setTimestamp(result.getString("timestamp"));

				list.add(loan);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	public List<Loan> findAllUser(int index)
	{
		String query = "SELECT * FROM " + DBManager.TABLE_LOAN +" WHERE member_idx='"+index +"';";
		ResultSet result = null;
		List<Loan> list = new ArrayList<Loan>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Loan loan = new Loan();
				loan.setItemNum(result.getInt("item_idx"));
				loan.setMemberIndex(result.getInt("member_idx"));
				loan.setType(result.getInt("item_type"));
				loan.setBorrowedDate(result.getString("borrowed_date"));
				loan.setReturnDate(result.getString("return_date"));
				loan.setTimestamp(result.getString("timestamp"));

				list.add(loan);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}

}
