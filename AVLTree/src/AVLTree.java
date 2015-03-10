public class AVLTree <E extends Comparable<E>>{
	
	private AVLNode<E> root;


	public AVLTree() {
		root = null;
	}

	public boolean isEmpty(){
		return root == null;
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	public boolean add(E newNode){
		try{
			root = add(root, newNode);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	public E findMin(){
		if(isEmpty()) return null;
		else return root.getLeftMostData();
	}
	
	public E findMax(){
		if(isEmpty()) return null;
		else return root.getRightMostData();
	}
	
	public int size(){
		if (root == null)
			return 0;
		else return size(root);
	}
	
	private int size(AVLNode<E> node){
		int count = 1;
		if(node.left != null)
			count += size(node.left);
		if(node.right != null)
			count += size(node.right);
		return count;
	}
	private AVLNode<E> add(AVLNode<E> root, E newNode) throws Exception{
		if(root == null)
			root = new AVLNode<E>(newNode);
		else if(newNode.compareTo(root.data) < 0){
			root.left = add(root.left, newNode);
			if(height(root.left) - height(root.right) == 2)
				if(newNode.compareTo(root.left.data) < 0)
					root = rotateWithLeftChild(root);
				else
					root = doubleRotateWithLeftChild(root);
		}
		else if(newNode.compareTo(root.data) > 0 ){
			root.right = add(root.right, newNode);
			if(height(root.right) - height(root.left) == 2)
				if(newNode.compareTo(root.right.data) > 0)
					root = rotateWithRightChild(root);
				else
					root = doubleRotateWithRightChild(root);
		}
		else
			throw new Exception("Duplicate value");
		root.height = max(height(root.left), height(root.right)) + 1;
		return root;
	}
	
	private int height(AVLNode<E> node){
		return node == null? -1 : node.height;
	}
	
	private int max(int left, int right){
		if(left>right)
			return left;
		else return right;
	}
	
	private AVLNode<E> rotateWithLeftChild(AVLNode<E> node1){
		AVLNode<E> node2 = node1.left;
		node1.left = node2.right;
		node2.right = node1;
		node1.height = max(height(node1.left), height(node1.right)) + 1;
		node2.height = max(height(node2.left), node1.height) + 1;
		return node2;
	}
	
	private AVLNode<E> doubleRotateWithLeftChild(AVLNode<E> node1){
		node1.left = rotateWithRightChild(node1.left);
		return rotateWithLeftChild(node1);
	}
	
	private AVLNode<E> rotateWithRightChild(AVLNode<E> node1){
		AVLNode<E> node2 = node1.right;
		node1.right = node2.left;
		node2.left = node1;
		node1.height = max(height(node1.left), height(node1.right)) + 1;
		node2.height = max(height(node2.right), node1.height) + 1;
		return node2;
	}
	
	private AVLNode<E> doubleRotateWithRightChild(AVLNode<E> node1){
		node1.right = rotateWithLeftChild(node1.right);
		return rotateWithRightChild(node1);
	}
	
	protected static class AVLNode<E> {
		protected E data;
		protected AVLNode<E> left, right;
		protected int height;
		
		public AVLNode(){
			data = null;
			left = null;
			right = null;
			height = 0;
		}
		
		public AVLNode(E data){
			this.data = data;
			left = null;
			right = null;
			height = 0;
		}
		
		public E getLeftMostData() {
		  	  if (left == null) return data;
		  	  else return left.getLeftMostData();
		  }
		
		public E getRightMostData() {
		  	   if (right == null) return data;
		  	   else return right.getRightMostData();
		  }
	}
}
