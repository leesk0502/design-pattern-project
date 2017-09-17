package model;

public class Loan 
{
	public final static String[] ITEM_TYPE = { "Book", "DVD", "Journal" };
	public final static int ITEM_TYPE_BOOK = 0;
	public final static int ITEM_TYPE_DVD = 1;
	public final static int ITEM_TYPE_JOURNAL = 2;
	private int itemNum; 
	private int type; 
	private int memberIdx; 
	private String title;
	private String borrowDate;
	private String returnDate;
	private String timeStamp;
	
	
	public Loan() {
		itemNum = -1; 
		title = ""; 
		type = -1; 
		memberIdx = -1;
		borrowDate = "";
		returnDate = "";
	}
	public Loan(int itemNum, int type, int memberIdx, String title, String borrowDate, String returnDate) {
		this.itemNum = itemNum;
		this.type = type;
		this.memberIdx = memberIdx;
		this.title = title;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}

	public int getItemNum() {
		return itemNum;
	}

	public int getType(){
		return type;
	}
	public String getTitle() {
		return title;
	}

	public int getMemberIndex(){
		return memberIdx;
	}
	
	public String getBorrowedDate(){
		return borrowDate;
	}
	
	public String getReturnDate(){
		return returnDate;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public void setItemNum(int itemNum2) {
		this.itemNum = itemNum2;
	}

	public void setType(int type2) {
		this.type = type2;
	}
	
	public void setTitle(String title2) {
		this.title = title2;
	}
	
	public void setMemberIndex(int memberIdx2) {
		this.memberIdx = memberIdx2;
	}
	
	public void setBorrowedDate(String borrowDate2) {
		this.borrowDate = borrowDate2;
	}
	
	public void setReturnDate(String returnDate2) {
		this.returnDate = returnDate2;
	}
	
	public void setTimestamp(String timeStamp) {
		this.timeStamp = timeStamp;
		
	}

}
