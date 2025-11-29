package org.likebnb.ds.sort;

import org.likebnb.ds.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeSort<T> extends SelectionSort<T> {
	final static private Logger LOG = LoggerFactory.getLogger(MergeSort.class);	

	private T[] sorted;
	private int step = 0;
	
	MergeSort(T[] data) {
		super(data);
		this.sorted = data.clone();
	}

	@Override
	public void sort() {
		
		LOG.info("병합정렬 Step 0 : 원소개수={}", data.length);
		LOG.info("  ->   정렬대상 : {}", printData());
		
		mergeSort(0, data.length - 1);
	}

	private void mergeSort(int begin, int end) {
		int middle;
		if (begin < end) {
			middle = (begin + end) / 2;
			mergeSort(begin, middle);
			mergeSort(middle + 1, end);
			merge(begin, middle, end);
		}
	}

	private void merge(int begin, int middle, int end){

		int i, j, k, t;
		i = begin;
		j = middle + 1;
		k = begin;

		LOG.info("---------------------------------------------");
		LOG.info("  ->     Step {} : beign={}, end={}, middle={}", new Object[] {++step, begin, end, middle});
		LOG.info("       prevStep : {}", printData());
		LOG.info("         target : {}", printTarget(begin, end));
		
		for (int zero = begin; zero <= end; zero++) sorted[zero] = null;
				
		while (i <= middle && j <= end) {
			if (ObjectUtils.compare(data[i], data[j]) <= 0)
				sorted[k] = data[i++];
			else
				sorted[k] = data[j++];
			k++;

			LOG.info("     sorting({}) : {}", k, printSorted());
		}
		
		if (i > middle) {
			for (t = j; t <= end; t++, k++) {
				sorted[k] = data[t];
				LOG.info("     sorting({}) : {}", k+1, printSorted());
			}
		} else {
			for (t = i; t <= middle; t++, k++) {
				sorted[k] = data[t];
				LOG.info("     sorting({}) : {}", k+1, printSorted());
			}
		}

		for (t = begin; t <= end; t++) {
			data[t] = sorted[t];
		}
	}
	
	public String printSorted() {
		StringBuffer sb = new StringBuffer("{ ");
		for (T datum: sorted) {
			sb.append(datum).append(" ");
		}
		sb.append("}");
		
		return sb.toString();
	}
	
	private String printTarget(int begin, int end) {
		StringBuffer sb = new StringBuffer("{ ");
		for (int i = begin; i <= end; i++) {
			sb.append(data[i]).append(" ");
		}
		sb.append("}");
		
		return sb.toString();
	}
}
