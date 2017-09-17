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

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.Member;
import view.BaseViewFrame;

public class JRegister extends BaseViewFrame {

	private Dimension dimen_scr, dimen_frm;
	private int xpos, ypos;
	private Label lblId;
	private Label lblPw;
	private Label lblName;
	private TextField txtFieldId;
	private TextField txtFieldPw;
	private TextField txtFieldName;
	private Button btnConfirm;

	@Override
	public void setWindowSize() {
		// TODO Auto-generated method stub
		this.setSize(240, 240);
	}

	@Override
	public void initView() {
		lblId = new Label("아이디 :", Label.LEFT);
		lblPw = new Label("비밀번호 :", Label.LEFT);
		lblName = new Label("이름 :", Label.LEFT);
		txtFieldId = new TextField();
		txtFieldPw = new TextField();
		txtFieldName = new TextField();
		btnConfirm = new Button("Confirm");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		this.setLayout(gridBagLayout);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblId, gc);
		this.add(lblId);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldId, gc);
		this.add(txtFieldId);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPw, gc);
		this.add(lblPw);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPw, gc);
		this.add(txtFieldPw);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblName, gc);
		this.add(lblName);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldName, gc);
		this.add(txtFieldName);

		gc.gridx = 1;
		gc.gridy = 3;
		GridLayout gridLayout = new GridLayout(1, 2, 5, 0);
		Panel panel = new Panel(gridLayout);
		panel.add(btnConfirm);
		gridBagLayout.setConstraints(panel, gc);
		this.add(panel);
	}

	public void addConfirmButtonListener(ActionListener listener) {
		btnConfirm.addActionListener(listener);
	}

	public String getTextIdInput() {
		return txtFieldId.getText();
	}

	public String getTextPwInput() {
		return txtFieldPw.getText();
	}

	public String getTextNameInput() {
		return txtFieldName.getText();
	}

}
