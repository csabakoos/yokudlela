package hu.yokudlela.haccp.spring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author csabakoos
 */

@SecurityScheme(
        type = SecuritySchemeType.OAUTH2,
        name = "oauth2",
        description = "KeyCloak Yokudlela",
        flows = @OAuthFlows(
                implicit = @OAuthFlow(authorizationUrl = "http://172.17.0.2:6080/auth/realms/yokudlela/protocol/openid-connect/auth"
                        + "?client_id=account"
                        + "&redirect_uri=http://172.17.0.2:8080/haccp/swagger-ui/oauth2-redirect.html"
                        + "&response_type=code"
                        + "&scope=openid")
        )
)

@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "apikey",
        paramName = "Authorization",
        description = "KeyCloak Yokudlela",
        in = SecuritySchemeIn.HEADER)


@SecurityScheme(
        type = SecuritySchemeType.OPENIDCONNECT,
        name = "openid",
        description = "KeyCloak Yokudlela",
        openIdConnectUrl = "http://localhost:6080/auth/realms/yokudlela/.well-known/openid-configuration"
)

@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080/haccp", description = "local dev")
        },

        info = @Info(
                title = "Yokudlela HACCP API",
                version = "v1",
                description = "Yokudlela HACCP API for Graphical User Interface",
                license = @License(
                        name = "",
                        url = ""),
                contact = @Contact(
                        url = "",
                        name = "Csaba Koós", email = "csaba.koos@stud.uni-obuda.hu")))

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "hu.yokudlela.haccp")
@EnableJpaRepositories("hu.yokudlela.haccp.logic")
@EntityScan("hu.yokudlela.haccp.model")
@EnableCaching
@SpringBootApplication
public class HaccpApplication {

    /**
     * This is the main method of the application.
     */
    public static void main(String[] args)
    {
        SpringApplication.run(HaccpApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
