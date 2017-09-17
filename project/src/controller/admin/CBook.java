package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Book;
import model.tool.BookTool;
import model.tool.Tool;
import view.admin.JBook;

public class CBook {
	List<Book> bookList = new ArrayList<Book>();
	JBook view;

	public CBook(JBook view) {
		this.view = view;

		// Add Listener to view
		this.view.addNewButtonListener(addNewButtonListener);
		this.view.addSaveButtonListener(addSaveButtonListener);
		this.view.addDeleteButtonListener(addDeleteButtonListener);

		this.view.addListSelectionListener(addListSelectionListener);

		initTableRows();
	}

	private void initTableRows() {
		Tool<Book> tool = new BookTool();
		bookList = tool.findAll();
		for (int i = 0; i < bookList.size(); i++) {
			view.addRow(bookList.get(i));
		}
		view.updateTableColumnIdx();
		view.setRowSelectionInterval(0, 0);
	}

	ActionListener addNewButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Book book = new Book();
			book.setTitle(view.getTextTitleInput());
			book.setAuthor(view.getTextAuthorInput());
			book.setPublisher(view.getTextPublisherInput());
			book.setPublishDate(view.getTextPubDateInput());
			book.setPage(view.getTextPageInput());

			Tool<Book> tool = new BookTool();
			int index = tool.add(book);
			book = tool.find(index);
			bookList.add(book);
			view.addRow(book);
		}
	};

	ActionListener addSaveButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// ���õȰ� ������ ����
			if (selectedIdx < 0)
				return;

			Book book = bookList.get(selectedIdx);
			book.setTitle(view.getTextTitleInput());
			book.setAuthor(view.getTextAuthorInput());
			book.setPublisher(view.getTextPublisherInput());
			book.setPublishDate(view.getTextPubDateInput());
			book.setPage(view.getTextPageInput());

			Tool<Book> tool = new BookTool();
			tool.edit(book);
			bookList.set(selectedIdx, book);
			view.editRow(selectedIdx, book);
		}
	};

	ActionListener addDeleteButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// User ��ü ����
			BookTool tool = new BookTool();
			tool.remove(bookList.get(selectedIdx));

			// ���̺��� ����
			view.removeRow(selectedIdx);

			// ���̺��� ���� �÷� ���� ������Ʈ
			view.updateTableColumnIdx();

			// �Է� �� Ŭ����
			view.clearForm();
		}
	};

	ListSelectionListener addListSelectionListener = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Table row�� ���õǾ��� �� ������ �гο� ����� ������ ����Ѵ�.
			if (e.getSource() == view.getSelectionModel() && e.getFirstIndex() >= 0) {
				int selectedIdx = view.getSelectedRow();
				if (selectedIdx < 0)
					return;

				Book book = bookList.get(selectedIdx);
				view.setTextTitle(book.getTitle());
				view.setTextAuthor(book.getAuthor());
				view.setTextPublish(book.getPublisher());
				view.setTextPubDate(book.getPublishDate());
				view.setTextPage(book.getPage());
			}
		}
	};
}
