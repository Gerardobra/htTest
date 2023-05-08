package it.aizoon.ersaf.config;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import it.aizoon.ersaf.security.ErsafAuthenticationProvider;
import it.aizoon.ersaf.security.ErsafTokenRepositoryImpl;
import it.aizoon.ersaf.security.ErsafUserDetailServiceImpl;

/**
 * @author ff
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "it.aizoon.ersaf.business")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public ErsafAuthenticationProvider authProvider() {
		ErsafAuthenticationProvider authProvider = new ErsafAuthenticationProvider();
		return authProvider;
	}

	 @Bean
	 public PersistentTokenRepository persistentTokenRepository() {
		ErsafTokenRepositoryImpl tokenRepositoryImpl = new ErsafTokenRepositoryImpl();
		return tokenRepositoryImpl;
	 }

	 @Override
     @Bean
	 public UserDetailsService userDetailsService() {
		ErsafUserDetailServiceImpl rememberMeServicesImpl = new ErsafUserDetailServiceImpl();
		return rememberMeServicesImpl;
	 }

	// @Bean
	// public HttpSessionEventPublisher httpSessionEventPublisher() {
	// return new HttpSessionEventPublisher();
	// }
	//

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authProvider()).userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder(12, new SecureRandom());
		return encoder;
	}

	/*@Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**"); 
  }*/
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()
			.antMatchers("/resources/**")
				.permitAll()
			.antMatchers("/**/*.html")
			  .permitAll()
			/*.antMatchers("/ajax/**")
        .permitAll()*/
			.antMatchers("/public/**")
				.permitAll()
			.antMatchers("/")
        .permitAll()
			.antMatchers("/home")
			  .permitAll()
			.antMatchers("/ajax/accettaCookie")		
        .permitAll()
        .antMatchers("/**/centroaziendale/dettaglio/**").permitAll()  
        .antMatchers("/**/azienda/editAttivitaMateriali/**").permitAll()
        .antMatchers("/**/campioni/editCampioni/**").permitAll()
        .antMatchers("/**/controllofisico/editOrgNocFisico/**").permitAll()
        .antMatchers("/**/import/servizi/documentazione/scaricaguida/*").permitAll()
		.antMatchers("/registrazione/**")
			  .permitAll()
			.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/login-required")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login-failed")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/home")
				.permitAll()
			.and()
			.exceptionHandling()
				.accessDeniedPage("/accesso-negato")
			.and()
			.rememberMe()
				.rememberMeParameter("remember-me")
				.tokenRepository(persistentTokenRepository())
				.userDetailsService(userDetailsService())
				.tokenValiditySeconds(86400)
			/*.and()
			.csrf()*/;
		;

	}
	
	// aggiunto per gestire gli slashes passati nella url(vedi su autorizzazioni, centroaziendale/modifica, variabile indirizzo centro az)
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    DefaultHttpFirewall firewall = new DefaultHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    return firewall;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
	
}
