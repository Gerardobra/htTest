package it.aizoon.ersaf.integration.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.apache.ibatis.datasource.jndi.JndiDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalTimeTypeHandler;
import org.mybatis.cdi.SessionFactoryProvider;

import it.aizoon.ersaf.integration.mybatis.extension.StringBooleanTypeHandler;
import it.aizoon.ersaf.util.CaronteConstants;

@ApplicationScoped
public class SqlSessionFactoryProvider {
  private static Properties dbProperties = null; 

  @Produces
  @ApplicationScoped
  @SessionFactoryProvider
  public SqlSessionFactory produceFactory() throws IOException {
    /*
     * Transaction factory
     * 
     * <transactionManager type="MANAGED"/>
     */
    TransactionFactory trxFactory = new ManagedTransactionFactory();
    
    /*
     * Environment
     * 
     *  <environment id="caronte">
     *    <transactionManager type="MANAGED"/>
     *    <dataSource type="JNDI">
     *      <property name="data_source" value="java:/ersaf/jdbc/caronteTxDS"/>
     *    </dataSource>
     *  </environment>
     */
    Environment env = new Environment("caronte", trxFactory, getDataSource());
    
    /*
     * Configuration
     * 
     *  <configuration>
     *    <settings>
     *      <setting name="mapUnderscoreToCamelCase" value="true"/>
     *      <setting name="defaultStatementTimeout" value="60"/> <!-- seconds -->
     *      <setting name="logImpl" value="SLF4J"/>
     *    </settings>
     *    
     *    <environments default="caronte">
     *      ....
     *      
     *    </environments>
     *  </configuration>
     */
    Configuration config = new Configuration(env);
    config.setMapUnderscoreToCamelCase(true);
    config.setDefaultStatementTimeout(60);
    config.setLogImpl(org.apache.ibatis.logging.slf4j.Slf4jImpl.class);
    
    /*TypeAliasRegistry aliases = config.getTypeAliasRegistry();
    aliases.registerAlias("utente", Utente.class);*/
    config.addMappers("it.aizoon.ersaf.integration.mybatis.mapper");
    
    /*
     * <typeHandlers>
     *   <typeHandler handler="it.aizoon.ersaf.integration.mybatis.extension.LocalTimeTypeHandler"/>
     * </typeHandlers>
     */
    config.getTypeHandlerRegistry().register(Boolean.class, JdbcType.CHAR, StringBooleanTypeHandler.class);
    //config.getTypeHandlerRegistry().register(LocalTime.class, JdbcType.TIME, LocalTimeTypeHandler.class);
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
    
    /*String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
    factory.getConfiguration().addMappers("it.aizoon.ersaf.integration.mybatis.mapper");
    inputStream.close();*/
    
    return factory;
  }
  
  public static DataSource getDataSource() {
    JndiDataSourceFactory dsFactory = new JndiDataSourceFactory();
    Properties properties = new Properties();
    Properties dbProperties = getDbProperties();
    
    if (dbProperties == null) {
      properties.put(JndiDataSourceFactory.DATA_SOURCE, CaronteConstants.CARONTE_DS);
    }else {
      properties.put(JndiDataSourceFactory.DATA_SOURCE, "java:"+dbProperties.get(CaronteConstants.DATASOURCE_JNDI_PROPERTY));
    }
    
    System.out.println("\n\nDATA SOURCE JNDI NAME: "+properties.get(JndiDataSourceFactory.DATA_SOURCE));
    
    dsFactory.setProperties(properties);
    return dsFactory.getDataSource();
  }
  
  public static Properties getDbProperties() {
    
    if (dbProperties == null) {
      dbProperties = new Properties();
      
      try (InputStream inputStream = SqlSessionFactoryProvider.class.getClassLoader().getResourceAsStream("db.properties")) {
        dbProperties.load(inputStream);
      }catch (IOException ioe) {
        dbProperties = null;
      }
    }
    
    return dbProperties;
  }
  
}
