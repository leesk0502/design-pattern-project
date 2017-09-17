package view.user;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Book;
import model.Loan;
import model.Member;
import util.DBManager;
import view.BaseViewPanel;

public class JBook extends BaseViewPanel {
	JTable bookTable;
	DefaultTableModel bookTableModel;

	JTable loanTable;
	DefaultTableModel loanTableModel;

	Vector<String> tableColumn;
	Vector<String> tableRow;

	Vector<String> tableColumn2;
	Vector<String> tableRow2;

	List<Book> mBookList;
	int mCountBook;

	GridLayout btnLayout;
	GridBagLayout gridBagLayout;

	JPanel bookPanel;
	JPanel loanPanel;

	JPanel bPanel;
	JPanel btnPanel;

	Button btnSearch;
	Button btnBorrow;
	Button btnReturn;

	TextField txtFieldSearch;

	JComboBox<?> cmbBoxSearch;

	public JBook() {
	}

	private void bookTable() {
		tableColumn.clear();
		tableColumn.addElement("순번");
		tableColumn.addElement("제목");
		tableColumn.addElement("상태");
		tableColumn.addElement("저자");
		tableColumn.addElement("페이지");
		tableColumn.addElement("출판사");
		tableColumn.addElement("출판년도");
		tableColumn.addElement("등록일");

		bookTableModel = new DefaultTableModel(tableColumn, 0);
		bookTable = new JTable(bookTableModel);
		getBookTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < getBookTable().getColumnCount(); i++) {
			column = getBookTable().getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}

		getBookTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(getBookTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		bookPanel.setPreferredSize(new Dimension(800, 300));
		bookPanel.add(scrollPane);
	}

	private void loanTable() {
		tableColumn2.clear();
		tableColumn2.addElement("순번");
		tableColumn2.addElement("제목");
		tableColumn2.addElement("고유 번호");
		tableColumn2.addElement("대출자 idx");
		tableColumn2.addElement("대출일");
		tableColumn2.addElement("반납일");

		loanTableModel = new DefaultTableModel(tableColumn2, 0);
		loanTable = new JTable(loanTableModel);
		getLoanTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < getLoanTable().getColumnCount(); i++) {
			column = getLoanTable().getColumnModel().getColumn(i);
			if (i < 4)
				column.setPreferredWidth(120);
			else
				column.setPreferredWidth(120);
		}

		getLoanTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(getLoanTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		loanPanel.setPreferredSize(new Dimension(800, 300));
		loanPanel.add(scrollPane);
	}

	public JTable getBookTable() {
		return bookTable;
	}

	public JTable getLoanTable() {
		return loanTable;
	}

	public void updateTableColumnIdx(JTable table) {
		for (int i = 0; i < bookTable.getRowCount(); i++) {
			bookTable.setValueAt(i + 1, i, 0);
		}
	}

	public void updateTableColumnIdxLoan() {
		for (int i = 0; i < loanTable.getRowCount(); i++) {
			loanTable.setValueAt(i + 1, i, 0);
		}
	}

	public void addListSelectionListenerBook(ListSelectionListener listener) {
		bookTable.getSelectionModel().addListSelectionListener(listener);
	}

	public void addListSelectionListenerLoan(ListSelectionListener listener) {
		loanTable.getSelectionModel().addListSelectionListener(listener);
	}

