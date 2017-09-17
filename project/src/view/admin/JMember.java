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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import view.BaseViewPanel;
import model.Member;
import util.DBManager;

public class JMember extends BaseViewPanel {
	JTable mTable;
	Vector<String> tableColumn = new Vector<String>();
	Vector<String> tableRow;
	List<Member> mUserList = new ArrayList<Member>();
	DefaultTableModel mTableModel;
	int mTotalCount = 0;

	TextField txtFieldId;
	TextField txtFieldPw;
	TextField txtFieldName;

	Button btnNew;
	Button btnSave;
	Button btnDelete;

	// 회원 등급 관련 콥보 박스 생성
	JComboBox<?> cmbBoxAuth;

	public JMember() {
		
	}

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
	
	public int getSelectedRow(){
		return mTable.getSelectedRow();
	}
	
	public ListSelectionModel getSelectionModel(){
		return mTable.getSelectionModel();
	}
	
	public void addRow(Member member){
		tableRow = new Vector<String>();
		tableRow.addElement((mTable.getRowCount()+1)+"");
		tableRow.addElement(member.getUserID());
		tableRow.addElement(member.getName());
		tableRow.addElement(Member.AUTH_STRINGS[member.getPrivilege()]);
		
		// 뒤의 시분초 잘라서 표시
		String date = new String(member.getTimestamp());
		tableRow.addElement(date.substring(0, 10));
		
		mTableModel.addRow(tableRow);
		setRowSelectionInterval(mTable.getRowCount() - 1, mTable.getRowCount() - 1);
	}
	
	public void setRowSelectionInterval(int idx0, int idx1){
		mTable.setRowSelectionInterval(idx0, idx1);
	}
	
	public void editRow(int selectedIdx, Member member){
		mTableModel.setValueAt(member.getUserID(), selectedIdx, 1);
		mTableModel.setValueAt(member.getName(), selectedIdx, 2);
		mTableModel.setValueAt(Member.AUTH_STRINGS[member.getPrivilege()], selectedIdx, 3);
	}
	
	public void removeRow(int selectedIdx){
		mTableModel.removeRow(selectedIdx);
	}
	
	public String getTextIdInput(){
		return txtFieldId.getText();
	}
	
	public String getTextPwInput(){
		return txtFieldPw.getText();
	}
	
	public String getTextNameInput(){
		return txtFieldName.getText();
	}
	
	public int getComboAuthSelectedIndex(){
		return cmbBoxAuth.getSelectedIndex();
	}
	
	public void setTextId(String id){
		txtFieldId.setText(id);
	}
	
	public void setTextPw(String pw){
		txtFieldPw.setText(pw);
	}
	
	public void setTextName(String pw){
		txtFieldName.setText(pw);
	}
	
	public void setCombAuth(int idx){
		cmbBoxAuth.setSelectedIndex(idx);
	}
	
	public void clearForm(){
		setTextId("");
		setTextPw("");
		setTextName("");
		setCombAuth(0);
	}

	@Override
	public void initView() {
		tableColumn = new Vector<String>();
		
		mUserList = new ArrayList<Member>();
		mTotalCount = 0;

		txtFieldId = new TextField();
		txtFieldPw = new TextField();
		txtFieldName = new TextField();

		btnNew = new Button("신규");
		btnSave = new Button("저장");
		btnDelete = new Button("삭제");
		
		BorderLayout leftLayout = new BorderLayout();
		this.setLayout(leftLayout);

		tableColumn.addElement("순번");
		// tableColumn.addElement("회원번호");
		tableColumn.addElement("아이디");
		tableColumn.addElement("회원명");
		tableColumn.addElement("회원 등급");
		tableColumn.addElement("등록일");
		
		cmbBoxAuth = new JComboBox<>(Member.AUTH_STRINGS);

		mTableModel = new DefaultTableModel(tableColumn, 0);
		mTable = new JTable(mTableModel);
		mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableColumn column = null;
		// 각 테이블 컬럼의 width 값을 고정
		for (int i = 0; i < mTable.getColumnCount(); i++) {
			column = mTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		mTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// 테이블의 가로 스크롤이 가능하도록 설정
		JScrollPane scrollPane = new JScrollPane(mTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);

		// 오른쪽 패널 셋팅
		JPanel rPanel = new JPanel();
		rPanel.setPreferredSize(new Dimension(300, 100));
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		rPanel.setLayout(gridBagLayout);
		this.add(rPanel, BorderLayout.EAST);

		Label lblId = new Label("ID", Label.LEFT);
		Label lblPw = new Label("Password", Label.LEFT);
		Label lblName = new Label("이름", Label.LEFT);
		Label lblAuth = new Label("회원등급", Label.LEFT);

		// ComboBox의 dropdown menu가 button 뒤로 가려지는 것 막기 위함.
		cmbBoxAuth.setLightWeightPopupEnabled(false);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblId, gc);
		rPanel.add(lblId);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldId, gc);
		rPanel.add(txtFieldId);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblPw, gc);
		rPanel.add(lblPw);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldPw, gc);
		rPanel.add(txtFieldPw);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblName, gc);
		rPanel.add(lblName);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(txtFieldName, gc);
		rPanel.add(txtFieldName);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(lblAuth, gc);
		rPanel.add(lblAuth);

		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 5;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(cmbBoxAuth, gc);
		rPanel.add(cmbBoxAuth);

		JPanel btnPanel = new JPanel();
		GridLayout btnLayout = new GridLayout(1, 2, 5, 0);

		btnPanel.setLayout(btnLayout);
		btnPanel.add(btnNew);
		btnPanel.add(btnSave);
		btnPanel.add(btnDelete);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weightx = 0;
		gc.gridwidth = 2;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.CENTER;
		gridBagLayout.setConstraints(btnPanel, gc);
		rPanel.add(btnPanel);
	}
}
