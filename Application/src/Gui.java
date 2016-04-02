/**
 * Created by Magnu on 31.03.2016.
 */

import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import tree.BinaryNode;

public class Gui extends Application {

    // Constants
    final public static double      RADIUS = 20;
    final public static double      PANELWIDTH = 125;
    final public static double      WINDOWWIDTH = PANELWIDTH + Controller.X;
    final private static Color      NODECOLOR =  Color.LIGHTBLUE;
    final private static Color      ANIMATECOLOR = Color.LIGHTCYAN;
    final private static Duration   BLINKTIME = new Duration( 100 );

    // Graphical elements
    static BorderPane root;
    static GridPane panel;
    static Group group;
    static Label iteration;
    static Button preOrder, inOrder, postOrder, levelOrder;
    static SequentialTransition animate;

    /**
     * Starts the show.
     * @param stage yep!
     */
    @Override
    public void start( Stage stage )
    {
        Controller< String > ctrl = new Controller<>( );
        Scene scene = new Scene( root, ctrl.X, ctrl.Y );
        stage.setScene( scene );
        stage.setTitle( "Graphical view of a binary tree" );
        ctrl.buildTree( );
        stage.show( );
    }

    /**
     * Constructor for the gui class. Used
     * by the controller class.
     */
    public Gui( )
    {
        // Readying the window:
        group = new Group( );
        panel = new GridPane();
        root = new BorderPane();
        root.setCenter(group);
        root.setBottom( panel );

        // Creating the controls:
        iteration = new Label("      Iterationeffects:     ");
        preOrder = new Button("  Preorder iteration ");
        postOrder = new Button("Postorder iteration " );
        inOrder = new Button(" Inorder iteration    " );
        levelOrder = new Button("Levelorder iteration" );
        panel.addRow(0, iteration, preOrder, postOrder, inOrder, levelOrder);

        // Readying animations.
        animate = new SequentialTransition(  );
    }

    /**
     * Method to visually represent a node. Creates a circle
     * and lines to parent if not root. Used with the
     * "makeTree" method in the Controller class.
     * @param value of node.
     * @param x coordinates for the new node.
     * @param y coordinates for the new node.
     * @param parentX coordinates for the new node.
     * @param parentY coordinates for the new node.
     */
    public void drawNode( BinaryNode value, double x, double y, double parentX, double parentY )
    {
        // Adding line to connect the new circle to parent node.
        if (y != Controller.treeHeight+ Gui.RADIUS) { //If not rot
            Line connection = new Line( x, y, parentX, parentY );
            connection.setStrokeWidth( 2 );
            group.getChildren( ).add( connection );
        }

        // Adding a circle representation of the node to scene.
        Circle circle = new Circle( x, y, RADIUS );
        circle.setFill( NODECOLOR );
        circle.setStroke( Color.BLACK );
        group.getChildren( ).add( circle );

        // Adding the text on top of the node.
        int offset = 3; //to make the text fit vertically.
        Text content = new Text( x - offset, y + offset, value.toString( ) );
        content.setFont( new Font( "Arial", 12 ) );
        content.setTextAlignment( TextAlignment.CENTER );
        group.getChildren( ).add( content );
    }

    /**
     * Method to find a node in the tree. Recives a node value
     * and searches through every child of "group" to find the
     * corresponding circle. Used with the "lightEffects"
     * method in the Controller class.
     * @param value of a node
     */
    public static void findCircle( BinaryNode value) {
        int i = 0;
        for( Node n : group.getChildren()) {
            if (n instanceof Text) {
                Text other = (Text) n;
                if(other.getText().equals(value.getElement().toString())) {
                    Circle c = (Circle)group.getChildren().get( i-1 );
                    FillTransition ft = new FillTransition( BLINKTIME, c, NODECOLOR, ANIMATECOLOR);
                    ft.setCycleCount(2);
                    ft.setAutoReverse(true);
                    animate.getChildren().addAll( ft );
                }
            }
            i++;
        }
    }

    /**
     * Main.
     * @param args needed.
     */
    public static void main( String[] args )
    {
        launch( args );
    }
}
