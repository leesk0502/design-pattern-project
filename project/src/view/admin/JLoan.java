package view.admin;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Book;
import model.DVD;
import model.Loan;
import model.Member;

import view.BaseViewPanel;

public class JLoan extends BaseViewPanel {

	JTable memberTable;
	DefaultTableModel memberTableModel;
	int mTotalCount;

	JTable bookTable;
	DefaultTableModel bookTableModel;

	JTable dvdTable;
	DefaultTableModel dvdTableModel;

	private JTable loanTable;
	DefaultTableModel loanTableModel;

	Vector<String> memberColumn;
	Vector<String> bookColumn;
	Vector<String> dvdColumn;
	Vector<String> loanColumn;
	Vector<String> tableRow;

	GridLayout btnLayout;
	GridBagLayout gridBagLayout;

	JPanel lPanel;
	JPanel memberPanel;
	JPanel itemPanel;
	JPanel bookPanel;
	JPanel dvdPanel;
	JPanel borrowedPanel;

	JPanel rPanel;
	JPanel memberInfoPanel;
	JPanel itemInfoPanel;
	JPanel btnPanel;

	Button btnLoan;
	Button btnReturn;

	ArrayList<TextField> memTextFieldList;
	ArrayList<TextField> itemTextFieldList;

	public JLoan() {

	}

	private void memberInfo() {
		int i = 0, j = 0;

		ArrayList<Label> labelList = new ArrayList<Label>();
		GridBagConstraints gc = new GridBagConstraints();

		labelList.add(new Label("ID", Label.LEFT));
		labelList.add(new Label("회원 번호", Label.LEFT));
		labelList.add(new Label("이름", Label.LEFT));
		labelList.add(new Label("회원등급", Label.LEFT));

		while (i++ < labelList.size())
			memTextFieldList.add(new TextField());

		Iterator<Label> labelIterator = labelList.iterator();
		Iterator<TextField> textIterator = memTextFieldList.iterator();

		memberInfoPanel.setMaximumSize(new Dimension(280, 280));
		memberInfoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		while (labelIterator.hasNext()) {
			memberInfoPanel.setLayout(gridBagLayout);

			Label label = labelIterator.next();
			TextField textField = textIterator.next();

			gc.gridx = 0;
			gc.gridy = j;
			gc.weightx = 0;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gridBagLayout.setConstraints(label, gc);
			memberInfoPanel.add(label);

			gc.gridx = 1;
			gc.gridy = j;
			gc.weightx = 5;
			gc.insets = new Insets(0, 0, 5, 5);
			gc.fill = GridBagConstraints.HORIZONTAL;
			gridBagLayout.setConstraints(textField, gc);
			memberInfoPanel.add(textField);

			j++;
		}
	}

	private void itemInfo() {
		int i = 0, j = 0;

		ArrayList<Label> labelList = new ArrayList<Label>();
		GridBagConstraints gc = new GridBagConstraints();

		labelList.add(new Label("제목", Label.LEFT));
		labelList.add(new Label("저자", Label.LEFT));
		labelList.add(new Label("출판사", Label.LEFT));
		labelList.add(new Label("출판년도", Label.LEFT));

		while (i++ < labelList.size())
			itemTextFieldList.add(new TextField());

		Iterator<Label> labelIterator = labelList.iterator();
		Iterator<TextField> textIterator = itemTextFieldList.iterator();

		itemInfoPanel.setMaximumSize(new Dimension(280, 300));
		itemInfoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		while (labelIterator.hasNext()) {
			itemInfoPanel.setLayout(gridBagLayout);

			Label label = labelIterator.next();
			TextField textField = textIterator.next();

			gc.gridx = 0;
			gc.gridy = j;
			gc.weightx = 0;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gridBagLayout.setConstraints(label, gc);
			itemInfoPanel.add(label);

			gc.gridx = 1;
			gc.gridy = j;
			gc.weightx = 5;
			gc.insets = new Insets(0, 0, 5, 5);
			gc.fill = GridBagConstraints.HORIZONTAL;
			gridBagLayout.setConstraints(textField, gc);
			itemInfoPanel.add(textField);

			j++;
		}
	}

