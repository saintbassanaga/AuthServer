package tech.saintbassanaga.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tech.saintbassanaga.authserver.config.RsaKeyProperties;


@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}

