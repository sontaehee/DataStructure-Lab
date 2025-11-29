package org.likebnb.ds.search;

import org.likebnb.ds.utils.ObjectUtils;

public class SequentialSearch<T> implements Search<T> {

	protected T[] data;
	
	public SequentialSearch(T[] data) {
		this.setData(data);
	}

	@Override
	public void setData(T[] data) {
		this.data = data;
	}

	@Override
	public T[] getData() {
		return this.data;
	}
	
	@Override
	public String printData() {
		StringBuffer sb = new StringBuffer("{ ");
		for (T datum: data) {
			sb.append(datum).append(" ");
		}
		sb.append("}");
		
		return sb.toString();
	}

	@Override
	public SearchResult search(T key) {
		return search(key, false);
	}

	@Override
	public SearchResult search(T key, boolean isSorted) {

		int compCount = 0;
		boolean isFound = false;
		for (int idx = 0; idx < data.length; idx++) {

			compCount = idx + 1;
			if (key == data[idx]) {
				isFound = true;
				break;
			}
			
			// 정렬된 데이터셋인 경우, 
			// 키 값 보다 비교 순서가 된 원소가 크면 종료   
			if (isSorted && (ObjectUtils.compare(key, data[idx]) < 0)) {
				break;
			}
		}
		
		return new SearchResult(compCount, isFound);
	}
}
