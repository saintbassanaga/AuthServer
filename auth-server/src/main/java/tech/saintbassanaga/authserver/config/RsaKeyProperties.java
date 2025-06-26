package tech.saintbassanaga.authserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project AuthServer at Thu - 6/26/25
 */
@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}
