package view.user;

import javax.swing.JTabbedPane;

import controller.user.CBook;
import controller.user.CDVD;
import controller.user.CJournal;
import view.BaseViewFrame;


public class JLibrary extends BaseViewFrame{
	
	public JLibrary(){
		
	}
	
	public void initView(){
		JTabbedPane pane = new JTabbedPane();
		JBook book = new JBook();
		JDVD DVD = new JDVD();
		JJournal journal = new JJournal();
		
		pane.addTab("����", book);
		pane.addTab("DVD", DVD);
		pane.addTab("��", journal);
		
		CBook bookController = new CBook(book);
		CDVD dvdController = new CDVD(DVD);
		CJournal journalController = new CJournal(journal);
		
		getContentPane().add(pane);
	}
	
}