package opgave2;

import javax.management.RuntimeErrorException;

/**
 * A binary tree in which each node has two children.
 */
public class BinaryTreeOpgave2<E> {
	private Node root;

	/**
	 * Constructs an empty tree.
	 */
	public BinaryTreeOpgave2() {
		root = null;
	}

	/**
	 * Constructs a tree with one node and no children.
	 * 
	 * @param rootData the data for the root
	 */
	public BinaryTreeOpgave2(E rootData) {
		root = new Node();
		root.data = rootData;
		root.left = null;
		root.right = null;
	}

	/**
	 * Constructs a binary tree.
	 * 
	 * @param rootData the data for the root
	 * @param left     the left subtree
	 * @param right    the right subtree
	 */
	public BinaryTreeOpgave2(E rootData, BinaryTreeOpgave2<E> left, BinaryTreeOpgave2<E> right) {
		root = new Node();
		root.data = rootData;
		if (left != null) {
			root.left = left.root;
		}
		if (right != null) {
			root.right = right.root;
		}
	}

	/**
	 * Checks whether this tree is empty.
	 * 
	 * @return true if this tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Gets the data at the root of this tree.
	 * 
	 * @return the root data
	 */
	public E data() {
		return root.data;
	}

	/**
	 * Gets the left subtree of this tree.
	 * 
	 * @return the left child of the root
	 */
	public BinaryTreeOpgave2<E> left() {
		BinaryTreeOpgave2<E> result = new BinaryTreeOpgave2<E>();
		result.root = root.left;
		return result;
	}

	/**
	 * Gets the right subtree of this tree.
	 * 
	 * @return the right child of the root
	 */
	public BinaryTreeOpgave2<E> right() {
		BinaryTreeOpgave2<E> result = new BinaryTreeOpgave2<E>();
		result.root = root.right;
		return result;
	}

	/**
	 *
	 * @param rootData the new data for the root
	 * @return the data previous in the root
	 */
	public E replace(E rootData) {
		E temp = root.data;
		root.data = rootData;
		return temp;
	}

	/**
	 * The size of the tree
	 * 
	 * @return the number of nodes in the tree
	 */
	public int size() {
		return size(root);
	}

	// Private recursive helper method
	private int size(Node n) {

		if (n == null) {
			return 0;
		} else {
			return 1 + size(n.left) + size(n.right);
		}
	}
	
	/**
	 * The size of the tree
	 * 
	 * @return the number of nodes in the tree
	 */
	public int height() {
		return height(root);
	}

	// Private recursive helper method
	private int height(Node n) {

		if (n == null) {
			return 0;
		} else {
			return 1 + Math.max(height(n.left), height(n.right));
		}
		
	}
	
	public String inOrder() {
		return inOrder(root);
	}
	
	private String inOrder(Node n) {
		if (n == null) {
			return "";
		}
		return  inOrder(n.left) + n.data + " " + inOrder(n.right);
	}
	
	public String preOrder() {
		return preOrder(root);
	}
	
	private String preOrder(Node n) {
		if (n == null) {
			return "";
		}
		return  n.data + " " + preOrder(n.left)  + preOrder(n.right);
	}
	
	public String postOrder() {
		return postOrder(root);
	}
	
	private String postOrder(Node n) {
		if (n == null) {
			return "";
		}
		return  postOrder(n.left)  + postOrder(n.right) + n.data + " ";
	}
	
	public int sum() {
		if (!(root.data instanceof Integer)) {
			throw new RuntimeException("Ikke int");
		}
		return sum(root);
	}
	
	private int sum(Node n) {
		if (n == null) {
			return 0;
		}
		else {
			return  (int)n.data + sum(n.left) + sum(n.right);
		}
	}
	
	public double avg() {
		return (double)(sum())/size();
	}
	

	private class Node {
		public E data;
		public Node left;
		public Node right;
	}
}
