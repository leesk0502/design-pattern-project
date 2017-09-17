package util.search;

import java.util.ArrayList;
import java.util.List;

import model.DVD;

public class DVDSearchAuthorFilter implements Filter<DVD> {

	@Override
	public List<DVD> filter(List<DVD> list, String keyword) {
		List<DVD> newList = new ArrayList<DVD>();

		for (DVD dvd : list) {
			if (dvd.getAuthor().contains(keyword)) {
				newList.add(dvd);
			}
		}
		return newList;

	}

}
