package com.mmm.grenway.javafx.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ScreenConfig.class, ControllerConfig.class})
public class AppConfig {

}
