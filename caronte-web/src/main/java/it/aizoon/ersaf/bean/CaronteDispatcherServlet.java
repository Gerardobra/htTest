package it.aizoon.ersaf.bean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.WebUtils;

@SuppressWarnings("serial")
public class CaronteDispatcherServlet extends DispatcherServlet {

  /**
   * Create a new {@code DispatcherServlet} with the given web application context. This
   * constructor is useful in Servlet 3.0+ environments where instance-based registration
   * of servlets is possible through the {@link ServletContext#addServlet} API.
   * <p>Using this constructor indicates that the following properties / init-params
   * will be ignored:
   * <ul>
   * <li>{@link #setContextClass(Class)} / 'contextClass'</li>
   * <li>{@link #setContextConfigLocation(String)} / 'contextConfigLocation'</li>
   * <li>{@link #setContextAttribute(String)} / 'contextAttribute'</li>
   * <li>{@link #setNamespace(String)} / 'namespace'</li>
   * </ul>
   * <p>The given web application context may or may not yet be {@linkplain
   * ConfigurableApplicationContext#refresh() refreshed}. If it has <strong>not</strong>
   * already been refreshed (the recommended approach), then the following will occur:
   * <ul>
   * <li>If the given context does not already have a {@linkplain
   * ConfigurableApplicationContext#setParent parent}, the root application context
   * will be set as the parent.</li>
   * <li>If the given context has not already been assigned an {@linkplain
   * ConfigurableApplicationContext#setId id}, one will be assigned to it</li>
   * <li>{@code ServletContext} and {@code ServletConfig} objects will be delegated to
   * the application context</li>
   * <li>{@link #postProcessWebApplicationContext} will be called</li>
   * <li>Any {@code ApplicationContextInitializer}s specified through the
   * "contextInitializerClasses" init-param or through the {@link
   * #setContextInitializers} property will be applied.</li>
   * <li>{@link ConfigurableApplicationContext#refresh refresh()} will be called if the
   * context implements {@link ConfigurableApplicationContext}</li>
   * </ul>
   * If the context has already been refreshed, none of the above will occur, under the
   * assumption that the user has performed these actions (or not) per their specific
   * needs.
   * <p>See {@link org.springframework.web.WebApplicationInitializer} for usage examples.
   * @param webApplicationContext the context to use
   * @see #initWebApplicationContext
   * @see #configureAndRefreshWebApplicationContext
   * @see org.springframework.web.WebApplicationInitializer
   */
  public CaronteDispatcherServlet(WebApplicationContext webApplicationContext) {
    super(webApplicationContext);
  }
  /**
   * Process the actual dispatching to the handler.
   * <p>The handler will be obtained by applying the servlet's HandlerMappings in order.
   * The HandlerAdapter will be obtained by querying the servlet's installed HandlerAdapters
   * to find the first that supports the handler class.
   * <p>All HTTP methods are handled by this method. It's up to HandlerAdapters or handlers
   * themselves to decide which methods are acceptable.
   * @param request current HTTP request
   * @param response current HTTP response
   * @throws Exception in case of any kind of processing failure
   */
  protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String uri = (String) request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE);
    System.out.println("\n\n\nURI IN REQUEST ATTRIBUTE: "+uri);
    if (uri == null) {
      System.out.println("\n\n\nURI IN REQUEST: "+request.getRequestURI());
    }
    
    super.doDispatch(request, response);
    System.out.println("\n\n\nIN DO DISPATCH RESPONSE STATUS: "+response.getStatus());
    
    if (response.getStatus() == 404) {
      /*String uri = getRequestUri(request);
      String errorUri = uri.substring(0, uri.indexOf("/", uri.indexOf("/") + 1) + 1) + "error/404";*/
      response.reset();
      //response.sendRedirect(errorUri)
      request.getRequestDispatcher("/WEB-INF/jsp/404.jsp").forward(request, response);
    }
  }
  
  /*private static String getRequestUri(HttpServletRequest request) {
    String uri = (String) request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE);
    if (uri == null) {
      uri = request.getRequestURI();
    }
    return uri;
  }*/
  
 
}
