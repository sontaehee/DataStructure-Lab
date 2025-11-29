package org.likebnb.ds.stack;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likebnb.ds.stack.Stack.OverflowException;
import org.likebnb.ds.stack.Stack.UnderflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackLinearListStringTest {
	final static private Logger LOG = LoggerFactory.getLogger(StackLinearListStringTest.class);

	static StackLinearList<String> stack;
	
	@BeforeAll
	static public void beforeAll() {
		LOG.info("Before All");
		stack = new StackLinearList<String>(5);
	}

	@AfterAll
	static public void afterAll() {
		LOG.info("After All");
		stack = null;
	}
	
	@AfterEach
	public void afterEach() {
		LOG.info("{}", stack.toString());
	}
	
	@Test
	public void testCase00() {
		LOG.info("[testCase00] getCurSize() -> 0");
		assertEquals(0, stack.getCurSize());
	}
	
	@Test
	public void testCase01() {
		try {
			LOG.info("[testCase01] push() & peek() -> top: 1st Node");
			stack.push(new StackNode<String>("1st Node"));
			assertEquals("1st Node", stack.peek());
		} catch (UnderflowException | OverflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}
	
	@Test
	public void testCase02() {
		try {
			LOG.info("[testCase02] push() & getCuSize() -> 2");
			stack.push(new StackNode<String>("2nd Node"));
			assertEquals(2, stack.getCurSize());
		} catch (OverflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}
	
	@Test
	public void testCase03() {
		try {
			LOG.info("[testCase03] pop() & getData() -> data: 2nd Node");
			StackNode<String> node = stack.pop();
			assertEquals("2nd Node", node.getData());
		} catch (UnderflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}
	
	@Test
	public void testCase04() {
		try {
			LOG.info("[testCase04] peek() -> top: 1st Node");
			assertEquals("1st Node", stack.peek());
		} catch (UnderflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}
	
	@Test
	public void testCase05() {
		try {
			LOG.info("[testCase05] pop() & getData() -> data: 1st Node");
			StackNode<String> node = stack.pop();
			assertEquals("1st Node", node.getData());
		} catch (UnderflowException e) {
			LOG.warn("{}", e.getMessage());
		}
	}
	
	@Test
	public void testCase06() {
		try {
			LOG.info("[testCase06] pop() & isEmpty -> true");
			StackNode<String> node = stack.pop();
			LOG.info("Node is popped -> {}", node.getData());
		} catch (UnderflowException e) {
			LOG.warn("{}", e.getMessage());
			assertEquals(true, stack.isEmpty());
		}
	}
}
