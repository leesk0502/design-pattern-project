package controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import model.Book;
import model.Loan;
import model.tool.BookTool;
import model.tool.LoanTool;
import model.tool.Tool;
import util.LoginManager;
import util.search.Searcher;
import util.search.SearcherBuilder;
import view.user.JBook;

public class CBook {
	List<Book> bookList = new ArrayList<Book>();
	List<Loan> loanList = new ArrayList<Loan>();
	JBook view;

	public CBook(JBook view) {
		this.view = view;
		// Adding Listener to view
		this.view.addSearchButtonListener(addSearchButtonListener);
		this.view.addBorrowButtonListener(addBorrowButtonListener);
		this.view.addReturnButtonListener(addReturnButtonListener);

		// this.view.addListSelectionListener(addListSelectionListener);

		initTableRows();
		initLoanTableRow();
	}

	private void initTableRows() {
		BookTool tool = new BookTool();
		bookList = tool.findAll();
		for (int i = 0; i < bookList.size(); i++) {
			view.addRow(bookList.get(i));
		}
		view.updateTableColumnIdx(view.getBookTable());
		view.setRowSelectionInterval(0, 0);
	}

	private void initLoanTableRow() {
		int userNum = LoginManager.getInstance().getMember().getUserNum();

		LoanTool tool = new LoanTool();
		loanList = tool.findAllUser(userNum);
		for (int i = 0; i < loanList.size(); i++) {
			if (loanList.get(i).getType() == Loan.ITEM_TYPE_BOOK)
				view.addLoanRow(loanList.get(i));
		}
		view.updateTableColumnIdx(view.getLoanTable());
		// view.setRowSelectionInterval(0, 0, view.getLoanTable());
	}

	ActionListener addSearchButtonListener = new ActionListener() {
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			view.refresh();
			int select = view.getComboSelectedIndex();

			Tool<Book> tool = new BookTool();
			bookList = tool.findAll();
			
			SearcherBuilder builder = new SearcherBuilder()
					.setItemList(bookList)
					.setSearchKeyword(view.getTextSearchInput());
			
			if (select == 0) {
				builder.setSearchField("title");
			} else if (select == 1) {
				builder.setSearchField("author");
			} else if (select == 2) {
				builder.setSearchField("publisher");
			}
			
			bookList = (List<Book>) builder.build().search();
			for (int i = 0; i < bookList.size(); i++) {
				view.addRow(bookList.get(i));
			}
			view.updateTableColumnIdx(view.getBookTable());
			view.setRowSelectionInterval(0, 0);
		}
	};

	ActionListener addBorrowButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			Book book = new Book();
			Loan loan = new Loan();
			int status;
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			String mon = String.format("%02d", cal.get(Calendar.MONTH) + 1);
			String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));

			String today = year + "-" + mon + "-" + day;

			cal.add(Calendar.DATE, 5);
			year = cal.get(Calendar.YEAR);
			mon = String.format("%02d", cal.get(Calendar.MONTH) + 1);
			day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));

			String returnDate = year + "-" + mon + "-" + day;

			int selectedItemIdx = -1;
			int userNum = LoginManager.getInstance().getMember().getUserNum();

			if (userNum < 0)
				return;

			selectedItemIdx = view.getBookRow();
			book = bookList.get(selectedItemIdx);

			if (book.getState() == 1) {
				JOptionPane.showMessageDialog(view, "현재 대여중인 도서입니다.");
				return;
			}

			loan.setItemNum(book.getBookNum());
			loan.setType(Loan.ITEM_TYPE_BOOK);
			status = Book.BOOK_STATE_BORROWED;

			book.setState(status);
			BookTool itemTool = new BookTool();
			itemTool.edit(book);

			bookList.set(selectedItemIdx, book);
			view.editBookRow(selectedItemIdx, book);

			bookList.set(selectedItemIdx, book);

			LoanTool tool = new LoanTool();
			loan.setMemberIndex(userNum);
			loan.setBorrowedDate(today);
			loan.setReturnDate(returnDate);

			tool.add(loan);

			view.addLoanRow(loan);

			loanList.add(loan);
			view.updateTableColumnIdxLoan();
		}
	};

	ActionListener addReturnButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			int selectedLoanIdx = view.getLoanRow();
			Book book = new Book();
			int status = -1;

			if (selectedLoanIdx < 0)
				return;

			Tool<Loan> tool = new LoanTool();
			Loan loan = loanList.get(selectedLoanIdx);
			tool.remove(loan);
			loanList.remove(selectedLoanIdx);

			int itemIdx = loan.getItemNum();

			status = Book.BOOK_STATE_NORMAL;
			Tool<Book> itemTool = new BookTool();
			book = itemTool.find(itemIdx);
			book.setState(status);
			itemTool.edit(book);

			for (int i = 0; i < bookList.size(); i++) {
				if (bookList.get(i).getBookNum() == itemIdx) {
					view.editBookRow(i, book);
					bookList.set(i, book);
					break;
				}
			}
			view.removeRow(selectedLoanIdx);
			view.updateTableColumnIdxLoan();
		}
	};

}
