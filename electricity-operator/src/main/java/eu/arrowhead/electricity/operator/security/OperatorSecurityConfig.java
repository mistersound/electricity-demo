package eu.arrowhead.electricity.operator.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import eu.arrowhead.client.library.config.DefaultSecurityConfig;

@Configuration
@ConditionalOnWebApplication
@EnableWebSecurity
public class OperatorSecurityConfig extends DefaultSecurityConfig {
	
}
