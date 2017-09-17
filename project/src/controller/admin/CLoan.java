package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Book;
import model.DVD;
import model.Journal;
import model.Loan;
import model.Member;
import model.tool.BookTool;
import model.tool.DVDTool;
import model.tool.LoanTool;
import model.tool.MemberTool;
import model.tool.Tool;
import view.admin.JLoan;

public class CLoan {
	List<Member> memberList = new ArrayList<Member>();
	List<DVD> dvdList = new ArrayList<DVD>();
	List<Book> bookList = new ArrayList<Book>();
	List<Loan> loanList = new ArrayList<Loan>();
	JLoan view;
	static int TYPE = -1;

	public CLoan(JLoan view)
	{
		this.view = view;

		this.view.addLoanButtonListener(addLoanButtonListener);
		this.view.addReturnButtonListener(addReturnButtonListener);

		//this.view.addLoanListSelectionListener(addLoanListSelectionListener);
		this.view.addMemberListSelectionListener(addMemberListSelectionListener);
		this.view.addBookListSelectionListener(addBookListSelectionListener);
		this.view.addDvdListSelectionListener(addDvdListSelectionListener);
		
		initTable();
	}


	ActionListener addLoanButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Book book = new Book();
			DVD dvd = new DVD();
			Loan loan = new Loan();
			Member member = new Member();
			int status;
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			String mon = String.format("%02d", cal.get(Calendar.MONTH)+1);
			String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
			
			String today = year + "-" + mon + "-" + day;
			
			cal.add(Calendar.DATE, 5);
			year = cal.get(Calendar.YEAR);
			mon = String.format("%02d", cal.get(Calendar.MONTH)+1);
			day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
			
			String returnDate = year + "-" + mon + "-" + day;
			
			int selectedItemIdx = -1;
			int selectedMemberIdx = view.getMemberRow();
			
			if(selectedMemberIdx < 0)
				return;
						
			if(TYPE == Loan.ITEM_TYPE_BOOK) 
			{
				selectedItemIdx = view.getBookRow();
				book = bookList.get(selectedItemIdx); 
				
				if(book.getState() == 1)
				{
					JOptionPane.showMessageDialog(view, "현재 대여중인 도서입니다.");
					return;
				}
				
				loan.setItemNum(book.getBookNum());
				loan.setType(TYPE);
				status = Book.BOOK_STATE_BORROWED;
				
				book.setState(status);
				Tool<Book> itemTool = new BookTool();
				itemTool.edit(book);
				
				bookList.set(selectedItemIdx, book);
				view.editBookRow(selectedItemIdx, book);

				bookList.set(selectedItemIdx, book);
			}
			else if(TYPE == Loan.ITEM_TYPE_DVD) 
			{
				selectedItemIdx = view.getDvdRow();
				dvd = dvdList.get(selectedItemIdx);
				
				if(dvd.getState() == 1)
				{
					JOptionPane.showMessageDialog(view, "현재 대여중인 DVD 입니다.");
					return;
				}
				
				loan.setItemNum(dvd.getDVDNum());
				loan.setType(TYPE);
				status = DVD.DVD_STATE_BORROWED;
				
				dvd.setState(status);
				Tool<DVD> itemTool = new DVDTool();
				itemTool.edit(dvd);
				
				dvdList.set(selectedItemIdx, dvd);
				view.editDVDRow(selectedItemIdx, dvd);

				dvdList.set(selectedItemIdx, dvd);
			}
	
			if( selectedItemIdx < 0)
				return;

			member = memberList.get(selectedMemberIdx);

			Tool<Loan> tool = new LoanTool();
			loan.setMemberIndex(member.getUserNum());
			loan.setBorrowedDate(today);
			loan.setReturnDate(returnDate);
		
			tool.add(loan);
						
			view.addLoanRow(loan);
			
