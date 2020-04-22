package org.foot.server.service.security.Filter;
import lombok.SneakyThrows;
import org.foot.server.model.User;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
@Order(2)
public  class AdapterFilter extends AbstractAnnotationConfigDispatcherServletInitializer implements  Filter  {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;

        if(httpServletRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {

        String s1 =((HttpServletRequest)servletRequest).getRequestURI();
        if(!s1.equals("/api/authenticate") ){
            String sessionid = httpServletRequest.getHeader("sessionID");
            CustomServletRequest customServletRequest = new CustomServletRequest((HttpServletRequest) servletRequest, sessionid);

        }

        filterChain.doFilter(servletRequest,servletResponse);
        }
    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { org.foot.server.service.security.Configuration.class };

    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }


    @Override
    protected String[] getServletMappings()  {
        return new String[0];
    }





}
