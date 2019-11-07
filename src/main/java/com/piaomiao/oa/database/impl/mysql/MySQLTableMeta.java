package com.piaomiao.oa.database.impl.mysql;

import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import com.piaomiao.oa.database.base.BaseTableMeta;
import com.piaomiao.oa.database.colmap.MySQLColumnMap;
import com.piaomiao.oa.database.model.DefaultTable;
import com.piaomiao.oa.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MySQLTableMeta extends BaseTableMeta
{
    private final String SQL_GET_COLUMNS = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ";
    private final String SQL_GET_COLUMNS_BATCH = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";
    private final String sqlComment = "select table_name,table_comment  from information_schema.tables t where t.table_schema=DATABASE() and table_name='%s' ";
    private final String sqlAllTable = "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE()";

    public Table getTableByName(String tableName)
    {
        Table model = getTableModel(tableName);

        List<Column> columnList = getColumnsByTableName(tableName);
        model.setColumnList(columnList);
        return model;
    }

    public Map<String, String> getTablesByName(String tableName)
    {
        String sql = sqlAllTable;
        if (StringUtils.isNotEmpty(tableName)) {
            sql = sql + " AND TABLE_NAME LIKE '%" + tableName + "%'";
        }
        List list = this.jdbcTemplate.query(sql, new RowMapper()
        {
            public Map<String, String> mapRow(ResultSet rs, int row)
                    throws SQLException
            {
                String tableName = rs.getString("table_name");
                String comments = rs.getString("table_comment");
                Map<String, String> map = new HashMap();
                map.put("name", tableName);
                map.put("comments", comments);
                return map;
            }
        });
        Map<String, String> map = new LinkedHashMap<>();
        for (Object o : list) {
            Map<String, String> tmp = (Map) o;
            String name = tmp.get("name");
            String comments =  tmp.get("comments");
            comments = getComments(comments, name);
            map.put(name, comments);
        }
        return map;
    }

    public Map<String, String> getTablesByName(List<String> names)
    {
        StringBuffer sb = new StringBuffer();
        for (String name : names)
        {
            sb.append("'");
            sb.append(name);
            sb.append("',");
        }
        sb.deleteCharAt(sb.length() - 1);
        String sql = "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE() and  lower(table_name) in (" + sb.toString().toLowerCase() + ")";

        List list = this.jdbcTemplate.query(sql, new RowMapper()
        {
            public Map<String, String> mapRow(ResultSet rs, int row)
                    throws SQLException
            {
                String tableName = rs.getString("table_name");
                String comments = rs.getString("table_comment");
                Map<String, String> map = new HashMap();
                map.put("tableName", tableName);
                map.put("tableComment", comments);
                return map;
            }
        });
        Map<String, String> map = new LinkedHashMap<>();
        for (Object o : list) {
            Map<String, String> tmp = (Map) o;
            String name =  tmp.get("tableName");
            String comments =  tmp.get("tableComment");
            map.put(name, comments);
        }
        return map;
    }

    private Table getTableModel(final String tableName)
    {
        String sql = String.format(sqlComment, tableName);
        System.out.println(this.jdbcTemplate);
        Table table = (Table)this.jdbcTemplate.queryForObject(sql, new RowMapper()
        {
            public Table mapRow(ResultSet rs, int row)
                    throws SQLException
            {
                Table table = new DefaultTable();
                String comments = rs.getString("table_comment");
                comments = MySQLTableMeta.getComments(comments, tableName);
                table.setTableName(tableName);
                table.setComment(comments);
                return table;
            }
        });
        if (BeanUtil.isEmpty(table)) {
            table = new DefaultTable();
        }
        return table;
    }

    private List<Column> getColumnsByTableName(String tableName)
    {
        String sql = String.format("SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ", new Object[] { tableName });

        List<Column> list = this.jdbcTemplate.query(sql, new MySQLColumnMap());
        for (Column column : list) {
           column.setTableName(tableName);
        }
        return list;
    }

    protected Map<String, List<Column>> getColumnsByTableName(List<String> tableNames)
    {
        String sql = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";
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

    public static String getComments(String comments, String defaultValue)
    {
        if (StringUtils.isEmpty(comments)) {
            return defaultValue;
        }
        int idx = comments.indexOf("InnoDB free");
        if (idx > -1) {
            comments = StringUtils.remove(comments.substring(0, idx).trim(), ";");
        }
        if (StringUtils.isEmpty(comments)) {
            comments = defaultValue;
        }
        return comments;
    }

    public List<Table> getTableModelByName(String tableName)
            throws Exception
    {
        String sql = sqlAllTable;
        if (StringUtils.isNotEmpty(tableName)) {
            sql = sql + " AND TABLE_NAME LIKE '%" + tableName + "%'";
        }
        RowMapper<Table> rowMapper = new RowMapper()
        {
            public Table mapRow(ResultSet rs, int row)
                    throws SQLException
            {
                Table table = new DefaultTable();
                table.setTableName(rs.getString("TABLE_NAME"));
                String comments = rs.getString("TABLE_COMMENT");
                comments = MySQLTableMeta.getComments(comments, table.getTableName());
                table.setComment(comments);
                return table;
            }
        };
        List<Table> tables = this.jdbcTemplate.query(sql, rowMapper);

        setComlumns(tables);

        return tables;
    }
}
