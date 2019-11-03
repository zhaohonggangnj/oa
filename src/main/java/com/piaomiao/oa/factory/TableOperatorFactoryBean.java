package com.piaomiao.oa.factory;

import com.piaomiao.oa.database.api.ITableOperator;
import com.piaomiao.oa.database.mybatis.dialect.Dialect;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class TableOperatorFactoryBean implements FactoryBean<ITableOperator> {
    private ITableOperator tableOperator;
    private String dbType = "mysql";
    private JdbcTemplate jdbcTemplate;
    private Dialect dialect;

    public TableOperatorFactoryBean() {
    }

    public ITableOperator getObject() throws Exception {
        this.tableOperator = DatabaseFactory.getTableOperator(this.dbType);
        this.tableOperator.setJdbcTemplate(this.jdbcTemplate);
        this.tableOperator.setDialect(this.dialect);
        return this.tableOperator;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public Class<?> getObjectType() {
        return ITableOperator.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
