package org.likebnb.ds.search;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likebnb.ds.search.Search.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchTest {
	final static private Logger LOG = LoggerFactory.getLogger(SearchTest.class);	

	private static Integer[] unSortedData = { 69, 10, 30, 2, 16, 8, 31, 22 };
	private static Integer[] sortedData = { 2, 8, 10, 16, 22, 30, 31, 69 };
	private static SearchResult result;

	@BeforeAll
	static public void beforeAll() {
	}

	@AfterAll
	static public void afterAll() {
	}

	@Test
	// 순차검색 : 정렬되지 않은 데이터셋
	public void testCase01() {
		sequentSearchTest(unSortedData, 1, false);
		sequentSearchTest(unSortedData, 2, false);
		sequentSearchTest(unSortedData, 16, false);
		sequentSearchTest(unSortedData, 69, false);
		sequentSearchTest(unSortedData, 70, false);
	}

	@Test
	// 순차검색 : 정렬된 데이터셋
	public void testCase02() {
		sequentSearchTest(sortedData, 1, true);
		sequentSearchTest(sortedData, 2, true);
		sequentSearchTest(sortedData, 16, true);
		sequentSearchTest(sortedData, 69, true);
		sequentSearchTest(sortedData, 70, true);
	}

	@Test
	// 이진검색 : 정렬된 데이터셋
	public void testCase03() {
		binarySearchTest(sortedData, 1, true);
		binarySearchTest(sortedData, 2, true);
		binarySearchTest(sortedData, 16, true);
		binarySearchTest(sortedData, 69, true);
		binarySearchTest(sortedData, 70, true);
	}
	
	@Test
	// 블룸필터 : 정렬되지 않은 데이터셋
	public void testCase04() {
		bloomFilterSearch(unSortedData, 1, false);
		bloomFilterSearch(unSortedData, 2, false);
		bloomFilterSearch(unSortedData, 16, false);
		bloomFilterSearch(unSortedData, 69, false);
		bloomFilterSearch(unSortedData, 70, false);
	}
	
	static public <T> void sequentSearchTest(T[] data, T key, boolean isSorted) {

		LOG.info("[순차검색] ------------------------------");
		SequentialSearch<T> sequentSearch = new SequentialSearch<T>(data.clone());
		result = sequentSearch.search(key, isSorted);
		LOG.info(" 검색대상 : {}", sequentSearch.printData());
		LOG.info("-----------------------------------------");
		LOG.info(" 정렬여부 : {}", isSorted ? "sorted" : "unsorted");
		LOG.info(" 검색키값 : {}", key);
		LOG.info(" 검색여부 : {}", result.isFound ? "success" : "fail");
		LOG.info(" 비교횟수 : {}", result.compCount);
		LOG.info("-----------------------------------------\n");
	}

	static public <T> void binarySearchTest(T[] data, T key, boolean isSorted) {

		LOG.info("[이진검색] ------------------------------");
		BinarySearch<T> binarySearch = new BinarySearch<T>(data.clone());
		LOG.info(" 검색대상 : {}", binarySearch.printData());
		LOG.info("-----------------------------------------");
		result = binarySearch.search(key);
		LOG.info("-----------------------------------------");
		LOG.info(" 정렬여부 : {}", isSorted ? "sorted" : "unsorted");
		LOG.info(" 검색키값 : {}", key);
		LOG.info(" 검색여부 : {}", result.isFound ? "success" : "fail");
		LOG.info(" 비교횟수 : {}", result.compCount);
		LOG.info("-----------------------------------------\n");
	}

	static public <T> void bloomFilterSearch(T[] data, T key, boolean isSorted) {

		LOG.info("[블룸필터] ------------------------------");
		BloomFilterSearch<T> bfSearch = new BloomFilterSearch<T>(data.clone());
		LOG.info(" 검색대상 : {}", bfSearch.printData());
		LOG.info("-----------------------------------------");
		result = bfSearch.search(key, isSorted);
		LOG.info("-----------------------------------------");
		LOG.info(" 정렬여부 : {}", isSorted ? "sorted" : "unsorted");
		LOG.info(" 검색키값 : {}", key);
		LOG.info(" 검색여부 : {}", result.isFound ? "Probably there(success)" : "Definitely not there(fail)");
		LOG.info(" 비교횟수 : {}", result.compCount);
		LOG.info("-----------------------------------------\n");
	}
}
