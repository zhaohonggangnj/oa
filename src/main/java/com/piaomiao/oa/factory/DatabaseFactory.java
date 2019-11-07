package com.piaomiao.oa.factory;


import com.piaomiao.oa.database.api.ITableOperator;
import com.piaomiao.oa.database.api.IViewOperator;
import com.piaomiao.oa.database.base.BaseTableMeta;
import com.piaomiao.oa.database.base.BaseTableOperator;
import com.piaomiao.oa.database.impl.mysql.MySQLTableMeta;
import com.piaomiao.oa.database.impl.mysql.MySQLTableOperator;
import com.piaomiao.oa.database.impl.mysql.MySQLViewOperator;
import com.piaomiao.oa.database.mybatis.dialect.Dialect;
import com.piaomiao.oa.database.mybatis.dialect.MySQLDialect;
import org.apache.commons.lang3.StringUtils;

public class DatabaseFactory
{
    private static String EXCEPTION_MSG = "没有设置合适的数据库类型";

    static BaseTableOperator getTableOperator(String dbType)
            throws Exception
    {
        BaseTableOperator tableOperator = null;
        if (dbType.equals("oracle")) {

        } else if (dbType.equals("mysql")) {
            tableOperator = new MySQLTableOperator();
        } else if ((dbType.equals("mssql")) || (dbType.equals("mssql2008"))) {

        } else if (dbType.equals("db2")) {

        } else if (dbType.equals("h2")) {

        } else if (dbType.equals("dm")) {

        } else {
            throw new Exception(EXCEPTION_MSG);
        }
        return tableOperator;
    }

    static BaseTableMeta getTableMetaByDbType(String dbType)
            throws Exception
    {
        BaseTableMeta meta = null;
        if (dbType.equals("oracle")) {

        } else if (dbType.equals("mysql")) {
            meta = new MySQLTableMeta();
        } else if (dbType.equals("mssql")) {

        } else if (dbType.equals("mssql2008")) {

        } else if (dbType.equals("db2")) {

        } else if (dbType.equals("h2")) {

        } else if (dbType.equals("dm")) {

        } else {
            throw new Exception(EXCEPTION_MSG);
        }
        return meta;
    }

    public static IViewOperator getViewOperator(String dbType)
            throws Exception
    {
        IViewOperator view = null;
        if (dbType.equals("oracle")) {

        } else if (dbType.equals("mysql")) {
            view = new MySQLViewOperator();
        } else if ((dbType.equals("mssql")) || (dbType.equals("mssql2008"))) {

        } else if (dbType.equals("db2")) {

        } else if (dbType.equals("h2")) {

        } else if (dbType.equals("dm")) {

        } else {
            throw new Exception(EXCEPTION_MSG);
        }
        return view;
    }

    static Dialect getDialect(String dbType)
            throws Exception
    {
        if (StringUtils.isEmpty(dbType)) {
            throw new Exception(EXCEPTION_MSG);
        }
        Dialect dialect = null;
        if (dbType.equals("oracle")) {

        } else if (dbType.equals("mysql")) {
            dialect = new MySQLDialect();
        } else if ((dbType.equals("mssql")) || (dbType.equals("mssql2008"))) {

        } else if (dbType.equals("db2")) {

        } else if (dbType.equals("h2")) {

        } else if (!dbType.equals("dm")) {
            throw new Exception(EXCEPTION_MSG);
        }
        return dialect;
    }
}
