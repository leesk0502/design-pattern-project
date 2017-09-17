package view.admin;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.admin.CBook;
import controller.admin.CDVD;
import controller.admin.CJournal;
import controller.admin.CLoan;
import controller.admin.CMember;

import view.BaseViewFrame;

public class JLibrary extends BaseViewFrame {

	@Override
	public void initView() {
		JTabbedPane pane = new JTabbedPane();
		JMember member = new JMember();
		JLoan loan = new JLoan();

		JPanel itemPanel = new JPanel();
		JBook book = new JBook();
		JDVD dvd = new JDVD();
		JJournal journal = new JJournal();
		JTabbedPane itemPane = new JTabbedPane();
		itemPane.addTab("档辑 包府", book);
		itemPane.addTab("DVD 包府", dvd);
		itemPane.addTab("稠巩 包府", journal);
		itemPanel.add(itemPane);
		
		pane.addTab("雀盔 包府", member);
		pane.addTab("历辑 包府", itemPanel);
		pane.addTab("措咯 格废", loan);

		CMember memberController = new CMember(member);
		CBook bookController = new CBook(book);
		CDVD dvdController = new CDVD(dvd);
		CJournal journalController = new CJournal(journal);
		CLoan loanController = new CLoan(loan);
		
		getContentPane().add(pane);
	}
}