

package com.piaomiao.oa.database.impl.mysql;

import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import com.piaomiao.oa.database.base.BaseTableOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySQLTableOperator extends BaseTableOperator {
    public MySQLTableOperator() {
    }

    public void createTable(Table table) throws SQLException {
        List columnList = table.getColumnList();
        StringBuffer sb = new StringBuffer();
        String pkColumn = null;
        sb.append("CREATE TABLE " + table.getTableName() + " (\n");

        for(int i = 0; i < columnList.size(); ++i) {
            Column cm = (Column)columnList.get(i);
            sb.append(cm.getFieldName()).append(" ");
            sb.append(this.getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
            sb.append(cm.getIsRequired() == 1?" NOT NULL ":" ");
            sb.append(" ");
            String defaultValue = cm.getDefaultValue();
            if(StringUtils.isNotEmpty(defaultValue)) {
                if(!"number".equals(cm.getColumnType()) && !"int".equals(cm.getColumnType())) {
                    sb.append(" default \'" + defaultValue + "\' ");
                } else {
                    sb.append(" default " + defaultValue);
                }
            }

            if(cm.getIsPk()) {
                if(pkColumn == null) {
                    pkColumn = cm.getFieldName();
                } else {
                    pkColumn = pkColumn + "," + cm.getFieldName();
                }
            }

            if(cm.getComment() != null && cm.getComment().length() > 0) {
                sb.append(" COMMENT \'" + cm.getComment() + "\'");
            }

            sb.append(",\n");
        }

        if(pkColumn != null) {
            sb.append(" PRIMARY KEY (" + pkColumn + ")");
        }

        sb.append("\n)");
        if(table.getComment() != null && table.getComment().length() > 0) {
            sb.append(" COMMENT=\'" + table.getComment() + "\'");
        }

        sb.append(";");
        this.jdbcTemplate.execute(sb.toString());
    }

    public String getColumnType(Column column) {
        return this.getColumnType(column.getColumnType(), column.getCharLen(), column.getIntLen(), column.getDecimalLen());
    }

    public String getColumnType(String columnType, int charLen, int intLen, int decimalLen) {
        return "varchar".equals(columnType)?"VARCHAR(" + charLen + ')':("number".equals(columnType)?"DECIMAL(" + (intLen + decimalLen) + "," + decimalLen + ")":("date".equals(columnType)?"datetime NULL":("int".equals(columnType)?"BIGINT(" + intLen + ")":("clob".equals(columnType)?"TEXT":""))));
    }

    public void dropTable(String tableName) throws SQLException {
        if(this.isTableExist(tableName)) {
            String sql = "drop table " + tableName;
            this.jdbcTemplate.execute(sql);
        }
    }

    public void updateTableComment(String tableName, String comment) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("ALTER TABLE ").append(tableName).append(" COMMENT \'").append(comment).append("\';\n");
        this.jdbcTemplate.execute(sb.toString());
    }

    public void addColumn(String tableName, Column model) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("ALTER TABLE ").append(tableName);
        sb.append(" ADD (");
        sb.append(model.getFieldName()).append(" ");
        sb.append(this.getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
        if(model.getIsRequired() == 1) {
            sb.append(" NOT NULL ");
        }

        if(model.getComment() != null && model.getComment().length() > 0) {
            sb.append(" COMMENT \'" + model.getComment() + "\'");
        }

        sb.append(");");
        this.jdbcTemplate.execute(sb.toString());
    }

    public void updateColumn(String tableName, String columnName, Column column) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("ALTER TABLE ").append(tableName).append(" CHANGE " + columnName + " " + column.getFieldName()).append(" ").append(this.getColumnType(column.getColumnType(), column.getCharLen(), column.getIntLen(), column.getDecimalLen()));
        if(!column.getIsNull()) {
            sb.append(" NOT NULL ");
        }

        if(column.getComment() != null && column.getComment().length() > 0) {
            sb.append(" COMMENT \'" + column.getComment() + "\'");
        }

        sb.append(";");
        this.jdbcTemplate.execute(sb.toString());
    }

    public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField) {
    /*    String shortTableName = fkTableName.replaceFirst("(?im)" + DbUtil.getTablePre(), "");
        String sql = "ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_" + shortTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
        this.jdbcTemplate.execute(sql);*/
    }

    public void dropForeignKey(String tableName, String keyName) {
        String sql = "ALTER TABLE " + tableName + " DROP FOREIGN KEY " + keyName;
        this.jdbcTemplate.execute(sql);
    }

    public List<String> getPKColumns(String tableName) {
        String schema = this.getSchema();
        String sql = "SELECT k.column_name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING(constraint_name,table_schema,table_name) WHERE t.constraint_type=\'PRIMARY KEY\' AND t.table_schema=\'" + schema + "\' " + "AND t.table_name=\'" + tableName + "\'";
        List columns = this.jdbcTemplate.query(sql, new RowMapper() {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String column = rs.getString(1);
                return column;
            }
        });
        return columns;
    }

    public Map<String, List<String>> getPKColumns(List<String> tableNames) throws SQLException {
        StringBuffer sb = new StringBuffer();
        Iterator schema = tableNames.iterator();

        String sql;
        while(schema.hasNext()) {
            sql = (String)schema.next();
            sb.append("\'");
            sb.append(sql);
            sb.append("\',");
        }

        sb.deleteCharAt(sb.length() - 1);
        String schema1 = this.getSchema();
        sql = "SELECT t.table_name,k.column_name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING(constraint_name,table_schema,table_name) WHERE t.constraint_type=\'PRIMARY KEY\' AND t.table_schema=\'" + schema1 + "\' " + "AND t.table_name in (" + sb.toString().toUpperCase() + ")";
        HashMap columnsMap = new HashMap();
        List maps = this.jdbcTemplate.query(sql, new RowMapper() {
            public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                String table = rs.getString(1);
                String column = rs.getString(2);
                HashMap map = new HashMap();
                map.put("name", table);
                map.put("column", column);
                return map;
            }
        });
        Iterator var7 = maps.iterator();

        while(var7.hasNext()) {
            Map map = (Map)var7.next();
            if(columnsMap.containsKey(map.get("name"))) {
                ((List)columnsMap.get(map.get("name"))).add(map.get("column"));
            } else {
                ArrayList cols = new ArrayList();
                cols.add(map.get("column"));
                columnsMap.put(map.get("name"), cols);
            }
        }

        return columnsMap;
    }

    public boolean isTableExist(String tableName) {
        String schema = this.getSchema();
        String sql = "select count(1) from information_schema.TABLES t where t.TABLE_SCHEMA=\'" + schema + "\' and table_name =\'" + tableName.toUpperCase() + "\'";
        boolean rtn = ((Integer)this.jdbcTemplate.queryForObject(sql.toString(), Integer.class)).intValue() > 0;
        return rtn;
    }

    private String getSchema() {
        String schema = null;

        try {
            Connection e = this.jdbcTemplate.getDataSource().getConnection();
            schema = e.getCatalog();
            e.close();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

        return schema;
    }

    public boolean isExsitPartition(String tableName, String partition) {
        partition = this.replaceLineThrough(partition);
        String sql = "select count(*) from information_schema.partitions  where table_schema = schema() and table_name=? and partition_name =?";
        String[] args = new String[]{tableName, "P_" + partition};
        Integer rtn = (Integer)this.jdbcTemplate.queryForObject(sql, args, Integer.class);
        return rtn.intValue() > 0;
    }

    public void createPartition(String tableName, String partition) {
        partition = this.replaceLineThrough(partition);
        String sql = "alter table " + tableName + " add partition (partition P_" + partition + " values in (\'" + partition + "\')) ";
        this.jdbcTemplate.update(sql);
    }

    public boolean supportPartition(String tableName) {
        String sql = "select count(*) from information_schema.partitions  where table_schema = schema() and table_name=? ";
        Integer rtn = (Integer)this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{tableName});
        return rtn.intValue() > 0;
    }
}
