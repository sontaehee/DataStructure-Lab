package org.likebnb.ds.queue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likebnb.ds.stack.Stack.OverflowException;
import org.likebnb.ds.stack.Stack.UnderflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueCircularListStringTest {
	final static private Logger LOG = LoggerFactory.getLogger(QueueCircularListStringTest.class);	

	static public QueueCircularList<String> queue;

	@BeforeAll
	static public void beforeAll() {
		queue = new QueueCircularList<String>(5);
		LOG.info("{}", queue);
	}

	@AfterAll
	static public void afterAll() {
		queue = null;
	}
	
	@Test
	public void testCase1() {
		try {
			for (int idx = 1; idx <= 5; idx++) {
				queue.enQueue(new QueueNode<String>(String.format("Node(%d)", idx)));
				LOG.info("{}", queue);
			}
		} catch (OverflowException e) {
			LOG.warn("{}", e.getMessage());
			LOG.info("{}", queue);			
		}
	}

	@Test
	public void testCase2() {
		try {
			for (int idx = 1; idx <= 5; idx++) {
				queue.deQueue();
				LOG.info("{}", queue);
			}
		} catch (UnderflowException e) {
			LOG.warn("{}", e.getMessage());
			LOG.info("{}", queue);			
		}
	}
	
	@Test
	public void testCase3() {
		try {
			for (int idx = 5; idx <= 9; idx++) {
				queue.enQueue(new QueueNode<String>(String.format("Node(%d)", idx)));
				LOG.info("{}", queue);
			}
		} catch (OverflowException e) {
			LOG.warn("{}", e.getMessage());
			LOG.info("{}", queue);			
		}
	}
	
	@Test
	public void testCase4() {
		try {
			for (int idx = 5; idx <= 9; idx++) {
				queue.deQueue();
				LOG.info("{}", queue);
			}
		} catch (UnderflowException e) {
			LOG.warn("{}", e.getMessage());
			LOG.info("{}", queue);			
		}
	}
}
