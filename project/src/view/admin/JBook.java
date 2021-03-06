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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Book;
import view.BaseViewPanel;

public class JBook extends BaseViewPanel {
	JTable mTable;
	Vector<String> tableColumn;
	Vector<String> tableRow;
	List<Book> mBookList;
	DefaultTableModel mTableModel;
	int mCountBook;

	TextField txtFieldTitle;
	TextField txtFieldAuthor;
	TextField txtFieldPublish;
	TextField txtFieldPubDate;
	TextField txtFieldPage;

	Button btnNew;
	Button btnSave;
	Button btnDelete;

	// 테이블 ROW 삭제시 순번 컬럼 재정렬
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

	public void addRow(Book book) {
		tableRow = new Vector<String>();
		tableRow.addElement((mTable.getRowCount()+1) + "");
		tableRow.addElement(book.getTitle());
		tableRow.addElement(Book.BOOK_STATE[book.getState()]);
		tableRow.addElement(book.getAuthor());
		tableRow.addElement(book.getPage());
		tableRow.addElement(book.getPublisher());
		tableRow.addElement(book.getPublishDate());

		// 뒤의 시분초 잘라서 표시
		String date = new String(book.getTimestamp());
		tableRow.addElement(date.substring(0, 10));

		mTableModel.addRow(tableRow);
		setRowSelectionInterval(mTable.getRowCount() - 1, mTable.getRowCount() - 1);
	}

	public void setRowSelectionInterval(int idx0, int idx1) {
		mTable.setRowSelectionInterval(idx0, idx1);
	}

	public void editRow(int selectedIdx, Book book) {
		mTableModel.setValueAt(book.getTitle(), selectedIdx, 1);
		mTableModel.setValueAt(Book.BOOK_STATE[book.getState()], selectedIdx, 2);
		mTableModel.setValueAt(book.getAuthor(), selectedIdx, 3);
		mTableModel.setValueAt(book.getPage(), selectedIdx, 4);
		mTableModel.setValueAt(book.getPublisher(), selectedIdx, 5);
		mTableModel.setValueAt(book.getPublishDate(), selectedIdx, 6);
		mTableModel.setValueAt(book.getTimestamp(), selectedIdx, 7);
	}

	public void removeRow(int selectedIdx) {
		mTableModel.removeRow(selectedIdx);
	}

	public String getTextTitleInput() {
		return txtFieldTitle.getText();
	}

	public String getTextAuthorInput() {
		return txtFieldAuthor.getText();
	}

	public String getTextPublisherInput() {
		return txtFieldPublish.getText();
	}

	public String getTextPubDateInput() {
		return txtFieldPubDate.getText();
	}

	public String getTextPageInput() {
		return txtFieldPage.getText();
	}

	public void setTextTitle(String title) {
		txtFieldTitle.setText(title);
	}

	public void setTextAuthor(String author) {
		txtFieldAuthor.setText(author);
	}

	public void setTextPublish(String publish) {
		txtFieldPublish.setText(publish);
	}

	public void setTextPubDate(String pubDate) {
		txtFieldPubDate.setText(pubDate);
	}

	public void setTextPage(String page) {
		txtFieldPage.setText(page);
	}

	public void clearForm() {
		setTextTitle("");
		setTextAuthor("");
		setTextPublish("");
		setTextPubDate("");
		setTextPage("");
	}

	@Override
	public void initView() {
		tableColumn = new Vector<String>();
		tableRow = new Vector<String>();
		mBookList = new ArrayList<Book>();
		mCountBook = 0;

		txtFieldTitle = new TextField();
		txtFieldAuthor = new TextField();
		txtFieldPublish = new TextField();
		txtFieldPubDate = new TextField();
		txtFieldPage = new TextField();

		btnNew = new Button("신규");
		btnSave = new Button("저장");
		btnDelete = new Button("삭제");
		
		BorderLayout leftLayout = new BorderLayout();
		this.setLayout(leftLayout);

		tableColumn.addElement("순번");
		tableColumn.addElement("제목");
		tableColumn.addElement("상태");
		tableColumn.addElement("저자");
		tableColumn.addElement("페이지");
		tableColumn.addElement("출판사");
		tableColumn.addElement("출판년도");
		tableColumn.addElement("등록일");

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

		Label lblTitle = new Label("제목", Label.LEFT);
		Label lblAuthor = new Label("저자", Label.LEFT);
		Label lblPublish = new Label("출판사", Label.LEFT);
		Label lblPubDate = new Label("출판년도", Label.LEFT);
		Label lblPage = new Label("페이지", Label.LEFT);

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
		gridBagLayout.setConstraints(lblAuthor, gc);
		rPanel.add(lblAuthor);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 0.5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldAuthor, gc);
		rPanel.add(txtFieldAuthor);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPublish, gc);
		rPanel.add(lblPublish);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 0.5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPublish, gc);
		rPanel.add(txtFieldPublish);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPubDate, gc);
		rPanel.add(lblPubDate);

		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 0.5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPubDate, gc);
		rPanel.add(txtFieldPubDate);

		gc.gridx = 0;
		gc.gridy = 4;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPage, gc);
		rPanel.add(lblPage);

		gc.gridx = 1;
		gc.gridy = 4;
		gc.weightx = 0.5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPage, gc);
		rPanel.add(txtFieldPage);

		JPanel btnPanel = new JPanel();
		GridLayout btnLayout = new GridLayout(1, 2, 5, 0);

		btnPanel.setLayout(btnLayout);
		btnPanel.add(btnNew);
		btnPanel.add(btnSave);
		btnPanel.add(btnDelete);
		gc.gridx = 0;
		gc.gridy = 5;
		gc.weightx = 0;
		gc.gridwidth = 2;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.CENTER;
		gridBagLayout.setConstraints(btnPanel, gc);
		rPanel.add(btnPanel);
	}

}