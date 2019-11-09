package com.piaomiao.oa.controller;


import com.piaomiao.oa.entity.SysBoList;
import com.piaomiao.oa.service.impl.SysBoListServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/sysBoList/")
public class SysBoListController {

    @Resource
    SysBoListServiceImpl sysBoListService;

    @RequestMapping("/edit2")
    public String edit2(Map<String, Object> model){

        System.out.println("123");
        SysBoList sysBoList = sysBoListService.getById("2600000004451000");
        model.put("sysBoList", sysBoList);
        System.out.println(sysBoList.getIdField());
        return  "sysBoList/sysBoListEdit";

    }



}
