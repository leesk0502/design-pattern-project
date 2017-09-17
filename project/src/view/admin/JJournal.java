package view.admin;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
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

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import view.BaseViewPanel;
import model.Journal;

public class JJournal extends BaseViewPanel {
	JTable mTable;
	Vector<String> tableColumn;
	Vector<String> tableRow;
	List<Journal> mJournalList;
	DefaultTableModel mTableModel;
	int mCountJournal;

	TextField txtFieldTitle;
	TextField txtFieldPublish;
	TextField txtFieldPubDate;

	Button btnNew;
	Button btnSave;
	Button btnDelete;

	// ���̺� ROW ������ ���� �÷� ������
	public void updateTableColumnIdx() {
		for (int i = 0; i < mTable.getRowCount(); i++) {
			mTable.setValueAt(i + 1, i, 0);
		}
	}

	public void addListSelectionListener(ListSelectionListener listener) {
		mTable.getSelectionModel().addListSelectionListener(listener);
	}

	public void addNewButtonListener(ActionListener listener) {
		btnNew.addActionListener(listener);
	}

	public void addSaveButtonListener(ActionListener listener) {
		btnSave.addActionListener(listener);
	}

	public void addDeleteButtonListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
	}

	public int getSelectedRow() {
		return mTable.getSelectedRow();
	}

	public ListSelectionModel getSelectionModel() {
		return mTable.getSelectionModel();
	}

	public void addRow(Journal journal) {
		tableRow = new Vector<String>();
		tableRow.addElement((mTable.getRowCount()+1) + "");
		tableRow.addElement(journal.getTitle());
		tableRow.addElement(journal.getPublisher());
		tableRow.addElement(journal.getPublishDate());

		// ���� �ú��� �߶� ǥ��
		String date = new String(journal.getTimestamp());
		tableRow.addElement(date.substring(0, 10));

		mTableModel.addRow(tableRow);
		setRowSelectionInterval(mTable.getRowCount() - 1, mTable.getRowCount() - 1);
	}

	public void setRowSelectionInterval(int idx0, int idx1) {
		mTable.setRowSelectionInterval(idx0, idx1);
	}

	public void editRow(int selectedIdx, Journal journal) {
		mTableModel.setValueAt(journal.getTitle(), selectedIdx, 1);
		mTableModel.setValueAt(journal.getPublisher(), selectedIdx, 2);
		mTableModel.setValueAt(journal.getPublishDate(), selectedIdx, 3);
	}

	public void removeRow(int selectedIdx) {
		mTableModel.removeRow(selectedIdx);
	}

	public String getTextTitleInput() {
		return txtFieldTitle.getText();
	}

	public String getTextPublisherInput() {
		return txtFieldPublish.getText();
	}

	public String getTextPubDateInput() {
		return txtFieldPubDate.getText();
	}

	public void setTextTitle(String title) {
		txtFieldTitle.setText(title);
	}

	public void setTextPublish(String publish) {
		txtFieldPublish.setText(publish);
	}

	public void setTextPubDate(String pubDate) {
		txtFieldPubDate.setText(pubDate);
	}

	public void clearForm() {
		setTextTitle("");
		setTextPublish("");
		setTextPubDate("");
	}

	@Override
	public void initView() {
		tableColumn = new Vector<String>();
		tableRow = new Vector<String>();
		mJournalList = new ArrayList<Journal>();
		
		mCountJournal = 0;

		txtFieldTitle = new TextField();
		txtFieldPublish = new TextField();
		txtFieldPubDate = new TextField();

		btnNew = new Button("�ű�");
		btnSave = new Button("����");
		btnDelete = new Button("����");
		
		BorderLayout leftLayout = new BorderLayout();
		this.setLayout(leftLayout);

		tableColumn.addElement("����");
		tableColumn.addElement("����");
		tableColumn.addElement("���ǻ�");
		tableColumn.addElement("���ǳ⵵");
		tableColumn.addElement("�����");

		mTableModel = new DefaultTableModel(tableColumn, 0);
		mTable = new JTable(mTableModel);
		mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		for (int i = 0; i < mTable.getColumnCount(); i++) {
			column = mTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		mTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(mTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);

		JPanel rPanel = new JPanel();
		rPanel.setPreferredSize(new Dimension(300, 100));
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		rPanel.setLayout(gridBagLayout);
		this.add(rPanel, BorderLayout.EAST);

		Label lblTitle = new Label("����", Label.LEFT);
		Label lblPublish = new Label("���ǻ�", Label.LEFT);
		Label lblPubDate = new Label("���ǳ⵵", Label.LEFT);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblTitle, gc);
		rPanel.add(lblTitle);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0.5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldTitle, gc);
		rPanel.add(txtFieldTitle);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPublish, gc);
		rPanel.add(lblPublish);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 0.5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPublish, gc);
		rPanel.add(txtFieldPublish);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPubDate, gc);
		rPanel.add(lblPubDate);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 0.5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPubDate, gc);
		rPanel.add(txtFieldPubDate);

		JPanel btnPanel = new JPanel();
		GridLayout btnLayout = new GridLayout(1, 2, 5, 0);

		btnPanel.setLayout(btnLayout);
		btnPanel.add(btnNew);
		btnPanel.add(btnSave);
		btnPanel.add(btnDelete);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 0;
		gc.gridwidth = 2;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.CENTER;
		gridBagLayout.setConstraints(btnPanel, gc);
		rPanel.add(btnPanel);
	}
}