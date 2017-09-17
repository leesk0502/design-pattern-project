package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public abstract class BaseViewFrame extends JFrame{
	private Dimension dimen_scr, dimen_frm;
    private int xpos, ypos;
    
	public BaseViewFrame(String title){
		super(title);
	}
	public BaseViewFrame(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
		}

		setWindowSize();
		this.setTitle("도서관에 오신 것을 환영합니다");
		// 화면 가운데에 위치하기 위함
        dimen_scr = Toolkit.getDefaultToolkit().getScreenSize();
        dimen_frm = this.getSize();
 
        xpos = (dimen_scr.width / 2) - (dimen_frm.width / 2);
        ypos = (dimen_scr.height / 2) - (dimen_frm.height / 2);
 
        this.setLocation(xpos, ypos);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initView();
	}
	public abstract void initView();
	
	public void setWindowSize(){
		this.setSize(800, 550);
	}
}
