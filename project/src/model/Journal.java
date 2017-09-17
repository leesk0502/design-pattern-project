package model;

public class Journal {
	public final static String[] SEARCH_STRINGS = { "제목", "출판사" };
	private int journalNum;
	private String title;
	private String publisher;
	private String publishdate;
	private String timestamp;

	public Journal() {
		journalNum = -1;
		title = "";
		publisher = "";
		publishdate = "";
	}

	public Journal(int num, String title2, String publisher2, String publishdate2) {
		journalNum = num;
		title = title2;
		publisher = publisher2;
		publishdate = publishdate2;
	}

	public int getJournalNum() {
		return journalNum;
	}

	public String getTitle() {
		return title;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublishDate() {
		return publishdate;
	}

	public void setJournalNum(int index) {
		this.journalNum = index;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPublishDate(String publishdate) {
		this.publishdate = publishdate;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return this.timestamp;
	}
}
