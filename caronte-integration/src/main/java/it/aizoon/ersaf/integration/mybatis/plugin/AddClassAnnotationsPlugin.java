package it.aizoon.ersaf.integration.mybatis.plugin;

import java.util.List;



import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * Mybatis generator plugin to add annotations at the class level.
 */

public class AddClassAnnotationsPlugin extends PluginAdapter {
	public static final String ANNOTATION_CLASS = "annotationClass";
	public static final String ANNOTATION_STRING = "annotationString";

	private String annotationClass;
	private String annotationString;

	@Override
	public boolean validate(List<String> warnings) {
		annotationClass = properties.getProperty(ANNOTATION_CLASS);
		annotationString = properties.getProperty(ANNOTATION_STRING);

		String warning = "Property %s not set for plugin %s";
		if (!stringHasValue(annotationClass)) {
			warnings.add(String.format(warning, ANNOTATION_CLASS, this.getClass().getSimpleName()));
		}
		if (!stringHasValue(annotationString)) {
			warnings.add(String.format(warning, ANNOTATION_STRING, this.getClass().getSimpleName()));
		}

		return stringHasValue(annotationClass) && stringHasValue(annotationString);
	}

	@Override
	public boolean //modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) 
	clientGenerated(Interface interfacex, TopLevelClass topLevelClass, IntrospectedTable introspectedTable){
		interfacex.addImportedType(new FullyQualifiedJavaType(annotationClass));
		interfacex.addAnnotation(annotationString);
		
		
    FullyQualifiedJavaType extendedClassToAdd = new FullyQualifiedJavaType("it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao");
    
    String interfaceName = interfacex.getType().getShortName();    
    extendedClassToAdd.addTypeArgument(new FullyQualifiedJavaType("it.aizoon.ersaf.dto.generati."+interfaceName.substring(0, interfaceName.indexOf("Mapper"))));
    extendedClassToAdd.addTypeArgument(new FullyQualifiedJavaType("it.aizoon.ersaf.dto.generati."+interfaceName.substring(0, interfaceName.indexOf("Mapper"))+"Example"));
    
    interfacex.addSuperInterface(extendedClassToAdd);
      

		return true;
	}
	
	
}
