package com.piaomiao.oa.factory;


import com.piaomiao.oa.database.base.BaseTableMeta;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;


public class TableMetaFactoryBean implements FactoryBean<BaseTableMeta> {
    private BaseTableMeta tableMeta;
    private String dbType = "mysql";
    private JdbcTemplate jdbcTemplate;

    public TableMetaFactoryBean() {

    }
   @Override
    public BaseTableMeta getObject() throws Exception {
        this.tableMeta = DatabaseFactory.getTableMetaByDbType(this.dbType);
        this.tableMeta.setJdbcTemplate(this.jdbcTemplate);
        return this.tableMeta;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Class<?> getObjectType() {
        return BaseTableMeta.class;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
}
