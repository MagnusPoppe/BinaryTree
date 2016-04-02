package tree;

import java.util.Iterator;

/**
 * Interface to use with the binary tree iterators.
 * Implements Iterator<> for compatibility.
 * Created by Magnu on 31.03.2016.
 */
public interface TreeIterator< T > extends Iterator {

    /**
     * Method to check if there are more elements in the
     * tree.
     *
     * @return "true" if there are more elements to iterate over.
     */
    @Override
    boolean hasNext( );

    /**
     * Finds and gets the next element in line for the iterator.
     *
     * @return next node's element.
     */
    @Override
    T next( );

    /**
     * Finds and gets the next node in line for the iterator.
     *
     * @return the next node.
     */
    BinaryNode< T > nextNode( );
}
