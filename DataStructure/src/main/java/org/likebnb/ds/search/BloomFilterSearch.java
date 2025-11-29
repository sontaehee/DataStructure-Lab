package org.likebnb.ds.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloomFilterSearch<T> implements Search<T> {
	final static private Logger LOG = LoggerFactory.getLogger(BloomFilterSearch.class);	

	private BloomFilter<T> bloomFilter;
	private T[] data;
	
	public BloomFilterSearch(T[] data) {
		this.setData(data);
	}

	@Override
	public void setData(T[] data) {

		this.data = data;
		double falsePositiveProbability = 0.1;
		int expectedSize = this.data.length;

		bloomFilter = new BloomFilter<T>(falsePositiveProbability, expectedSize);
		for (int idx = 0; idx < this.data.length; idx++) {
			bloomFilter.add((T)this.data[idx]);
		}
	}

	@Override
	public T[] getData() {
		// TODO Auto-generated method stub
		return null;
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
		
		boolean isFound = false;
		int compCount = 0;
		if (bloomFilter.contains((T)key)) {
			isFound = true;
			compCount++;
		} else {
			compCount++;
		}
		
		LOG.info(" 해시함수 : k = {}", bloomFilter.getK());
		LOG.info(" 비트배열 : m = {}", bloomFilter.size());
		LOG.info(" 기대확률 : p = {}", bloomFilter.getFalsePositiveProbability());
		LOG.info(" 원소갯수 : n = {}", bloomFilter.count());
		
		return new SearchResult(compCount, isFound);
	}

	@Override
	public SearchResult search(T key, boolean isSorted) {
		return search(key);
	}
}
