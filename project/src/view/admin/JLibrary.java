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
		itemPane.addTab("���� ����", book);
		itemPane.addTab("DVD ����", dvd);
		itemPane.addTab("�� ����", journal);
		itemPanel.add(itemPane);
		
		pane.addTab("ȸ�� ����", member);
		pane.addTab("���� ����", itemPanel);
		pane.addTab("�뿩 ���", loan);

		CMember memberController = new CMember(member);
		CBook bookController = new CBook(book);
		CDVD dvdController = new CDVD(dvd);
		CJournal journalController = new CJournal(journal);
		CLoan loanController = new CLoan(loan);
		
		getContentPane().add(pane);
	}
}