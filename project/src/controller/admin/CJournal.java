package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Journal;
import model.tool.JournalTool;
import model.tool.Tool;
import view.admin.JJournal;

public class CJournal {
	List<Journal> JournalList = new ArrayList<Journal>();
	JJournal view;

	public CJournal(JJournal view) {
		this.view = view;

		// Add Listener to view
		this.view.addNewButtonListener(addNewButtonListener);
		this.view.addSaveButtonListener(addSaveButtonListener);
		this.view.addDeleteButtonListener(addDeleteButtonListener);

		this.view.addListSelectionListener(addListSelectionListener);

		initTableRows();
	}

	private void initTableRows() {
		Tool<Journal> tool = new JournalTool();
		JournalList = tool.findAll();
		for (int i = 0; i < JournalList.size(); i++) {
			view.addRow(JournalList.get(i));
		}
		view.updateTableColumnIdx();
		view.setRowSelectionInterval(0, 0);
	}

	ActionListener addNewButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Journal Journal = new Journal();
			Journal.setTitle(view.getTextTitleInput());
			Journal.setPublisher(view.getTextPublisherInput());
			Journal.setPublishDate(view.getTextPubDateInput());

			Tool<Journal> tool = new JournalTool();
			int index = tool.add(Journal);
			Journal = tool.find(index);

			JournalList.add(Journal);
			view.addRow(Journal);

		}
	};

	ActionListener addSaveButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// 선택된게 없으면 리턴
			if (selectedIdx < 0)
				return;

			Journal Journal = JournalList.get(selectedIdx);
			Journal.setTitle(view.getTextTitleInput());
			Journal.setPublisher(view.getTextPublisherInput());
			Journal.setPublishDate(view.getTextPubDateInput());

			Tool<Journal> tool = new JournalTool();
			tool.edit(Journal);
			JournalList.set(selectedIdx, Journal);
			view.editRow(selectedIdx, Journal);

		}
	};

	ActionListener addDeleteButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// User 객체 삭제
			JournalTool tool = new JournalTool();
			tool.remove(JournalList.get(selectedIdx));

			// 테이블에서 삭제
			view.removeRow(selectedIdx);

			// 테이블의 순번 컬럼 정보 업데이트
			view.updateTableColumnIdx();

			// 입력 폼 클리어
			view.clearForm();
		}
	};

	ListSelectionListener addListSelectionListener = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Table row가 선택되었을 때 오른쪽 패널에 사용자 정보를 출력한다.
			if (e.getSource() == view.getSelectionModel() && e.getFirstIndex() >= 0) {
				int selectedIdx = view.getSelectedRow();
				if (selectedIdx < 0)
					return;

				Journal Journal = JournalList.get(selectedIdx);
				view.setTextTitle(Journal.getTitle());
				view.setTextPublish(Journal.getPublisher());
				view.setTextPubDate(Journal.getPublishDate());
			}
		}
	};
}
