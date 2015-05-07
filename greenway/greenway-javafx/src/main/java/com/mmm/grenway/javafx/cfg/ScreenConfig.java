package com.mmm.grenway.javafx.cfg;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ScreenConfig {
	private Stage primaryStage;

	@Autowired
	private ResourceBundle resourceBundle;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("GreenWay App");
	}

	/**
	 * @param node
	 */
	private void setNode(Node node) {
		Scene scene = new Scene((Parent) node, 800, 600);
		scene.getStylesheets().add(ScreenConfig.class.getResource("/css/gw-style.css").toExternalForm());
		primaryStage.setScene(scene);
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
//		System.out.println("isAopProxy -> " + AopUtils.isAopProxy(control));
//		System.out.println("isCglibProxy -> " + AopUtils.isCglibProxy(control));
//		System.out.println("isJdkDynamicProxy -> " + AopUtils.isJdkDynamicProxy(control));
//		System.out.println("control -> " + control.getClass());
//		System.out.println("getTargetClass -> " + AopUtils.getTargetClass(control));
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			public Object call(Class<?> aClass) {
//				System.out.println("aClass -> " + aClass);
//				System.out.println("casted class -> " + aClass.cast(control).getClass());
//				try {
//					System.out.println("supper cast -> " + aClass.cast(((Advised) control).getTargetSource().getTarget()));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return aClass.cast(control);
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