	private void memberTable() {
		memberColumn.clear();
		memberColumn.addElement("순번");
		memberColumn.addElement("회원번호");
		memberColumn.addElement("아이디");
		memberColumn.addElement("회원명");
		memberColumn.addElement("회원 등급");
		memberColumn.addElement("등록일");

		memberTableModel = new DefaultTableModel(memberColumn, 0);
		memberTable = new JTable(memberTableModel);
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < memberTable.getColumnCount(); i++) {
			column = memberTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		memberTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(memberTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		memberPanel.setPreferredSize(new Dimension(520, 200));

		memberPanel.add(scrollPane);
	}

	private void itemTable() {
		bookTable();
		dvdTable();
		loanTable();

		JTabbedPane itemPane = new JTabbedPane();
		itemPane.addTab("도서", bookPanel);
		itemPane.addTab("DVD", dvdPanel);
		itemPane.addTab("대여목록", borrowedPanel);
		itemPane.setPreferredSize(new Dimension(450, 250));
		JScrollPane scrollPane = new JScrollPane(itemPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		itemPanel.add(itemPane);
	}

	private void bookTable() {
		bookColumn.clear();
		bookColumn.addElement("순번");
		bookColumn.addElement("제목");
		bookColumn.addElement("상태");
		bookColumn.addElement("저자");
		bookColumn.addElement("페이지");
		bookColumn.addElement("출판사");
		bookColumn.addElement("출판년도");
		bookColumn.addElement("등록일");

		bookTableModel = new DefaultTableModel(bookColumn, 0);
		bookTable = new JTable(bookTableModel);
		getBookTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < getBookTable().getColumnCount(); i++) {
			column = getBookTable().getColumnModel().getColumn(i);
			column.setPreferredWidth(90);
		}

		getBookTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(getBookTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		bookPanel.setPreferredSize(new Dimension(450, 100));
		bookPanel.add(scrollPane);

	}

	private void dvdTable() {
		dvdColumn.clear();
		dvdColumn.addElement("순번");
		dvdColumn.addElement("제목");
		dvdColumn.addElement("저자");
		dvdColumn.addElement("출판사");
		dvdColumn.addElement("출판년도");
		dvdColumn.addElement("상태");
		dvdColumn.addElement("등록일");

		dvdTableModel = new DefaultTableModel(dvdColumn, 0);
		dvdTable = new JTable(dvdTableModel);
		getDVDTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < getDVDTable().getColumnCount(); i++) {
			column = getDVDTable().getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}

		getDVDTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(getDVDTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		dvdPanel.setPreferredSize(new Dimension(450, 100));
		dvdPanel.add(scrollPane);
	}

	private void loanTable() {
		loanColumn.clear();
		loanColumn.addElement("순번");
		loanColumn.addElement("유형");
		loanColumn.addElement("고유 번호");
		loanColumn.addElement("대출자 idx");
		loanColumn.addElement("대출일");
		loanColumn.addElement("반납일");

		loanTableModel = new DefaultTableModel(loanColumn, 0);
		loanTable = new JTable(loanTableModel);
		getLoanTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < getLoanTable().getColumnCount(); i++) {
			column = getLoanTable().getColumnModel().getColumn(i);
			if (i < 4)
				column.setPreferredWidth(70);
			else
				column.setPreferredWidth(100);
		}

		getLoanTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(getLoanTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		borrowedPanel.setPreferredSize(new Dimension(450, 100));
		borrowedPanel.add(scrollPane);
	}

	public void addMemberRow(Member member) {
		tableRow = new Vector<String>();
		tableRow.addElement(memberTable.getRowCount() + "");
		tableRow.addElement(member.getUserNum() + "");
		tableRow.addElement(member.getUserID());
		tableRow.addElement(member.getName());
		tableRow.addElement(Member.AUTH_STRINGS[member.getPrivilege()]);

		// 뒤의 시분초 잘라서 표시
		String date = new String(member.getTimestamp());
		tableRow.addElement(date.substring(0, 10));

		memberTableModel.addRow(tableRow);
		//setRowSelectionInterval(memberTable.getRowCount() - 1, memberTable.getRowCount() - 1, memberTable);
	}

	public void addBookRow(Book book) {
		tableRow = new Vector<String>();
		tableRow.addElement(bookTable.getRowCount() + "");
		tableRow.addElement(book.getTitle());
		tableRow.addElement(Book.BOOK_STATE[book.getState()]);
		tableRow.addElement(book.getAuthor());
		tableRow.addElement(book.getPage());
		tableRow.addElement(book.getPublisher());
		tableRow.addElement(book.getPublishDate());

		// 뒤의 시분초 잘라서 표시
		String date = new String(book.getTimestamp());
		tableRow.addElement(date.substring(0, 10));

		bookTableModel.addRow(tableRow);
		//setRowSelectionInterval(bookTable.getRowCount() - 1, bookTable.getRowCount() - 1, bookTable);
	}

	public void addDVDRow(DVD dvd) {
		tableRow = new Vector<String>();
		tableRow.addElement(dvdTable.getRowCount() + "");
		tableRow.addElement(dvd.getTitle());
		tableRow.addElement(DVD.DVD_STATE[dvd.getState()]);
		tableRow.addElement(dvd.getAuthor());
		tableRow.addElement(dvd.getPublisher());
		tableRow.addElement(dvd.getPublishDate());

		// 뒤의 시분초 잘라서 표시
		String date = new String(dvd.getTimestamp());
		tableRow.addElement(date.substring(0, 10));

		dvdTableModel.addRow(tableRow);
		//setRowSelectionInterval(dvdTable.getRowCount() - 1, dvdTable.getRowCount() - 1, dvdTable);
	}

	public void addLoanRow(Loan loan) {
		tableRow = new Vector<String>();
		tableRow.addElement(getLoanTable().getRowCount() + "");
		tableRow.addElement(Loan.ITEM_TYPE[loan.getType()]);
		tableRow.addElement(Integer.toString(loan.getItemNum()));
		tableRow.addElement(Integer.toString(loan.getMemberIndex()));
		tableRow.addElement(loan.getBorrowedDate());
		tableRow.addElement(loan.getReturnDate());

		loanTableModel.addRow(tableRow);
		setRowSelectionInterval(getLoanTable().getRowCount() - 1, getLoanTable().getRowCount() - 1, getLoanTable());

	}

	public void setRowSelectionInterval(int idx0, int idx1, JTable table) {
		table.setRowSelectionInterval(idx0, idx1);
	}

	public void updateTableColumnIdx(JTable table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			table.setValueAt(i + 1, i, 0);
		}
	}

	public void addLoanButtonListener(ActionListener listener) {
		btnLoan.addActionListener(listener);
	}

	public void addReturnButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
	}

