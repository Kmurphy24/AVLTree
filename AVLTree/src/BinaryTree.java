import java.util.*;

public class BinaryTree <E> {
	
	protected Node <E> root;
	
	public BinaryTree(){
		root = null;
	}
	
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree){
		
		root = new Node<E>(data);
		if(leftTree != null)
			root.left = leftTree.root;
	      else root.left = null;

		  if (rightTree != null)
			  root.right = rightTree.root;
		  else
		      root.right = null;	
	}
	
    public int size() {
    	if(root == null)
    		return 0;
    	return size(root);
   }
    
    private int size(Node<E> node){
    	int count = 1;
    	if (node.left != null)
    		count += size(node.left);
    	if(node.right !=null)
    		count += size(node.right);
    	return count;
    }
    
    public int height() {
 	   return height(root);
    }
    
    private int height(Node<E> node){
 	   if(node == null)
 		   return -1;   //returns -1 if the tree is empty
 	   int left = height(node.left);
 	   int right = height(node.right);
 	   if (left > right) //checks which side of the tree is higher
 		   return (++left);
 	   else return (++right);
    }
	
	protected static class Node<E>{
		
		protected E data;
		protected Node<E> left,right;
		
		public Node (E initData){
			data = initData;
			left = null;
			right = null;
		}
		
		public Node (E initData, Node <E> initLeftData, Node <E> initRightData){
			data = initData;
			left = initLeftData;
			right = initRightData;
		}
		
		public boolean isLeaf(){
			return (left == null && right==null);
		}
		
		public E getLeftMostData(){
			if (left==null) return data;
			else return left.getLeftMostData();
		}
		
		public E getRightMostData(){
			if(right == null) return data;
			else return right.getRightMostData();
		}
	}

}
