package tree;

/**
 * Created by Magnus on 01.04.2016.
 */
public class SmartNode<T> extends BinaryNode
{

    private int timesSeen;
    protected BinaryNode<T> node;

    public SmartNode(BinaryNode node) {
        super(node, null, null);
        timesSeen = 0;
        this.node = node;
    }

    public void iSawYou() {
        timesSeen++;
    }

    public int getTimesSeen() {
        return timesSeen;
    }

}
