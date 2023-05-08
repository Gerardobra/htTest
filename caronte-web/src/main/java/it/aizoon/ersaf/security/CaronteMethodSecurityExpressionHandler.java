package it.aizoon.ersaf.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CaronteMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

  private AuthenticationTrustResolver trustResolver = 
      new AuthenticationTrustResolverImpl();
 
    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
      Authentication authentication, MethodInvocation invocation) {
        CaronteSecurityExpressionRoot root = 
          new CaronteSecurityExpressionRoot(authentication);
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        root.setDefaultRolePrefix(getDefaultRolePrefix());
        return root;
    }
    
    /*@Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
        Authentication authentication, MethodInvocation invocation) {
          CaronteSecurityExpressionRoot root = 
            new CaronteSecurityExpressionRoot(authentication);
          root.setPermissionEvaluator(getPermissionEvaluator());
          root.setTrustResolver(this.trustResolver);
          root.setRoleHierarchy(getRoleHierarchy());
          return root;
      }*/
}
