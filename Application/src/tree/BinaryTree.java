package tree;

import tree.Iterator.PreOrderIterator;

import java.util.Iterator;

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
    public static int calculateSize(BinaryNode node, int size)
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
    public int calculateHeight(BinaryNode node, int height)
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

    @Override
    public Iterator<T> iterator() {
        return new PreOrderIterator<>();
    }



    /**
     * TODO: Make navigation with bit adresses.
     *       1 is rootnode, and 10 is rootnode's leftnode.
     *       101 is rootnode's leftchild's rightchild.
     * TODO: runWithCallback for all types of iterators.
     */
}
