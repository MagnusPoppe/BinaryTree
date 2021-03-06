package tree;

/**
 * Created by Magnus Poppe Wang on 31.03.2016.
 * <p/>
 * A nodeclass spesified to be used with a
 * binary tree.
 *
 * @Author Magnus Poppe Wang
 */
public class BinaryNode< T > {

    private T element;        // This nodes content.
    private BinaryNode lc;    // The child on the left hand side.
    private BinaryNode rc;    // The child on the right hand side.

    /**
     * Constructor:
     *
     * @param element for this node.
     * @param lc      left hand child node.
     * @param rc      right hand child node.
     */
    protected BinaryNode( T element, BinaryNode lc, BinaryNode rc )
    {
        this.element = element;
        this.lc = lc;
        this.rc = rc;
    }

    protected BinaryNode(){
        this(null, null, null);
    }

    /**
     * Standard getter.
     *
     * @return this nodes content.
     */
    public T getElement( )
    {
        return element;
    }

    /**
     * Standard setter and getter.
     * @return the node of the left child.
     */
    public BinaryNode getLeftChild( )
    {
        return lc;
    }

    /**
     * Standard setter and getter.
     *
     * @return the node of the right child.
     */
    public BinaryNode getRightChild( )
    {
        return rc;
    }

    /**
     *
     * @param element
     * @param node
     *
    protected void insert( T element , BinaryNode node)
    {
        if (element.compareTo(node.element) > 0)
        {
            if ( node.lc != null ) node = insert(element, node.lc);
            else{
                node.lc = new BinaryNode<>(element, null, null);
                return;
            }
        }
        else if (element.compareTo(node.element) < 0)
        {
            if ( node.rc != null ) node = insert(element, node.rc);
            else {
                node.rc = new BinaryNode<>(element, null, null);
                return;
            }
        }
        else throw new Exception("Duplicate.");
    }

    /**
     * ToString.
     *
     * @return ToString of the element.
     */
    public String toString( ) {
        return getElement( ).toString( );
    }
}
