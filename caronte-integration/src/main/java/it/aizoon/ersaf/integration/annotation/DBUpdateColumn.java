package it.aizoon.ersaf.integration.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface DBUpdateColumn {
  String value();
  boolean whereClause() default false;
  String defaultUpdateValue() default "";
  String defaultInsertValue() default "";
  Class<?> type() default Object.class; 
}
