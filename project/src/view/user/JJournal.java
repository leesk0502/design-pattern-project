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

import model.Journal;
import model.Loan;
import model.Member;
import util.DBManager;
import view.BaseViewPanel;

public class JJournal extends BaseViewPanel {
	JTable JournalTable;
	DefaultTableModel JournalTableModel;

	JTable loanTable;
	DefaultTableModel loanTableModel;

	Vector<String> tableColumn;
	Vector<String> tableRow;

	Vector<String> tableColumn2;
	Vector<String> tableRow2;

	List<Journal> mJournalList;
	int mCountJournal;

	GridLayout btnLayout;
	GridBagLayout gridBagLayout;

	JPanel JournalPanel;
	JPanel loanPanel;

	JPanel bPanel;
	JPanel btnPanel;

	Button btnSearch;

	TextField txtFieldSearch;

	JComboBox<?> cmbBoxSearch;

	private void JournalTable() {
		tableColumn.clear();
		tableColumn.addElement("순번");
		tableColumn.addElement("제목");
		tableColumn.addElement("출판사");
		tableColumn.addElement("출판년도");
		tableColumn.addElement("등록일");

		JournalTableModel = new DefaultTableModel(tableColumn, 0);
		JournalTable = new JTable(JournalTableModel);
		getJournalTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < getJournalTable().getColumnCount(); i++) {
			column = getJournalTable().getColumnModel().getColumn(i);
			column.setPreferredWidth(140);
		}

		getJournalTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(getJournalTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JournalPanel.setPreferredSize(new Dimension(800, 300));
		JournalPanel.add(scrollPane);
	}

	public JTable getJournalTable() {
		return JournalTable;
	}

	public void updateTableColumnIdx(JTable table) {
		for (int i = 0; i < JournalTable.getRowCount(); i++) {
			JournalTable.setValueAt(i + 1, i, 0);
		}
	}

	public void updateTableColumnIdxLoan() {
		for (int i = 0; i < loanTable.getRowCount(); i++) {
			loanTable.setValueAt(i + 1, i, 0);
		}
	}

	public void addListSelectionListenerJournal(ListSelectionListener listener) {
		JournalTable.getSelectionModel().addListSelectionListener(listener);
	}

	public void addListSelectionListenerLoan(ListSelectionListener listener) {
		loanTable.getSelectionModel().addListSelectionListener(listener);
	}

	public void addSearchButtonListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}

	public int getSelectedRowJournal() {
		return JournalTable.getSelectedRow();
	}

	public int getSelectedRowLoan() {
		return loanTable.getSelectedRow();
	}

	public void addRow(Journal Journal) {
		tableRow2 = new Vector<String>();
		tableRow2.addElement(JournalTable.getRowCount() + "");
		tableRow2.addElement(Journal.getTitle());
		tableRow2.addElement(Journal.getPublisher());
		tableRow2.addElement(Journal.getPublishDate());

		// 뒤의 시분초 잘라서 표시
		String date = new String(Journal.getTimestamp());
		tableRow2.addElement(date.substring(0, 10));

		JournalTableModel.addRow(tableRow2);
		setRowSelectionInterval(JournalTable.getRowCount() - 1, JournalTable.getRowCount() - 1);
	}


	public void setRowSelectionInterval(int idx0, int idx1, JTable table) {
		table.setRowSelectionInterval(idx0, idx1);
	}

	public void setRowSelectionInterval(int idx0, int idx1) {
		JournalTable.setRowSelectionInterval(idx0, idx1);
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
	
	public int getJournalRow(){
		return getJournalTable().getSelectedRow();
	}
	
	public void editJournalRow(int index, Journal Journal)
	{
		JournalTableModel.setValueAt(Journal.getTitle(), index, 1);
		JournalTableModel.setValueAt(Journal.getPublisher(), index, 2);
		//JournalTableModel.setValueAt(Journal.getPublishDate(), index, 6);
		//JournalTableModel.setValueAt(Journal.getTimestamp(), index, 7);
	}
	

	public void refresh() {
		JournalTableModel.setRowCount(0);
	}

	@Override
	public void initView() {
		tableColumn = new Vector<String>();
		tableRow = new Vector<String>();

		tableColumn2 = new Vector<String>();
		tableRow2 = new Vector<String>();

		mJournalList = new ArrayList<Journal>();
		mCountJournal = 0;

		btnLayout = new GridLayout(1, 2, 5, 0);
		gridBagLayout = new GridBagLayout();

		JournalPanel = new JPanel();
		loanPanel = new JPanel();

		bPanel = new JPanel();
		btnPanel = new JPanel();

		btnSearch = new Button("검색");

		txtFieldSearch = new TextField();

		cmbBoxSearch = new JComboBox<>(Journal.SEARCH_STRINGS);

		this.setLayout(new BorderLayout());
		JournalPanel.setLayout(new BoxLayout(JournalPanel, BoxLayout.Y_AXIS));
		this.add(JournalPanel, BorderLayout.CENTER);
		JournalPanel.setPreferredSize(new Dimension(400, 200));
		JournalTable();

		btnPanel.setLayout(btnLayout);
		btnPanel.add(btnSearch);
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
