package tree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Magnu on 31.03.2016.
 */
public class BinaryTree<T> implements Iterable<T>
{

    /**
     * This is the root of the tree. All
     * branches and leafes are attached to
     * the root.
     */
    private BinaryNode<T> root;

    /**
     * Constuctor to create a complete tree.
     * @param element
     * @param lt
     * @param rt
     */
    public BinaryTree( T element, BinaryTree lt, BinaryTree rt )
    {
        this.root = new BinaryNode<>(
            element,
            (lt != null) ? lt.getRoot() : null,
            (rt != null) ? rt.getRoot() : null
        );
    }

    /**
     * Second constructor for creating only the root node.
     * @param element
     */
    public BinaryTree( T element )
    {
        this(element, null, null);
    }
    /**
     * Standard getter for the root node.
     */
    public BinaryNode getRoot()
    {
        return root;
    }

    /**
     * Gets the size of the whole tree, beginning from root.
     * Previously used, but not anymore. Factory method.
     * @return the total amount of nodes in the tree.
     */
    public int size()
    {
        return calculateSize(getRoot(), 1);
    }

    /**
     * Calculates the size of the tree by recursivly visiting
     * every node. NOTE: Slow.
     * @param node used for recursion
     * @param size used for recursion
     * @return the size of the tree.
     */
    private static int calculateSize(BinaryNode node, int size)
    {

        if (node.getLeftChild() != null)
        {
            size = calculateSize(node.getLeftChild(), size+1);
        }
        if (node.getRightChild() != null)
        {
            size = calculateSize(node.getRightChild(), size+1);
        }
        return size;
    }

    /**
     * Gets the height of the tree. Factory method for the
     * "calculateHeight() method.
     * @return the height of the tree
     */
    public int height()
    {
        return calculateHeight(getRoot(), 0);
    }

    /**
     * calculates the height of the left child tree and the
     * right child tree, then selects the tallest.
     * @param node
     * @param height
     * @return the height of the tree.
     */
    private int calculateHeight(BinaryNode node, int height)
    {
        int height1 = 0;
        int height2 = 0;
        if (node.getLeftChild() != null)
        {
            height1 = Math.max(height, calculateSize(node.getLeftChild(), height+1));
        }
        if (node.getRightChild() != null)
        {
            height2 = Math.max(height, calculateSize(node.getRightChild(), height+1));
        }
        return Math.max(height1, height2);
    }


    /**
     * Factory method to create an iterator.
     * Default iterator is Pre Order Iterator.
     * @return Default Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new PreOrderIterator();
    }
    /****************************************************
     *                  THE ITERATORS:                  *
     *                                                  *
     * The following inner classes are the different    *
     * iterators for the binary tree and binary node    *
     * classes.                                         *
     *                                                  *
     ****************************************************/


    /**
     * Operates with me first, then others.
     * The first node is root, then the whole left tree,
     * followed by the right tree.
     *
     * THIS IS THE DEFAULT ITERATOR FOR BINARYTREE/BINARYNODE.
     */
    public class PreOrderIterator implements TreeIterator
    {
        BinaryNode<T> current;
        LinkedList<BinaryNode<T>> queue;

        public PreOrderIterator() {
            this.queue = new LinkedList<>();
            queue.push(getRoot());
        }

        /**
         * If the queue is empty, then there are no more nodes to get.
         * @return the opposite of linkedlists "isEmpty()" method.
         */
        @Override
        public boolean hasNext()
        {
            return !queue.isEmpty();
        }

        /**
         * Gets the next object according to the rules of pre order.
         * @return
         */
        @Override
        public T next()
        {
            // Getting the object in question:
            current = queue.pop();

            // Testing if we can queue the right hand node.
            if (current.getRightChild() != null) {
                queue.push(current.getRightChild());
            }

            // Testing if we can queue the left hand node.
            if( current.getLeftChild() != null) {
                queue.push(current.getLeftChild());
            }

            //returns the current element:
            return current.getElement();
        }
    }

    public class PostOrderIterator implements TreeIterator
    {
        LinkedList<T> queue;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

    public class LevelOrderIterator implements TreeIterator
    {
        LinkedList<T> queue;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

    public class InOrderIterator implements TreeIterator
    {
        LinkedList<T> queue;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

    /**
     * TODO: Make navigation with bit adresses.
     *       1 is rootnode, and 10 is rootnode's leftnode.
     *       101 is rootnode's leftchild's rightchild.
     * TODO: runWithCallback for all types of iterators.
     */
}
