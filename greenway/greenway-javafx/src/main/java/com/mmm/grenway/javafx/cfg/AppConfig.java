package com.mmm.grenway.javafx.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mmm.greenway.data.cfg.DataConfig;
import com.mmm.greenway.data.cfg.SecurityConfig;

@Configuration
@Import({ScreenConfig.class, ControllerConfig.class, DataConfig.class, SecurityConfig.class})
public class AppConfig {

}
