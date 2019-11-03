package com.piaomiao.oa.database.api.model;

public interface Column {
    String COLUMN_TYPE_VARCHAR = "varchar";
    String COLUMN_TYPE_CLOB = "clob";
    String COLUMN_TYPE_NUMBER = "number";
    String COLUMN_TYPE_INT = "int";
    String COLUMN_TYPE_DATE = "date";

    String getFieldName();

    String getComment();

    boolean getIsPk();

    boolean getIsNull();

    String getColumnType();

    int getCharLen();

    int getIntLen();

    int getDecimalLen();

    String getDefaultValue();

    String getTableName();

    void setFieldName(String var1);

    void setColumnType(String var1);

    void setComment(String var1);

    void setIsNull(boolean var1);

    void setIsPk(boolean var1);

    void setCharLen(int var1);

    void setIntLen(int var1);

    void setDecimalLen(int var1);

    void setDefaultValue(String var1);

    void setTableName(String var1);

    int getIsRequired();

    void setIsRequired(int var1);
}
