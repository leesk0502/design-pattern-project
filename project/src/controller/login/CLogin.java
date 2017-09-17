package controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.admin.CLibrary;
import model.Member;
import model.tool.MemberTool;
import util.LoginManager;
import view.login.JLogin;
import view.user.JLibrary;
import controller.user.CLibraryUser;


public class CLogin {
	JLogin view;

	public CLogin() {
		view = new JLogin();

		// Add Listener to view
		view.addLoginButtonListener(addLoginButtonListener);
		view.addRegisterButtonListener(addRegisterButtonListener);
	}

	ActionListener addLoginButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Member member = new Member(view.getTextIdInput(), view.getTextPwInput(), view.getComboAuthSelectedIndex());

			MemberTool memberTool = new MemberTool();
			if (!memberTool.isExist(member)) {
				JOptionPane.showMessageDialog(view, "아이디 또는 비밀번호를 확인해 주세요.");
				return;
			}

			view.setVisible(false);
			
			member = memberTool.find(member);
			LoginManager.getInstance().setMember(member);
			
			if (view.getComboAuthSelectedIndex() == Member.AUTH_ADMIN) {
				CLibrary controller= new CLibrary();
			} else {
				CLibraryUser controller2 = new CLibraryUser();
			}
		}
	};

	ActionListener addRegisterButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.setVisible(false);
			CRegister controller = new CRegister();
		}
	};
}
