package controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Member;
import model.tool.MemberTool;
import model.tool.Tool;
import view.login.JRegister;

public class CRegister {
	Member model;
	JRegister view;

	public CRegister() {
		model = new Member();
		view = new JRegister();

		view.addConfirmButtonListener(addConfirmButtonListener);
	}

	ActionListener addConfirmButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String memberId = view.getTextIdInput();
			String memberPw = view.getTextPwInput();
			String memberName = view.getTextNameInput();

			// ID 입력 체크
			if (memberId.equals("")) {
				JOptionPane.showMessageDialog(view, "아이디를 입력해 주세요");
				return;
			}
			// 비밀번호 입력 체크
			if (memberPw.equals("")) {
				JOptionPane.showMessageDialog(view, "비밀번호를 입력해 주세요");
				return;
			}

			Member member = new Member();
			member.setUserID(memberId);
			
			Tool<Member> tool = new MemberTool();
			if (tool.isExist(member)) {
				JOptionPane.showMessageDialog(view, "이미 존재하는 아이디 입니다.");
				return;
			}

			member.setPassword(memberPw);
			member.setName(memberName);
			member.setPrivilege(Member.AUTH_MEMBER);
			
			if (tool.add(member) < 0) {
				return;
			}

			JOptionPane.showMessageDialog(view, "회원가입이 완료되었습니다.");
			// TODO Auto-generated method stub
			// context.dispatchEvent(new WindowEvent(context,
			// WindowEvent.WINDOW_CLOSING));
			view.setVisible(false);

			CLogin controller = new CLogin();
		}
	};
}
