package com.piaomiao.oa;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.piaomiao.oa.bean.AppBeanUtil;
import com.piaomiao.oa.dao.SysBoListDao;
import com.piaomiao.oa.database.api.ITableMeta;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import com.piaomiao.oa.database.impl.mysql.MySQLTableMeta;
import com.piaomiao.oa.database.impl.mysql.MySQLTableOperator;
import com.piaomiao.oa.entity.GridHeader;
import com.piaomiao.oa.entity.SysBoList;
import com.piaomiao.oa.util.DbUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OaApplicationTests {

    @Resource
    SysBoListDao sysBoListDao;

    @Test
    void contextLoads() {
       // MySQLTableOperator mySQLTableOperator =  AppBeanUtil.getBean(MySQLTableOperator.class);
        MySQLTableMeta mySQLTableMeta = AppBeanUtil.getBean(MySQLTableMeta.class);
  /*     ArrayList a = new ArrayList();
        a.add("pro_pro_infor");
        a.add("bpm_solution");
        Map<String, List<String>>  map  = mySQLTableOperator.getPKColumns(a);
        System.out.println(mySQLTableOperator.isTableExist("pro_pro_infor"));*/
       // Map<String, String> map = mySQLTableMeta.getTablesByName("pro_pro_infor");
       // List<Table>  tables = mySQLTableMeta.getTableModelByName("pro_pro_infor");
       /* Map<String, String>  map = mySQLTableMeta.getTablesByName("pro_pro_infor");
        System.out.println(map);*/
       SysBoList sysBoList = sysBoListDao.selectByPrimaryKey("2600000004451000");

       List<GridHeader> headers = DbUtil.getGridHeaders("select * from pro_pro_infor");
       Map<String, JSONObject> jsonFieldMap = this.getFieldJsonMap(sysBoList.getFieldsJson());
      /* for (GridHeader headerCol)*/


        System.out.println(jsonFieldMap);
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
