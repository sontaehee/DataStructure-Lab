package org.likebnb.ds.queue;

import org.likebnb.ds.stack.Stack.OverflowException;
import org.likebnb.ds.stack.Stack.UnderflowException;

public interface Queue<T> {
	int maxSize = 100;
	void enQueue(QueueNode<T> node) throws OverflowException;
	QueueNode<T> deQueue() throws UnderflowException; 
	QueueNode<T> getFront() throws UnderflowException;
	boolean isEmpty();
	boolean isFull();
	int getCurSize();
}
