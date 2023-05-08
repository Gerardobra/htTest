package it.aizoon.ersaf.integration.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class ForceIdToLongPlugin extends PluginAdapter {

	public ForceIdToLongPlugin() {

	}

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {

		String forceTo = "java.lang.Long";
		
		for (IntrospectedColumn o : introspectedTable.getAllColumns()) {
			if (o.getActualColumnName().toLowerCase().startsWith("id_")) {
				FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(forceTo);
				o.setFullyQualifiedJavaType(fullyQualifiedJavaType);
			}
		}

	}
}