package com.piaomiao.oa;

import com.piaomiao.oa.bean.AppBeanUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OaApplicationTests {

    @Test
    void contextLoads() {
        JdbcTemplate jdbcTemplate = AppBeanUtil.getBean(JdbcTemplate.class);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from  pro_pro_infor");
        System.out.println(sqlRowSet);
    }

}
