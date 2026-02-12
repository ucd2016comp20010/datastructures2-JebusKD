package project20280.tree;

import org.junit.jupiter.api.Test;
import project20280.interfaces.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedBinaryTreeTest {

    @Test
    void testSize() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
        Position<Integer> root = bt.addRoot(1);
        assertEquals(1, bt.size());

        Position<Integer> l = bt.addLeft(root, 2);

        bt.remove(bt.root());
        assertEquals(1, bt.size());
    }

    @Test
    void testAddRoot() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        assertEquals(c, bt.root().getElement(), "root not added correctly");
    }

    @Test
    void testAddLeft() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addLeft(bt.root(), 1);
        assertEquals(1, bt.left(bt.root()).getElement());
    }

    @Test
    void testAddRight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        assertEquals(1, bt.right(bt.root()).getElement());
    }

    @Test
    void testRemove() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        Integer old = bt.remove(bt.right(bt.root()));
        assertEquals(old, 1);
        assertEquals(1, bt.size());
    }

    @Test
    void testToString() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
    }

    @Test
    void testCreateLevelOrder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
    }

    @Test
    void testInorder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.inorder().toString());
    }

    @Test
    void testDepth() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(0, bt.depth(bt.root()));
    }

    @Test
    void testHeight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(3, bt.height());
    }
    
    @Test
    void testDiameter() {
    	
    	LinkedBinaryTree<Integer> bt1 = new LinkedBinaryTree<Integer>();

        Integer[] arr1 = new Integer[]{1,
        							   2, 3,
        							   4, 5, null, null
        							   };
        bt1.createLevelOrder(arr1);

        assertEquals(3, bt1.diameter());
        
        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<Integer>();
        
        Integer[] arr2 = new Integer[] {1,
        								2, 3,
        								4, 5, 6, 7,
        								8, 9, 10, 11, 12, 13, 14, 15,
        								16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, null, null, null, 35
        								};
        
        bt2.createLevelOrder(arr2);
        
        assertEquals(8, bt2.diameter());
        
        LinkedBinaryTree<Integer> bt3 = new LinkedBinaryTree<Integer>();
        
        Integer[] arr3 = new Integer[]{1,
				   					   2, null,
				   					   4, 5, null, null,
				   					   8, 9, null, null, null, null, null, null
				   };
        bt3.createLevelOrder(arr3);

        assertEquals(3, bt3.diameter());
        
        LinkedBinaryTree<Integer> bt4 = new LinkedBinaryTree<Integer>();

        Integer[] arr4 = new Integer[]{1};
        bt4.createLevelOrder(arr4);

        assertEquals(0, bt4.diameter());
        
        LinkedBinaryTree<Integer> bt5 = new LinkedBinaryTree<Integer>();

        Integer[] arr5 = new Integer[]{1,
        							   2, 3};
        bt4.createLevelOrder(arr5);

        assertEquals(2, bt5.diameter());
    }

}
