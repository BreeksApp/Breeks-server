package project.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secretKey;
    private long validityInMs;
    private long refreshValidityInMs;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getValidityInMs() {
        return validityInMs;
    }

    public void setValidityInMs(long validityInMs) {
        this.validityInMs = validityInMs;
    }

    public long getRefreshValidityInMs() {
        return refreshValidityInMs;
    }

    public void setRefreshValidityInMs(long validityInMsRefresh) {
        this.refreshValidityInMs = validityInMsRefresh;
    }
}