package tree.Iterator;

import java.util.LinkedList;

/**
 * Created by Magnus on 01.04.2016.
 */
public class PreOrderIterator<T> implements TreeIterator<T>
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
