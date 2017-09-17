package controller.user;

import view.BaseViewFrame;
import view.user.JLibrary;

public class CLibraryUser extends BaseViewFrame {
	JLibrary view;

	public CLibraryUser() {
		super("도서관에 오신것을 환영합니다");
		view = new JLibrary();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

}