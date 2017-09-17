package util.search;

import java.util.ArrayList;
import java.util.List;

import model.Journal;

public class JournalSearchPublisherFilter implements Filter<Journal> {

	@Override
	public List<Journal> filter(List<Journal> list, String keyword) {
		List<Journal> newList = new ArrayList<Journal>();

		for (Journal journal : list) {
			if (journal.getPublisher().contains(keyword)) {
				newList.add(journal);
			}
		}
		return newList;

	}

}
