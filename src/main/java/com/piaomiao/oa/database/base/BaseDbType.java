package com.piaomiao.oa.database.base;

import com.piaomiao.oa.database.api.IDbType;
import com.piaomiao.oa.database.mybatis.dialect.Dialect;
import com.piaomiao.oa.database.mybatis.dialect.IDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDbType implements IDbType {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected JdbcTemplate jdbcTemplate;
    protected Dialect dialect;

    public BaseDbType() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDialect(IDialect dialect) {
        this.dialect = (Dialect)dialect;
    }
}
