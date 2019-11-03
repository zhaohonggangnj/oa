package com.piaomiao.oa.database.base;
import com.piaomiao.oa.database.api.IViewOperator;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import com.piaomiao.oa.database.model.DefaultColumn;
import com.piaomiao.oa.database.model.DefaultTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseViewOperator extends BaseDbType implements IViewOperator {
    public BaseViewOperator() {
    }

    public abstract String getType(String var1);

    public Table getModelByViewName(String viewName) throws SQLException {
        Connection conn = this.jdbcTemplate.getDataSource().getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        DefaultTable table = new DefaultTable();
        table.setTableName(viewName);
        table.setComment(viewName);

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from " + viewName);
            ResultSetMetaData e = rs.getMetaData();
            int count = e.getColumnCount();

            for(int i = 1; i <= count; ++i) {
                DefaultColumn column = new DefaultColumn();
                String columnName = e.getColumnName(i);
                String typeName = e.getColumnTypeName(i);
                String dataType = this.getType(typeName);
                column.setFieldName(columnName);
                column.setColumnType(dataType);
                column.setComment(columnName);
                table.addColumn(column);
            }
        } catch (SQLException var21) {
            var21.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }

                if(stmt != null) {
                    stmt.close();
                }

                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException var20) {
                var20.printStackTrace();
            }

        }

        return table;
    }

    protected abstract Map<String, List<Column>> getColumnsByTableName(List<String> var1);

    protected void setComlumns(List<Table> tableModels) {
        ArrayList tableNames = new ArrayList();
        Iterator tableColumnsMap = tableModels.iterator();

        while(tableColumnsMap.hasNext()) {
            Table table = (Table)tableColumnsMap.next();
            tableNames.add(table.getTableName());
        }

        Map tableColumnsMap1 = this.getColumnsByTableName(tableNames);
        Iterator table2 = tableColumnsMap1.entrySet().iterator();

        while(table2.hasNext()) {
            Entry entry = (Entry)table2.next();
            Iterator var6 = tableModels.iterator();

            while(var6.hasNext()) {
                Table table1 = (Table)var6.next();
                if(table1.getTableName().equalsIgnoreCase((String)entry.getKey())) {
                    table1.setColumnList((List)entry.getValue());
                }
            }
        }

    }

    protected void convertToMap(Map<String, List<Column>> map, List<Column> columnModels) {
        Iterator var3 = columnModels.iterator();

        while(var3.hasNext()) {
            Column columnModel = (Column)var3.next();
            String tableName = columnModel.getTableName();
            if(map.containsKey(tableName)) {
                ((List)map.get(tableName)).add(columnModel);
            } else {
                ArrayList cols = new ArrayList();
                cols.add(columnModel);
                map.put(tableName, cols);
            }
        }

    }
}