	public void addLoanListSelectionListener(ListSelectionListener listener) {
		getLoanTable().getSelectionModel().addListSelectionListener(listener);
	}

	public void addMemberListSelectionListener(ListSelectionListener listener) {
		memberTable.getSelectionModel().addListSelectionListener(listener);
	}

	public void addBookListSelectionListener(ListSelectionListener listener) {
		bookTable.getSelectionModel().addListSelectionListener(listener);
	}

	public void addDvdListSelectionListener(ListSelectionListener listener) {
		dvdTable.getSelectionModel().addListSelectionListener(listener);
	}

	public ListSelectionModel getLoanModel() {
		return getLoanTable().getSelectionModel();
	}

	public ListSelectionModel getBookModel() {
		return bookTable.getSelectionModel();
	}

	public ListSelectionModel getMemberModel() {
		return memberTable.getSelectionModel();
	}

	public ListSelectionModel getDvdModel() {
		return dvdTable.getSelectionModel();
	}

	public int getLoanRow() {
		return getLoanTable().getSelectedRow();
	}

	public int getBookRow() {
		return getBookTable().getSelectedRow();
	}

	public int getMemberRow() {
		return getMemberTable().getSelectedRow();
	}

	public int getDvdRow() {
		return getDVDTable().getSelectedRow();
	}

	public void setMemTextId(String userID) {
		memTextFieldList.get(0).setText(userID);
	}

	public void setMemTextNum(int userNum) {
		memTextFieldList.get(1).setText(userNum + "");
	}

