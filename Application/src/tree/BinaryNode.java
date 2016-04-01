package tree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Magnus Poppe Wang on 31.03.2016.
 *
 * A nodeclass spesified to be used with a
 * binary tree.
 *
 * @Author Magnus Poppe Wang
 */
public class BinaryNode< T > implements Iterable
{

    private T element;        // This nodes content.
    private BinaryNode lc;    // The child on the left hand side.
    private BinaryNode rc;    // The child on the right hand side.

    protected BinaryNode(T element, BinaryNode lc, BinaryNode rc)
    {
        this.element = element;
        this.lc = lc;
        this.rc = rc;
    }

    /**
     * Standard getter.
     * @return this nodes content.
     */
    public T getElement()
    {
        return element;
    }

    /**
     * Standard setter and getter.
     * @return the node of the left child.
     */
    public BinaryNode getLeftChild()
    {
        return lc;
    }

    /**
     * Standard setter and getter.
     * @return the node of the right child.
     */
    public BinaryNode getRightChild()
    {
        return rc;
    }

    /**
     * ToString.
     * @return ToString of the element.
     */
    public String toString() {
        return getElement().toString();
    }

    /**
     * Factory method to create an iterator.
     * Default iterator is Pre Order Iterator.
     * @return Default Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new PreOrderIterator<>();
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
        BinaryNode Current;
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

}
