package com.piaomiao.oa.database.api;


import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITableOperator extends IDbType {
    String getColumnType(String var1, int var2, int var3, int var4);

    String getColumnType(Column var1);

    void createTable(Table var1) throws SQLException;

    void dropTable(String var1) throws SQLException;

    void updateTableComment(String var1, String var2) throws SQLException;

    void addColumn(String var1, Column var2) throws SQLException;

    void updateColumn(String var1, String var2, Column var3) throws SQLException;

    void addForeignKey(String var1, String var2, String var3, String var4);

    void dropForeignKey(String var1, String var2);

    List<String> getPKColumns(String var1) throws SQLException;

    Map<String, List<String>> getPKColumns(List<String> var1) throws SQLException;

    boolean isTableExist(String var1);

    boolean isExsitPartition(String var1, String var2);

    void createPartition(String var1, String var2);

    boolean supportPartition(String var1);

    boolean hasData(String var1);

    void createIndex(String var1, String var2, String var3);

    void dropColumn(String var1, String var2);
}
