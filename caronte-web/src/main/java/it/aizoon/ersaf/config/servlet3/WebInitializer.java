package it.aizoon.ersaf.config.servlet3;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import it.aizoon.ersaf.bean.CaronteDispatcherServlet;
import it.aizoon.ersaf.config.SpringEjbConfig;
import it.aizoon.ersaf.config.SpringMethodSecurityConfig;
import it.aizoon.ersaf.config.SpringSecurityConfig;
import it.aizoon.ersaf.config.SpringWebConfig;
import it.aizoon.ersaf.filter.CaronteSessionFilter;
import it.aizoon.ersaf.filter.SiteMeshFilter;

@Configuration
@Import({ /* SpringJdbcConfig.class, */SpringEjbConfig.class, SpringWebConfig.class, SpringSecurityConfig.class, SpringMethodSecurityConfig.class })
// @ImportResource(value = {"classpath:spring-beans.xml",
// "classpath:beanRefContext.xml"})
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { /* SpringJdbcConfig.class, */SpringEjbConfig.class, SpringSecurityConfig.class, SpringMethodSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class, SpringMethodSecurityConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
	  Filter[] filtriSuperClasse = super.getServletFilters();
	  Filter[] filtri = new Filter[filtriSuperClasse != null ? filtriSuperClasse.length + 4 : 4];
	  
	  if (filtriSuperClasse != null) {
	    System.arraycopy(filtriSuperClasse, 0, filtri, 0, filtri.length - 4);
	  }
	  
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		filtri[filtri.length-4] = characterEncodingFilter;
		filtri[filtri.length-3] = new CaronteSessionFilter();
		filtri[filtri.length-2] = new ResourceUrlEncodingFilter();
		filtri[filtri.length-1] = new SiteMeshFilter();
		
		return filtri;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		/*
		 * In questo punto si settano i valori che nel file web.xml si troverebbero nei
		 * tag <context-param>
		 */
		servletContext.setInitParameter("defaultHtmlEscape", "true");
		super.onStartup(servletContext);
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {

		/*
		 * Cartella temporanea per memorizzare i file caricati
		 */
		File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

		/*
		 * Registrazione del Multipart parsing con Servlet 3.0
		 */
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
				maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

		registration.setMultipartConfig(multipartConfigElement);
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		final DispatcherServlet dispatcherServlet = /*(DispatcherServlet) super.createDispatcherServlet(
				servletAppContext);*/
		    new CaronteDispatcherServlet(servletAppContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return dispatcherServlet;
	}
	
}
