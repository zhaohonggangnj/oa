package com.piaomiao.oa.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.piaomiao.oa.entity.GridHeader;
import com.piaomiao.oa.entity.SysBoList;
import com.piaomiao.oa.service.impl.SysBoListServiceImpl;
import com.piaomiao.oa.util.DbUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysBoList/")
public class SysBoListController {

    @Resource
    SysBoListServiceImpl sysBoListService;

    @RequestMapping("/edit2")
    public String edit2(Map<String, Object> model){
        SysBoList sysBoList = sysBoListService.getById("2600000004451000");
        List<GridHeader> headers = DbUtil.getGridHeaders("select * from pro_pro_infor");
        JSONArray fieldCols = new JSONArray();
        Map<String,JSONObject> jsonFieldMap = this.getFieldJsonMap(sysBoList.getFieldsJson());
        for(GridHeader headerColumns:headers){
            JSONObject fieldObj = new JSONObject();
            if ("YES".equals(sysBoList.getIsDialog())) {
                JSONObject fieldJson = jsonFieldMap.get(headerColumns.getFieldName());
                if (fieldJson == null) {
                    fieldObj.put("header", headerColumns.getFieldLabel());
                } else {
                    fieldObj.put("header", fieldJson.getString("header"));
                    fieldObj.put("isReturn", fieldJson.getString("isReturn"));
                    fieldObj.put("visible", fieldJson.getString("visible"));
                }
            } else {
                fieldObj.put("header", headerColumns.getFieldLabel());
            }
            fieldObj.put("field",headerColumns.getFieldName());
            if (!headerColumns.getFieldName().equals("CREATE_TIME_") && !headerColumns.getFieldName().equals("UPDATE_TIME_")) {
                fieldObj.put("dataType", headerColumns.getDataType());
            } else {
                fieldObj.put("dataType", "date");
                fieldObj.put("format", "yyyy-MM-dd");
            }
            fieldObj.put("queryDataType", headerColumns.getQueryDataType());
            fieldObj.put("width", 100);
            fieldCols.add(fieldObj);
        }

        model.put("fieldColumns", JSON.toJSONString(fieldCols));
        model.put("sysBoList", JSON.toJSONString(sysBoList));
        return  "sysBoList/sysBoListEdit";

    }

        private Map<String, JSONObject> getFieldJsonMap(String fieldJson) {
            HashMap<String, JSONObject> map = new HashMap<>();
            if(StringUtils.isEmpty(fieldJson)) {
                return map;
            } else {
                JSONArray jsonArray = JSONArray.parseArray(fieldJson);
                for(int i = 0; i < jsonArray.size(); ++i) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    String field = jsonObj.getString("field");
                    map.put(field, jsonObj);
                }
                return map;
            }
        }





}
