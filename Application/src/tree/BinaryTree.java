package tree;

import java.util.Iterator;

/**
 * Created by Magnu on 31.03.2016.
 */
public class BinaryTree<T> {

    /**
     * This is the root of the tree. All
     * branches and leafes are attached to
     * the root.
     */
    private BinaryNode<T> root;



    /**
     * Constuctor to create a complete tree.
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
    public int size() {
        return calculateSize(getRoot(), 1);
    }

    public static int calculateSize(BinaryNode node, int size) {

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

    public int height()
    {
        return 10;
    }


    //TODO: runWithCallback for all types of iterators.
    /**
     * TODO: Make navigation with bit adresses.
     *       1 is rootnode, and 10 is rootnode's leftnode.
     *       101 is rootnode's leftchild's rightchild.
     */
}