			loanList.add(loan);
		}
	};
	
	ActionListener addReturnButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedLoanIdx = view.getLoanRow();
			Book book = new Book();
			DVD dvd = new DVD();
			int status = -1;

			if (selectedLoanIdx < 0)
				return;

			
			Tool<Loan> tool = new LoanTool();
			Loan loan = loanList.get(selectedLoanIdx);
			tool.remove(loan);
			loanList.remove(selectedLoanIdx);
			
			int itemType = loan.getType();
			int itemIdx = loan.getItemNum();
			
			if(itemType == Loan.ITEM_TYPE_BOOK) 
			{
				status = Book.BOOK_STATE_NORMAL;
				Tool<Book> itemTool = new BookTool();
				book = itemTool.find(itemIdx);
				book.setState(status);
				itemTool.edit(book);
				for( int i=0; i<bookList.size(); i++ ){
					if( bookList.get(i).getBookNum() == itemIdx ){
						view.editBookRow(i, book);
						bookList.set(i, book);
						break;
					}
				}
			}
			
			else if(itemType == Loan.ITEM_TYPE_DVD) 
			{
				status = DVD.DVD_STATE_NORMAL;
				Tool<DVD> itemTool = new DVDTool();				
				dvd = itemTool.find(itemIdx);
				dvd.setState(status);
				itemTool.edit(dvd);
				for( int i=0; i<dvdList.size(); i++ ){
					if( dvdList.get(i).getDVDNum() == itemIdx ){
						view.editDVDRow(i, dvd);
						dvdList.set(i, dvd);
						break;
					}
				}
			}		
			
			view.removeLoanRow(selectedLoanIdx);
			view.updateTableColumnIdx(view.getLoanTable());

		}
	};

	ListSelectionListener addMemberListSelectionListener = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			// Table row가 선택되었을 때 오른쪽 패널에 사용자 정보를 출력한다.
			if (e.getSource() == view.getMemberModel() && e.getFirstIndex() >= 0) {
				int selectedIdx = view.getMemberRow();
				if (selectedIdx < 0)
					return;

				Member member = memberList.get(selectedIdx);
				view.setMemTextId(member.getUserID());
				view.setMemTextNum(member.getUserNum());
				view.setMemTextName(member.getName());
				view.setMemTextPriv(member.getPrivilege());
			}
		}
	};
	
	ListSelectionListener addBookListSelectionListener = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			TYPE = Loan.ITEM_TYPE_BOOK;
			// Table row가 선택되었을 때 오른쪽 패널에 사용자 정보를 출력한다.
			if (e.getSource() == view.getBookModel() && e.getFirstIndex() >= 0) {
				int selectedIdx = view.getBookRow();
				if (selectedIdx < 0)
					return;

				Book book = bookList.get(selectedIdx);
				view.setItemTextTitle(book.getTitle());
				view.setItemTextAuthor(book.getAuthor());
				view.setItemTextPublisher(book.getPublisher());
				view.setItemTextYear(book.getPublishDate());
			}
		}
	};
	
	ListSelectionListener addDvdListSelectionListener = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			TYPE = Loan.ITEM_TYPE_DVD;
			// Table row가 선택되었을 때 오른쪽 패널에 사용자 정보를 출력한다.
			if (e.getSource() == view.getDvdModel() && e.getFirstIndex() >= 0) {
				int selectedIdx = view.getDvdRow();
				if (selectedIdx < 0)
					return;

				DVD dvd = dvdList.get(selectedIdx);
				view.setItemTextTitle(dvd.getTitle());
				view.setItemTextAuthor(dvd.getAuthor());
				view.setItemTextPublisher(dvd.getPublisher());
				view.setItemTextYear(dvd.getPublishDate());
			}
		}
	};
	
	private void initTable() {
		initMemberTableRow();
		initBookTableRow();
		initDVDTableRow();
		initLoanTableRow();
	}
	
	private void initDVDTableRow()
	{
		Tool<DVD> tool = new DVDTool();
		dvdList = tool.findAll();
		for (int i = 0; i < dvdList.size(); i++) {
			view.addDVDRow(dvdList.get(i));
		}
		view.updateTableColumnIdx(view.getDVDTable());
		//view.setRowSelectionInterval(0, 0, view.getDVDTable());
	}
	
	private void initBookTableRow()
	{
		Tool<Book> tool = new BookTool();
		bookList = tool.findAll();
		for (int i = 0; i < bookList.size(); i++) {
			view.addBookRow(bookList.get(i));
		}
		view.updateTableColumnIdx(view.getBookTable());
		//view.setRowSelectionInterval(0, 0, view.getBookTable());
	}
	
	private void initLoanTableRow()
	{
		Tool<Loan> tool = new LoanTool();
		loanList = tool.findAll();
		for (int i = 0; i < loanList.size(); i++) {
			view.addLoanRow(loanList.get(i));
		}
		view.updateTableColumnIdx(view.getLoanTable());
		//view.setRowSelectionInterval(0, 0, view.getLoanTable());
	}
	
	private void initMemberTableRow()
	{
		Tool<Member> tool = new MemberTool();
		memberList = tool.findAll();
		for(int i=0; i<memberList.size(); i++){
			view.addMemberRow(memberList.get(i));
		}
		view.updateTableColumnIdx(view.getMemberTable());
		//view.setRowSelectionInterval(0, 0, view.getMemberTable());
	}
}
	
