package com.ljja.web.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.ljja.web.doc.ControllerActionDocument;
import com.ljja.web.doc.ControllerActionParamDocument;
import com.ljja.web.doc.ControllerDocument;

public abstract class BaseController {

	public BaseContext context = null;

	public ControllerDocument _doc() {

		ControllerDocument doc = new ControllerDocument();

		Class<?> controllerClass = this.getClass();

		doc.controllerName = controllerClass.getName();
		doc.packageName = controllerClass.getPackage().getName();

		Method[] methodArray = controllerClass.getMethods();
		for (Method methodItem : methodArray) {

			String methodName = methodItem.getName();

			if (methodName.equals("_doc") == false && 
					methodName.equals("wait") == false && 
					methodName.equals("equals") == false && 
					methodName.equals("toString") == false && 
					methodName.equals("hashCode") == false && 
					methodName.equals("getClass") == false && 
					methodName.equals("notify") == false && 
					methodName.equals("notifyAll") == false) {

				ControllerActionDocument actionDoc = new ControllerActionDocument();
				actionDoc.actionName = methodName;
				actionDoc.returnType = methodItem.getReturnType().getTypeName();

				Parameter[] paramArray = methodItem.getParameters();
				for (Parameter parameterItem : paramArray) {
					ControllerActionParamDocument actionParamDoc = new ControllerActionParamDocument();
					actionParamDoc.name = parameterItem.getName();
					actionParamDoc.type = parameterItem.getType().getTypeName();
					actionDoc.ParamList.add(actionParamDoc);
				}

				doc.methodList.add(actionDoc);
			}
		}

		return doc;
	}

}
