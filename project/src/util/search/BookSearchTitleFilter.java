package util.search;

import java.util.ArrayList;
import java.util.List;

import model.Book;

public class BookSearchTitleFilter implements Filter<Book> {

	@Override
	public List<Book> filter(List<Book> list, String keyword) {
		List<Book> newList = new ArrayList<Book>();

		for (Book book : list) {
			if (book.getTitle().contains(keyword)) {
				newList.add(book);
			}
		}
		return newList;

	}

}
