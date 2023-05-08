package it.aizoon.ersaf.business;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author ivan.morra
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {SpringEjbConfig.class, SpringJdbcConfig.class}, loader=AnnotationConfigContextLoader.class)
public class UtenteTest /*extends AbstractTestDataSourceInitializer*/ {

  private static Context ctx;
  private static EJBContainer ejbContainer;
  
  @BeforeClass
  public static void setUpClass() {
    /*ejbContainer = EJBContainer.createEJBContainer();
    System.out.println("Opening the container" );
    ctx = ejbContainer.getContext();
    */
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put(EJBContainer.MODULES, new File("build/jar"));
    ejbContainer = EJBContainer.createEJBContainer(properties);
    System.out.println("Opening the container");
    ctx = ejbContainer.getContext();
  }
  
  @AfterClass
  public static void tearDownClass() {
    ejbContainer.close();
    System.out.println("Closing the container" );
  }
  
  //@Test
  public void testApp() throws NamingException {

    /*CommentService converter = (CommentService) ctx.lookup("java:global/classes/CommentService");
    assertNotNull(converter);

    Comment t = new Comment();
    converter.create(t);
    t = new Comment();
    converter.create(t);
    t = new Comment();
    converter.create(t);
    t = new Comment();
    converter.create(t);

    Collection<Comment> ts = converter.getAll();

    assertEquals(4, ts.size());*/
}
  
//  @Inject
//  UtenteMapper utenteMapper;
//  
//  @Test
//  public void testGetUtenteById() {
//    System.out.println("\n\nUTENTE MAPPER: "+utenteMapper);
//
//      /*List<Parent> parents = service.selectAllParent();
//
//      assertNotNull("failure - parents is null", parents);
//
//      assertTrue("failure - expected 3 parents", parents.size() == 3);
//
//      assertTrue("failure - expected 2 children", parents.get(0).getChildren().size() == 2);
//*/
//  
//  }
}
