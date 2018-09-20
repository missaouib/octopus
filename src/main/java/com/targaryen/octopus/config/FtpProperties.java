package com.targaryen.octopus.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties
public class FtpProperties {
    @Value("${octopus.ftp.address}")
    private String FTP_ADDRESS;

    @Value("${octopus.ftp.username}")
    private String LOGIN_NAME;

    @Value("${octopus.ftp.password}")
    private String PASSWORD;

    @Value("${octopus.ftp.root}")
    private String FTP_ROOT;
}
