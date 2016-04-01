/**
 * Created by Magnu on 31.03.2016.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tree.BinaryNode;

public class Gui extends Application
{

    // Constants
    final public static double RADIUS = 20;

    // Graphical elements
    static Group root;


    @Override
    public void start(Stage stage)
    {
        Controller<String> ctrl = new Controller<>();

        Scene scene = new Scene( root, ctrl.X, ctrl.Y );
        stage.setScene( scene );
        stage.setTitle( "Graphical view of a binary tree" );
        ctrl.run();
        stage.show();
    }

    public Gui()
    {
        root = new Group();
    }

    public void drawNode(BinaryNode value, double x, double y, double parentX, double parentY)
    {
        // Adding line to connect the new circle to parent node.
        Line connection = new Line( x, y, parentX, parentY );
        connection.setStrokeWidth(2);
        root.getChildren().add( connection );

        // Adding a circle representation of the node to scene.
        Circle circle = new Circle( x, y, RADIUS );
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        root.getChildren().add( circle );

        // Adding the text on top of the node.
        int offset = 3; //to make the text fit vertically.
        Text content = new Text( x-3, y+offset, value.toString() );
        content.setFont( new Font( "Arial", 12) );
        content.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add( content );
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
