package util.search;

import java.util.ArrayList;
import java.util.List;

import model.Journal;

public class JournalSearchTitleFilter implements Filter<Journal> {

	@Override
	public List<Journal> filter(List<Journal> list, String keyword) {
		List<Journal> newList = new ArrayList<Journal>();

		for (Journal journal : list) {
			if (journal.getTitle().contains(keyword)) {
				newList.add(journal);
			}
		}
		return newList;

	}

}
