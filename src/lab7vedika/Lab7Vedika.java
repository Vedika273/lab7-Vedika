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
    public SequentialTransition seqB; 
    
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
        
       
        //rectangle path for animation 1
        Rectangle rectanglePath = new Rectangle(100, 100, 300, 300);
        rectanglePath.setFill(Color.WHITE);
        rectanglePath.setStroke(Color.BLACK);
        topPane.getChildren().add(rectanglePath);
        
        
        //object A
        circleA = new Circle(20, Color.RED);
        circleA.setCenterX(100);
        circleA.setCenterY(100);
        topPane.getChildren().add(circleA);
        
        //Path transition
        pathTransitionA = new PathTransition();
        pathTransitionA.setDuration(Duration.seconds(4));
        pathTransitionA.setPath(rectanglePath);
        pathTransitionA.setNode(circleA);
        pathTransitionA.setOrientation(PathTransition.OrientationType.
        ORTHOGONAL_TO_TANGENT);
        pathTransitionA.setCycleCount(Timeline.INDEFINITE);
        pathTransitionA.setAutoReverse(true);
        
        //circle.setOnMousePressed(e -> pathTransitionA.pause());
        //circle.setOnMouseReleased(e -> pathTransitionA.play());
    
        
        //create object B 
        ellipse = new Ellipse();
        ellipse.setCenterX(250);
        ellipse.setCenterY(250);
        ellipse.setRadiusX(60);
        ellipse.setRadiusY(20);
        ellipse.setFill(Color.ORANGE);
        ellipse.setStroke(Color.BLACK);
        topPane.getChildren().add(ellipse);
        
         // Fade
        FadeTransition fade = new FadeTransition(Duration.seconds(3), ellipse);
        fade.setFromValue(1.0);
        fade.setToValue(0.2);
        
        // Scale
        ScaleTransition scale = new ScaleTransition(Duration.seconds(3), ellipse);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(1.5);
        scale.setToY(1.5);

        // Rotate
        RotateTransition rotate = new RotateTransition(Duration.seconds(3), ellipse);
        rotate.setByAngle(360);

        // Translate upward
        TranslateTransition translate = new TranslateTransition(Duration.seconds(3), ellipse);
        translate.setByY(-100);
        
        
        // Combine all into a SequentialTransition
        seqB = new SequentialTransition(fade, scale, rotate, translate);
        seqB.setCycleCount(SequentialTransition.INDEFINITE);
        seqB.setAutoReverse(true);
        
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
            pathTransitionA.play(); 
            seqB.play(); 
        });
        
        //reset button stops everything and resaets the circle positions
        resetBtn.setOnAction(e -> {
            pathTransitionA.stop();
            
            circleA.setTranslateX(0);
            circleA.setTranslateY(0);
            circleA.setRotate(0);

            seqB.stop();
            ellipse.setOpacity(1.0);
            ellipse.setScaleX(1.0);
            ellipse.setScaleY(1.0);
            ellipse.setRotate(0);
            ellipse.setTranslateY(0);
            
              // Start animations again
               pathTransitionA.play();
               seqB.play();
        });
        
           exitBtn.setOnAction (e -> {
            stage.close();
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
