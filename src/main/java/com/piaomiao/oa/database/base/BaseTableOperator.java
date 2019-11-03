package com.piaomiao.oa.database.base;


import com.piaomiao.oa.database.api.ITableOperator;

public abstract class BaseTableOperator extends BaseDbType implements ITableOperator {
    public BaseTableOperator() {
    }

    public boolean hasData(String tableName) {
         String sql = "select count(*) from " + tableName;
         int rtn = this.jdbcTemplate.queryForObject(sql,Integer.class);
        return rtn > 0;
    }

    protected String replaceLineThrough(String partition) {
        return partition.toUpperCase().replaceAll("-", "");
    }

    public void createIndex(String name, String table, String colName) {
        String sql = "CREATE INDEX idx_" + name + " ON " + table + " (" + colName + ")";
        this.jdbcTemplate.execute(sql);
    }

    public void dropColumn(String tableName, String column) {
        String sql = "ALTER TABLE " + tableName + "   DROP COLUMN\t" + column;
        this.jdbcTemplate.execute(sql);
    }
}
