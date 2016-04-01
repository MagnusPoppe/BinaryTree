import tree.BinaryNode;
import tree.BinaryTree;

/**
 * Created by Magnu on 31.03.2016.
 */
public class Controller< T > {

    // Constants:
    final public static int X = 800; //X-axis length of gui window.
    final public static int Y = 600; //Y-axis length of gui window.
    int treeHeight;

    // Objects:
    Gui gui;
    BinaryTree< T > tree;

    public Controller( ) {
        gui = new Gui( );
        tree = createDummyTree( );
    }

    public void run( )
    {
        treeHeight = Y / tree.height( );
        makeTree( tree.getRoot( ), X / 2, ( int ) Gui.RADIUS, 0, X );
    }

    public void makeTree( BinaryNode node, int prevX, int prevY, int leftBorder, int rightBorder )
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
        return;
    }

    public BinaryTree createDummyTree( )
    {

        BinaryTree< String > t = new BinaryTree<>( "t" );
        BinaryTree< String > x = new BinaryTree<>( "x" );
        BinaryTree< String > c = new BinaryTree<>( "c" );
        BinaryTree< String > e = new BinaryTree<>( "e" );
        BinaryTree< String > g = new BinaryTree<>( "g" );
        BinaryTree< String > æ = new BinaryTree<>( "ø" );
        BinaryTree< String > q = new BinaryTree<>( "q" );
        BinaryTree< String > ø = new BinaryTree<>( "æ", q, null );
        BinaryTree< String > å = new BinaryTree<>( "å", æ, ø );
        BinaryTree< String > f = new BinaryTree<>( "f", g, å );
        BinaryTree< String > a = new BinaryTree<>( "a", e, f );
        BinaryTree< String > d = new BinaryTree<>( "d", t, x );
        BinaryTree< String > b = new BinaryTree<>( "b", c, d );
        BinaryTree< String > tree = new BinaryTree<>( "R", a, b );
        return tree;
    }
}
