package util.search;

public class SearchFactory {

	public static Search getSearch(SearchType type) {
		if (type == SearchType.BOOK) {
			return new BookSearch();
		} else if (type == SearchType.DVD) {
			return new DVDSearch();
		} else if (type == SearchType.JOURNAL) {
			return new JournalSearch();
		}
		return null;
	}
}
