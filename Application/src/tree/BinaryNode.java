package tree;

/**
 * Created by Magnus Poppe Wang on 31.03.2016.
 *
 * A nodeclass spesified to be used with a
 * binary tree.
 *
 * @Author Magnus Poppe Wang
 */
public class BinaryNode< T >
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

    public String toString() {
        return getElement().toString();
    }
}
