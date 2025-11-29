package org.likebnb.ds.search;

import org.likebnb.ds.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearch<T> extends SequentialSearch<T> {
	final static private Logger LOG = LoggerFactory.getLogger(BinarySearch.class);	

	public BinarySearch(T[] data) {
		super(data);
	}

	@Override
	public SearchResult search(T key) {

		int begin = 1;
		int end = data.length;
		int middle;
		int compCount = 0;
		boolean isFound = false;
				
		while (begin <= end) {
			middle = (begin + end) / 2;
			compCount++;
			
			LOG.info(" +Step[{}] : begin={}, end={}, middle={}", 
					          new Object[]{compCount, begin, end, middle});
			
			if (key == data[middle - 1]) {
				isFound = true;
				break;
			} else if (ObjectUtils.compare(key, data[middle - 1]) < 0) {
				end = middle - 1;
			} else {
				begin = middle + 1;
			}
		}
		
		return new SearchResult(compCount, isFound);
	}
 }
