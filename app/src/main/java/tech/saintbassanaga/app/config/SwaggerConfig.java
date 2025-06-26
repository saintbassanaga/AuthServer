package tech.saintbassanaga.app.config;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project AuthServer at Thu - 6/26/25
 */
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "oauth2";

        return new OpenAPI()
                .info(new Info()
                        .title("Product API")
                        .version("1.0")
                        .description("API secured with OAuth2"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .clientCredentials(new OAuthFlow()
                                                        .tokenUrl("http://localhost:8081/oauth2/token")
                                                        .scopes(new Scopes()
                                                                .addString("read", "Read access")
                                                                .addString("write", "Write access")
                                                        )
                                                )
                                        )
                        ))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName, List.of("read", "write")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName, List.of("read")));

    }
}