	public void addSearchButtonListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}

	public void addBorrowButtonListener(ActionListener listener) {
		btnBorrow.addActionListener(listener);
	}

	public void addDeleteButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
	}

	public int getSelectedRowBook() {
		return bookTable.getSelectedRow();
	}

	public int getSelectedRowLoan() {
		return loanTable.getSelectedRow();
	}

	public void addRow(Book book) {
		tableRow2 = new Vector<String>();
		tableRow2.addElement(bookTable.getRowCount() + "");
		tableRow2.addElement(book.getTitle());
		tableRow2.addElement(Book.BOOK_STATE[book.getState()]);
		tableRow2.addElement(book.getAuthor());
		tableRow2.addElement(book.getPage());
		tableRow2.addElement(book.getPublisher());
		tableRow2.addElement(book.getPublishDate());

		// 뒤의 시분초 잘라서 표시
		String date = new String(book.getTimestamp());
		tableRow2.addElement(date.substring(0, 10));

		bookTableModel.addRow(tableRow2);
		setRowSelectionInterval(bookTable.getRowCount() - 1, bookTable.getRowCount() - 1);
	}

	public void addLoanRow(Loan loan) {
		tableRow2 = new Vector<String>();
		tableRow2.addElement(getLoanTable().getRowCount() + "");
		tableRow2.addElement(Loan.ITEM_TYPE[loan.getType()]);
		tableRow2.addElement(Integer.toString(loan.getItemNum()));
		tableRow2.addElement(Integer.toString(loan.getMemberIndex()));
		tableRow2.addElement(loan.getBorrowedDate());
		tableRow2.addElement(loan.getReturnDate());

		loanTableModel.addRow(tableRow2);
		setRowSelectionInterval(getLoanTable().getRowCount() - 1, getLoanTable().getRowCount() - 1, getLoanTable());
	}

	public void setRowSelectionInterval(int idx0, int idx1, JTable table) {
		table.setRowSelectionInterval(idx0, idx1);
	}

	public void setRowSelectionInterval(int idx0, int idx1) {
		bookTable.setRowSelectionInterval(idx0, idx1);
	}

	public void removeRow(int selectedIdx) {
		loanTableModel.removeRow(selectedIdx);
	}

	public String getTextSearchInput() {
		return txtFieldSearch.getText();
	}

	public int getComboSelectedIndex() {
		return cmbBoxSearch.getSelectedIndex();
	}

	public void setCombSearch(int idx) {
		cmbBoxSearch.setSelectedIndex(idx);
	}
	
	public int getBookRow(){
		return getBookTable().getSelectedRow();
	}
	
	public void editBookRow(int index, Book book)
	{
		bookTableModel.setValueAt(book.getTitle(), index, 1);
		bookTableModel.setValueAt(Book.BOOK_STATE[book.getState()], index, 2);
		bookTableModel.setValueAt(book.getAuthor(), index, 3);
		bookTableModel.setValueAt(book.getPage(), index, 4);
		bookTableModel.setValueAt(book.getPublisher(), index, 5);
		//bookTableModel.setValueAt(book.getPublishDate(), index, 6);
		//bookTableModel.setValueAt(book.getTimestamp(), index, 7);
	}
	
	public void addReturnButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
	}
	
	public int getLoanRow(){
		return getLoanTable().getSelectedRow();
	}

	public void refresh() {
		bookTableModel.setRowCount(0);
	}

	//public void clearTable() {
	//	for (int i = 0; i < bookTableModel.getRowCount(); i++)
	//		bookTableModel.removeRow(i);
	//}
	public void refreshLoan() {
		loanTableModel.setRowCount(0);
	}

	@Override
	public void initView() {
		tableColumn = new Vector<String>();
		tableRow = new Vector<String>();

		tableColumn2 = new Vector<String>();
		tableRow2 = new Vector<String>();

		mBookList = new ArrayList<Book>();
		mCountBook = 0;

		btnLayout = new GridLayout(1, 2, 5, 0);
		gridBagLayout = new GridBagLayout();

		bookPanel = new JPanel();
		loanPanel = new JPanel();

		bPanel = new JPanel();
		btnPanel = new JPanel();

		btnSearch = new Button("검색");
		btnBorrow = new Button("대여");
		btnReturn = new Button("반납");
		
		txtFieldSearch = new TextField();

		cmbBoxSearch = new JComboBox<>(Book.SEARCH_STRINGS);
		
		this.setLayout(new BorderLayout());
		bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
		this.add(bookPanel, BorderLayout.NORTH);
		bookPanel.setPreferredSize(new Dimension(400, 200));
		bookTable();

		loanPanel.setLayout(new BoxLayout(loanPanel, BoxLayout.Y_AXIS));
		this.add(loanPanel, BorderLayout.CENTER);
		loanPanel.setPreferredSize(new Dimension(400, 200));
		loanTable();

		btnPanel.setLayout(btnLayout);
		btnPanel.add(btnSearch);
		btnPanel.add(btnBorrow);
		btnPanel.add(btnReturn);
		btnPanel.setMaximumSize(new Dimension(200, 50));

		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		bPanel.setPreferredSize(new Dimension(100, 50));
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.Y_AXIS));
		bPanel.setLayout(gridBagLayout);
		this.add(bPanel, BorderLayout.SOUTH);
		bPanel.add(btnPanel);

		txtFieldSearch.setPreferredSize(new Dimension(300, 40));
		cmbBoxSearch.setPreferredSize(new Dimension(100, 40));
		// cmbBoxSearch.setLightWeightPopupEnabled(false);
		cmbBoxSearch.setMaximumSize(cmbBoxSearch.getPreferredSize());

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldSearch, gc);
		bPanel.add(txtFieldSearch);

		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(btnPanel, gc);
		bPanel.add(btnPanel);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0;
		// gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(cmbBoxSearch, gc);
		bPanel.add(cmbBoxSearch);
	}

}
