package project20280.exercises;

/* 
 	Improve solution for leetcode 215,
 	beats 30% on runtime and 40% on memory
 */

class Solution {
	
    public int findKthLargest(int[] nums, int k) {
        PQ q = new PQ(k);
        for (int n : nums) {
            q.add(n);
        }
        return q.tree.root.val;
    }

    static class PQ {
        BinTree tree;
        int maxSize;
        BinTreeNode cache;
        boolean[] stack;
        int stackPointer;

        public PQ(int k) {
            this.tree = new BinTree();
            this.maxSize = k;
            this.stack = new boolean[(int)Math.round(Math.ceil(Math.log(k)/Math.log(2)))];
            this.stackPointer = 0;
        }

        public BinTreeNode append(int val) {
            cache = getParent(tree.size++);
            if (cache == null) {
                tree.root = new BinTreeNode(val, null, null, null);
                return tree.root;
            }
            else if (cache.left == null) {
                cache.left = new BinTreeNode(val, cache, null, null);
                return cache.left;
            } else {
                cache.right = new BinTreeNode(val, cache, null, null);
                return cache.right;
            }
        }

        public BinTreeNode getParent(int i) {
            return i % 2 == 1 ? get(i/2) : get((i-1)/2);
        }

        public boolean brokenStructure(BinTreeNode n) {
            if (n.right != null) {
                if (n.left == null) return true;
                else return brokenStructure(n.left) || brokenStructure(n.right);
            }
            return false;
        }

        public BinTreeNode get(int i) {
            while (i > 0) {
                if (i % 2 == 1) {
                    stack[stackPointer++] = true;
                } else {
                    stack[stackPointer++] = false;
                }
                i = i % 2 == 1 ? i/2: (i-1)/2;
            }
            cache = tree.root;
            while (stackPointer > 0) {
                if (stack[--stackPointer]) {
                    cache = cache.left;
                } else {
                    cache = cache.right;
                }
            }
            return cache;
        }

        public void downheap(BinTreeNode node) {
            if (node.left != null) {
                if (node.val > node.left.val) {
                    if (node.right == null || node.left.val < node.right.val) {
                        node.swap(node.left);
                        downheap(node.left);
                    }
                    else {
                        node.swap(node.right);
                        downheap(node.right);
                    }
                }
                else if (node.right != null && node.val > node.right.val) {
                    node.swap(node.right);
                    downheap(node.right);
                }
            }
        }
        
        public void upheap(BinTreeNode n) {
        	if (n.parent != null && n.parent.val > n.val) {
        		n.swap(n.parent);
        		upheap(n.parent);
        	}
        }
        
        public void add(int val) {
        	if (tree.size < maxSize) {
        		upheap(append(val));
        	}
        	else if (val > tree.root.val) {
        		removeMin();
        		upheap(append(val));
        	}
        }

        public void removeMin() {
            if (tree.size == 1) {
                tree.size = 0;
                tree.root = null;
            }
            else {
                cache = get(tree.size-1);
                tree.root.swap(cache);
                if (tree.size % 2 == 0)
                    cache.parent.left = null;
                else
                    cache.parent.right = null;
                tree.size--;
                downheap(tree.root);
            }
        }
    }

    static class BinTree {

        BinTreeNode root;
        int size;

        public BinTree() {
            size = 0;
        }

        
    }

    static class BinTreeNode {
        int val;
        BinTreeNode parent;
        BinTreeNode left;
        BinTreeNode right;

        int held; // cache for swap

        public BinTreeNode(int val, BinTreeNode parent, BinTreeNode left, BinTreeNode right) {
            this.val = val;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public void swap(BinTreeNode target) {
        	if (target == null) return;
            held = val;
            val = target.val;
            target.val = held;
        }
    }
}