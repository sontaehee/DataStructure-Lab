package org.likebnb.ds.sort;

public interface Sort<T> {

	void setData(T[] data);
	T[] getData();
	String printData();
	void sort();
}
