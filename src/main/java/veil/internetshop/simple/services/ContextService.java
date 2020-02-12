package veil.internetshop.simple.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ContextService {

	HttpServletResponse currentResponse();

	HttpServletRequest currentRequest();

    void updateContext(HttpServletRequest request, HttpServletResponse response);
}
