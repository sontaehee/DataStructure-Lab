package org.likebnb.ds.apps;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PolynomialLinkedListTest {
	final static private Logger LOG = LoggerFactory.getLogger(PolynomialLinkedListTest.class);	
	
	static public PolynomialLinkedList p1;
	static public PolynomialLinkedList p2;
	static public PolynomialLinkedList polyAddition;
	
	@BeforeAll
	static public void beforeAll() {
		p1 = new PolynomialLinkedList();
		p2 = new PolynomialLinkedList();
		polyAddition = new PolynomialLinkedList();
	}
	
	@AfterAll
	static public void afterAll() {
		p1 = null;
		p2 = null;
		polyAddition = null;
	}
	
	@Test
	public void testCase01() {
		TermNode node = new TermNode(3, 4);
		p1.addTerm(node);
		p1.addTerm(new TermNode(7, 0));
		p1.addTerm(new TermNode(3, 2));
		p1.addTerm(new TermNode(9, 3));
		p1.removeTerm(1);
		p1.removeTerm(3);
		LOG.info("Final Poly: {}", p1.printPoly());
	}
	
	// TODO: 1) 다항식, p2를 2X^4 + 5X^3 + 12X + 3로 만들고
	// TODO: 2) 다항식, p1 + p2를 실행하는 테스트 케이스를 코딩하고 실행해보자. 
	// --> 이를 위해선 testCase02()의 이름을 먼저 testCase03()으로 바꿔야 한다.
	
	@Test
	public void testCase02() {
		p2.addTerm(new TermNode(2, 4));
		p2.addTerm(new TermNode(5, 3));
		p2.addTerm(new TermNode(12, 1));
		p2.addTerm(new TermNode(3, 0));
		
		LOG.info("Final Poly p2: {}", p2.printPoly());
	}
	
	@Test
	public void testCase03() {
		polyAddition.addPoly(p1, p2);

		LOG.info("    p1 --> {}", p1.printPoly());
		LOG.info("    p2 --> {}", p2.printPoly());
		LOG.info(" p1+p2 --> {}", polyAddition.printPoly());
	}
}
