package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.DVD;
import model.tool.DVDTool;
import model.tool.Tool;
import view.admin.JDVD;

public class CDVD {
	List<DVD> dvdList = new ArrayList<DVD>();
	JDVD view;

	public CDVD(JDVD view) {
		this.view = view;

		// Add Listener to view
		this.view.addNewButtonListener(addNewButtonListener);
		this.view.addSaveButtonListener(addSaveButtonListener);
		this.view.addDeleteButtonListener(addDeleteButtonListener);

		this.view.addListSelectionListener(addListSelectionListener);

		initTableRows();
	}

	private void initTableRows() {
		Tool<DVD> tool = new DVDTool();
		dvdList = tool.findAll();
		for (int i = 0; i < dvdList.size(); i++) {
			view.addRow(dvdList.get(i));
		}
		view.updateTableColumnIdx();
		view.setRowSelectionInterval(0, 0);
	}

	ActionListener addNewButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			DVD dvd = new DVD();
			dvd.setTitle(view.getTextTitleInput());
			dvd.setAuthor(view.getTextAuthorInput());
			dvd.setPublisher(view.getTextPublisherInput());
			dvd.setPublishDate(view.getTextPubDateInput());

			Tool<DVD> tool = new DVDTool();
			int index = tool.add(dvd);
			dvd = tool.find(index);
			dvdList.add(dvd);
			view.addRow(dvd);
		}
	};

	ActionListener addSaveButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// 선택된게 없으면 리턴
			if (selectedIdx < 0)
				return;

			DVD dvd = dvdList.get(selectedIdx);
			dvd.setTitle(view.getTextTitleInput());
			dvd.setAuthor(view.getTextAuthorInput());
			dvd.setPublisher(view.getTextPublisherInput());
			dvd.setPublishDate(view.getTextPubDateInput());

			Tool<DVD> tool = new DVDTool();
			tool.edit(dvd);
			dvdList.set(selectedIdx, dvd);
			view.editRow(selectedIdx, dvd);

		}
	};

	ActionListener addDeleteButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// User 객체 삭제
			DVDTool tool = new DVDTool();
			tool.remove(dvdList.get(selectedIdx));

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

				DVD dvd = dvdList.get(selectedIdx);
				view.setTextTitle(dvd.getTitle());
				view.setTextAuthor(dvd.getAuthor());
				view.setTextPublish(dvd.getPublisher());
				view.setTextPubDate(dvd.getPublishDate());
			}
		}
	};
}
