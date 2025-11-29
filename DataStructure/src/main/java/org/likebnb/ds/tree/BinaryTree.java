package org.likebnb.ds.tree;

public interface BinaryTree<T> {
	void insertNode(TreeNode<T> node);
	void removeNode(TreeNode<T> node);
	boolean searchNode(TreeNode<T> node);
}
