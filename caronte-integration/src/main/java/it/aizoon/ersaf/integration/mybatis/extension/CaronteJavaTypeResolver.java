package it.aizoon.ersaf.integration.mybatis.extension;

import java.sql.Types;
import java.time.LocalTime;
import java.util.Properties;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * @author Ivan Morra
 *
 */
public class CaronteJavaTypeResolver extends JavaTypeResolverDefaultImpl {

  @Override
  public void addConfigurationProperties(Properties properties) {
    super.addConfigurationProperties(properties);

    typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", new FullyQualifiedJavaType(LocalTime.class.getName())));
    
    //typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(LocalTime.class.getName())));
  }

}
