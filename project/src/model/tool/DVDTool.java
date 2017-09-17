package model.tool;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.DVD;
import util.DBManager;

public class DVDTool extends Tool<DVD> {

	@Override
	public boolean isExist(DVD obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int add(DVD obj) {
		String query = "INSERT INTO " + DBManager.TABLE_DVD
				+ " (title, author, publisher, publishdate) VALUES ( '" + obj.getTitle() + "', '"
				+ obj.getAuthor() + "', '" + obj.getPublisher() + "', '" + obj.getPublishDate() + "');";

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
	public boolean remove(DVD obj) {
		String query = "DELETE FROM " + DBManager.TABLE_DVD + " WHERE idx = '" + obj.getDVDNum() + "'";

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
	public boolean edit(DVD obj) {
		String query = "UPDATE " + DBManager.TABLE_DVD + " SET title='" + obj.getTitle() + "', author='"
				+ obj.getAuthor() + "', publisher='" + obj.getPublisher() + "', publishdate='" + obj.getPublishDate()
				+ "', state='"+obj.getState()+"' WHERE idx='" + obj.getDVDNum() + "';";

		try {
			DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public DVD find(int index) {
		String query = "SELECT * FROM " + DBManager.TABLE_DVD + " WHERE idx='" + index + "';";
		ResultSet result = null;
		DVD DVD = new DVD();
		try {
			result = DBManager.getInstance().query(query);
			if (result.next()) {
				DVD.setDVDNum(result.getInt("idx"));
				DVD.setTitle(result.getString("title"));
				DVD.setAuthor(result.getString("author"));
				DVD.setPublisher(result.getString("publisher"));
				DVD.setPublishDate(result.getString("publishdate"));
				DVD.setState(result.getInt("state"));
				DVD.setTimestamp(result.getString("timestamp"));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return DVD;
	}

	@Override
	public DVD find(DVD obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DVD> findAll() {
		String query = "SELECT * FROM " + DBManager.TABLE_DVD + ";";
		ResultSet result = null;
		List<DVD> list = new ArrayList<DVD>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				DVD DVD = new DVD();
				DVD.setDVDNum(result.getInt("idx"));
				DVD.setTitle(result.getString("title"));
				DVD.setAuthor(result.getString("author"));
				DVD.setPublisher(result.getString("publisher"));
				DVD.setPublishDate(result.getString("publishdate"));
				DVD.setState(result.getInt("state"));
				DVD.setTimestamp(result.getString("timestamp"));

				list.add(DVD);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	

	public List<DVD> searchTitle(String text) {
		String query = "select * from "+DBManager.TABLE_DVD+" where title like '%"+text+"%'";
		ResultSet result = null;
		List<DVD> list = new ArrayList<DVD>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				DVD DVD = new DVD();
				DVD.setDVDNum(result.getInt("idx"));
				DVD.setTitle(result.getString("title"));
				DVD.setAuthor(result.getString("author"));
				DVD.setPublisher(result.getString("publisher"));
				DVD.setPublishDate(result.getString("publishdate"));
				DVD.setState(result.getInt("state"));
				DVD.setTimestamp(result.getString("timestamp"));

				list.add(DVD);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	public List<DVD> searchAuth(String text) {
		String query = "select * from "+DBManager.TABLE_DVD+" where author like '%"+text+"%'";
		ResultSet result = null;
		List<DVD> list = new ArrayList<DVD>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				DVD DVD = new DVD();
				DVD.setDVDNum(result.getInt("idx"));
				DVD.setTitle(result.getString("title"));
				DVD.setAuthor(result.getString("author"));
				DVD.setPublisher(result.getString("publisher"));
				DVD.setPublishDate(result.getString("publishdate"));
				DVD.setState(result.getInt("state"));
				DVD.setTimestamp(result.getString("timestamp"));

				list.add(DVD);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	public List<DVD> searchPublisher(String text) {
		String query = "select * from "+DBManager.TABLE_DVD+" where publisher like '%"+text+"%'";
		ResultSet result = null;
		List<DVD> list = new ArrayList<DVD>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				DVD DVD = new DVD();
				DVD.setDVDNum(result.getInt("idx"));
				DVD.setTitle(result.getString("title"));
				DVD.setAuthor(result.getString("author"));
				DVD.setPublisher(result.getString("publisher"));
				DVD.setPublishDate(result.getString("publishdate"));
				DVD.setState(result.getInt("state"));
				DVD.setTimestamp(result.getString("timestamp"));

				list.add(DVD);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
}
