package tree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Magnu on 31.03.2016.
 */
public class BinaryTree< T > implements Iterable< T > {

    /**
     * This is the root of the tree. All
     * branches and leafes are attached to
     * the root.
     */
    private BinaryNode< T > root;

    /**
     * Constuctor to create a complete tree.
     *
     * @param element
     * @param lt
     * @param rt
     */
    public BinaryTree( T element, BinaryTree lt, BinaryTree rt ) {
        this.root = new BinaryNode<>(
                element,
                ( lt != null ) ? lt.getRoot( ) : null,
                ( rt != null ) ? rt.getRoot( ) : null
        );
    }

    /**
     * Second constructor for creating only the root node.
     *
     * @param element
     */
    public BinaryTree( T element ) {
        this( element, null, null );
    }

    /**
     * Standard getter for the root node.
     */
    public BinaryNode getRoot( ) {
        return root;
    }

    /**
     * Gets the size of the whole tree, beginning from root.
     * Previously used, but not anymore. Factory method.
     *
     * @return the total amount of nodes in the tree.
     */
    public int size( ) {
        return calculateSize( getRoot( ), 1 );
    }

    /**
     * Calculates the size of the tree by recursively visiting
     * every node. NOTE: Slow.
     *
     * @param node used for recursion
     * @param size used for recursion
     * @return the size of the tree.
     */
    private static int calculateSize( BinaryNode node, int size ) {

        if ( node.getLeftChild( ) != null ) {
            size = calculateSize( node.getLeftChild( ), size + 1 );
        }
        if ( node.getRightChild( ) != null ) {
            size = calculateSize( node.getRightChild( ), size + 1 );
        }
        return size;
    }

    /**
     * Gets the height of the tree. Factory method for the
     * "calculateHeight() method.
     *
     * @return the height of the tree
     */
    public int height( ) {
        return calculateHeight( getRoot( ), 0 );
    }

    /**
     * calculates the height of the left child tree and the
     * right child tree, then selects the tallest.
     *
     * @param node
     * @param height
     * @return the height of the tree.
     */
    private int calculateHeight( BinaryNode node, int height ) {
        int height1 = 0;
        int height2 = 0;
        if ( node.getLeftChild( ) != null ) {
            height1 = Math.max( height, calculateSize( node.getLeftChild( ), height + 1 ) );
        }
        if ( node.getRightChild( ) != null ) {
            height2 = Math.max( height, calculateSize( node.getRightChild( ), height + 1 ) );
        }
        return Math.max( height1, height2 );
    }


    /**
     * Factory method to create an iterator.
     * Default iterator is Pre Order Iterator.
     *
     * @return Default Iterator
     */
    @Override
    public Iterator< T > iterator( ) {
        return new PreOrderIterator( );
    }

    public Iterator< T > preOrderIterator( ) {
        return new PreOrderIterator( );
    }

    public Iterator< T > postOrderIterator( ) {
        return new PostOrderIterator( );
    }

    public Iterator< T > levelOrderterator( ) {
        return new LevelOrderIterator( );
    }

    public Iterator< T > inOrderIterator( ) {
        return new InOrderIterator( );
    }


    /****************************************************
     *                  THE ITERATORS:                  *
     *                                                  *
     * The following inner classes are the different    *
     * iterators for the binary tree and binary node    *
     * classes.                                         *
     *                                                  *
     ****************************************************/


    /****************************************************
     * Operates with me first, then others.
     * The first node is root, then the whole left tree,
     * followed by the right tree.
     * <p/>
     * THIS IS THE DEFAULT ITERATOR FOR
     * BINARYTREE/BINARYNODE.
     */
    public class PreOrderIterator implements TreeIterator {
        BinaryNode< T > current;
        LinkedList< BinaryNode< T > > stack;

        public PreOrderIterator( ) {
            this.stack = new LinkedList<>( );
            stack.push( getRoot( ) );
        }

        /**
         * If the stack is empty, then there are no more nodes to get.
         *
         * @return the opposite of linkedlists "isEmpty()" method.
         */
        @Override
        public boolean hasNext( ) {
            return !stack.isEmpty( );
        }

