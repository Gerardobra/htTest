package it.aizoon.ersaf.integration.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * Mybatis generator plugin to add annotations at the class level.
 */

public class AddClassExtendPlugin extends PluginAdapter {
  public static final String EXTENDED_CLASS = "extendedClass";

  private String extendedClass;
  @Override
  public boolean validate(List<String> warnings) {
    
    extendedClass = properties.getProperty(EXTENDED_CLASS);

    return true;
  }

  @Override
  public boolean  modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    
    topLevelClass.addImportedType(new FullyQualifiedJavaType(extendedClass));    

    FullyQualifiedJavaType extendedClassToAdd = new FullyQualifiedJavaType(extendedClass);
    topLevelClass.setSuperClass(extendedClassToAdd);
    
    return true;
  }

}
