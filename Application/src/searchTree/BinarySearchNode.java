package searchTree;

/**
 * Created by Magnu on 07.04.2016.
 */
public class BinarySearchNode<T extends Comparable<? super T>>
{

    private int height;          // Height of this node.

    private T element;           // This nodes content.
    private BinarySearchNode lc; // The child on the left hand side.
    private BinarySearchNode rc; // The child on the right hand side.

    /**
     * Constructor:
     *
     * @param element for this node.
     * @param lc      left hand child node.
     * @param rc      right hand child node.
     */
    protected BinarySearchNode(T element, BinarySearchNode lc, BinarySearchNode rc )
    {
        this.element = element;
        this.lc = lc;
        this.rc = rc;
        height = Math.max(lc.getHeight(), lc.getHeight());
    }

    protected BinarySearchNode(T element)
    {
        this(element, null, null);
    }

    protected BinarySearchNode(){
        this(null, null, null);
    }

    public int getHeight()
    {
        return height;
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
    public BinarySearchNode getLeftChild( )
    {
        return lc;
    }

    /**
     * Standard setter and getter.
     *
     * @return the node of the right child.
     */
    public BinarySearchNode getRightChild( )
    {
        return rc;
    }

    public boolean setLeftChild( BinarySearchNode<T> node)
    {
        if( lc == null ){
            lc = node;
            return true;
        }
        return false;
    }
    public boolean setRightChild( BinarySearchNode<T> node)
    {
        if( rc == null ){
            rc = node;
            return true;
        }
        return false;
    }

    public int compareTo( BinarySearchNode<T> other ) {
        return element.compareTo(other.getElement());
    }
    public int compareTo( T other ) {
        return element.compareTo(other);
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