	public void setMemTextName(String name) {
		memTextFieldList.get(2).setText(name);
	}

	public void setMemTextPriv(int privilege) {
		memTextFieldList.get(3).setText(Member.AUTH_STRINGS[privilege]);
	}

	public void setItemTextTitle(String title) {
		itemTextFieldList.get(0).setText(title);
	}

	public void setItemTextAuthor(String author) {
		itemTextFieldList.get(1).setText(author);
	}

	public void setItemTextPublisher(String publisher) {
		itemTextFieldList.get(2).setText(publisher);
	}

	public void setItemTextYear(String year) {
		itemTextFieldList.get(3).setText(year);
	}

	public void removeLoanRow(int selectedIdx) {
		loanTableModel.removeRow(selectedIdx);
	}

	public void removeBookRow(int selectedIdx) {
		bookTableModel.removeRow(selectedIdx);
	}

	public void removeMemberRow(int selectedIdx) {
		memberTableModel.removeRow(selectedIdx);
	}

	public void removeDVDRow(int selectedIdx) {
		dvdTableModel.removeRow(selectedIdx);
	}

	public JTable getLoanTable() {
		return loanTable;
	}

	public JTable getBookTable() {
		return bookTable;
	}

	public JTable getMemberTable() {
		return memberTable;
	}

	public JTable getDVDTable() {
		return dvdTable;
	}

	public void editDVDRow(int index, DVD dvd) {
		dvdTableModel.setValueAt(dvd.getTitle(), index, 1);
		dvdTableModel.setValueAt(DVD.DVD_STATE[dvd.getState()], index, 2);
		dvdTableModel.setValueAt(dvd.getAuthor(), index, 3);
		dvdTableModel.setValueAt(dvd.getPublisher(), index, 4);
		dvdTableModel.setValueAt(dvd.getPublishDate(), index, 5);
	}

	public void editBookRow(int index, Book book) {
		bookTableModel.setValueAt(book.getTitle(), index, 1);
		bookTableModel.setValueAt(Book.BOOK_STATE[book.getState()], index, 2);
		bookTableModel.setValueAt(book.getAuthor(), index, 3);
		bookTableModel.setValueAt(book.getPage(), index, 4);
		bookTableModel.setValueAt(book.getPublisher(), index, 5);
		bookTableModel.setValueAt(book.getPublishDate(), index, 6);
		bookTableModel.setValueAt(book.getTimestamp(), index, 7);
	}

	public void refreshTable() {
	}

	@Override
	public void initView() {
		memberColumn = new Vector<String>();
		bookColumn = new Vector<String>();
		dvdColumn = new Vector<String>();
		loanColumn = new Vector<String>();
		tableRow = new Vector<String>();

		btnLayout = new GridLayout(1, 2, 5, 0);
		gridBagLayout = new GridBagLayout();

		lPanel = new JPanel();
		memberPanel = new JPanel();
		itemPanel = new JPanel();
		bookPanel = new JPanel();
		dvdPanel = new JPanel();
		borrowedPanel = new JPanel();

		rPanel = new JPanel();
		memberInfoPanel = new JPanel();
		itemInfoPanel = new JPanel();
		btnPanel = new JPanel();

		btnLoan = new Button("대여");
		btnReturn = new Button("반납");

		memTextFieldList = new ArrayList<TextField>();
		itemTextFieldList = new ArrayList<TextField>();
		
		memberTable();
		itemTable();
		memberInfo();
		itemInfo();
				
		this.setLayout(new BorderLayout());
		lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.Y_AXIS));
		this.add(lPanel, BorderLayout.CENTER);
		
		rPanel.setPreferredSize(new Dimension(280, 500));
		rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));
		this.add(rPanel, BorderLayout.EAST);
		
		btnPanel.setLayout(btnLayout);
		btnPanel.add(btnReturn);
		btnPanel.add(btnLoan);
		btnPanel.setMaximumSize(new Dimension(280, 100));
		
		rPanel.add(memberInfoPanel);
		rPanel.add(itemInfoPanel);
		rPanel.add(btnPanel);
		
		lPanel.add(memberPanel);
		lPanel.add(itemPanel);
	}

}