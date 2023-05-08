package it.aizoon.ersaf.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import it.aizoon.ersaf.util.CaronteConstants;

@Configuration
@ComponentScan(basePackages="it.aizoon.ersaf.integration")
public class SpringJdbcConfig {

  @Bean
  public JndiObjectFactoryBean dataSource() {
    JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
    dataSource.setJndiName(CaronteConstants.CARONTE_DS);
    dataSource.setProxyInterface(javax.sql.DataSource.class);
    return dataSource;
  }
  
  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
    NamedParameterJdbcTemplate template = 
        new NamedParameterJdbcTemplate((DataSource)dataSource().getObject());
    return template;
  }
  
  @Bean
  public AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
    return new AutowiredAnnotationBeanPostProcessor();
  }
  
  /*@Bean
  public UtenteDAO utenteDAO() {
     return new UtenteDAO();
  }*/
  
}
