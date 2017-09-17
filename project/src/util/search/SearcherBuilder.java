package util.search;

import java.util.List;

import model.Book;
import model.DVD;
import model.Journal;

public class SearcherBuilder {
	private SearchType itemType = SearchType.NONE;
	private List<?> itemList;
	private String searchField;
	private String keyword;

	public SearcherBuilder() {
	}

	public SearcherBuilder setItemList(List<?> list) {
		this.itemList = list;
		if (itemList.size() > 0) {
			if (itemList.get(0) instanceof Book) {
				this.itemType = SearchType.BOOK;
			} else if (itemList.get(0) instanceof DVD) {
				this.itemType = SearchType.DVD;
			} else if (itemList.get(0) instanceof Journal) {
				this.itemType = SearchType.JOURNAL;
			}
		}
		return this;
	}

	public SearcherBuilder setItemType(SearchType type) {
		this.itemType = type;
		return this;
	}

	public SearcherBuilder setSearchField(String field) {
		this.searchField = field;
		return this;
	}

	public SearcherBuilder setSearchKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}

	public List<?> getItemList() {
		return itemList;
	}

	public SearchType getItemType() {
		return itemType;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getSearchField() {
		return searchField;
	}

	public Searcher build() {
		return new Searcher(this);
	}
}
