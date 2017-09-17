package controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.DVD;
import model.Journal;
import model.Journal;
import model.tool.DVDTool;
import model.tool.JournalTool;
import view.user.JJournal;
import util.LoginManager;
import util.search.SearcherBuilder;
import model.Loan;
import model.Member;
import model.tool.LoanTool;
import model.tool.Tool;

public class CJournal {
	List<Journal> JournalList = new ArrayList<Journal>();
	List<Loan> loanList = new ArrayList<Loan>();
	JJournal view;
	static int TYPE = 0;

	public CJournal(JJournal view) {
		this.view = view;
		// Adding Listener to view
		this.view.addSearchButtonListener(addSearchButtonListener);
	
		initTableRows();
	}

	private void initTableRows() {
		JournalTool tool = new JournalTool();
		JournalList = tool.findAll();
		for (int i = 0; i < JournalList.size(); i++) {
			view.addRow(JournalList.get(i));
		}
		view.updateTableColumnIdx(view.getJournalTable());
		view.setRowSelectionInterval(0, 0);
	}
	
	ActionListener addSearchButtonListener = new ActionListener() {
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			view.refresh();
			int select = view.getComboSelectedIndex();

			Tool<Journal> tool = new JournalTool();
			JournalList = tool.findAll();
			
			SearcherBuilder builder = new SearcherBuilder()
					.setItemList(JournalList)
					.setSearchKeyword(view.getTextSearchInput());
			
			if (select == 0) {
				builder.setSearchField("title");
			} else if (select == 1) {
				builder.setSearchField("publisher");
			}
			
			JournalList = (List<Journal>) builder.build().search();
			for (int i = 0; i < JournalList.size(); i++) {
				view.addRow(JournalList.get(i));
			}
			view.updateTableColumnIdx(view.getJournalTable());
			view.setRowSelectionInterval(0, 0);
		}
	};

}
