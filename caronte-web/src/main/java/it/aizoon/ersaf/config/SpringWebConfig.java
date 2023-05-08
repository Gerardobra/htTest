package it.aizoon.ersaf.config;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.resource.CssLinkResourceTransformer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.XmlViewResolver;

import it.aizoon.ersaf.bean.CaronteViewResolver;
import it.aizoon.ersaf.formatter.StringTrimmingConverter;
import it.aizoon.ersaf.interceptor.LocaleHolderInterceptor;
import it.aizoon.ersaf.interceptor.ViewMessageInterceptor;
import it.aizoon.ersaf.util.ApplicationContextProvider;

@Configuration
@EnableWebMvc
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "it.aizoon.ersaf.controller,it.aizoon.ersaf.service,it.aizoon.ersaf.filter,it.aizoon.ersaf.validator,it.aizoon.ersaf.bean")
public class SpringWebConfig extends WebMvcConfigurerAdapter {
  // public class SpringWebConfig extends WebMvcConfigurationSupport {

  @Autowired
  ServletContext servletContext;

  @Bean
  public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
    RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
    requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    return requestMappingHandlerAdapter;
  }

  /*
   * @Override
   * 
   * @Bean public FormattingConversionService mvcConversionService() {
   * FormattingConversionServiceFactoryBean factory = new
   * FormattingConversionServiceFactoryBean(); Set<Formatter> formatters = new
   * HashSet<>(); formatters.add(new DateFormatter()); formatters.add(new
   * BigDecimalFormatter()); factory.setFormatters(formatters);
   * 
   * Set<Converter> converters = new HashSet<>(); converters.add(new
   * StringTrimmingConverter()); factory.setFormatters(formatters);
   * 
   * factory.afterPropertiesSet();
   * 
   * return factory.getObject(); }
   */
  @Override
  public void addFormatters(FormatterRegistry registry) {
    // System.out.println("\n\nREGISTRO FORMATTERS!!!!");
    /*
     * registry.addFormatterForFieldType(Date.class, new DateFormatter());
     * registry.addFormatterForFieldType(BigDecimal.class, new
     * BigDecimalFormatter());
     */
    registry.addConverter(new StringTrimmingConverter());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {    
    /*
     * La gestione delle risorse statiche prevede che queste siano cacheate per
     * 30 giorni, e che siano versionate in modo da far riconoscere al browser
     * quando una nuova versione sia disponibile per il download. Questo
     * versionamento viene applicato anche agli import dentro i css
     */
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
        .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS)).resourceChain(false)
        .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))
        .addTransformer(new CssLinkResourceTransformer());
  }

  // @Override
  // public void configureMessageConverters(List<HttpMessageConverter<?>>
  // converters) {
  // converters.add(converter());
  // addDefaultHttpMessageConverters(converters);
  // }

  // @Bean
  // MappingJackson2HttpMessageConverter converter() {
  // MappingJackson2HttpMessageConverter converter = new
  // MappingJackson2HttpMessageConverter();
  // return converter;
  // }

  @Bean
  /*public InternalResourceViewResolver viewResolver() {*/
  public CaronteViewResolver viewResolver() {
    CaronteViewResolver viewResolver = new CaronteViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setOrder(1);
    viewResolver.setCache(false);
    return viewResolver;
  }

  @Bean
  public XmlViewResolver xmlViewResolver() {
    XmlViewResolver xmlViewResolver = new XmlViewResolver();
    Resource resource = new ServletContextResource(servletContext, "/WEB-INF/views.xml");
    xmlViewResolver.setLocation(resource);
    xmlViewResolver.setOrder(0);
    return xmlViewResolver;
  }

  @Bean
  public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
    rb.setBasenames(new String[] { "classpath:messages/messages", "classpath:messages/errors" });
    rb.setDefaultEncoding("UTF-8");
    return rb;
  }

  /*
   * @Bean(name = "validator") public LocalValidatorFactoryBean
   * localValidatorFactoryBean() { LocalValidatorFactoryBean lvfb = new
   * LocalValidatorFactoryBean();
   * lvfb.setValidationMessageSource(messageSource()); return lvfb; }
   */

  @Override
  public Validator getValidator() {
    super.getValidator();
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(messageSource());
    return validator;
  }

  @Bean
  public CookieLocaleResolver localeResolver() {
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setDefaultLocale(Locale.ITALIAN);
    resolver.setCookieName("caronteLocaleCookie");
    resolver.setCookieMaxAge(3600);
    return resolver;
  }

  @Bean
  public ApplicationContextProvider applicationContextProvider() {
    ApplicationContextProvider acp = new ApplicationContextProvider();
    return acp;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    resolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
    return resolver;
  }

  /*
   * Interceptors
   */

  @Bean
  LocaleChangeInterceptor localeInterceptor() {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("locale");
    return interceptor;
  }

  @Bean
  LocaleHolderInterceptor localeHolderInterceptor() {
    LocaleHolderInterceptor interceptor = new LocaleHolderInterceptor();
    return interceptor;
  }

  @Bean
  ViewMessageInterceptor viewMessageInterceptor() {
    ViewMessageInterceptor interceptor = new ViewMessageInterceptor();
    return interceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(viewMessageInterceptor());
    registry.addInterceptor(localeInterceptor());
    registry.addInterceptor(localeHolderInterceptor());
  }

  /*
   * @Bean public SimpleUrlHandlerMapping getUrlHandlerMapping() {
   * SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
   * Properties mappings = new Properties(); mappings.put("/home",
   * "homeController"); mappings.put("/", "homeController");
   * 
   * handlerMapping.setMappings(mappings); return handlerMapping; }
   * 
   * @Bean(name = "homeController") public ParameterizableViewController
   * getHomeController() { ParameterizableViewController viewController = new
   * ParameterizableViewController(); viewController.setViewName("home"); return
   * viewController; }
   */

  /*
   * IMPORTANTE: questo handler gestisce tutti gli URL non mappati dai
   * controller, e per questo motivo deve stare in ultima posizione tra tutti
   * gli handler
   * 
   * Equivalente a <mvc:default-servlet-handler/>
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

}
