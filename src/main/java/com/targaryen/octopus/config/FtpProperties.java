package com.targaryen.octopus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
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

    public String getFTP_ADDRESS() {
        return FTP_ADDRESS;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getFTP_ROOT() {
        return FTP_ROOT;
    }
}
