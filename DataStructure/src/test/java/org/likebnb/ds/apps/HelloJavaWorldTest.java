package org.likebnb.ds.apps;

// import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJavaWorldTest {
	final static private Logger LOG = LoggerFactory.getLogger(HelloJavaWorldTest.class);

	@BeforeAll
	static public void beforeAll() {
		LOG.info("이 클래스가 생성되는 시점에 자동으로 실행");
	}
	
	@AfterAll
	static public void afterAll() {
		LOG.info("모든 @Test 수행 후 마지막으로 실행되는 메쏘드");
	}
	
	@Test
	public void testCase01() {
		HelloJavaWorld hjw = new HelloJavaWorld();
		HelloJavaWorld.main(null);
		assertEquals(true, (hjw instanceof HelloJavaWorld));
	}
}