package org.likebnb.ds.apps;

// import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PolynomialTest {

	static public Polynomial p1;
	static public Polynomial p2;
	static public Polynomial polyAddition;

	@BeforeAll
	static public void beforeAll() {
		p1 = new Polynomial();
		p2 = new Polynomial();
		polyAddition = new Polynomial();
	}

	@AfterAll
	static public void afterAll() {
		p1 = null;
		p2 = null;
		polyAddition = null;
	}

	@Test
	public void testCase01() {
		p1.addTerm(new Term(2, 1));
		p1.addTerm(new Term(7, 2));
		p1.printSummary("다항식(Polynomial) p1 생성");
		assertEquals(2, p1.getDegree());
	}

	@Test
	public void testCase02() {
		assertEquals(-1, p1.findTerm(new Term(1,6), p1));
		p1.removeTerm(6);
		p1.printSummary("다항식(Polynomial)에서 존재하지 않는 항 제거 시도 : p1.removeTerm(6)");
		assertEquals(-1, p1.findTerm(new Term(1,6), p1));
	}

	@Test
	public void testCase03() {
		assertEquals(2, p1.getTermCount());
		p1.removeTerm(1);
		p1.printSummary("다항식(Polynomial)에서 항 제거 : p1.removeTerm(1)");
		assertEquals(1, p1.getTermCount());
	}

	@Test
	public void testCase04() {
		p2.addTerm(new Term(2, 0));
		p2.addTerm(new Term(4, 3));
		p2.addTerm(new Term(3, 2));
		p2.printSummary("다항식(Polynomial) p2 생성");
		assertEquals(3, p2.getDegree());
	}

	@Test
	public void testCase05() {
		polyAddition.addPoly(p1, p2);
		polyAddition.printSummary("다항식(Polynomial)의 덧셈 p1 + p2");
		assertEquals(3, polyAddition.getDegree());
	}
}
