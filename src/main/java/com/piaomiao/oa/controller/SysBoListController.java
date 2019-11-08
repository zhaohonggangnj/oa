package com.piaomiao.oa.controller;

import com.piaomiao.oa.dao.SysBoListDao;
import com.piaomiao.oa.entity.SysBoList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sysBoList/")
public class SysBoListController {
    @Resource
    SysBoListDao sysBoListDao;

    @RequestMapping("/edit2")
    public String edit2(){

        System.out.println("123");
        SysBoList sysBoList = sysBoListDao.selectByPrimaryKey("2600000004451000");
        System.out.println(sysBoList);
        return  null;

    }



}
