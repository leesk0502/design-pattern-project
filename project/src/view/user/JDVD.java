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
import model.DVD;
import model.Loan;
import model.Member;
import util.DBManager;
import view.BaseViewPanel;

public class JDVD extends BaseViewPanel {
	JTable DVDTable;
	DefaultTableModel DVDTableModel;

	JTable loanTable;
	DefaultTableModel loanTableModel;

	Vector<String> tableColumn;
	Vector<String> tableRow;

	Vector<String> tableColumn2;
	Vector<String> tableRow2;

	List<DVD> mDVDList;
	int mCountDVD;

	GridLayout btnLayout;
	GridBagLayout gridBagLayout;

	JPanel DVDPanel;
	JPanel loanPanel;

	JPanel bPanel;
	JPanel btnPanel;

	Button btnSearch;
	Button btnBorrow;
	Button btnReturn;

	TextField txtFieldSearch;

	JComboBox<?> cmbBoxSearch;

	public JDVD() {

		
	}

	private void DVDTable() {
		tableColumn.clear();
		tableColumn.addElement("순번");
		tableColumn.addElement("제목");
		tableColumn.addElement("상태");
		tableColumn.addElement("저자");
		tableColumn.addElement("출판사");
		tableColumn.addElement("출판년도");
		tableColumn.addElement("등록일");

		DVDTableModel = new DefaultTableModel(tableColumn, 0);
		DVDTable = new JTable(DVDTableModel);
		getDVDTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < getDVDTable().getColumnCount(); i++) {
			column = getDVDTable().getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}

		getDVDTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(getDVDTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		DVDPanel.setPreferredSize(new Dimension(800, 300));
		DVDPanel.add(scrollPane);
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

	public JTable getDVDTable() {
		return DVDTable;
	}

	public JTable getLoanTable() {
		return loanTable;
	}

	public void updateTableColumnIdx(JTable table) {
		for (int i = 0; i < DVDTable.getRowCount(); i++) {
			DVDTable.setValueAt(i + 1, i, 0);
		}
	}

	public void updateTableColumnIdxLoan() {
		for (int i = 0; i < loanTable.getRowCount(); i++) {
			loanTable.setValueAt(i + 1, i, 0);
		}
	}

	public void addListSelectionListenerDVD(ListSelectionListener listener) {
		DVDTable.getSelectionModel().addListSelectionListener(listener);
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

	public int getSelectedRowDVD() {
		return DVDTable.getSelectedRow();
	}

	public int getSelectedRowLoan() {
		return loanTable.getSelectedRow();
	}

	public void addRow(DVD DVD) {
		tableRow2 = new Vector<String>();
		tableRow2.addElement(DVDTable.getRowCount() + "");
		tableRow2.addElement(DVD.getTitle());
		tableRow2.addElement(DVD.DVD_STATE[DVD.getState()]);
		tableRow2.addElement(DVD.getAuthor());
		tableRow2.addElement(DVD.getPublisher());
		tableRow2.addElement(DVD.getPublishDate());

		// 뒤의 시분초 잘라서 표시
		String date = new String(DVD.getTimestamp());
		tableRow2.addElement(date.substring(0, 10));

		DVDTableModel.addRow(tableRow2);
		setRowSelectionInterval(DVDTable.getRowCount() - 1, DVDTable.getRowCount() - 1);
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
		DVDTable.setRowSelectionInterval(idx0, idx1);
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
	
	public int getDVDRow(){
		return getDVDTable().getSelectedRow();
	}
	
	public void editDVDRow(int index, DVD DVD)
	{
		DVDTableModel.setValueAt(DVD.getTitle(), index, 1);
		DVDTableModel.setValueAt(DVD.DVD_STATE[DVD.getState()], index, 2);
		DVDTableModel.setValueAt(DVD.getAuthor(), index, 3);
		DVDTableModel.setValueAt(DVD.getPublisher(), index, 4);
		//DVDTableModel.setValueAt(DVD.getPublishDate(), index, 6);
		//DVDTableModel.setValueAt(DVD.getTimestamp(), index, 7);
	}
	
	public void addReturnButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
	}
	
	public int getLoanRow(){
		return getLoanTable().getSelectedRow();
	}

	public void refresh() {
		DVDTableModel.setRowCount(0);
	}

	//public void clearTable() {
	//	for (int i = 0; i < DVDTableModel.getRowCount(); i++)
	//		DVDTableModel.removeRow(i);
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

		mDVDList = new ArrayList<DVD>();
		mCountDVD = 0;

		btnLayout = new GridLayout(1, 2, 5, 0);
		gridBagLayout = new GridBagLayout();

		DVDPanel = new JPanel();
		loanPanel = new JPanel();

		bPanel = new JPanel();
		btnPanel = new JPanel();

		btnSearch = new Button("검색");
		btnBorrow = new Button("대여");
		btnReturn = new Button("반납");
		
		txtFieldSearch = new TextField();

		cmbBoxSearch = new JComboBox<>(DVD.SEARCH_STRINGS);
		
		this.setLayout(new BorderLayout());
		DVDPanel.setLayout(new BoxLayout(DVDPanel, BoxLayout.Y_AXIS));
		this.add(DVDPanel, BorderLayout.NORTH);
		DVDPanel.setPreferredSize(new Dimension(400, 200));
		DVDTable();

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
