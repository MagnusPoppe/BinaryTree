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


    public BinaryTree( T element, BinaryTree lt, BinaryTree rt)
    {
        this.root = new BinaryNode<>(
            element,
            lt.getRoot(),
            rt.getRoot()
        );
    }

    public BinaryNode getRoot() {
        return root;
    }



    //TODO: runWithCallback for all types of iterators.
    /**
     * TODO: Make navigation with bit adresses.
     *       1 is rootnode, and 10 is rootnode's leftnode.
     *       101 is rootnode's leftchild's rightchild.
     */
}
