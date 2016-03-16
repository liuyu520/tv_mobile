package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import oa.web.controller.base.BaseController;

import com.entity.ApkUploadLog;

@Controller
@RequestMapping("/app/upload")
public class ApkUploadLogController extends BaseController<ApkUploadLog> {
    @Override
    protected void beforeAddInput(Model model) {

    }

    @Override
    protected void errorDeal(Model model) {

    }

    @Override
    public String getJspFolder() {
        return null;
    }

}
