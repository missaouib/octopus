package com.targaryen.octopus.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties
public class ContextProperties {
    @Value("${server.servlet.context-path}")
    private String contextPath;
}
