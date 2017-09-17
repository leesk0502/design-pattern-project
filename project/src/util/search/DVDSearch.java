package util.search;

import java.util.List;

import model.DVD;

public class DVDSearch extends Search {

	@SuppressWarnings("unchecked")
	@Override
	public List<?> search(List<?> list, String field, String keyword) {
		Filter<DVD> filter = null;

		switch (field) {
		case "title":
			filter = new DVDSearchTitleFilter();
			break;
		case "author":
			filter = new DVDSearchAuthorFilter();
			break;
		case "publisher":
			filter = new DVDSearchPublisherFilter();
			break;
		}

		return filter.filter((List<DVD>) list, keyword);
	}

}
