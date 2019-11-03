package com.piaomiao.oa.database.impl.mysql;

import com.piaomiao.oa.database.api.IViewOperator;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import com.piaomiao.oa.database.base.BaseViewOperator;
import com.piaomiao.oa.database.colmap.MySQLColumnMap;
import com.piaomiao.oa.database.model.DefaultTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLViewOperator extends BaseViewOperator implements IViewOperator
{
    private static final String sqlAllView = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
    private static final String SQL_GET_COLUMNS_BATCH = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";

    public void createOrRep(String viewName, String sql)
            throws Exception
    {
        String getSql = "CREATE OR REPLACE VIEW " + viewName + " as (" + sql + ")";
        this.jdbcTemplate.execute(getSql);
    }

    public List<String> getViews(String viewName)
            throws SQLException
    {
        String sql = "show table status where comment='view'";
        if (StringUtils.isNotEmpty(viewName)) {
            sql = sql + " AND NAME LIKE '" + viewName + "%'";
        }
        List<String> list = new ArrayList();
        List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql);
        for (Map<String, Object> line : results) {
            list.add(line.get("Name").toString());
        }
        return list;
    }

    public List<Table> getViewsByName(String viewName)
            throws Exception
    {
        String sql = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
        if (StringUtils.isNotEmpty(viewName)) {
            sql = sql + " AND TABLE_NAME LIKE '" + viewName + "%'";
        }
        RowMapper<Table> rowMapper = new RowMapper()
        {
            public Table mapRow(ResultSet rs, int row)
                    throws SQLException
            {
                Table table = new DefaultTable();
                table.setTableName(rs.getString("table_name"));
                table.setComment(table.getTableName());
                return table;
            }
        };
        List<Table> tableModels = this.jdbcTemplate.query(sql, rowMapper);

        setComlumns(tableModels);

        return tableModels;
    }

    protected Map<String, List<Column>> getColumnsByTableName(List<String> tableNames)
    {
        String sql = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";
        Map<String, List<Column>> map = new HashMap();
        if ((tableNames != null) && (tableNames.size() == 0)) {
            return map;
        }
        StringBuffer buf = new StringBuffer();
        for (String str : tableNames) {
            buf.append("'" + str + "',");
        }
        buf.deleteCharAt(buf.length() - 1);
        sql = sql + " AND TABLE_NAME IN (" + buf.toString() + ") ";

        Object columns = this.jdbcTemplate.query(sql, new MySQLColumnMap());

        convertToMap(map, (List)columns);

        return map;
    }

    public String getType(String type)
    {
        type = type.toLowerCase();
        if (type.indexOf("number") > -1) {
            return "number";
        }
        if (type.indexOf("date") > -1) {
            return "date";
        }
        if (type.indexOf("char") > -1) {
            return "varchar";
        }
        return "varchar";
    }
}
