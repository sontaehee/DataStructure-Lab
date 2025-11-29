package org.likebnb.ds.sort;

import java.util.concurrent.atomic.AtomicInteger;

import org.likebnb.ds.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickSort<T> extends SelectionSort<T> {
	final static private Logger LOG = LoggerFactory.getLogger(QuickSort.class);

	private AtomicInteger step = new AtomicInteger(0);

	QuickSort(T[] data) {
		super(data);
	}

	@Override
	public void sort() {
		LOG.info(" 퀵정렬 Step 0 : 원소개수={}", data.length);
		LOG.info("   -> 정렬대상 : {}", printData());

		quickSort(0, data.length - 1);
	}

	private void quickSort(int begin, int end) {
		if (begin < end) {
			int p;
			p = splitPartition(begin, end);
			quickSort(begin, p - 1);
			quickSort(p + 1, end);
		}
	}

	private int splitPartition(int begin, int end) {
		int pivot, L, R;
		T temp;
		L = begin;
		R = end;
		pivot = (begin + end) / 2;
		LOG.info("---------------------------------------------");
		LOG.info("  ->    Step {} : pivot={}", step.incrementAndGet(), data[pivot]);
		LOG.info("      prevStep : {}", printData());
		while (L < R) {
			while ((ObjectUtils.compare(data[L], data[pivot]) < 0) && (L < R)) L++;
			while ((ObjectUtils.compare(data[R], data[pivot]) >= 0) && (L < R)) R--;
			if (L < R) {
				LOG.info("                -----------------------------");
				LOG.info("    L,R을 찾음 : L={}, R={}", data[L], data[R]);
				temp = data[L];
				data[L] = data[R];
				data[R] = temp;
				if (L == pivot) { pivot = R; }
				LOG.info("    L,R을 교환 : {}", printData());
			}
		}
		LOG.info("                -----------------------------");
		LOG.info("    R을 못찾음 : L={}, R={}, pivot={}",
		                                 new Object[] { data[L], data[R], data[pivot]});

		temp = data[pivot];
		data[pivot] = data[R];
		data[R] = temp;
		LOG.info(" 피벗교환/확정 : {}", printData());
		return R;
	}
}
