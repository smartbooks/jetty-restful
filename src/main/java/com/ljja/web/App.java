package com.ljja.web;

import com.ljja.web.core.ControllerConfig;
import com.ljja.web.core.RestHandler;
import org.eclipse.jetty.server.Server;

public class App {

    /**
     * 请求示例:
     * 请求方式:
     * POST或GET
     *
     * URL:
     * http://localhost:8090/Home?action=Index&date=2017-01-01&sex=1
     *
     * POST BODY:
     * {"UserName":"admin","Passowrd":"123456"}
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        //服务器端口
        Server server = new Server(8090);

        //设置扫描的包名
        ControllerConfig.controllerPackageList.add("com.ljja.web.controller");

        server.setHandler(new RestHandler());

        server.start();

        server.join();
    }
}
