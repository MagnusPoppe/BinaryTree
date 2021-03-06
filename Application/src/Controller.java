import searchTree.BinarySearchNode;
import searchTree.BinarySearchTree;
import searchTree.TreeIterator;

/**
 * Controller class for the graphical view of a
 * binary trees MVC. Runs different animations.
 * Created by Magnus Poppe Wang on 31.03.2016.
 *
 * @author Magnus Poppe Wang
 */
public class Controller< T extends Comparable<? super T> > {

    // Constants:
    final public static int X = 800; //X-axis length of gui window.
    final public static int Y = 800; //Y-axis length of gui window.

    // Objects:
    Gui gui;

    BinarySearchTree< T > tree;
    public static int treeHeight;

    /**
     * constructor.
     */
    public Controller( )
    {
        gui = new Gui( );
        setActionListeners( );
        tree = new BinarySearchTree<T>();


        System.out.println(tree.getRoot().getHeight() );
        buildTree();
    }

    /**
     * Runs the "makeTree()" method with the
     * appropriate values.
     */
    public void buildTree( )
    {
        treeHeight = Y/ 10;//(tree.getRoot().getHeight() + 5);
        makeTree( tree.getRoot( ), X / 2, ( int ) Gui.RADIUS, 0, X );
    }

    /**
     * Graphicly builds the tree from a root node.
     *
     * @param node        rootnode.
     * @param prevX       for recursion
     * @param prevY       for recursion
     * @param leftBorder  to find placements for circles.
     * @param rightBorder to find placements for circles.
     */
    public void makeTree(BinarySearchNode node, int prevX, int prevY, int leftBorder, int rightBorder )
    {
        int center = ( rightBorder - leftBorder ) / 2;
        if ( node.getLeftChild( ) != null ) {
            makeTree(
                    node.getLeftChild( ),
                    ( leftBorder + center ),
                    ( prevY + treeHeight ),
                    leftBorder,
                    rightBorder - center
            );
        }
        if ( node.getRightChild( ) != null ) {
            makeTree(
                    node.getRightChild( ),
                    ( leftBorder + center ),
                    ( prevY + treeHeight ),
                    leftBorder + center,
                    rightBorder
            );
        }
        gui.drawNode(
                node,
                ( leftBorder + center ),
                ( prevY + treeHeight ),
                prevX,
                prevY
        );
    }

    public void insert( T value )
    {
        tree.insert(value);
        //gui = new Gui();
        buildTree();
    }

    /**
     * Creates an animation of a iteration. Accepts
     * all kinds of tree iterators.
     *
     * @param iterator of your choosing.
     */
    public void lighteffects( TreeIterator iterator )
    {
        gui.animate.getChildren( ).clear( );
        while ( iterator.hasNext( ) ) {
            BinarySearchNode current = iterator.nextNode( );
            gui.findCircle( current );
        }
        gui.animate.play( );
    }

    /**
     * Sets action listeners to the animation buttons.
     */
    public void setActionListeners( )
    {
        gui.preOrder.setOnAction( e -> lighteffects( tree.preOrderIterator( ) ) );
        gui.postOrder.setOnAction( e -> lighteffects( tree.postOrderIterator( ) ) );
        gui.inOrder.setOnAction( e -> lighteffects( tree.inOrderIterator( ) ) );
        gui.levelOrder.setOnAction( e -> lighteffects( tree.levelOrderIterator( ) ) );
    }

    /**
     * Creates dummytree for testing purposes.
     *
     * @return a tree.
     */
//    public static BinaryTree createDummyTree( )
//    {
//
//        BinaryTree< String > t = new BinaryTree<>( "t" );
//        BinaryTree< String > x = new BinaryTree<>( "x" );
//        BinaryTree< String > c = new BinaryTree<>( "c" );
//        BinaryTree< String > e = new BinaryTree<>( "e" );
//        BinaryTree< String > g = new BinaryTree<>( "g" );
//        BinaryTree< String > æ = new BinaryTree<>( "ø" );
//        BinaryTree< String > q = new BinaryTree<>( "q" );
//        BinaryTree< String > ø = new BinaryTree<>( "æ", q, null );
//        BinaryTree< String > å = new BinaryTree<>( "å", æ, ø );
//        BinaryTree< String > f = new BinaryTree<>( "f", g, å );
//        BinaryTree< String > a = new BinaryTree<>( "a", e, f );
//        BinaryTree< String > d = new BinaryTree<>( "d", t, x );
//        BinaryTree< String > b = new BinaryTree<>( "b", c, d );
//        BinaryTree< String > tree = new BinaryTree<>( "R", a, b );
//        return tree;
//    }
//
    /**
     * Creates dummytree for testing purposes.
     * ONLY WORKS WITH INTEGER.
     *
     * @return a tree.
     */
    public static BinarySearchTree createDummySearchTree( )
    {
        BinarySearchTree dummyTree = new BinarySearchTree<>( 500 );
        for (int i = 0; i < 10; i++) {
            dummyTree.insert((int)(Math.random() * 1000) -1);
        }
        return dummyTree;
    }
}
