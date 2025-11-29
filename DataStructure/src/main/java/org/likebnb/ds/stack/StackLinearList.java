package org.likebnb.ds.stack;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackLinearList<T> implements Stack<T> {
	final static private Logger LOG = LoggerFactory.getLogger(StackLinearList.class);	

	private int top;
	private int curSize;
	private int maxSize;
	private ArrayList<StackNode<T>> nodes;
	
	StackLinearList() {
		this(Stack.maxSize);
	}
	
	StackLinearList(int maxSize) {
		this.top = -1;
		this.curSize = 0;
		this.maxSize = maxSize;
		this.nodes = new ArrayList<StackNode<T>>(maxSize);
		
		LOG.info(" ## create Stack({}), the Stack is", maxSize);
		LOG.info("      maxSize : {}", maxSize);
		LOG.info("      curSize : {}", curSize);
	}
	
	@Override
	public void push(StackNode<T> node) throws OverflowException {
		if (isFull()) throw new OverflowException();

		top++;
		this.nodes.add(node);
		curSize = top + 1;
		
		LOG.info(" ## push({}), the Stack is", node.getData());
	}

	@Override
	public StackNode<T> pop() throws UnderflowException {
		if (isEmpty()) throw new UnderflowException();
		
		StackNode<T> node = this.nodes.get(top--);
		curSize = top + 1;

		LOG.info(" ## pop() --> {}, the Stack is", node.getData());
		
		return node;
	}

	@Override
	public T peek() throws UnderflowException {
		if (isEmpty()) throw new UnderflowException("스택이 비었습니다.");
		
		LOG.info(" ## peek() --> {}, the Stack is", this.nodes.get(top).getData());

		return this.nodes.get(top).getData();
	}

	@Override
	public boolean isEmpty() {
		return curSize == 0 ? true : false;
	}

	@Override
	public boolean isFull() {
		if (curSize == maxSize) 
			return true;
		else
			return false;
	}

	@Override
	public int getCurSize() {
		return this.curSize;
	}

	@Override
	public String toString() {
		
		StackNode<T> node;
		StringBuffer sb = new StringBuffer("\n"); 
		for (int pos = curSize; pos > 0; pos--) {
			node = nodes.get(pos - 1);
			sb.append("       +----------------+\n");
			sb.append(String.format("%2d --> | %14s | %s", 
					pos, node.getData(), pos == getCurSize() ? " <-- top\n" : "\n"));
			sb.append("       +----------------+\n");
		} 
		
		if (curSize == 0) {
			sb.append("       +----------------+\n");
			sb.append(" 0 --> |           null |  <-- top\n");
			sb.append("       +----------------+\n");
		}
		
		return sb.toString();
	}
}
