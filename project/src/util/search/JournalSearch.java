package util.search;

import java.util.List;

import model.Journal;

public class JournalSearch extends Search{

	@SuppressWarnings("unchecked")
	@Override
	public List<?> search(List<?> list, String field, String keyword) {
		Filter<Journal> filter = null;

		switch (field) {
		case "title":
			filter = new JournalSearchTitleFilter();
			break;
		case "publisher":
			filter = new JournalSearchPublisherFilter();
			break;
		}

		return filter.filter((List<Journal>) list, keyword);
	}	

}
