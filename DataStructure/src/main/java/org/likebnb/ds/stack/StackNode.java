package org.likebnb.ds.stack;

public class StackNode<T> {
	private T data;
	private StackNode<T> link;
	
	protected StackNode() {
		this.data = null;
		this.link = null;
	}
	
	protected StackNode(T data) {
		this.data = data;
		this.link = null;
	}
	
	public T getData() {
		return this.data != null ? this.data : null;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public StackNode<T> getLink() {
		return this.link;
	}
	
	public void setLink(StackNode<T> link) {
		this.link = link;
	}
	
	@Override 
	public String toString() {
		return (String) this.getData();
	}
}
