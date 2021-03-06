package main.data.response;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("blog.info")
public class InitResponse {
    private String title;
    private String subtitle;
    private String phone;
    private String email;
    private String copyright;
    private String copyrightFrom;
}
