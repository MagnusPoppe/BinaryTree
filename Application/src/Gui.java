/**
 * Created by Magnu on 31.03.2016.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tree.BinaryNode;

public class Gui extends Application {

    // Constants
    final public static double RADIUS = 20;
    final public static double PANELWIDTH = 125;
    final public static double WINDOWWIDTH = PANELWIDTH + Controller.X;

    // Graphical elements
    static GridPane root, panel;
    static Group group;
    static Label animation;
    static Button preOrder, inOrder, postOrder, levelOrder;


    @Override
    public void start( Stage stage )
    {
        Controller< String > ctrl = new Controller<>( );

        Scene scene = new Scene( root, WINDOWWIDTH, ctrl.Y );
        stage.setScene( scene );
        stage.setTitle( "Graphical view of a binary tree" );
        ctrl.buildTree( );
        stage.show( );
    }

    public Gui( )
    {
        // Readying the window:
        group = new Group( );
        panel = new GridPane();
        root = new GridPane();
        root.addRow( 0, group, panel );
        root.getColumnConstraints().add(
            new ColumnConstraints( Controller.X )
        );
        root.getColumnConstraints().add(
            new ColumnConstraints( PANELWIDTH )
        );

        // Creating the controls:
        animation = new Label("Iterationeffects");
        preOrder = new Button("  Preorder iteration ");
        preOrder.setOnAction( e -> {

        });
        postOrder = new Button("Postorder iteration " );
        inOrder = new Button(" Inorder iteration    " );
        levelOrder = new Button("Levelorder iteration" );

        panel.addColumn( 0, animation, preOrder, postOrder, inOrder, levelOrder );

    }

    public void drawNode( BinaryNode value, double x, double y, double parentX, double parentY )
    {
        // Adding line to connect the new circle to parent node.
        Line connection = new Line( x, y, parentX, parentY );
        connection.setStrokeWidth( 2 );
        group.getChildren( ).add( connection );

        // Adding a circle representation of the node to scene.
        Circle circle = new Circle( x, y, RADIUS );
        circle.setFill( Color.LIGHTBLUE );
        circle.setStroke( Color.BLACK );
        group.getChildren( ).add( circle );

        // Adding the text on top of the node.
        int offset = 3; //to make the text fit vertically.
        Text content = new Text( x - offset, y + offset, value.toString( ) );
        content.setFont( new Font( "Arial", 12 ) );
        content.setTextAlignment( TextAlignment.CENTER );
        group.getChildren( ).add( content );
    }

    public static void main( String[] args )
    {
        launch( args );
    }
}
