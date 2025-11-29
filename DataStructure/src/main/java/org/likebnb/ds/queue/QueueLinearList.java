package org.likebnb.ds.queue;

import java.util.ArrayList;

import org.likebnb.ds.stack.Stack.OverflowException;
import org.likebnb.ds.stack.Stack.UnderflowException;
import org.likebnb.ds.stack.StackNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueLinearList<T> implements Queue<T> {
	final static private Logger LOG = LoggerFactory.getLogger(QueueLinearList.class);	
	
	protected int front;
	protected int rear;
	protected int maxSize;
	protected int curSize;
	protected ArrayList<QueueNode<T>> nodes;
	
	public QueueLinearList() {
		this(Queue.maxSize);
	}
	
	public QueueLinearList(int maxSize) {
		this.front = 0;
		this.rear = 0;
		this.curSize = 0;
		this.maxSize = maxSize;
		this.nodes = new ArrayList<QueueNode<T>>(maxSize);
		for (int idx = 0; idx < maxSize; idx++) {
			nodes.add(new QueueNode<T>());
		}
		
		LOG.info(" ## create Queue({}), the Queue is", maxSize);
		LOG.info("      maxSize : {}", maxSize);
		LOG.info("      curSize : {}", curSize);
		LOG.info("        front : {}", front);
		LOG.info("         rear : {}\n", rear);
	}

	@Override
	public void enQueue(QueueNode<T> node) throws OverflowException {
		if (isFull()) throw new OverflowException(
				String.format("큐가 가득 찼습니다. 노드(%s) 추가 실패.", node.getData()));
		
		nodes.set(rear, node);
		rear =  rear + 1;
		curSize++;
		
		LOG.info(" ## enQueue({}), the Queue is", node.getData());
	}

	@Override
	public QueueNode<T> deQueue() throws UnderflowException {
		if(isEmpty()) throw new UnderflowException("큐가 비었습니다.");
		
		QueueNode<T> queue = nodes.get(front);
		nodes.set(front, new QueueNode<T>());
		
		front = front + 1;
		curSize--;

		LOG.info(" ## deQueue({}), the Queue is", queue.getData());
		
		return queue;
	}

	@Override
	public QueueNode<T> getFront() throws UnderflowException {
		if(isEmpty()) throw new UnderflowException("큐가 비었습니다.");
		
		LOG.info(" ## getFront() --> {}, the Queue is", this.nodes.get(front).getData());

		return nodes.get(front);
	}

	@Override
	public boolean isEmpty() {
		if (rear == front) 
			return true;
		else 
			return false;
	}

	@Override
	public boolean isFull() {
		return rear == (maxSize) ? true : false;
	}

	@Override
	public int getCurSize() {
		return this.curSize;
	}
	
	@Override
	public String toString() {
		
		QueueNode<T> node;
		StringBuffer sb = new StringBuffer("\n"); 
		
		for (int pos = 0; pos < maxSize; pos++) {
			node = nodes.get(pos);
			sb.append("          +----------------+\n");

			if (pos == front) {
				sb.append(String.format(" front--> | %14s | %d%s\n", 
						node.getData(), pos, front == rear ? ", <-- rear" : ""));
			} else {
				sb.append(String.format("%10s| %14s | %d\n", 
						pos == rear ? " rear --> " : "", 
					    node.getData(), pos));
			}
		} 
		sb.append("          +----------------+\n");
		
		if (rear == maxSize) {
			sb.append(String.format("%10s|over the maxSize| %d%s\n", 
					front == maxSize ? " front--> " : "          " ,
					maxSize,
					" 	 <-- rear"));
			sb.append("          +----------------+\n");
		}
		
		return sb.toString();
	}
}
