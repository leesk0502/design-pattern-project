package view.login;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.Member;
import view.BaseViewFrame;

//import admin.JLibrary;

public class JLogin extends BaseViewFrame {
	private Label lblId;
	private Label lblPw;
	private Label lblAuth;
	private TextField txtFieldId;
	private TextField txtFieldPw;
	private Button btnLogin;
	private Button btnRegister;
	private JComboBox<?> cmbBoxAuth;
	
	@Override
	public void setWindowSize() {
		// TODO Auto-generated method stub
		this.setSize(250, 180);
	}

	@Override
	public void initView() {
		lblId = new Label("아이디 :", Label.LEFT);
		lblPw = new Label("비밀번호 :", Label.LEFT);
		lblAuth = new Label("회원구분 : ", Label.LEFT);
		txtFieldId = new TextField();
		txtFieldPw = new TextField();
		btnLogin = new Button("로그인");
		btnRegister = new Button("회원가입");
		cmbBoxAuth = new JComboBox<>(Member.AUTH_STRINGS);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		this.setLayout(gridBagLayout);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblAuth, gc);
		this.add(lblAuth);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(cmbBoxAuth, gc);
		this.add(cmbBoxAuth);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblId, gc);
		this.add(lblId);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldId, gc);
		this.add(txtFieldId);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPw, gc);
		this.add(lblPw);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPw, gc);
		this.add(txtFieldPw);

		gc.gridx = 1;
		gc.gridy = 3;
		GridLayout gridLayout = new GridLayout(1, 2, 5, 0);
		Panel panel = new Panel(gridLayout);
		panel.add(btnLogin);
		panel.add(btnRegister);
		gridBagLayout.setConstraints(panel, gc);
		this.add(panel);
	}

	public void addLoginButtonListener(ActionListener listener) {
		btnLogin.addActionListener(listener);		
	}

	public void addRegisterButtonListener(ActionListener listener){
		btnRegister.addActionListener(listener);
	}
	
	public String getTextIdInput(){
		return txtFieldId.getText();
	}
	
	public String getTextPwInput(){
		return txtFieldPw.getText();
	}
	
	public int getComboAuthSelectedIndex(){
		return cmbBoxAuth.getSelectedIndex();
	}
}