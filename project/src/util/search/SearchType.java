package util.search;

public class SearchType implements Comparable<Object> {
	public final static SearchType BOOK = new SearchType("book");
	public final static SearchType DVD = new SearchType("dvd");
	public final static SearchType JOURNAL = new SearchType("journal");
	public final static SearchType NONE = new SearchType("none");

	private final String name;
	private static int nextOrdinal = 0;
	private final int ordinal = nextOrdinal++;
	
	public String toString() {
		return name;
	}

	public SearchType(String name) {
		this.name = name;
	}

	public int getOrdinal(){
		return ordinal;
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof SearchType) {
			return ordinal - ((SearchType) o).getOrdinal(); 
		}
		return 0;
	}

}
