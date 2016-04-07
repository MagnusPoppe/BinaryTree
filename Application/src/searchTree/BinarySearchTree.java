package searchTree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Magnu on 07.04.2016.
 */
public class BinarySearchTree< T extends Comparable< ? super T > > implements Iterable<T>
{

    private BinarySearchNode<T> root;

    public BinarySearchTree(T element, BinarySearchTree lc, BinarySearchTree rc)
    {
        this.root = new BinarySearchNode<>(
                element,
                (lc != null) ? lc.getRoot() : null,
                (rc != null) ? rc.getRoot() : null
        );
    }

    public BinarySearchTree(T element)
    {
        this(element, null, null);
    }


    public void insert( T element ) {
        insertNode(element, getRoot());
    }

    private void insertNode( T element, BinarySearchNode<T> node)
    {
        if ( element.compareTo(node.getElement()) < 0)
        {
            if(node.getLeftChild() != null) {
                insertNode(element, node.getLeftChild());
            }
            else {
                node.setLeftChild(new BinarySearchNode<>(element));
            }
        }
        else if ( element.compareTo(node.getElement()) > 0)
        {
            if(node.getRightChild() != null) {
                insertNode(element, node.getRightChild());
            }
            else {
                node.setRightChild(new BinarySearchNode<>(element));
            }
        }
        else System.out.println("DUPLIKAT");
    }

//    private void insertNode( T element, BinarySearchNode<T> node)
//    {
//        if( node.getLeftChild() != null) {
//            if (node.getRightChild() != null) {
//                if (node.getLeftChild().compareTo(node.getRightChild()) > 0) {
//
//                }
//                else if (node.getLeftChild().compareTo(node.getRightChild()) < 0) {
//                    insertNode(element, node.getLeftChild());
//                }
//                else {
//                    System.out.println("duplicate.");
//                    return;
//                }
//            }
//            else {
//                node.setRightChild(new BinarySearchNode<>(element));
//            }
//        }
//        else
//        {
//            if (node.getElement().compareTo(element) < 0) {
//                node.setLeftChild(new BinarySearchNode<>(element));
//            }
//            else if (node.getElement().compareTo(element) > 0) {
//                node.setRightChild(new BinarySearchNode<>(element));
//            }
//        }
//    }


    public BinarySearchNode<T> getRoot()
    {
        return root;
    }

    public String toString() {
        return getRoot().getElement().toString();
    }

    /**
     * Factory method to create an iterator.
     * Default iterator is Pre Order Iterator.
     *
     * @return Default Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return preOrderIterator();
    }

    /**
     * Factory method to create the preorder
     * iterator.
     *
     * @return preorder iterator
     */
    public TreeIterator<T> preOrderIterator() {
        return new PreOrderIterator();
    }

    /**
     * Factory method to create the postorder
     * iterator.
     *
     * @return postorder iterator
     */
    public TreeIterator<T> postOrderIterator() {
        return new PostOrderIterator();
    }

    /**
     * Factory method to create the levelorder
     * iterator.
     *
     * @return levelorder iterator
     */
    public TreeIterator<T> levelOrderIterator() {
        return new LevelOrderIterator();
    }

    /**
     * Factory method to create the inorder
     * iterator.
     *
     * @return inorder iterator
     */
    public TreeIterator<T> inOrderIterator() {
        return new InOrderIterator();
    }


    /****************************************************
     *               THE ITERATOR CLASSES:              *
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
        BinarySearchNode<T> current;
        LinkedList<BinarySearchNode<T>> stack;

        /**
         * Constructor.
         */
        public PreOrderIterator() {
            if (getRoot() == null) {
                throw new NullPointerException("No elements in the tree.");
            }
            this.stack = new LinkedList<>();
            stack.push(getRoot());
        }

        /**
         * If the stack is empty, then there are no more nodes to get.
         *
         * @return the opposite of linkedlists "isEmpty()" method.
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next element.
         */
        @Override
        public T next() {
            return nextMethod().getElement();
        }

