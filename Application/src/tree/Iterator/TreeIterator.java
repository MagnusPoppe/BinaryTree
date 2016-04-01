package tree.Iterator;

import java.util.Iterator;

/**
 * Created by Magnu on 31.03.2016.
 */
interface TreeIterator<T> extends Iterator
{
    boolean hasNext();
    T next();
}
