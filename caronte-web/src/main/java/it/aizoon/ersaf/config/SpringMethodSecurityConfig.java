package it.aizoon.ersaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import it.aizoon.ersaf.security.CaronteMethodSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
 
  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    CaronteMethodSecurityExpressionHandler expressionHandler = new CaronteMethodSecurityExpressionHandler();
    return expressionHandler;
  }
  
}
