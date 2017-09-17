package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class DBManager {
	public final static String DBName = "oodp";
	public final static String DBURL = "jdbc:mysql://db.huy.kr:3306/" + DBName + "?autoReconnect=true";
	public final static String DBID = "oodp";
	public final static String DBPW = "oodp2016";

	public final static String TABLE_MEMBER = "member";
	public final static String TABLE_BOOK = "book";
	public final static String TABLE_DVD = "dvd";
	public final static String TABLE_JOURNAL = "journal";
	public final static String TABLE_LOAN = "borrowedItem";

	private Connection mConn;

	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			mConn = DriverManager.getConnection(DBURL, DBID, DBPW);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};

	private static class SingleHolder {
		public static DBManager single = new DBManager();
	}

	public static DBManager getInstance() {
		return SingleHolder.single;
	}

	/**
	 *
	 * @param query
	 *            String The query to be executed
	 * @return a ResultSet object containing the results or null if not
	 *         available
	 * @throws SQLException
	 */
	public ResultSet query(String query) throws SQLException {
		Statement statement = mConn.createStatement();
		ResultSet res = statement.executeQuery(query);
		return res;
	}

	/**
	 * @desc Method to insert, update, delete data to a table
	 * @param insertQuery
	 *            String The Insert query
	 * @return boolean
	 * @throws SQLException
	 */
	public int update(String query) throws SQLException {
		PreparedStatement stmt = mConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		int result = stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next())
			return rs.getInt(1);
		
		return result;
	}
}