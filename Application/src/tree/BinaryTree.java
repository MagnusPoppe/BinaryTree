package tree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Binary tree class uses a single rootnode to create
 * a tree.Because this class is BIG, here's an overiew:
 * <p>
 * INNER CLASSES:
 * PreOrderIterator, creates a preorder iterator.
 * PostOrderIterator, creates a postorder iterator.
 * LevelOrderIterator, creates a levelorder iterator.
 * InOrderIterator, creates a inorder iterator.
 * <p>
 * METHOD LIST:
 * getRoot(): returns the root element.
 * size() = calculateSize(): returns the number of elements in the tree.
 * height() = calculateHeight(): returns the height of the tree.
 * iterator(): Returns the default iterator.
 * preOrderIterator(): returns a preorder iterator.
 * postOrderIterator(): returns a postorder iterator.
 * levelOrderIterator(): returns a levelorder iterator.
 * inOrderIterator(): returns a inorder Iterator.
 * <p>
 * Created by Magnus Poppe Wang on 31.03.2016.
 *
 * @Author Magnus Poppe Wang
 */
public class BinaryTree<T> implements Iterable<T> {

    /**
     * This is the root of the tree. All
     * branches and leafes are attached to
     * the root.
     */
    private BinaryNode<T> root;

    /**
     * Constuctor to create a complete tree.
     *
     * @param element for the root node.
     * @param lt      left hand tree.
     * @param rt      right hand tree.
     */
    public BinaryTree(T element, BinaryTree lt, BinaryTree rt) {
        this.root = new BinaryNode<>(
                element,
                (lt != null) ? lt.getRoot() : null,
                (rt != null) ? rt.getRoot() : null
        );
    }

    /**
     * Second constructor for creating only the root node.
     *
     * @param element for the root node.
     */
    public BinaryTree(T element) {
        this(element, null, null);
    }

    public BinaryTree()
    {
        this(null, null, null);
    }

    /**
     * Standard getter for the root node.
     */
    public BinaryNode getRoot() {
        return root;
    }

    /******************************* KLADD FRA TIMEN! ****************************

    /**
     * Insert inside the node class.
     *
    public void insert( T element ) {
        root = root.insert(element);
    }

    /**
     * Insert method 2, iterative
     *
    public void insert( T element ) throws Exception
    {
        BinaryNode<T> node = getRoot();
        if (root == null) root = new BinaryNode<>(element, null, null);

        while ( true )
        {
            if (element.compareTo(node.getElement()) > 0)
            {
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();
                }
                else {
                    node.lc = new BinaryNode<>(element, null, null);
                    return;
                }
            }
            else if (element.compareTo(node.getElement()) < 0)
            {
                if (node.getRightChild() != null) {
                    node = node.getRightChild();
                }
                else {
                    node.rc = new BinaryNode<>(element, null, null);
                    return;
                }
            }
            else throw new Exception("Duplicate.");
        }
    }


    /**
     * Inserts element into a balanced tree.
     *
     * NOTE: CANNOT BE USED BEFORE YOU IMPLEMENT COMPARABLE.
     * @param element
     * @param node
     *
    protected void insert(T element, BinaryNode node) throws Exception
    {
        if (getRoot() == null)
        {
            node = new BinaryNode(element, null, null);
        }
        else if (node.getElement().compareTo(element) > 0)
        {
            if (node.getLeftChild() != null)
            {
                insert(element, node.getLeftChild());
            }
            else node = new BinaryNode(element, null, null);
        }
        else if (node.getElement().compareTo(element) < 0)
        {
            if (node.getRightChild() != null)
            {
                insert(element, node.getRightChild());
            }
            else node = new BinaryNode(element, null, null);
        }
        else throw new Exception("Duplicate elements.");
    }

    *******************************Slutt på kladd**********************************/

    /**
     * Gets the size of the whole tree, beginning from root.
     * Previously used, but not anymore.
     *
     * @return the total amount of nodes in the tree.
     */
    public int size() {
        return calculateSize(getRoot(), 1);
    }

    /**
     * Calculates the size of the tree by recursively visiting
     * every node. NOTE: Slow.
     *
     * @param node used for recursion
     * @param size used for recursion
     * @return the size of the tree.
     */
    private static int calculateSize(BinaryNode node, int size) {

        if (node.getLeftChild() != null) {
            size = calculateSize(node.getLeftChild(), size + 1);
        }
        if (node.getRightChild() != null) {
            size = calculateSize(node.getRightChild(), size + 1);
        }
        return size;
    }

    /**
     * Gets the height of the tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return calculateHeight(getRoot(), 0);
    }

    /**
     * calculates the height of the left child tree and the
     * right child tree, then selects the tallest.
     *
     * @param node
     * @param height
     * @return the height of the tree.
     */
    private int calculateHeight(BinaryNode node, int height) {
        int height1 = 0;
        int height2 = 0;
        if (node.getLeftChild() != null) {
            height1 = Math.max(height, calculateSize(node.getLeftChild(), height + 1));
        }
        if (node.getRightChild() != null) {
            height2 = Math.max(height, calculateSize(node.getRightChild(), height + 1));
        }
        return Math.max(height1, height2);
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
        BinaryNode<T> current;
        LinkedList<BinaryNode<T>> stack;

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
        public BinaryNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Gets the next node according to the rules of pre order.
         *
         * @return next node.
         */
        public BinaryNode<T> nextMethod() {
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

        BinaryNode<T> current;
        LinkedList<BinaryNode<T>> stack;
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
        public BinaryNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Gets the next node according to the rules of post order.
         *
         * @return next node.
         */
        public BinaryNode<T> nextMethod() {

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
        BinaryNode<T> current;

        // Using my own queue class to get the
        // proper method names.
        Queue<BinaryNode<T>> queue;

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
        public BinaryNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Gets the next node according to the rules of level order.
         *
         * @return next node.
         */
        public BinaryNode<T> nextMethod() {
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

        BinaryNode<T> current;
        LinkedList<BinaryNode<T>> stack;
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
        public BinaryNode<T> nextNode() {
            return nextMethod();
        }

        /**
         * Delivers the next element according to the rules
         * of the inorder iterator.
         *
         * @return next element.
         */
        public BinaryNode<T> nextMethod() {
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
