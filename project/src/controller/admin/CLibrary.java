package controller.admin;

import view.BaseViewFrame;
import view.admin.JLibrary;

public class CLibrary extends BaseViewFrame{
	JLibrary view;
	public CLibrary() {
		super("도서 관리 시스템");	
		view = new JLibrary();
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}
	
}
