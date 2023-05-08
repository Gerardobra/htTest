package it.aizoon.ersaf.integration.mybatis.plugin;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class ForceNumericToLongPlugin extends PluginAdapter {

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {

		List<String> toReplace = new ArrayList<String>();
		toReplace.add("java.lang.Short");
		toReplace.add("java.lang.Integer");
		String forceTo = "java.lang.Long";
		
		for (IntrospectedColumn o : introspectedTable.getAllColumns()) {
			if (toReplace.contains(o.getFullyQualifiedJavaType().getFullyQualifiedName())) {
				FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(forceTo);
				o.setFullyQualifiedJavaType(fullyQualifiedJavaType);
			}
		}

	}
}