        /**
         * @return the next node
         */
        @Override
        public BinarySearchNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Gets the next node according to the rules of pre order.
         *
         * @return next node.
         */
        public BinarySearchNode<T> nextMethod() {
            // Getting the object in question:
            current = stack.pop();

            // Testing if we can stack the right hand node.
            if (current.getRightChild() != null) {
                stack.push(current.getRightChild());
            }

            // Testing if we can stack the left hand node.
            if (current.getLeftChild() != null) {
                stack.push(current.getLeftChild());
            }

            //returns the current element:
            return current;
        }
    }


    /****************************************************
     * Operates after the principles of "you first, then
     * me", getting children from first left, then right
     * before processing it self.
     */
    public class PostOrderIterator implements TreeIterator {

        BinarySearchNode<T> current;
        LinkedList<BinarySearchNode<T>> stack;
        LinkedList<Integer> seenStack;

        /**
         * Constructor.
         */
        public PostOrderIterator() {
            if (getRoot() == null) {
                throw new NullPointerException("No elements in the tree.");
            }
            stack = new LinkedList<>();
            stack.push(getRoot());

            seenStack = new LinkedList<>();
            seenStack.push(0);
        }

        /**
         * If the stack is empty, then there are no more nodes to get.
         *
         * @return the opposite of linkedlists "isEmpty()" method.
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next element.
         */
        @Override
        public T next() {
            return nextMethod().getElement();
        }

        /**
         * @return the next node
         */
        @Override
        public BinarySearchNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Gets the next node according to the rules of post order.
         *
         * @return next node.
         */
        public BinarySearchNode<T> nextMethod() {

            while (true) {

                current = stack.pop();
                int seen = seenStack.pop();
                seen++;

                // The third time is the time to process.
                if (seen == 3) {
                    return current;
                }
                // The second time we go to the right.
                else if (seen == 2) {

                    stack.push(current);
                    seenStack.push(seen);

                    if (current.getRightChild() != null) {
                        stack.push(current.getRightChild());
                        seenStack.push(0);
                    }
                }
                // The first time we go left.
                else if (seen == 1) {

                    stack.push(current);
                    seenStack.push(seen);

                    if (current.getLeftChild() != null) {
                        stack.push(current.getLeftChild());
                        seenStack.push(0);
                    }
                }
            }
        }
    }

    /****************************************************
     * Gets the elements by the height of the element,
     * from top being 0 to bottom being last.
     */
    public class LevelOrderIterator implements TreeIterator {
        BinarySearchNode<T> current;

        // Using my own queue class to get the
        // proper method names.
        Queue<BinarySearchNode<T>> queue;

        /**
         * Constructor.
         */
        public LevelOrderIterator() {
            if (getRoot() == null) {
                throw new NullPointerException("No elements in the tree.");
            }
            queue = new Queue<>();
            queue.enqueue(getRoot());
        }

        /**
         * Checks if the queue is empty to find out wheither
         * there are more elements in the tree.
         *
         * @return "true" if the queue is not empty.
         */
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        /**
         * Gets the next node via "nextMethod()".
         *
         * @return Element of the next node.
         */
        @Override
        public T next() {
            return nextMethod().getElement();
        }

        /**
         * @return the next node
         */
        @Override
        public BinarySearchNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Gets the next node according to the rules of level order.
         *
         * @return next node.
         */
        public BinarySearchNode<T> nextMethod() {
            // Dequeues the current element for processing.
            current = queue.dequeue();

            // Enqueues the items of the next line, in proper succession.
            if (current.getLeftChild() != null) {
                queue.enqueue(current.getLeftChild());
            }
            if (current.getRightChild() != null) {
                queue.enqueue(current.getRightChild());
            }

            // Returns the element for processing.
            return current;
        }
    }

    /****************************************************
     * Operates after the "left first, then me, then
     * right" principle, getting objects from left to
     * right in the tree.
     */
    public class InOrderIterator implements TreeIterator {

        BinarySearchNode<T> current;
        LinkedList<BinarySearchNode<T>> stack;
        LinkedList<Integer> seenStack;

        /**
         * Constructor.
         */
        public InOrderIterator() {
            if (getRoot() == null) {
                throw new NullPointerException("No elements in the tree.");
            }
            stack = new LinkedList<>();
            stack.push(getRoot());
            seenStack = new LinkedList<>();
            seenStack.push(0);
        }

        /**
         * @return true if the stack has elements.
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Uses the "nextMethod()" to get the element of the
         * next node.
         *
         * @return the element of the next node.
         */
        @Override
        public T next() {
            return nextMethod().getElement();
        }

        /**
         * @return the next node
         */
        @Override
        public BinarySearchNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Delivers the next element according to the rules
         * of the inorder iterator.
         *
         * @return next element.
         */
        public BinarySearchNode<T> nextMethod() {
            while (true) {
                current = stack.pop();
                int seen = seenStack.pop();
                seen++;

                // The first time we go left.
                if (seen == 1) {

                    // We place current back on the stack.
                    stack.push(current);
                    seenStack.push(seen);

                    // The left, if exists, is now next in line to process.
                    if (current.getLeftChild() != null) {
                        stack.push(current.getLeftChild());
                        seenStack.push(0);
                    }
                }

                // The second time is the time to process.
                else if (seen == 2) {

                    // Right if exists is now second in line to process.
                    if (current.getRightChild() != null) {
                        stack.push(current.getRightChild());
                        seenStack.push(0);
                    }

                    // We return the element.
                    return current;
                }
            }
        }
    }
}

