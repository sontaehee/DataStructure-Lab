package org.likebnb.ds.sort;

import org.likebnb.ds.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectionSort<T> implements Sort<T> {
	final static private Logger LOG = LoggerFactory.getLogger(SelectionSort.class);	
	
	T[] data;
	int step = 0;
	
	SelectionSort(T[] data) {
		setData(data);
	}

	@Override
	public void setData(T[] data) {
		this.data = data.clone();
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
	public void sort() {

		LOG.info("선택정렬 Step 0 : 원소개수={}", data.length);
		LOG.info("  ->   정렬대상 : {}", printData());
		
		int i, j, min;
		// 기준자리를 오른쪽으로 하나씩 이동한다 
		for (i = 0; i < data.length - 1; i++) {
			min = i;
			
			// 기준자리 이후부터 끝까지 데이터 중 최소값을 찾는다  
			for (j = i + 1; j < data.length; j++) {
				if (ObjectUtils.compare(data[j], data[min]) <0) {
					min = j;
				}
			}
			
			LOG.info("---------------------------------------------");
			LOG.info("  ->     Step {} : position={}, minDatum={}", new Object[] {++step, i+1, data[min]});
			LOG.info("       prevStep : {}", printData());
			
			// 찾은 최소값을 기준자리와 맞교환 
			swap(min, i);
			LOG.info("           swap : {}", printData());
		}
	}

	private void swap(int i, int j){
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}