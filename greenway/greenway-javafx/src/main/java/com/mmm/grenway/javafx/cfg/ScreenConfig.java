package com.mmm.grenway.javafx.cfg;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class ScreenConfig {
	private Stage primaryStage;
	private StackPane root;
	private Scene scene;
	
	public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
	
    public void showMainScreen() {
        root = new StackPane();
        primaryStage.setTitle("SpringFX");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        primaryStage.show();
    }
    
    private void setNode(Node node) {
    	root.getChildren().setAll(node);
    }
    
    public void loadView(Object controllerInstance, String fxmlFileName) {
        setNode(getNode(controllerInstance, controllerInstance.getClass().getResource(fxmlFileName)));
    }
    
    private Node getNode(final Object control, URL location) {
        FXMLLoader loader = new FXMLLoader(location);
        loader.setResources(ResourceBundle.getBundle("lang", new Locale("ua", "UA")));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            public Object call(Class<?> aClass) {
                return control;
            }
        });

        try {
            return (Node) loader.load();
        } catch (Exception e) {
        	System.out.println("Error casting node");
//            logger.error("Error casting node", e);
            return null;
        }
    }

}
