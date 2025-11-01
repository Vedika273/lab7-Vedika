package lab7vedika;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
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
    
    public Circle circleA;  
    public Ellipse ellipse; 
    public PathTransition pathTransitionA;
    public FadeTransition fadeB;
    public ScaleTransition scaleB;
    public RotateTransition rotateB;
    public TranslateTransition translateB;
    
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
       Pane topPane = new Pane();
       topPane.setPrefHeight(400);
       topPane.setStyle("-fx-background-color : lightblue");
        
       
        //animation 1
        Rectangle rectanglePath = new Rectangle(100, 100, 300, 300);
        rectanglePath.setFill(Color.WHITE);
        rectanglePath.setStroke(Color.BLACK);
        topPane.getChildren().add(rectanglePath);
        
        
        //object A
        Circle circle = new Circle(20, Color.RED);
        circleA.setCenterX(100);
        circleA.setCenterY(100);
        topPane.getChildren().add(circleA);
        
        //Path transition
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(rectanglePath);
        pt.setNode(circleA);
        pt.setOrientation(PathTransition.OrientationType.
        ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        
        //circle.setOnMousePressed(e -> pt.pause());
        //circle.setOnMouseReleased(e -> pt.play());
    
        
        //create object B 
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(250);
        ellipse.setCenterY(250);
        ellipse.setRadiusX(60);
        ellipse.setRadiusY(20);
        ellipse.setFill(Color.ORANGE);
        ellipse.setStroke(Color.BLACK);
        topPane.getChildren().add(ellipse);
        
         // Fade
        fadeB = new FadeTransition(Duration.seconds(3), ellipse);
        fadeB.setFromValue(1.0);
        fadeB.setToValue(0.2);
        
        

        
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
        buttonBox.setStyle("-fx-padding :40;");
        
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
            fadeB.play(); //object B fade aniamation 
        });
        
        //reset button stops everything and resaets the circle positions
        resetBtn.setOnAction( e -> {
            pt.stop();
            circle.setCenterX(125);
            circle.setCenterY(100);
            rectanglePath.setOpacity(1.0);
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
        root.setTop(topPane);
        root.setBottom(buttonBox);
        
        Scene scene = new Scene(root, 600, 550);
        stage.setTitle("PathTransitionDemo");
        stage.setScene(scene);
        stage.show();
        
 }
}
