package model.tool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import util.DBManager;

public class BookTool extends Tool<Book> {

	@Override
	public boolean isExist(Book obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int add(Book obj) {
		String query = "INSERT INTO " + DBManager.TABLE_BOOK
				+ " (title, author, publisher, publishdate, page) VALUES ( '" + obj.getTitle() + "', '"
				+ obj.getAuthor() + "', '" + obj.getPublisher() + "', '" + obj.getPublishDate() + "', '" + obj.getPage()
				+ "');";

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
	public boolean remove(Book obj) {
		String query = "DELETE FROM " + DBManager.TABLE_BOOK + " WHERE idx = '" + obj.getBookNum() + "'";

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
	public boolean edit(Book obj) {
		String query = "UPDATE " + DBManager.TABLE_BOOK + " SET title='" + obj.getTitle() + "', author='"
				+ obj.getAuthor() + "', publisher='" + obj.getPublisher() + "', publishdate='" + obj.getPublishDate()
				+ "', page='" + obj.getPage() + "', state='"+obj.getState()+"' WHERE idx='" + obj.getBookNum() + "';";

		try {
			DBManager.getInstance().update(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public Book find(int index) {
		String query = "SELECT * FROM " + DBManager.TABLE_BOOK + " WHERE idx='" + index + "';";
		ResultSet result = null;
		Book book = new Book();
		
		try {
			result = DBManager.getInstance().query(query);
			if (result.next()) {
				book.setNum(result.getInt("idx"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setPublisher(result.getString("publisher"));
				book.setPublishDate(result.getString("publishdate"));
				book.setPage(result.getString("page"));
				book.setState(result.getInt("state"));
				book.setTimestamp(result.getString("timestamp"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return book;
	}

	@Override
	public Book find(Book obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll() {
		String query = "SELECT * FROM " + DBManager.TABLE_BOOK + ";";
		ResultSet result = null;
		List<Book> list = new ArrayList<Book>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Book book = new Book();
				book.setNum(result.getInt("idx"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setPublisher(result.getString("publisher"));
				book.setPublishDate(result.getString("publishdate"));
				book.setPage(result.getString("page"));
				book.setState(result.getInt("state"));
				book.setTimestamp(result.getString("timestamp"));

				list.add(book);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	

	public List<Book> searchTitle(String text) {
		String query = "select * from "+DBManager.TABLE_BOOK+" where title like '%"+text+"%'";
		ResultSet result = null;
		List<Book> list = new ArrayList<Book>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Book book = new Book();
				book.setNum(result.getInt("idx"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setPublisher(result.getString("publisher"));
				book.setPublishDate(result.getString("publishdate"));
				book.setPage(result.getString("page"));
				book.setState(result.getInt("state"));
				book.setTimestamp(result.getString("timestamp"));

				list.add(book);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	public List<Book> searchAuth(String text) {
		String query = "select * from "+DBManager.TABLE_BOOK+" where author like '%"+text+"%'";
		ResultSet result = null;
		List<Book> list = new ArrayList<Book>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Book book = new Book();
				book.setNum(result.getInt("idx"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setPublisher(result.getString("publisher"));
				book.setPublishDate(result.getString("publishdate"));
				book.setPage(result.getString("page"));
				book.setState(result.getInt("state"));
				book.setTimestamp(result.getString("timestamp"));

				list.add(book);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	public List<Book> searchPublisher(String text) {
		String query = "select * from "+DBManager.TABLE_BOOK+" where publisher like '%"+text+"%'";
		ResultSet result = null;
		List<Book> list = new ArrayList<Book>();

		try {
			result = DBManager.getInstance().query(query);
			while (result.next()) {
				Book book = new Book();
				book.setNum(result.getInt("idx"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setPublisher(result.getString("publisher"));
				book.setPublishDate(result.getString("publishdate"));
				book.setPage(result.getString("page"));
				book.setState(result.getInt("state"));
				book.setTimestamp(result.getString("timestamp"));

				list.add(book);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
}
