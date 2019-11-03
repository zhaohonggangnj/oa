package com.piaomiao.oa.database.model;



import com.alibaba.fastjson.JSONObject;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DefaultTable implements Table {
    private String name = "";
    private String comment = "";
    private String entName = "";
    private String type = "";
    private List<Column> columnList = new ArrayList<>();
    private boolean isMain = false;

    public DefaultTable() {
    }

    public String getTableName() {
        return this.name;
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

    public void addColumn(Column model) {
        this.columnList.add(model);
    }

    public List<Column> getColumnList() {
        return this.columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public List<Column> getPrimayKey() {
        ArrayList pks = new ArrayList();

        for (Column column : this.columnList) {
            if (column.getIsPk()) {
                pks.add(column);
            }
        }

        return pks;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public void setTableName(String name) {
        this.name = name;
    }

    public boolean isMain() {
        return this.isMain;
    }

    public void setMain(boolean isMain) {
        this.isMain = isMain;
    }

    public static void main(String[] args) {
        DefaultTable table = new DefaultTable();
        table.setComment("订单表");
        table.setTableName("T_ORDER");
        DefaultColumn column = new DefaultColumn();
        column.setIsPk(true);
        column.setCharLen(64);
        column.setColumnType("varchar");
        column.setFieldName("id");
        column.setComment("主键");
        DefaultColumn column1 = new DefaultColumn();
        column1.setColumnType("varchar");
        column1.setCharLen(64);
        column1.setFieldName("name");
        column1.setComment("名称");
        DefaultColumn column2 = new DefaultColumn();
        column2.setColumnType("date");
        column2.setFieldName("createTime");
        column2.setComment("创建时间");
        DefaultColumn column3 = new DefaultColumn();
        column3.setColumnType("number");
        column3.setFieldName("createTime");
        column3.setComment("创建时间");
        column3.setIntLen(10);
        column3.setDecimalLen(2);
        table.addColumn(column);
        table.addColumn(column1);
        table.addColumn(column2);
        table.addColumn(column3);
        System.out.println(table);
    }

    public String getEntName() {
        return this.entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
