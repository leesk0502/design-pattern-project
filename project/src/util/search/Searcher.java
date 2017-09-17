package util.search;

import java.util.List;

public class Searcher {
	SearcherBuilder builder;

	public Searcher(SearcherBuilder builder) {
		this.builder = builder;
	}

	public List<?> search() {
		Search search = SearchFactory.getSearch(builder.getItemType());
		if (search != null)
			return search.search(builder.getItemList(), builder.getSearchField(), builder.getKeyword());

		return null;
	}
}
