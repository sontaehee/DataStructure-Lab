package org.likebnb.ds.tree;

import java.util.ArrayList;

import org.likebnb.ds.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearchTree<T> implements BinaryTree<T> {
	final static private Logger LOG = LoggerFactory.getLogger(BinarySearchTree.class);	

	TreeNode<T> root;					// 트리의 진입점
	int height;							// 트리의 높이 
	int degree;							// 트리의 차수 
	int size;							// 노드의 개수 

	TreeNode<T> deleted;				// 삭제될 노드, 이진탐색트리를 유지하기 위해 후계자로 대치 
	TreeNode<T> predecessor;			// 삭제될 노드의 부모 노드
	TreeNode<T> successor; 				// 삭제될 노드의 후계자  
	TreeNode<T> parentOfSuccessor;		// 후계자의 부모 노드 
	
	ArrayList<StringBuffer> treeView; 	// 트리의 모양을 시각적으로 표현하기 위한 문자열버퍼 리스트 

	BinarySearchTree() {
		this.height = -1;
		this.degree = -1;
		this.size = 0;
		this.treeView = new ArrayList<StringBuffer>();
	}

	/**
	 * 이진탐색트리에 추가할 키의 위치를 결정하는 메서드 
	 * @param parent : 재귀적으로 호출할 때 되돌아 갈 노드  	
	 * @param newNode : 새로 추가할 키 노드 
	 * @return : 호출한 횟수 만큼 반환, 마지막으로 root 반환 후 종료    
	 */
	private TreeNode<T> insertKey(TreeNode<T> parent, TreeNode<T> newNode) {

		if (parent == null) {
			size++;
			return newNode;
		} else if (ObjectUtils.compare(newNode.value, parent.value) < 0) {	// less then
			newNode.level++;
			newNode.position = parent.position * 2;
			parent.left = insertKey(parent.left, newNode);
			return parent;
		} else if (ObjectUtils.compare(newNode.value, parent.value) > 0) { 	// greater then
			newNode.level++;
			newNode.position = parent.position * 2 + 1;
			parent.right = insertKey(parent.right, newNode);
			return parent;
		} else {															// the same
			LOG.info("  The node {} is already exist.", newNode.value);
			return parent;
		}
	}

	/**
	 * 이진탐색트리에서 지정한 키를 찾아 삭제하고 트리를 재구성한다 
	 * @param parent : 재귀호출 후 돌아갈 위치 
	 * @param node : 삭제할 키 노드 
	 * @return : 호출한 횟수 만큼 반환, 마지막으로 root를 반환하고 종료 
	 */
	private TreeNode<T> removeKey(TreeNode<T> parent, TreeNode<T> node) {

		if (parent == null) {
			return null;
			
		// 노드의 좌측 섭트리로 재귀 호출 
		} else if (ObjectUtils.compare(node.value, parent.value) < 0) {
			parent.left = removeKey(parent.left, node);
			if (parent.left == null) {
				deleted = null;
				predecessor = null;
			} else if (parent.left.value == node.value) {
				deleted = parent.left;
				predecessor = parent;
			}
			
		// 노드의 우측 섭트리로 재귀 호출 
		} else if (ObjectUtils.compare(node.value, parent.value) > 0) {
			parent.right = removeKey(parent.right, node);
			if (parent.right == null) {
				deleted = null;
				predecessor = null;
			} else if (parent.right.value == node.value) {
				deleted = parent.right;
				predecessor = parent;
			}
			
		// 삭제할 키 노드를 찾음
		} else {
			deleted = parent;
			predecessor = parent;
		}
		
		// 삭제될 노드의 후계자를 찾고, 이진탐색트리를 재구성함 
		if (deleted != null && parent == root) {
			
			if (deleted.left != null) {
					findSuccessor(deleted.left, deleted.left);
			} else if (deleted.right != null) {
					findSuccessor(deleted.right, deleted.right);
			} else {
				successor = deleted;
			}
			
			LOG.info("--------------------------------------------");
			LOG.info("* 노드 삭제");
			LOG.info("--------------------------------------------");
			LOG.info("  + predecessor : {}", predecessor.value);
			LOG.info("  +     deleted : {}", deleted.value);
			LOG.info("  +   successor : {}", successor.value);
			
			// 후계자의 부모노드를 미리 찾아 놓는다
			parentOfSuccessor = findParent(root, successor);

			// 삭제할 노드의 차수에 따라 처리 방식 결정  
			switch(deleted.getDegree()) {
				case 0:
					if (predecessor.left == deleted) {
						predecessor.left = null;
					} else if (predecessor.right == deleted) {
						predecessor.right = null;
					}
					break;
				case 1:
				case 2:
					deleted.value = successor.value;
					if (successor.getDegree() == 0) {
						if (parentOfSuccessor.left == successor) parentOfSuccessor.left = null;
						if (parentOfSuccessor.right == successor) parentOfSuccessor.right = null;
					} else {
						if (deleted == parentOfSuccessor) {
							successor.value = successor.left.value;
							successor.left = null;
						} else {
							updateNode(successor);
						}
					}
					break;
			}

			size--;
		} else if (deleted == null && parent == root){
			LOG.info("--------------------------------------------");
			LOG.info("* 노드 삭제");
			LOG.info("--------------------------------------------");
			LOG.info("  There is no node, {}", node.value + ".");
		}
		
		return parent;
	}
	
	private void updateNode(TreeNode<T> node) {
		
		if (node.left == null) {
			return;
		}
		node.value = node.left.value;
		
		if (node.left.left == null) {
			node.left = null;
		} else {
			updateNode(node.left);
		}
	}
	
	/**
	 * 전달한 노드의 부모노들 찾아줌 
	 * @param currentRoot : 재귀적으로 호출하므로 돌아 갈 부모노드 
	 * @param node : 이 노드의 부모노드를 찾을 것임 
	 * @return : 부모노드를 반환 
	 */
	private TreeNode<T> findParent(TreeNode<T> currParent, TreeNode<T> node) {        

		if (node == root || currParent == null) {
			return null;
		} 
				
		if (currParent.left == node || currParent.right == node) {
			return currParent;
		}

		if (ObjectUtils.compare(node.value, currParent.value) < 0) {
			return findParent(currParent.left, node);
		} else if (ObjectUtils.compare(node.value, currParent.value) > 0) {
			return findParent(currParent.right, node);
		} else
			return currParent;
	}
	
	/**
	 * 삭제할 노드의 후계자를 찾음
	 * 1) 왼쪽 섭트리에서 
	 * 2) 오른쪽 자식노드를 갖고 있지 않은 첫번째 노드를 후계자로 지정 
	 * @param parent : 재귀호출 기준노드 
	 * @param node : 후계자 후보노드 
	 */
	private void findSuccessor(TreeNode<T> parent, TreeNode<T> node) {
		if (parent == null) {
			successor = null;
			
		// 오른쪽 자식노드가 있으면 재귀호출 
		} else if (node.right != null) {
			findSuccessor(parent, node.right);
		} else {
			successor = node;
		}
	}
	
	/**
	 * 주어진 노드를 루트로 하는 트리의 높이를 구한다  
	 * @param node : leaf(-1)를 만날 때 까지 재귀호출  
	 * @return : 재귀호출에 대한 반환 때 마다 1씩 증가 
	 */
	private int getHeight(TreeNode<T> node) {
		if (node == null)
			return -1;
		else
			return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}

	/**
	 * 주어진 노드를 루트로 하는 트리의 차수를 구한다 
	 * @param node
	 */
	private void setDegree(TreeNode<T> node) {
		if (node == root) degree = -1;	// root 노드에서 차수를 초기화 
		if (node != null) {
			setDegree(node.left);
			setDegree(node.right);
			degree = degree > node.getDegree() ? degree : node.getDegree();
		}
	}
	
	@Override
	public void insertNode(TreeNode<T> newNode) {
		root = insertKey(root, newNode);
		height = getHeight(root);
		setDegree(root);
	}

	@Override
	public void removeNode(TreeNode<T> node) {
		removeKey(root, node);
		height = getHeight(root);
		setDegree(root);
	}

	@Override
	public boolean searchNode(TreeNode<T> node) {
		TreeNode<T> p = root;
		while (p != null) {
			if (ObjectUtils.compare(node.value, p.value) < 0) {
				p = p.left;
			} else if (ObjectUtils.compare(node.value, p.value) > 0) {
				p = p.right;
			} else
				return true;
		}

		return false;
	}

	public void preorder(TreeNode<T> root, StringBuffer sb) {
		if (root != null) {
			sb.append(root.value).append(" ");
			preorder(root.left, sb);
			preorder(root.right, sb);
		}
	}

	public void inorder(TreeNode<T> root, StringBuffer sb) {
		if (root != null) {
			inorder(root.left, sb);
			sb.append(root.value).append(" ");
			inorder(root.right, sb);
		}
	}

	public void postorder(TreeNode<T> root, StringBuffer sb) {
		if (root != null) {
			postorder(root.left,sb);
			postorder(root.right, sb);
			sb.append(root.value).append(" ");
		}
	}

	/**
	 * 이진탐색트리를 시각적으로 보여주기 위해 레벨별로 위치를 결정 
	 * @param root
	 * @param level : 주어진 레벨의 노드들을 제 위치에 배치  
	 */
	private void getLevel(TreeNode<T> root, int level) {
		int maxNode = (int) (Math.pow(2, height));			// 맨 마지막 레벨의 최대 노드개수 
		int lineLength = maxNode * 6;						// 노드를 표현할 한 줄의 길이 
		int slot = (int) (lineLength / Math.pow(2, level));	// 레벨별로 하나의 노드가 차지할 슬롯  

		if (root != null) {
			if (root.level == level) {
				int offset = (int) (slot * (root.position - Math.pow(2, level))) + slot / 2;
				treeView.get(level).insert(offset, 
				                           String.format("%s(%d)", root.value, root.position));
			}
			getLevel(root.left, level);
			getLevel(root.right, level);
		}
	}

	public void printBinarySearchTree() {
		LOG.info("--------------------------------------------");
		LOG.info("* 트리 구조 ");
		LOG.info("--------------------------------------------");
		LOG.info("  +     height : {}", height);
		LOG.info("  +     degree : {}", degree);
		LOG.info("  +      nodes : {}", size);
		LOG.info("  +  structure : ");

		treeView.clear();
		for (int level = 0; level <= height; level++) {
			treeView.add(new StringBuffer(String.format("%200s", " ")));
			getLevel(root, level);
			LOG.info(treeView.get(level).toString());
			LOG.info("");
		}
		LOG.info("--------------------------------------------\n");
	}
	
	public void printTreeTraversal() {
		StringBuffer sb = new StringBuffer();
		
		preorder(root, sb);
		LOG.info("--------------------------------------------");
		LOG.info("* 트리 순회");
		LOG.info("--------------------------------------------");
		LOG.info("  + preOrder   --> {}", sb.toString());

		sb = new StringBuffer();
		inorder(root, sb);
		LOG.info("  + inOrder    --> {}", sb.toString());

		sb = new StringBuffer();
		postorder(root, sb);
		LOG.info("  + postOrder  --> {}", sb.toString());
		LOG.info("--------------------------------------------");
	}
}
