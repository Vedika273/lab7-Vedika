package lab7vedika;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author 6303129
 */
public class Lab7Vedika extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        
       BorderPane root = new BorderPane();
       
       //Top part animations 
       Pane pane = new Pane();
        
        //animation 1
        Rectangle rectangle = new Rectangle(100, 100, 300, 300);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        
        //object A
        Circle circle = new Circle(125, 100, 50);
        circle.setFill(Color.ORANGE);
        circle.setStroke(Color.BLACK);
        
        //Path transition
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(rectangle);
        pt.setNode(circle);
        pt.setOrientation(PathTransition.OrientationType.
        ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(false);
        pt.play(); // Start animation
        
        //circle.setOnMousePressed(e -> pt.pause());
        //circle.setOnMouseReleased(e -> pt.play());
    
        
        //create object B 
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(250);
        ellipse.setCenterY(250);
        ellipse.setRadiusX(60);
        ellipse.setRadiusY(20);
        ellipse.setFill(Color.RED);
        ellipse.setStroke(Color.BLACK);
        
        pane.getChildren().addAll(rectangle, circle, ellipse);
        
//        ellipse.setFill(Color.RED); 
//        ellipse.setStroke(Color.BLACK);
//        ellipse.centerXProperty().bind(pane.widthProperty().divide(2)); 
//        ellipse.centerYProperty().bind(pane.heightProperty().divide(2)); 
//        ellipse.radiusXProperty().bind(pane.widthProperty().multiply(0.4)); 
//        ellipse.radiusYProperty().bind(pane.heightProperty().multiply(0.4)); 
//        pane.getChildren().add(ellipse);
        // Apply a fade transition to ellipse
//        FadeTransition ft = new FadeTransition(Duration.millis(3000), ellipse); 
//        ft.setFromValue(1.0);
//        ft.setToValue(0.1); 
//        ft.setCycleCount(Timeline.INDEFINITE); 
//        ft.setAutoReverse(true);
//        ft.play(); // Start animation
//        // Control animation
//        ellipse.setOnMousePressed(e -> ft.pause()); 
//        ellipse.setOnMouseReleased(e -> ft.play());
        
        
        //SequentialTransition seq = new Sequiential 
        
        //Bottom part 
        HBox buttonBox  = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        
        Button startBtn = new Button("Start");
        Button resetBtn = new Button("Reset");
        Button exitBtn = new Button("Exit");

        buttonBox.getChildren().addAll(startBtn, resetBtn, exitBtn);

        // Add top and bottom to the BorderPane
        root.setTop(pane);
        root.setBottom(buttonBox);
        
        Scene scene = new Scene(pane, 600, 550);
        stage.setTitle("PathTransitionDemo");
        stage.setScene(scene);
        stage.show();
        
 }
}
