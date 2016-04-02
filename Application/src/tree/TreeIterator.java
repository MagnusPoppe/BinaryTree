package tree;

import java.util.Iterator;

/**
 * Created by Magnu on 31.03.2016.
 */
public interface TreeIterator< T > extends Iterator {
    boolean hasNext( );
    T next( );
    BinaryNode< T > nextNode( );
}
