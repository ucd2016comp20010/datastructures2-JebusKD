package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
    	return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
    	return size == 0;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty.");
        this.root = createNode(e, null, null, null);
        this.size = 1;
        return this.root;
    }
    
    /* Can be ignored.
    public void insert(E e) {
        // TODO

    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        return null;
    }*/

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = ((Node<E>)p);
        if (n.getLeft() != null) throw new IllegalStateException("Node already has a left child.");
        Node<E> child = createNode(e, n, null, null);
        n.setLeft(child);
        this.size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = ((Node<E>)p);
        if (n.getRight() != null) throw new IllegalStateException("Node already has a right child.");
        Node<E> child = createNode(e, n, null, null);
        n.setRight(child);
        this.size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> target = ((Node<E>)p);
        E element = target.getElement();
        target.setElement(e);
        return element;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
    	if (!isExternal(p)) throw new IllegalArgumentException("Node is not a leaf.");
    	if (this == t1 || t1 == t2 || t2 == this) throw new IllegalArgumentException("Trees not independent");
    	
    	Node<E> leaf = ((Node<E>)p);
    	
    	this.size += t1.size() + t2.size();    	
    	
    	leaf.setLeft(t1.root);
    	leaf.setRight(t2.root);
    	
    	t1.root = null;
    	t2.root = null;
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
    	Node<E> n = ((Node<E>)p),
        		parent = n.getParent();
        Position<E> child;
        if (this.left(p) != null) {
        	if (this.right(p) != null) throw new IllegalArgumentException("Removed node has two children.");
        	child = this.left(p);
        }
        else {
        	/* this.right is either null or the only child, both are adequate */
        	child = this.right(p);
        }
        
        if (this.root == n) this.root = ((Node<E>)child);
        else if (this.left(parent) == p) parent.setLeft((Node<E>)child);
        else parent.setRight((Node<E>)child);
        
        this.size--;
        
        return n.getElement();
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        root = createLevelOrderHelper(l, root, 0);
        this.size = l.size();
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> l, Node<E> p, int i) {
        Node<E> n;
        if (i >= l.size()) return p;
        else if (i == 0) n = createNode(l.get(i), null, null, null);
        else n = createNode(l.get(i), p, null, null);
        n.left = createLevelOrderHelper(l, n.left, 2*i + 1);
        n.right = createLevelOrderHelper(l, n.right, 2*i + 2);
        return n;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
        this.size = arr.length;
        
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
    	Node<E> n;
        if (i >= arr.length || arr[i] == null) return p;
        else if (i == 0) n = createNode(arr[i], null, null, null);
        else n = createNode(arr[i], p, null, null);
        n.left = createLevelOrderHelper(arr, n.left, 2*i + 1);
        n.right = createLevelOrderHelper(arr, n.right, 2*i + 2);
        return n;
    }
    
    private int diameterHelper(Node<E> p) {
    	if (isExternal(p)) return 0;
    	
    	int curMax = 0;
    	int thisDiam = 0;
    	
    	/* If left node exists, it has a diameter of its own in its subtree. */
    	if (left(p) != null) {
    		curMax = Math.max(curMax, diameterHelper(p.getLeft()));
    		/* Add the height of the left child + 1 to the diameter of this tree,
    		 	presuming it travels through the root.
    		 */
    		thisDiam += nodeHeight(p.getLeft()) +1;
    	}
    	if (right(p) != null) {
    		curMax = Math.max(curMax, diameterHelper(p.getRight()));
    		thisDiam += nodeHeight(p.getRight()) +1;
    	}
    	
    	curMax = Math.max(curMax, thisDiam);
    	return curMax;
    }
    
    private int nodeHeight(Node<E> p) {
    	if (isExternal(p)) return 0;
    	
    	int val = -1;
    	
    	if (left(p) != null) val = nodeHeight(p.getLeft());
    	if (right(p) != null) val = 1 + Math.max(val, nodeHeight(p.getRight()));
    	
    	return val;
    }
    
    /* The diameter of a binary
	tree is the length of the longest path between any two nodes in a tree. This path
	may or may not pass through the root. */
    public int diameter() {
    	return diameterHelper(this.root);
    }
    
    private static LinkedBinaryTree<E> construct_helper(E[] inorder_representation, E[] preorder_representation) {
    	
    	
    	
    }
    
    
    
    /* Construct a binary tree of UNIQUE elements given
     	the inorder and preorder representation of the tree
     */
    public void construct(E[] inorder_representation, E[] preorder_representation) {
    	if (inorder_representation.length != preorder_representation.length) throw new IllegalArgumentException("inorder and preorder must be of same length");
    	
    	this.root = createNode(preorder_representation[0], null, null, null);
    	this.size = 1;
    	
    	
    	this.attach(construct_helper(), construct_helper());
    }
    
    /**
     * Returns the number of external nodes in a binary tree.
     * 
     * @return number of external nodes in binary tree
     */
    public int externalNodeCount() {
    	if (this.root == null) return 0;
    	return externalNodeCountHelper((Position<E>)this.root);
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }
    

    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
    	private E element;
		private Node<E> left, right, parent;
		
		public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
			this.element = e;
			this.parent = p;
			this.left = l;
			this.right = r;
		}

        // accessor
        public E getElement() {
            return this.element;
        }

        // modifiers
        public void setElement(E e) {
            this.element = e;
        }

        public Node<E> getLeft() {
            return this.left;
        }

        public void setLeft(Node<E> n) {
            this.left = n;
        }

        public Node<E> getRight() {
            return this.right;
        }

        public void setRight(Node<E> n) {
            this.right = n;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public void setParent(Node<E> n) {
            this.parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}
