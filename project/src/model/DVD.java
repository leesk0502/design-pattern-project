package model;

public class DVD {
	public final static String[] SEARCH_STRINGS = { "제목", "저자", "출판사" };
	public final static String[] DVD_STATE = { "대출 가능", "대여중", "연체중" };
	public final static int DVD_STATE_NORMAL = 0;
	public final static int DVD_STATE_BORROWED = 1;
	public final static int DVD_STATE_DELAY = 2;
	private int state; // 대여여부
	private int DVDNum; // 도서 순번
	private String title; // 도서명
	private String author; // 저자
	private String publisher; // 출판사
	private String publishdate;
	private String timestamp;

	public DVD() {
		DVDNum = -1;
		title = "";
		author = "";
		publisher = "";
		publishdate = "";
		// state = 0;
	}

	public DVD(int num, String title2, String author2, String publisher2, String publishdate2, int state2) {
		DVDNum = num;
		title = title2;
		author = author2;
		publisher = publisher2;
		publishdate = publishdate2;
		state = state2;
	}

	public int getDVDNum() {
		return DVDNum;
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

	public int getState() {
		return state;
	}

	public void setDVDNum(int idx) {
		this.DVDNum = idx;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPublishDate(String publishdate) {
		this.publishdate = publishdate;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return this.timestamp;
	}
}
