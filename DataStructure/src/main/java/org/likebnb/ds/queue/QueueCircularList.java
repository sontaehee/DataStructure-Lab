package org.likebnb.ds.queue;

import org.likebnb.ds.stack.Stack.OverflowException;
import org.likebnb.ds.stack.Stack.UnderflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueCircularList<T> extends QueueLinearList<T> {
	final static private Logger LOG = LoggerFactory.getLogger(QueueCircularList.class);	
	
	QueueCircularList() {
		super();
	}
	
	QueueCircularList(int maxSize) {
		super(maxSize);
	}
	
	@Override
	public void enQueue(QueueNode<T> node) throws OverflowException {
		if (isFull()) throw new OverflowException(
				String.format("큐가 가득 찼습니다. 노드(%s) 추가 실패.", node.getData()));
		
		nodes.set(rear, node);
		rear =  (rear + 1) % maxSize;
		curSize++;
		
		LOG.info(" ## enQueue({}), the Queue is", node.getData());
	}

	@Override
	public QueueNode<T> deQueue() throws UnderflowException {
		if(isEmpty()) throw new UnderflowException("큐가 비었습니다.");
		
		QueueNode<T> queue = nodes.get(front);
		nodes.set(front, new QueueNode<T>());
		
		front = ((front + 1) % maxSize);
		curSize--;

		LOG.info(" ## deQueue({}), the Queue is", queue.getData());

		return queue;
	}

	@Override
	public boolean isFull() {
		return ((rear + 1) % maxSize) == front ? true : false;
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
		
		return sb.toString();
	}
}
