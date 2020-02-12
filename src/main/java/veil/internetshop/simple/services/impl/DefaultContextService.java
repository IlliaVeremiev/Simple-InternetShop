package veil.internetshop.simple.services.impl;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import veil.internetshop.simple.services.ContextService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultContextService implements ContextService{

    @Override
    public HttpServletResponse currentResponse(){
        return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
    }

    @Override
    public HttpServletRequest currentRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    @Override
    public void updateContext(HttpServletRequest request, HttpServletResponse response){
        ServletRequestAttributes attributes = new ServletRequestAttributes(request, response);
        RequestContextHolder.setRequestAttributes(attributes);
    }
}
