package org.likebnb.ds.search;

public interface Search<T> {

	void setData(T[] data);
	T[] getData();
	String printData();
	SearchResult search(T key);
	SearchResult search(T key, boolean isSorted);
	
	public class SearchResult {
		public int compCount;
		public boolean isFound;
		
		public SearchResult(int compCount, boolean isFound) {
			this.compCount = compCount;
			this.isFound = isFound;
		}
	}
}
