/**
 * 
 */
package it.aizoon.ersaf.integration;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao;

/**
 * @author ivan.morra
 *
 */
public abstract class AbstractTestDataSourceInitializer<T extends IBaseDao<? extends BaseDto,? extends GenericExample>> {

  private static Environment environment = null;
  protected static SqlSessionFactory sqlSessionFactory = null;
  protected SqlSession sqlSession = null;
  protected T mapper = null;
  private Class<T> mapperClass = null;
  
  public AbstractTestDataSourceInitializer(Class<T> mapperClass) {
    this.mapperClass = mapperClass;
  }
  
  private static void buildEnvironment() {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriverClass(org.postgresql.Driver.class);
    
    //dataSource.setUsername("postgres");
    //dataSource.setPassword("7c!!OC7x");

    dataSource.setUsername("Caronte");
    //dataSource.setPassword("F45%fEGS");
    dataSource.setPassword("caronte");

    dataSource.setUrl("jdbc:postgresql://localhost:5432/caronte"); //LOCAL
    //dataSource.setUrl("jdbc:postgresql://192.168.1.20:5432/caronte"); //TEST    
   
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    environment = new Environment("development", transactionFactory, dataSource);
  }
  
  protected static SqlSessionFactory getSqlSessionFactory(Class<? extends IBaseDao<? extends BaseDto,? extends GenericExample>> mapperClass) {
    if (environment == null) {
      buildEnvironment();
    }
    
    Configuration configuration = new Configuration(environment);
    configuration.addMapper(mapperClass);
    return new SqlSessionFactoryBuilder().build(configuration);
  }
  
  @Before
  public void beforeEachTest() {
    sqlSession = sqlSessionFactory.openSession();
    mapper = sqlSession.getMapper(mapperClass);
  }
  
  @After
  public void afterEachTest() {
    sqlSession.rollback();
    //sqlSession.commit();
    sqlSession.close();
    System.out.println("This is exceuted after each Test");
  }
  
  
}
