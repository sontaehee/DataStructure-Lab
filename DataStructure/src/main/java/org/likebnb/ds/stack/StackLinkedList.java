package org.likebnb.ds.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackLinkedList<T> implements Stack<T> {
	final static private Logger LOG = LoggerFactory.getLogger(StackLinkedList.class);	
	
	private int maxSize;
	private int curSize;
	private StackNode<T> top;

	StackLinkedList() {
		this(Stack.maxSize);
	}
	
	StackLinkedList(int maxSize) {
		this.curSize = 0;
		this.maxSize = maxSize;
		this.top = new StackNode<T>();
		
		LOG.info(" ## create Stack({}), the Stack is", maxSize);
		LOG.info("      maxSize : {}", maxSize);
		LOG.info("      curSize : {}", curSize);
	}
	
	@Override
	public void push(StackNode<T> node) throws OverflowException {
		if (isFull()) throw new OverflowException();
	
		node.setLink(top);
		this.top = node;
		setCurSize(getCurSize() + 1);		
		LOG.info(" ## push({}), the Stack is", node.getData());
	}

	@Override
	public StackNode<T> pop() throws UnderflowException {
		if (isEmpty()) throw new UnderflowException();

		StackNode<T> node = top;
		top = node.getLink();
		setCurSize(getCurSize() - 1);
		LOG.info(" ## pop() --> {}, the Stack is", node.getData());
		
		return node;
	}

	@Override
	public T peek() throws UnderflowException {
		if (isEmpty()) throw new UnderflowException("스택이 비었습니다.");
		
		LOG.info(" ## peek() --> {}, the Stack is", top.getData());

		return top.getData();
	}

	@Override
	public boolean isEmpty() {
		return curSize == 0 ? true : false;
	}

	@Override
	public boolean isFull() {
		if (curSize == maxSize) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getCurSize() {
		return this.curSize;
	}

	private void setCurSize(int curSize) {
		this.curSize = curSize;
	}
	
	@Override
	public String toString() {
		
		StackNode<T> node = top;
		int pos = getCurSize();
		StringBuffer sb = new StringBuffer("\n"); 
		do {
			sb.append("       +----------------+\n");
			sb.append(String.format("%2d --> | %14s | %s", 
					pos, node.getData(), pos == getCurSize() ? " <-- top\n" : "\n"));
			sb.append("       +----------------+\n");
			node = node.getLink();
			pos--;
		} while (node != null && node.getLink() != null);
		
		return sb.toString();
	}
}
