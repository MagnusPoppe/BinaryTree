import tree.BinaryTree;

/**
 * Created by Magnu on 31.03.2016.
 */
public class Controller<T> {

    // Constants:
    final public static int X = 600; //X-axis length of gui window.
    final public static int Y = 600; //Y-axis length of gui window.

    // Objects:
    Gui gui;
    BinaryTree<T> tree;

    public Controller() {
        gui = new Gui();
        tree = createDummyTree();
    }

    public void run()
    {

    }

    public BinaryTree createDummyTree()
    {
        BinaryTree<String> c = new BinaryTree<>("c");
        BinaryTree<String> d = new BinaryTree<>("d");
        BinaryTree<String> e = new BinaryTree<>("e");
        BinaryTree<String> g = new BinaryTree<>("g");
        BinaryTree<String> æ = new BinaryTree<>("ø");
        BinaryTree<String> ø = new BinaryTree<>("æ");
        BinaryTree<String> f = new BinaryTree<>("f", g, null);
        BinaryTree<String> å = new BinaryTree<>("å", æ, ø);
        BinaryTree<String> a = new BinaryTree<>("a", e, f);
        BinaryTree<String> b = new BinaryTree<>("b", c, d);
        BinaryTree<String> tree = new BinaryTree<>("root", a, b);

        return tree;
    }
}
