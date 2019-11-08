package com.piaomiao.oa;

import com.piaomiao.oa.bean.AppBeanUtil;
import com.piaomiao.oa.database.api.ITableMeta;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import com.piaomiao.oa.database.impl.mysql.MySQLTableMeta;
import com.piaomiao.oa.database.impl.mysql.MySQLTableOperator;
import com.piaomiao.oa.entity.GridHeader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OaApplicationTests {

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
        Map<String, String>  map = mySQLTableMeta.getTablesByName("pro_pro_infor");
        System.out.println(map);



    }







}
