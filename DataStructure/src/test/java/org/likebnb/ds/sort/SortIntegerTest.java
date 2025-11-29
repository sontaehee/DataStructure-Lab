package org.likebnb.ds.sort;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortIntegerTest {
	final static private Logger LOG = LoggerFactory.getLogger(SortIntegerTest.class);	

	private static Integer[] data = { 69, 10, 30, 2, 16, 8, 31, 22 };

	private static long start, end;

	@BeforeAll
	static public void beforeAll() {
		//data = makeDataSet(10);
		LOG.info("데이터셋의 크기 : {}(개)", data.length);
		StringBuffer sb = new StringBuffer("{ ");
		for (Integer datum: data) {
			sb.append(datum).append(" ");
		}
		sb.append("}");
		LOG.info("      초기 상태 : {}", sb.toString());
	}

	@AfterAll
	static public void afterAll() {
		data = null;
	}
	
	@Test
	public void testCase01() {
		start = System.currentTimeMillis();
		LOG.info("---------------------------------------------");
		SelectionSort<Integer> selectionSort = new SelectionSort<Integer>(data.clone());
		selectionSort.sort();
		end = System.currentTimeMillis();
		LOG.info("---------------------------------------------");
		LOG.info("선택정렬 Result : {}", selectionSort.printData());
		LOG.info("   Elapsed Time : {}(ms)", (end - start));
		LOG.info("---------------------------------------------");
	}
	
	@Test
	public void testCase02() {
		LOG.info("---------------------------------------------");
		start = System.currentTimeMillis();
		MergeSort<Integer> mergeSort = new MergeSort<Integer>(data.clone());
		mergeSort.sort();
		end = System.currentTimeMillis();
		LOG.info("---------------------------------------------");
		LOG.info("병합정렬 Result : {}", mergeSort.printData());
		LOG.info("   Elapsed Time : {}(ms)", (end - start));
		LOG.info("---------------------------------------------");
	}
	
	@Test
	public void testCase03() {
		LOG.info("---------------------------------------------");
		start = System.currentTimeMillis();
		QuickSort<Integer> quickSort = new QuickSort<Integer>(data.clone());
		quickSort.sort();
		end = System.currentTimeMillis();
		LOG.info("---------------------------------------------");
		LOG.info(" 퀵정렬 Result : {}", quickSort.printData());
		LOG.info("  Elapsed Time : {}(ms)", (end - start));
		LOG.info("---------------------------------------------");
	}

	private static Integer[] makeDataSet(int n) {
		Integer[] data = new Integer[n];
		Random random = new Random();
		for (int idx = 0; idx < n; idx++) {
			data[idx] = random.nextInt(n*10);
		}

		return data;
	}
}
