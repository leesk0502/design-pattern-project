package model;

public class Book {
	public final static String[] SEARCH_STRINGS = { "제목", "저자", "출판사" };
	public final static String[] BOOK_STATE = { "대출 가능", "대여중", "연체중" };
	public final static int BOOK_STATE_NORMAL = 0;
	public final static int BOOK_STATE_BORROWED = 1;
	public final static int BOOK_STATE_DELAY = 2;
	private int state; // 대여여부
	private int bookNum; // 도서 순번
	private String title; // 도서명
	private String author; // 저자
	private String publisher; // 출판사
	private String page;
	private String publishdate;
	private String personname; // 대여자
	private String timestamp;
	
	public Book() {
		bookNum = -1; // 도서 순번
		title = ""; // 도서명
		author = ""; // 저자
		publisher = ""; // 출판사
		publishdate = "";
		page = "";
		// state = 0;
	}

	public Book(int num, String title, String author, String publisher, String publishdate, String page, int state) {
		this.bookNum = num;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publishdate = publishdate;
		this.page = page;
		this.state = state; // 대여자 없음
	}

	/*
	 * public boolean isBorrow() { return borrow; } public void
	 * setBorrow(boolean borrow) { this.borrow = borrow; }
	 */
	public int getBookNum() {
		return bookNum;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublishDate() {
		return publishdate;
	}

	public String getPage() {
		return page;
	}

	public int getState() {
		return state;
	}

	public String getPersonname() {
		return personname;
	}

	public void setNum(int idx) {
		this.bookNum = idx;
	}

	public void setTitle(String title2) {
		this.title = title2;
	}

	public void setAuthor(String author2) {
		this.author = author2;
	}

	public void setPublisher(String publisher2) {
		this.publisher = publisher2;
	}

	public void setPublishDate(String publishdate2) {
		this.publishdate = publishdate2;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setState(int state2) {
		state = state2;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return this.timestamp;
	}
}