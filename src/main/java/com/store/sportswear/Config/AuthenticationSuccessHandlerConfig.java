package com.store.sportswear.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class AuthenticationSuccessHandlerConfig implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        authorities.forEach(grantedAuthority -> {
            if(grantedAuthority.getAuthority().equals("ROLE_MEMBER")){
                try{
                    redirectStrategy.sendRedirect(request,response,"/");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                try{
                    redirectStrategy.sendRedirect(request,response,"/admin");
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else if (grantedAuthority.getAuthority().equals("ROLE_SHIPPER")) {
                try{
                    redirectStrategy.sendRedirect(request,response,"/shipper");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                throw new IllegalStateException();
            }
        });
    }
}
