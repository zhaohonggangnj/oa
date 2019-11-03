package com.piaomiao.oa.database.model;

import com.piaomiao.oa.database.api.model.Column;
import org.apache.commons.lang3.StringUtils;

public class DefaultColumn implements Column {
    private String name = "";
    private String comment = "";
    private boolean isPk = false;
    private boolean isFk = false;
    private boolean isNull = true;
    private String columnType;
    private int charLen = 0;
    private int decimalLen = 0;
    private int intLen = 0;
    private String fkRefTable = "";
    private String fkRefColumn = "";
    private String defaultValue = "";
    private String tableName = "";
    private int isRequired = 0;
    private String label;
    private int index;

    public DefaultColumn() {
    }

    public String getFieldName() {
        return this.name;
    }

    public void setFieldName(String name) {
        this.name = name;
    }

    public String getComment() {
        if(StringUtils.isNotEmpty(this.comment)) {
            this.comment = this.comment.replace("\'", "\'\'");
        }

        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getIsPk() {
        return this.isPk;
    }

    public void setIsPk(boolean isPk) {
        this.isPk = isPk;
    }

    public boolean getIsNull() {
        return this.isNull;
    }

    public void setIsNull(boolean isNull) {
        this.isNull = isNull;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getCharLen() {
        return this.charLen;
    }

    public void setCharLen(int charLen) {
        this.charLen = charLen;
    }

    public int getDecimalLen() {
        return this.decimalLen;
    }

    public void setDecimalLen(int decimalLen) {
        this.decimalLen = decimalLen;
    }

    public int getIntLen() {
        return this.intLen;
    }

    public void setIntLen(int intLen) {
        this.intLen = intLen;
    }

    public boolean getIsFk() {
        return this.isFk;
    }

    public void setIsFk(boolean isFk) {
        this.isFk = isFk;
    }

    public String getFkRefTable() {
        return this.fkRefTable;
    }

    public void setFkRefTable(String fkRefTable) {
        this.fkRefTable = fkRefTable;
    }

    public String getFkRefColumn() {
        return this.fkRefColumn;
    }

    public void setFkRefColumn(String fkRefColumn) {
        this.fkRefColumn = fkRefColumn;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIsRequired() {
        return this.isRequired;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }
}
