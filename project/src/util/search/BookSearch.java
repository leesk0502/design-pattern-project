package util.search;

import java.util.List;

import model.Book;

public class BookSearch extends Search{

	@SuppressWarnings("unchecked")
	@Override
	public List<?> search(List<?> list, String field, String keyword) throws NullPointerException, ClassCastException{
		Filter<Book> filter = null;
		
		switch(field){
		case "title" :
			filter = new BookSearchTitleFilter();
			break;
		case "author" :
			filter = new BookSearchAuthorFilter();
			break;
		case "publisher" :
			filter = new BookSearchPublisherFilter();
			break;
		}
		
		return filter.filter((List<Book>)list, keyword);
	}

}
