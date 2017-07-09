package com.github.smartbooks.route;

import java.lang.reflect.Method;

public class RouteControllerItem {

	public String packagePrefix = "";

	public String controllerName = "";

	public String actionName = "";

	public String httpMethod = "get";
	
	public Class<?> controllerClass = null;
	
	public Method  controllerMethod = null;
}
