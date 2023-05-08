package it.aizoon.ersaf.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;

import it.aizoon.ersaf.business.IUtenteEJB;
import it.aizoon.ersaf.config.SpringCoreConfig;
import it.aizoon.ersaf.config.SpringWebConfig;
import it.aizoon.ersaf.config.servlet3.WebInitializer;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringCoreConfig.class, SpringWebConfig.class, WebInitializer.class})
public class HomeControllerTest {

  ApplicationContext context;
  
  /*@InjectMocks
  private IndexController indexController;
  */
  //private MockMvc mockMvc;
  
  @Mock
  private IUtenteEJB utenteEjb;
  
  @InjectMocks
  private HomeController controller;

  private MockMvc mockMvcHome;
  private MockMvc mockMvcIndex;

  @Before
  public void setup() {
    // Process mock annotations
    MockitoAnnotations.initMocks(this);

    /*context.getBean("viewResolver");
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/jsp/view/");
    viewResolver.setSuffix(".jsp");

    mockMvc = MockMvcBuilders.standaloneSetup(new HelpController())
                             .setViewResolvers(viewResolver)
                             .build();
    */

    // Setup Spring test in standalone mode
    //this.mockMvcIndex = MockMvcBuilders.standaloneSetup(indexController).build();

    
    // Setup Spring test in standalone mode
    //this.mockMvcHome = MockMvcBuilders.standaloneSetup(controller).setViewResolvers((ViewResolver)/*context.getBean("viewResolver")*/new SpringWebConfig().viewResolver()).build();
    this.mockMvcHome = MockMvcBuilders.standaloneSetup(controller).setViewResolvers((ViewResolver)/*context.getBean("viewResolver")*/new SpringWebConfig().viewResolver()).build();
  }
  
  /*@Autowired
  private IUtenteEJB utenteEjb = null;*/

  @BeforeClass
  public static void initController() {
    //controller = new HomeController();
  }
  
  @Test
  public void testHome() throws Exception {
    
    /*when(sampleService.saveFrom(any(SignupForm.class)))
            .thenThrow(new InvalidUserException("For Testing"));*/
    when(utenteEjb.getUtenteProva()).thenReturn("Artan test Bora");
 
    this.mockMvcHome.perform(get("/home")
            /*.param("email", "mvcemail@test.com")
            .param("firstName", "mvcfirst")
            .param("lastName", "mvclastname")*/)
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/jsp/home.jsp"))
            .andExpect(model().attributeExists("nomeUtente"));
  }
  
//  @Test
//  public void testCreateSignupFormInvalidUser() throws Exception {
//   
//      when(sampleService.saveFrom(any(SignupForm.class)))
//              .thenThrow(new Exception("For Testing"));
//   
//      /*this.mockMvc.perform(post("/create")
//              .param("email", "mvcemail@test.com")
//              .param("firstName", "mvcfirst")
//              .param("lastName", "mvclastname"))
//              .andExpect(status().isOk())
//              .andExpect(forwardedUrl(IndexController.PAGE_INDEX))
//              .andExpect(model().attributeExists("page_error"));*/
//      
//      this.mockMvcIndex.perform(post("/create")
//          .param("email", "mvcemail@test.com")
//          .param("firstName", "dave")
//          .param("lastName", "mvclastname"))
//          .andExpect(status().isOk())
//          .andExpect(forwardedUrl(IndexController.PAGE_INDEX))
//          .andExpect(model().attributeExists("page_error"));
//   
//  }


}
