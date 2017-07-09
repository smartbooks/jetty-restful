package com.ljja.web.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public final class DefaultContext extends BaseContext {

	public DefaultContext(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response){
		super.rawUrl = target;
		super.baseRequest = baseRequest;
		super.request = request;
		super.response = response;		
	}
	
}
