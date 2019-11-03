package com.piaomiao.oa.database.api;

import com.piaomiao.oa.database.mybatis.dialect.IDialect;
import org.springframework.jdbc.core.JdbcTemplate;

public interface IDbType {
    void setJdbcTemplate(JdbcTemplate var1);

    void setDialect(IDialect iDialect);
}
