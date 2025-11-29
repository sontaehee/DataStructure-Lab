package org.likebnb.ds.tree;

public class TreeNode<T> {

	T value;
	TreeNode<T> left;
	TreeNode<T> right;
	int position;
	int level;
	private int degree;
	
	TreeNode(T value) {
		this(value, null, null);
	}
	
	TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
		this.left = left;
		this.right = right;
		this.value = value;
		this.level = 0;
		this.position = 1;
		this.degree = 0;
	}
	
	public int getDegree() {
		this.degree = 0;
		if (this.left != null) degree++;
		if (this.right != null) degree++;
		
		return this.degree;
	}
}
