package com.github.smartbooks.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller 默认配置
 * 
 * @author Administrator
 *
 */
public class ControllerConfig {

	public static List<String> controllerPackageList = new ArrayList<String>();

	public static final String defaultController = "Home";
	
	public static final String defaultAction = "_doc";
	
	public static final String defaultActionParamName = "action";
	
	public static final String defaultControllerSuffix = "Controller";
	
	public static final String defaultCharset = "utf-8";

}
