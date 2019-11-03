package com.piaomiao.oa.bean.configuration;

import com.piaomiao.oa.bean.AppBeanUtil;
import com.piaomiao.oa.database.base.BaseTableMeta;
import com.piaomiao.oa.factory.TableMetaFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class TableMetaFactoryBeanConfig {
    @Bean
    public TableMetaFactoryBean tableMetaFactoryBean(){
        TableMetaFactoryBean tableMetaFactoryBean = new TableMetaFactoryBean();
        tableMetaFactoryBean.setDbType("mysql");
        tableMetaFactoryBean.setJdbcTemplate(AppBeanUtil.getBean(JdbcTemplate.class));
       return tableMetaFactoryBean;
    }

    @Bean
    public BaseTableMeta factoryBean() throws Exception{
        return  tableMetaFactoryBean().getObject();

    }

}
