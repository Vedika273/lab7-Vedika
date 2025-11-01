package lab7vedika;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
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
       pane.setStyle("-fx-background-color : lightgray");
        
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
        
        // Fade Transition for ellipse (Object B)
        FadeTransition fadeEllipse = new FadeTransition(Duration.seconds(3), ellipse);
        fadeEllipse.setFromValue(1.0); // fully visible
        fadeEllipse.setToValue(0.1);   // almost invisible
        fadeEllipse.setCycleCount(1);  // only once (as requested in assignment)
        fadeEllipse.setAutoReverse(false);
        
        // Scale Transition for ellipse (Object B)
        ScaleTransition scaleEllipse = new ScaleTransition(Duration.seconds(3), ellipse);
        scaleEllipse.setFromX(1.0);
        scaleEllipse.setFromY(1.0);
        scaleEllipse.setToX(1.5); // grow bigger
        scaleEllipse.setToY(1.5);
        scaleEllipse.setCycleCount(1);
        scaleEllipse.setAutoReverse(false);
        
        // Rotate Transition for ellipse (Object B)
        RotateTransition rotateEllipse = new RotateTransition(Duration.seconds(3), ellipse);
        rotateEllipse.setByAngle(360); // full rotation
        rotateEllipse.setCycleCount(1);
        rotateEllipse.setAutoReverse(false);
        
        // Translate Transition for ellipse (Object B)
        TranslateTransition moveEllipse = new TranslateTransition(Duration.seconds(3), ellipse);
        moveEllipse.setByY(-100); // Move ellipse upward by 100 pixels
        moveEllipse.setCycleCount(1);
        moveEllipse.setAutoReverse(false);

        


        
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
        buttonBox.setStyle("-fx-padding :25;");
        
        Button startBtn = new Button("Start");
        Button resetBtn = new Button("Reset");
        Button exitBtn = new Button("Exit");
        
        // Make buttons bigger
        startBtn.setPrefSize(80, 30);
        resetBtn.setPrefSize(80, 30);
        exitBtn.setPrefSize(80, 30);
      
        
        //the start button controls both animations 
        startBtn.setOnAction(e -> {
            pt.play(); //object A fade aniamation 
            fadeEllipse.play(); //object B fade aniamation 
        });
        
        //reset button stops everything and resaets the circle positions
        resetBtn.setOnAction( e -> {
            pt.stop();
            circle.setCenterX(125);
            circle.setCenterY(100);
            rectangle.setOpacity(1.0);
        });
        
           exitBtn.setOnAction (e -> {
            stage.close();
        });
           
        fadeEllipse.setOnFinished(e -> {
         scaleEllipse.play();
         });
        
        scaleEllipse.setOnFinished(e -> {
          rotateEllipse.play();
          });
        
        rotateEllipse.setOnFinished(e -> {
          moveEllipse.play();
          });

        buttonBox.getChildren().addAll(startBtn, resetBtn, exitBtn);

        // Add top and bottom to the BorderPane
        root.setTop(pane);
        root.setBottom(buttonBox);
        
        Scene scene = new Scene(root, 600, 550);
        stage.setTitle("PathTransitionDemo");
        stage.setScene(scene);
        stage.show();
        
 }
}
