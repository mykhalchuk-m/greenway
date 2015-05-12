package com.mmm.grenway.javafx.cfg;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.mmm.greenway.data.cfg.DataConfig;
import com.mmm.greenway.data.cfg.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
@Import({ ScreenConfig.class, ControllerConfig.class, DataConfig.class, SecurityConfig.class })
public class AppConfig {
	
	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("lang", new Locale("ua", "UA"));
	}
	
}
