package org.likebnb.ds.tree;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearchTreeCharTest {
	final static private Logger LOG = LoggerFactory.getLogger(BinarySearchTreeCharTest.class);	
	
	static BinarySearchTree<Character> bst;
	
	@BeforeAll
	static public void beforeEach() {
		LOG.info("Before All");
		bst = new BinarySearchTree<Character>();
	}

	@AfterAll
	static public void afterEach() {
		LOG.info("After All");
		bst = null;
	}
	
	@Test
	public void testCase0() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 0 : 이진탐색트리 자료구조 생성 ");
		LOG.info("--------------------------------------------");
		
		if (bst != null) {	
			LOG.info("  BinarySearchTree is created.");
			LOG.info("    height: {}", bst.height);
			LOG.info("    degree: {}", bst.degree);
			LOG.info("     nodes: {}", bst.size);
		} else {
			LOG.info("  There is no BinarySearchTree.");			
		}
	}

	/**
	 * 다음과 같은 형태의 이진탐색트리를 만든다 
     *                         I(1)                                                                                                                              
     *
     *             F(2)                    K(3)                                                                                                                      
     *
     *       C(4)        H(5)        J(6)        L(7)                                                                                                                        
     *
     *   A(8)  D(9)  G(10)                                	 
	 */
	@Test
	public void testCase1() {
		LOG.info("\n\n--------------------------------------------");
		LOG.info("* Test Case 1 : 이진탐색트리에 노드 추가  ");
		LOG.info("--------------------------------------------");
		bst.insertNode(new TreeNode<Character>('I'));
		bst.insertNode(new TreeNode<Character>('F'));
		bst.insertNode(new TreeNode<Character>('C'));
		bst.insertNode(new TreeNode<Character>('D'));
		bst.insertNode(new TreeNode<Character>('H'));
		bst.insertNode(new TreeNode<Character>('A'));
		bst.insertNode(new TreeNode<Character>('G'));
		bst.insertNode(new TreeNode<Character>('K'));
		bst.insertNode(new TreeNode<Character>('L'));
		bst.insertNode(new TreeNode<Character>('J'));
		
		LOG.info("{I,F, C, D, H, A, G, K, L, J}");

		viewTreeStructure();
		viewTreeTraversal();
	}
	
	@Test
	public void testCase2() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 2 : 단말(left child) A의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('A'));
		viewTreeStructure();
		viewTreeTraversal();
	}
	
	@Test
	public void testCase3() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 3 : 단말(right child) D의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('D'));
		viewTreeStructure();
		viewTreeTraversal();
	}

	@Test
	public void testCase4() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 4 : 없는 노드 X의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('X'));
	}

	@Test
	public void testCase5() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 5 : 지워진 노드 A의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('A'));
	}

	@Test
	public void testCase6() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 6 : 차수가 1(left)인 H의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('H'));
		viewTreeStructure();
		viewTreeTraversal();
	}

	@Test
	public void testCase7() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 7 : 노드D를 추가");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.insertNode(new TreeNode<Character>('D'));
		viewTreeStructure();
		viewTreeTraversal();
	}
	
	@Test
	public void testCase8() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 8 : 차수가 1(right)인 C의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('C'));
		viewTreeStructure();
		viewTreeTraversal();
	}

	@Test
	public void testCase9() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 9 : 차수가 2인 노드 K의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('K'));
		viewTreeStructure();
		viewTreeTraversal();
	}
	
	@Test
	public void testCase10() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 10 : root 노드 I의 삭제");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.removeNode(new TreeNode<Character>('I'));
		viewTreeStructure();
		viewTreeTraversal();
	}

	@Test
	public void testCase11() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 11 : 노드 탐색");
		LOG.info("--------------------------------------------");
		LOG.info("  Is it node 'A'? : " + bst.searchNode(new TreeNode<Character>('A')));
		LOG.info("  Is it node 'G'? : " + bst.searchNode(new TreeNode<Character>('G')));
		viewTreeStructure();
	}
		
	@Test
	public void testCase12() {
		LOG.info("--------------------------------------------");
		LOG.info("* Test Case 12 : 이미 존재하는 D를 추가");
		LOG.info("--------------------------------------------");
		viewTreeStructure();
		bst.insertNode(new TreeNode<Character>('D'));		
		viewTreeStructure();
		viewTreeTraversal();
	}
	
	private void viewTreeStructure() {
		bst.printBinarySearchTree();
	}

	private void viewTreeTraversal() {
		bst.printTreeTraversal();
	}
}
