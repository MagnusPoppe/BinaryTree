/**
 * Created by Magnu on 31.03.2016.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tree.BinaryNode;

public class Gui extends Application {

    // Constants
    final private static double RADIUS = 50;

    // Graphical elements
    Group root;


    @Override
    public void start(Stage stage) {
        Controller<String> ctrl = new Controller<>();

        Scene scene = new Scene( root, ctrl.X, ctrl.Y );
        stage.setScene( scene );
        stage.setTitle( "Graphical view of a binary tree" );
        stage.show();

        ctrl.run();
    }

    public Gui() {
        root = new Group();
    }

    public void drawNode(BinaryNode value, double x, double y, double parentX, double parentY)
    {
        // Adding line to connect the new circle to parent node.
        Line connection = new Line( x, y, parentX, parentY );
        root.getChildren().add( connection );

        // Adding a circle representation of the node to scene.
        Circle circle = new Circle( x, y, RADIUS );
        root.getChildren().add( circle );

        // Adding the text on top of the node.
        Text content = new Text( x, y, value.toString() );
        content.setFont( new Font( "Arial", 12 ) );
        root.getChildren().add( content );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
