package com.targaryen.octopus;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Created by zhouy on 2018/9/7.
 */
@Configuration
public class MyConfig {

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/octopus/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/octopus/**").addResourceLocations("/resources/");
    }
}
