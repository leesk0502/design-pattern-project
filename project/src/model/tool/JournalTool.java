package model.tool;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Journal;
import model.Journal;
import util.DBManager;

public class JournalTool extends Tool<Journal> {

	@Override
	public boolean isExist(Journal obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int add(Journal obj) {
		String query = "INSERT INTO " + DBManager.TABLE_JOURNAL + " (title, publisher, publishdate) VALUES ( '"
				+ obj.getTitle() + "', '" + obj.getPublisher() + "', '" + obj.getPublishDate() + "');";

		int index = 0;
		try {
			index = DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return index;
	}

	@Override
	public boolean remove(Journal obj) {
		String query = "DELETE FROM " + DBManager.TABLE_JOURNAL + " WHERE idx = '" + obj.getJournalNum() + "'";

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
	public boolean edit(Journal obj) {
		String query = "UPDATE " + DBManager.TABLE_JOURNAL + " SET title='" + obj.getTitle() + "', publisher='"
				+ obj.getPublisher() + "', publishdate='" + obj.getPublishDate() + "' WHERE idx='" + obj.getJournalNum()
				+ "';";

		try {
			DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public Journal find(int index) {
		String query = "SELECT * FROM " + DBManager.TABLE_JOURNAL + " WHERE idx='" + index + "';";
		ResultSet result = null;
		Journal journal = new Journal();
		try {
			result = DBManager.getInstance().query(query);
			if (result.next()) {
				journal.setJournalNum(result.getInt("idx"));
				journal.setTitle(result.getString("title"));
				journal.setPublisher(result.getString("publisher"));
				journal.setPublishDate(result.getString("publishdate"));
				journal.setTimestamp(result.getString("timestamp"));
				System.out.println(journal.getTimestamp());
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return journal;
	}
	
	@Override
	public Journal find(Journal obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Journal> findAll() {
		String query = "SELECT * FROM " + DBManager.TABLE_JOURNAL + ";";
		ResultSet result = null;
		List<Journal> list = new ArrayList<Journal>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Journal journal = new Journal();
				journal.setJournalNum(result.getInt("idx"));
				journal.setTitle(result.getString("title"));
				journal.setPublisher(result.getString("publisher"));
				journal.setPublishDate(result.getString("publishdate"));
				journal.setTimestamp(result.getString("timestamp"));

				list.add(journal);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	
	
	
	public List<Journal> searchTitle(String text) {
		String query = "select * from "+DBManager.TABLE_JOURNAL+" where title like '%"+text+"%'";
		ResultSet result = null;
		List<Journal> list = new ArrayList<Journal>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Journal Journal = new Journal();
				Journal.setJournalNum(result.getInt("idx"));
				Journal.setTitle(result.getString("title"));
				Journal.setPublisher(result.getString("publisher"));
				Journal.setPublishDate(result.getString("publishdate"));
				Journal.setTimestamp(result.getString("timestamp"));

				list.add(Journal);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	public List<Journal> searchAuth(String text) {
		String query = "select * from "+DBManager.TABLE_JOURNAL+" where author like '%"+text+"%'";
		ResultSet result = null;
		List<Journal> list = new ArrayList<Journal>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Journal Journal = new Journal();
				Journal.setJournalNum(result.getInt("idx"));
				Journal.setTitle(result.getString("title"));
				Journal.setPublisher(result.getString("publisher"));
				Journal.setPublishDate(result.getString("publishdate"));
				Journal.setTimestamp(result.getString("timestamp"));

				list.add(Journal);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	public List<Journal> searchPublisher(String text) {
		String query = "select * from "+DBManager.TABLE_JOURNAL+" where publisher like '%"+text+"%'";
		ResultSet result = null;
		List<Journal> list = new ArrayList<Journal>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Journal Journal = new Journal();
				Journal.setJournalNum(result.getInt("idx"));
				Journal.setTitle(result.getString("title"));
				Journal.setPublisher(result.getString("publisher"));
				Journal.setPublishDate(result.getString("publishdate"));
				Journal.setTimestamp(result.getString("timestamp"));

				list.add(Journal);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
}