        /**
         * Gets the next object according to the rules of pre order.
         *
         * @return
         */
        @Override
        public T next( ) {
            // Getting the object in question:
            current = stack.pop( );

            // Testing if we can stack the right hand node.
            if ( current.getRightChild( ) != null ) {
                stack.push( current.getRightChild( ) );
            }

            // Testing if we can stack the left hand node.
            if ( current.getLeftChild( ) != null ) {
                stack.push( current.getLeftChild( ) );
            }

            //returns the current element:
            return current.getElement( );
        }
    }


    /****************************************************
     * Operates after the principles of "you first, then
     * me", getting children from first left, then right
     * before processing it self.
     */
    public class PostOrderIterator implements TreeIterator {

        BinaryNode< T > current;
        LinkedList< BinaryNode< T > > stack;
        LinkedList< Integer > seenStack;

        public PostOrderIterator( ) {
            stack = new LinkedList<>( );
            stack.push( getRoot( ) );

            seenStack = new LinkedList<>( );
            seenStack.push( 0 );
        }

        /**
         * If the stack is empty, then there are no more nodes to get.
         *
         * @return the opposite of linkedlists "isEmpty()" method.
         */
        @Override
        public boolean hasNext( ) {
            return !stack.isEmpty( );
        }

        @Override
        public T next( ) {

            while ( true ) {

                current = stack.pop( );
                int seen = seenStack.pop( );
                seen++;

                // The third time is the time to process.
                if ( seen == 3 ) {
                    return current.getElement( );
                }
                // The second time we go to the right.
                else if ( seen == 2 ) {

                    stack.push(current);
                    seenStack.push(seen);

                    if ( current.getRightChild( ) != null ) {
                        stack.push( current.getRightChild( ) );
                        seenStack.push( 0 );
                    }
                }
                // The first time we go left.
                else if ( seen == 1 ) {

                    stack.push(current);
                    seenStack.push(seen);

                    if ( current.getLeftChild( ) != null ) {
                        stack.push( current.getLeftChild( ) );
                        seenStack.push( 0 );
                    }
                }
            }
        }
    }

    /****************************************************
     *
     */
    public class LevelOrderIterator implements TreeIterator {
        LinkedList< T > queue;

        @Override
        public boolean hasNext( ) {
            return false;
        }

        @Override
        public T next( ) {
            return null;
        }
    }

    /****************************************************
     * Operates after the "left first, then me, then
     * right" principle, getting objects from left to
     * right in the tree.
     */
    public class InOrderIterator implements TreeIterator {

        BinaryNode< T > current;
        LinkedList< BinaryNode< T > > stack;
        LinkedList< Integer > seenStack;

        public InOrderIterator()
        {
            stack = new LinkedList<>( );
            stack.push( getRoot() );
            seenStack = new LinkedList<>();
            seenStack.push(0);
        }

        /**
         * @return true if the stack has elements.
         */
        @Override
        public boolean hasNext( )
        {
            return !stack.isEmpty();
        }

        /**
         * Delivers the next element according to the rules
         * of the inorder iterator.
         * @return next element.
         */
        @Override
        public T next( )
        {
            while ( true )
            {
                current = stack.pop( );
                int seen = seenStack.pop( );
                seen++;

                // The first time we go left.
                if ( seen == 1 ) {

                    // We place current back on the stack.
                    stack.push(current);
                    seenStack.push(seen);

                    // The left, if exists, is now next in line to process.
                    if ( current.getLeftChild( ) != null ) {
                        stack.push( current.getLeftChild( ) );
                        seenStack.push( 0 );
                    }
                }

                // The second time is the time to process.
                else if ( seen == 2 ) {

                    // Right if exists is now second in line to process.
                    if ( current.getRightChild( ) != null ) {
                        stack.push( current.getRightChild( ) );
                        seenStack.push( 0 );
                    }

                    // We return the element.
                    return current.getElement( );
                }
            }
        }
    }

    /**
     * TODO: Make navigation with bit adresses.
     *       1 is rootnode, and 10 is rootnode's leftnode.
     *       101 is rootnode's leftchild's rightchild.
     * TODO: runWithCallback for all types of iterators.
     */
}
