package tech.saintbassanaga.authserver.controller;

import com.nimbusds.jose.jwk.JWKSet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project AuthServer at Thu - 6/26/25
 */
@RestController
@RequiredArgsConstructor
public class JwkSetRestController {

    private final JWKSet jwkSet;

    @GetMapping(value = "/.well-known/jwks.json",produces = "application/json" )
    public Map<String, Object> getJwkSet() {
        return jwkSet.toJSONObject();
    }

}
