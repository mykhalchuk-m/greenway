package com.mmm.grenway.javafx.cfg;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ScreenConfig {
	private Stage primaryStage;

	@Autowired
	private Environment env;

	@Autowired
	private ResourceBundle resourceBundle;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle(env.getProperty("application.title"));
	}

	/**
	 * @param node
	 */
	private void setNode(Node node) {
		Scene scene = new Scene((Parent) node);
		scene.getStylesheets().add(ScreenConfig.class.getResource("/css/gw-style.css").toExternalForm());
		primaryStage.setScene(scene);
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		primaryStage.setMaximized(true);

		if (!primaryStage.isShowing()) {
			primaryStage.show();
		}
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
