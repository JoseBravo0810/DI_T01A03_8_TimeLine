/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t01a03_8_timeline;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jose
 */
public class T01A03_8_TimeLine extends Application {
    
    public static double ballSpeedX = 1;
    public static double ballSpeedY = 1;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Esta vez el nodo raiz será un grupo
        Group pane = new Group();
        
        // Creamos la pelota de la animacion
        Circle ball = new Circle(10);
        // Le damos la posicion inicial en el eje X
        ball.setTranslateX(540 * 0.5);
        // Le damos la posicion inicial en el eje Y
        ball.setTranslateY(400 * 0.5);
        // Añadimos la pelota al grupo (Panel raiz)
        pane.getChildren().add(ball);
        
        // Creamos la etiqueta que nos dira el valor de Frames Por Segundo (FPS - Frames Per Second)
        Label lb = new Label();
        // La ubicamos en su posicion inicial en el eje X
        lb.setTranslateX(10);
        // Y en el eje Y
        lb.setTranslateY(10);
        // La añadimos al grupo (nodo raiz)
        pane.getChildren().add(lb);
        
        // Creamos la escena con el grupo raiz como nodo y las dimensiones
        Scene scene = new Scene(pane, 540, 400);
        
        // Creamos el listener a incluir en el bucle de TimeLine
        EventHandler<ActionEvent> eH = e ->{
            // Extraemos la frecuencia de refresco, FPS
            PerformanceTracker perfTracker = PerformanceTracker.getSceneTracker(scene);
            // Mostramos los FPS a traves de la etiqueta
            lb.setText("FPS (TimeLine) = " + perfTracker.getInstantFPS());
            
            // Cambiar la direccion de la bola si llega a los extremos de la ventana en el eje X
            if(ball.getTranslateX() < 0 || ball.getTranslateX() > 540)
            {
                ballSpeedX *= -1;
            }
            
            // Cambiar la direccion de la bola si llega a los extremos de la ventana en el eje Y
            if(ball.getTranslateY() < 0 || ball.getTranslateY() > 400)
            {
                ballSpeedY *= -1;
            }
            
            // Transladamos, movemos la bola con las velocidades tanto de X como de Y (de 1 pixel en 1 pixel a cada bucle), 
            //las cuales variaran al tocar alguna pared (segun la pared variará la velocidad de X o de Y, yendo al contrario de la velocidad que iba [*= -1])
            ball.setTranslateX(ball.getTranslateX() + ballSpeedX);
            ball.setTranslateY(ball.getTranslateY() + ballSpeedY);
        };
        
        // Definimos el bucle con: duracion en milisegundos = 5, aprox 60 FPS
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), eH));
        // Le decimos que la animacion se realice en un bucle ifinito
        animation.setCycleCount(Timeline.INDEFINITE);
        // Iniciamos la animacion
        animation.play();
        
        primaryStage.setTitle("TimeLine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
