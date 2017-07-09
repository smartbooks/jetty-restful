package com.github.smartbooks.core;

import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseContext {

	public String rawUrl = null;
	public Request baseRequest = null;
	public HttpServletRequest request = null;
	public HttpServletResponse response = null;
	
	public Object model = null;
	
}
