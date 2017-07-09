package com.ljja.web.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljja.web.route.RouteControllerItem;
import com.ljja.web.route.RouteControllerResolve;

public class RestHandler extends AbstractHandler {

    @Override
    protected void doStart() throws Exception {
        System.out.println("RestHandler-doStart");
        super.doStart();
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RouteControllerItem routeControllerItem = RouteControllerResolve.formUrl(
                target,
                request.getParameter(ControllerConfig.defaultActionParamName),
                request.getMethod());

        if (null != routeControllerItem.controllerClass) {

            BaseContext context = new DefaultContext(target, baseRequest, request, response);

            try {
                renderController(routeControllerItem, context);
            } catch (Exception e) {
                response.setStatus(500);
                e.printStackTrace(response.getWriter());
            }

        } else {
            response.setStatus(404);
        }

        response.flushBuffer();
        baseRequest.setHandled(true);
    }

    private void renderController(RouteControllerItem routeControllerItem, BaseContext context) throws Exception {

        // 实例化用户类Controller
        Object userControllerInstance = routeControllerItem.controllerClass.newInstance();

        // 上下文环境初始化
        ((BaseController) userControllerInstance).context = context;

        // 获取用户类Controller方法参数列表
        Parameter[] methodParamArray = routeControllerItem.controllerMethod.getParameters();

        // 传递到方法的实参
        Object[] paramArrayInstance = new Object[methodParamArray.length];

        // 初始化方法参数列表
        for (int i = 0; i < methodParamArray.length; i++) {

            if (BaseRequestModel.class.isAssignableFrom(methodParamArray[i].getType())) {

                if (context.request.getContentLengthLong() > 0) {

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(context.request.getInputStream(), ControllerConfig.defaultCharset));

                    StringBuilder requestBody = new StringBuilder();

                    String line = null;
                    while ((line = br.readLine()) != null) {
                        requestBody.append(line);
                    }

                    ObjectMapper mapper = new ObjectMapper();

                    paramArrayInstance[i] = mapper.readValue(requestBody.toString(), methodParamArray[i].getType());
                } else {
                    paramArrayInstance[i] = null;
                }

            } else {

                try {
                    String requestParamValue = context.request.getParameter(methodParamArray[i].getName());

                    Class<?> methodParamType = methodParamArray[i].getType();
                    String methodParamTypeName = methodParamType.getTypeName();

                    if (methodParamTypeName.equals(Integer.class.getTypeName()) || methodParamTypeName.equals(int.class.getTypeName())) {
                        paramArrayInstance[i] = null == requestParamValue ? 0 : Integer.valueOf(requestParamValue);

                    } else if (methodParamTypeName.equals(Double.class.getTypeName()) || methodParamTypeName.equals(double.class.getTypeName())) {
                        paramArrayInstance[i] = null == requestParamValue ? 0D : Double.valueOf(requestParamValue);

                    } else if (methodParamTypeName.equals(float.class.getTypeName()) || methodParamTypeName.equals(Float.class.getTypeName())) {
                        paramArrayInstance[i] = null == requestParamValue ? 0F : Float.valueOf(requestParamValue);

                    } else if (methodParamTypeName.equals(boolean.class.getTypeName()) || methodParamTypeName.equals(Boolean.class.getTypeName())) {
                        paramArrayInstance[i] = null == requestParamValue ? true : Boolean.valueOf(requestParamValue);

                    } else if (methodParamTypeName.equals(long.class.getTypeName()) || methodParamTypeName.equals(Long.class.getTypeName())) {
                        paramArrayInstance[i] = null == requestParamValue ? 0L : Long.valueOf(requestParamValue);

                    } else if (methodParamTypeName.equals(short.class.getTypeName()) || methodParamTypeName.equals(Short.class.getTypeName())) {
                        paramArrayInstance[i] = null == requestParamValue ? (short) 0 : Short.valueOf(requestParamValue);

                    } else if (methodParamTypeName.equals(byte.class.getTypeName()) || methodParamTypeName.equals(Byte.class.getTypeName())) {
                        paramArrayInstance[i] = null == requestParamValue ? (byte) 0 : Byte.valueOf(requestParamValue);

                    } else if (methodParamTypeName.equals(char.class.getTypeName()) || methodParamTypeName.equals(Character.class.getTypeName())) {
                        if (null != requestParamValue && requestParamValue.length() > 0) {
                            paramArrayInstance[i] = requestParamValue.charAt(0);
                        } else {
                            paramArrayInstance[i] = Character.valueOf('0');
                        }

                    } else if (null != requestParamValue) {
                        paramArrayInstance[i] = requestParamValue;

                    } else {
                        paramArrayInstance[i] = methodParamType.newInstance();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // filter->start_action

        Object methodResult = routeControllerItem.controllerMethod.invoke(userControllerInstance, paramArrayInstance);

        // filter->end_action

        // 媒体类型封装
        new MediaTypeOutput().write(context.request, context.response, methodResult);

        // 直接返回
        context.baseRequest.setHandled(true);
    }

}
