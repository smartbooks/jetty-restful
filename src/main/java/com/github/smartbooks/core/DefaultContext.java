package com.github.smartbooks.core;

import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class DefaultContext extends BaseContext {

	public DefaultContext(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response){
		super.rawUrl = target;
		super.baseRequest = baseRequest;
		super.request = request;
		super.response = response;		
	}
	
}
