package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Member;
import model.tool.MemberTool;
import view.admin.JMember;

public class CMember {
	List<Member> memberList = new ArrayList<Member>();
	JMember view;

	public CMember(JMember view) {
		this.view = view;

		this.view.addNewButtonListener(addNewButtonListener);
		this.view.addSaveButtonListener(addSaveButtonListener);
		this.view.addDeleteButtonListener(addDeleteButtonListener);

		this.view.addListSelectionListener(addListSelectionListener);

		initTableRows();
	}

	private void initTableRows() {
		MemberTool tool = new MemberTool();
		memberList = tool.findAll();
		for (int i = 0; i < memberList.size(); i++) {
			view.addRow(memberList.get(i));
		}
		view.updateTableColumnIdx();
		view.setRowSelectionInterval(0, 0);
	}

	ActionListener addNewButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Member member = new Member();
			member.setUserID(view.getTextIdInput());
			member.setPassword(view.getTextPwInput());
			member.setName(view.getTextNameInput());
			member.setPrivilege(view.getComboAuthSelectedIndex());

			MemberTool tool = new MemberTool();
			int index = tool.add(member);
			member = tool.find(index);
			memberList.add(member);
			view.addRow(member);
		}
	};

	ActionListener addSaveButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// 선택된게 없으면 리턴
			if (selectedIdx < 0)
				return;

			Member member = memberList.get(selectedIdx);
			member.setUserID(view.getTextIdInput());
			member.setPassword(view.getTextPwInput());
			member.setName(view.getTextNameInput());
			member.setPrivilege(view.getComboAuthSelectedIndex());

			// 수정작업에서 비밀번호를 입력안했다면 기존 정보 입력
			if (view.getTextPwInput().equals("")) {
				member.setPassword(memberList.get(selectedIdx).getPassword());
			}

			MemberTool tool = new MemberTool();
			tool.edit(member);
			memberList.set(selectedIdx, member);
			view.editRow(selectedIdx, member);

		}
	};

	ActionListener addDeleteButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIdx = view.getSelectedRow();

			// User 객체 삭제
			MemberTool tool = new MemberTool();
			tool.remove(memberList.get(selectedIdx));

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

				Member member = memberList.get(selectedIdx);

				view.setTextId(member.getUserID());
				view.setTextName(member.getName());
				view.setCombAuth(member.getPrivilege());
			}
		}
	};
}
