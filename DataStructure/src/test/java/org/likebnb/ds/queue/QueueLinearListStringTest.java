package org.likebnb.ds.queue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likebnb.ds.stack.Stack.OverflowException;
import org.likebnb.ds.stack.Stack.UnderflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueLinearListStringTest {
	final static private Logger LOG = LoggerFactory.getLogger(QueueLinearListStringTest.class);	

	static public QueueLinearList<String> queue;

	@BeforeAll
	static public void beforeAll() {
		queue = new QueueLinearList<String>(5);
	}

	@AfterAll
	static public void afterAll() {
		queue = null;
	}
	
	@Test
	public void testCase1() {
		try {
			LOG.info("{}", queue);
			queue.enQueue(new QueueNode<String>("1st Node"));
			LOG.info("{}", queue);
			queue.enQueue(new QueueNode<String>("2nd Node"));
			LOG.info("{}", queue);
			queue.deQueue();
			LOG.info("{}", queue);
			queue.enQueue(new QueueNode<String>("3rd Node"));
			LOG.info("{}", queue);
			queue.enQueue(new QueueNode<String>("4th Node"));
			LOG.info("{}", queue);
			
			queue.getFront();
			LOG.info("{}", queue);
			
			for (int idx = 0; idx < 4; idx++) {
				queue.deQueue();
				LOG.info("{}", queue);
			}
		} catch (UnderflowException | OverflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}
	
	@Test
	public void testCase2() {
		try {
			for (int idx = 5; idx < 7; idx++) {
				queue.enQueue(new QueueNode<String>(String.format("%dth Node", idx)));
				LOG.info("{}", queue);
			}
		} catch (OverflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}	
	
	@Test
	public void testCase3() {
		try {
			queue.deQueue();
			LOG.info("{}", queue);

			queue.deQueue();
			LOG.info("{}", queue);
		} catch (UnderflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}	
}
