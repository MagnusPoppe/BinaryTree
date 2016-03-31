import tree.BinaryNode;
import tree.BinaryTree;

/**
 * Created by Magnu on 31.03.2016.
 */
public class Controller<T> {

    // Constants:
    final public static int X = 1000; //X-axis length of gui window.
    final public static int Y = 600; //Y-axis length of gui window.
    final public static int treeheight = (int)Gui.RADIUS *3;

    // Objects:
    Gui gui;
    BinaryTree<T> tree;

    public Controller() {
        gui = new Gui();
        tree = createDummyTree();
    }

    public void run()
    {
        //TODO: Finn en måte å beregne avstander (dele plassen på bredden av sirkler).
        //TODO: eller, i de rekursive tallene blir jo alltid bredden doblet?

        makeTree( tree.getRoot(), X/2, treeheight , 0, X);

        //TODO: tegn sirklene rekursivt slik at alle sirklene blir telt med.
    }

    public void makeTree( BinaryNode node, int prevX, int prevY, int leftBorder, int rightBorder)
    {
        int center = (rightBorder - leftBorder) / 2;
        if( node.getLeftChild() != null ) {
            makeTree(
                node.getLeftChild(),
                (leftBorder+center),
                (prevY+treeheight),
                leftBorder,
                rightBorder-center
            );
        }
        if ( node.getRightChild() != null ) {
            makeTree(
                node.getRightChild(),
                (leftBorder+center),
                (prevY+treeheight),
                leftBorder+center,
                rightBorder
            );
        }
        gui.drawNode(
                node,
                (leftBorder + center),
                (prevY + treeheight),
                prevX,
                prevY
        );
        return;
    }

    public BinaryTree createDummyTree()
    {
        BinaryTree<String> c = new BinaryTree<>("c");
        BinaryTree<String> d = new BinaryTree<>("d");
        BinaryTree<String> e = new BinaryTree<>("e");
        BinaryTree<String> g = new BinaryTree<>("g");
        BinaryTree<String> æ = new BinaryTree<>("ø");
        BinaryTree<String> ø = new BinaryTree<>("æ");
        BinaryTree<String> å = new BinaryTree<>("å", æ, ø);
        BinaryTree<String> f = new BinaryTree<>("f", g, å);
        BinaryTree<String> a = new BinaryTree<>("a", e, f);
        BinaryTree<String> b = new BinaryTree<>("b", c, d);
        BinaryTree<String> tree = new BinaryTree<>("root", a, b);
        return tree;
    }
}
