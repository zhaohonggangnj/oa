package com.piaomiao.oa.bean.configuration;

import com.piaomiao.oa.bean.AppBeanUtil;
import com.piaomiao.oa.database.base.BaseTableMeta;
import com.piaomiao.oa.database.base.BaseTableOperator;
import com.piaomiao.oa.factory.TableMetaFactoryBean;
import com.piaomiao.oa.factory.TableOperatorFactoryBean;
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
    public TableOperatorFactoryBean tableOperatorFactoryBean(){
        TableOperatorFactoryBean tableOperatorFactoryBean = new TableOperatorFactoryBean();
        tableOperatorFactoryBean.setDbType("mysql");
        tableOperatorFactoryBean.setJdbcTemplate(AppBeanUtil.getBean(JdbcTemplate.class));
        return  tableOperatorFactoryBean;
    }


    @Bean
    public BaseTableMeta factoryTableMetaBean() throws Exception{
        return  tableMetaFactoryBean().getObject();

    }

    @Bean
    public BaseTableOperator factoryTableOperatorBean() throws Exception{
        return tableOperatorFactoryBean().getObject();

    }

}
