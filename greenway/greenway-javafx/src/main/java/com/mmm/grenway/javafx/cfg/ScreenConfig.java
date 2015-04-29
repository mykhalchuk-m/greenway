package com.mmm.grenway.javafx.cfg;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ScreenConfig {
	private Stage primaryStage;
	private Scene scene;

	@Autowired
	private ResourceBundle resourceBundle;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void preparePrimaryScreen() {
		primaryStage.setTitle("GreenWay App");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);

		primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});

		primaryStage.show();
	}

	/**
	 * @param node
	 */
	private void setNode(Node node) {
		Scene scene = new Scene((Parent) node);
		primaryStage.setScene(scene);
	}

	public Node getView(Object controllerInstance, String fxmlFileName) {
		return getNode(controllerInstance, controllerInstance.getClass().getResource(fxmlFileName));
	}

	public void loadView(Object controllerInstance, String fxmlFileName) {
		setNode(getNode(controllerInstance, controllerInstance.getClass().getResource(fxmlFileName)));
	}

	private Node getNode(final Object control, URL location) {
		FXMLLoader loader = new FXMLLoader(location);
		loader.setResources(resourceBundle);
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			public Object call(Class<?> aClass) {
				return control;
			}
		});

		try {
			return loader.load();
		} catch (Exception e) {
			System.out.println("Error casting node");
			System.out.println(control.getClass() + " " + location);
			e.printStackTrace();
			return null;
		}
	}

}
