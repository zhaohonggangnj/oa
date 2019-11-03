package com.piaomiao.oa.database.api.model;

import java.util.List;

public interface Table {
    String CUSTOM_TABLE_PRE = "W_";
    String CUSTOM_COLUMN_PRE = "F_";
    String CUSTOM_COLUMN_SUFFIX = "_";
    String TYPE_TABLE = "table";
    String TYPE_VIEW = "view";

    String getTableName();

    String getEntName();

    String getType();

    void setEntName(String var1);

    String getComment();

    List<Column> getColumnList();

    List<Column> getPrimayKey();

    void setTableName(String var1);

    void setComment(String var1);

    void setColumnList(List<Column> var1);

    void addColumn(Column var1);

    boolean isMain();

    void setMain(boolean var1);

    void setType(String var1);
}
