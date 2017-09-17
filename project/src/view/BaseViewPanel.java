package view;

import javax.swing.JPanel;

public abstract class BaseViewPanel extends JPanel{
	
	public BaseViewPanel(){
		initView();
	}
	public abstract void initView();

}
