package controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import model.Book;
import model.DVD;
import model.Loan;
import model.Member;
import model.tool.BookTool;
import model.tool.DVDTool;
import model.tool.LoanTool;
import model.tool.Tool;
import util.LoginManager;
import util.search.SearcherBuilder;
import view.user.JDVD;

public class CDVD {
	List<DVD> DVDList = new ArrayList<DVD>();
	List<Loan> loanList = new ArrayList<Loan>();
	JDVD view;

	public CDVD(JDVD view) {
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
		DVDTool tool = new DVDTool();
		DVDList = tool.findAll();
		for (int i = 0; i < DVDList.size(); i++) {
			view.addRow(DVDList.get(i));
		}
		view.updateTableColumnIdx(view.getDVDTable());
		view.setRowSelectionInterval(0, 0);
	}

	private void initLoanTableRow() {
		int userNum = LoginManager.getInstance().getMember().getUserNum();

		LoanTool tool = new LoanTool();
		loanList = tool.findAllUser(userNum);
		for (int i = 0; i < loanList.size(); i++) {
			if (loanList.get(i).getType() == Loan.ITEM_TYPE_DVD)
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

			Tool<DVD> tool = new DVDTool();
			DVDList = tool.findAll();
			
			SearcherBuilder builder = new SearcherBuilder()
					.setItemList(DVDList)
					.setSearchKeyword(view.getTextSearchInput());
			
			if (select == 0) {
				builder.setSearchField("title");
			} else if (select == 1) {
				builder.setSearchField("author");
			} else if (select == 2) {
				builder.setSearchField("publisher");
			}
			
			DVDList = (List<DVD>) builder.build().search();
			for (int i = 0; i < DVDList.size(); i++) {
				view.addRow(DVDList.get(i));
			}
			view.updateTableColumnIdx(view.getDVDTable());
			view.setRowSelectionInterval(0, 0);
		}
	};

	ActionListener addBorrowButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			DVD DVD = new DVD();
			Loan loan = new Loan();
			Member member = new Member();
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

			selectedItemIdx = view.getDVDRow();
			DVD = DVDList.get(selectedItemIdx);

			if (DVD.getState() == 1) {
				JOptionPane.showMessageDialog(view, "현재 대여중인 도서입니다.");
				return;
			}

			loan.setItemNum(DVD.getDVDNum());
			loan.setType(Loan.ITEM_TYPE_DVD);
			status = DVD.DVD_STATE_BORROWED;

			DVD.setState(status);
			DVDTool itemTool = new DVDTool();
			itemTool.edit(DVD);

			DVDList.set(selectedItemIdx, DVD);
			view.editDVDRow(selectedItemIdx, DVD);

			DVDList.set(selectedItemIdx, DVD);

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
			DVD DVD = new DVD();
			DVD dvd = new DVD();
			int status = -1;

			if (selectedLoanIdx < 0)
				return;

			Tool<Loan> tool = new LoanTool();
			Loan loan = loanList.get(selectedLoanIdx);
			tool.remove(loan);
			loanList.remove(selectedLoanIdx);

			int itemIdx = loan.getItemNum();

			status = DVD.DVD_STATE_NORMAL;
			Tool<DVD> itemTool = new DVDTool();
			DVD = itemTool.find(itemIdx);
			DVD.setState(status);
			itemTool.edit(DVD);
			
			for( int i=0; i<DVDList.size(); i++ ){
				if( DVDList.get(i).getDVDNum() == itemIdx ){
					view.editDVDRow(i, dvd);
					DVDList.set(i, dvd);
					break;
				}
			}
			
			view.updateTableColumnIdx(view.getLoanTable());
			view.refresh();
		}
	};

}
