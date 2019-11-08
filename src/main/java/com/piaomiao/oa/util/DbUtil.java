package com.piaomiao.oa.util;

import com.piaomiao.oa.bean.AppBeanUtil;
import com.piaomiao.oa.database.api.ITableMeta;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import com.piaomiao.oa.database.impl.mysql.MySQLTableMeta;
import com.piaomiao.oa.entity.GridHeader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbUtil {

    public static List<GridHeader> getGridHeaders(String sql)
    {

        JdbcTemplate jdbcTemplate = AppBeanUtil.getBean(JdbcTemplate.class);
        ITableMeta iTableMeta = AppBeanUtil.getBean(MySQLTableMeta.class);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
        ArrayList<GridHeader> headers = new ArrayList<>();
        SqlRowSetMetaData sqlRowSetMetaData = sqlRowSet.getMetaData();
        int columnCount = sqlRowSetMetaData.getColumnCount();
        sql = sql.toUpperCase();
        int indexOfFrom = sql.indexOf("FROM");
        HashMap<String, Column> hashMap = new HashMap<>();
        String tableName;
        if(indexOfFrom>0){
            String tableNameStart = sql.toLowerCase().substring(indexOfFrom+5).trim();
            int indexOfTable = tableNameStart.indexOf(" ");
            if(indexOfTable>0){
                tableName = tableNameStart.substring(0,indexOfTable).trim();
                System.out.println(tableName);
            }else {
                tableName = tableNameStart;
            }
            Table table = iTableMeta.getTableByName(tableName);
            List<Column> columns = table.getColumnList();
            for (Column column:columns){
                hashMap.put(column.getFieldName(),column);
            }
        }
        for(int i=1;i<columnCount;++i){
            String columnLabel=sqlRowSetMetaData.getColumnLabel(i);
            tableName =  sqlRowSetMetaData.getColumnTypeName(i);
            GridHeader gridHeader = new GridHeader();
            gridHeader.setFieldName(columnLabel);
            gridHeader.setFieldType((tableName));
            gridHeader.setLength(sqlRowSetMetaData.getColumnDisplaySize(i));
            gridHeader.setPrecision(sqlRowSetMetaData.getPrecision(i));
            Column column =  hashMap.get(columnLabel);
            if(column!=null){
                gridHeader.setFieldLabel(column.getComment());
            }
            gridHeader.setDbFieldType(sqlRowSetMetaData.getColumnType(i));

            headers.add(gridHeader);
        }

        return headers;

    }


}
