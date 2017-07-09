package com.github.smartbooks.route;

import com.github.smartbooks.core.BaseController;
import com.github.smartbooks.core.ControllerConfig;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RouteControllerResolve {

	public static RouteControllerItem formUrl(String url, String action, String method) {

		RouteControllerItem item = new RouteControllerItem();

		// http method
		if (null != method && method.length() > 0) {
			item.httpMethod = method.toLowerCase();
		}

		// controller
		if (null == url || url.length() == 0 || url.equals("/")) {
			item.controllerName = ControllerConfig.defaultController + ControllerConfig.defaultControllerSuffix;
		} else {
			item.controllerName = url.replace("/", ".");
		}

		// action
		if (null == action || action.length() == 0) {
			item.actionName = ControllerConfig.defaultAction;
		} else {
			item.actionName = action;
		}

		for (String packageItem : ControllerConfig.controllerPackageList) {
			try {
				String tempPackagePath = packageItem + item.controllerName + ControllerConfig.defaultControllerSuffix;
				Class<?> tempControllerClass = ClassLoader.getSystemClassLoader().loadClass(tempPackagePath);
				if (BaseController.class.isAssignableFrom(tempControllerClass) &&
						tempControllerClass.isInterface() == false &&
						tempControllerClass.isArray() == false &&
						tempControllerClass.isEnum() == false && 
						tempControllerClass.isPrimitive() == false &&
						tempControllerClass.getModifiers() == Modifier.PUBLIC &&
						tempControllerClass.getModifiers() != Modifier.ABSTRACT ) {
					Method[] methodArray = tempControllerClass.getMethods();
					for (Method methodItem : methodArray) {
						if (methodItem.getName().equals(item.actionName)) {
							item.packagePrefix = packageItem;
							item.controllerClass = tempControllerClass;
							item.controllerMethod = methodItem;							
							return item;
						}
					}
				}
			} catch (Exception e) {
				
			}
		}

		return item;
	}

}
