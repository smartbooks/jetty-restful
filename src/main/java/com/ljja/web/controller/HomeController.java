package com.ljja.web.controller;

import com.ljja.web.core.BaseController;
import com.ljja.web.model.HomeIndexRequestModel;
import com.ljja.web.model.HomeIndexResponseModel;

public class HomeController extends BaseController {

    /**
     * 默认主页示例
     *
     * @param date  从url取值
     * @param model 从BODY取值,且继承自BaseRequestModel
     * @return
     */
    public HomeIndexResponseModel Index(
            int sex1,
            Integer sex2,
            Double sex3,
            double sex4,
            float sex5,
            Float sex6,
            long sex7,
            Long sex8,
            HomeIndexRequestModel model,
            String date) {

        System.out.println("HomeController->Index");

        HomeIndexResponseModel rs = new HomeIndexResponseModel();

        rs.Date = date;
        rs.Sex = sex1;
        //rs.UserName = model.UserName;
        //rs.Passowrd = model.Passowrd;

        return rs;
    }

    public void hi(int sex1, Integer sex2) {
        System.out.println("HomeController->hi" + sex1 + sex2);
    }
}
