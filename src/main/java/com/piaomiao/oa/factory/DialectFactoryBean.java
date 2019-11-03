package com.piaomiao.oa.factory;


import com.piaomiao.oa.database.mybatis.dialect.Dialect;
import org.springframework.beans.factory.FactoryBean;

public class DialectFactoryBean implements FactoryBean<Dialect> {
    private Dialect dialect;
    private String dbType = "mysql";

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public Dialect getObject() throws Exception {
        this.dialect = DatabaseFactory.getDialect((String)this.dbType);
        return this.dialect;
    }

    public Class<?> getObjectType() {
        return Dialect.class;
    }

    public boolean isSingleton() {
        return true;
    }
}

