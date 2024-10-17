package az.keycloak.demo.ayaznacafli.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {


    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> roles = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, roles);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        if (Objects.nonNull(jwt.getClaim("realm_access"))) {
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> keycloakRoles = objectMapper.convertValue(realmAccess.get("roles"), new TypeReference<>() {});

            List<GrantedAuthority> roles = new ArrayList<>();
            for (String keycloakRole : keycloakRoles) {
                roles.add(new SimpleGrantedAuthority(keycloakRole));
            }

            return roles;
        }
        return new ArrayList<>();
    }
}
