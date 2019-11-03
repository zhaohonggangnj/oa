package com.piaomiao.oa.database.colmap;

import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.impl.mysql.MySQLTableMeta;
import com.piaomiao.oa.database.model.DefaultColumn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLColumnMap implements RowMapper<Column> {
    public MySQLColumnMap() {
    }

    public Column mapRow(ResultSet rs, int row) throws SQLException {
        DefaultColumn column = new DefaultColumn();
        String name = rs.getString("column_name");
        String is_nullable = rs.getString("is_nullable");
        String data_type = rs.getString("data_type");
        String length = rs.getString("length");
        String precisions = rs.getString("precisions");
        String scale = rs.getString("scale");
        String column_key = rs.getString("column_key");
        String column_comment = rs.getString("column_comment");
        String table_name = rs.getString("table_name");
        column_comment = MySQLTableMeta.getComments(column_comment, name);
        int iLength = 0;

        try {
            iLength = StringUtils.isEmpty(length)?0:Integer.parseInt(length);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        int iPrecisions = StringUtils.isEmpty(precisions)?0:Integer.parseInt(precisions);
        int iScale = StringUtils.isEmpty(scale)?0:Integer.parseInt(scale);
        column.setFieldName(name);
        column.setTableName(table_name);
        column.setComment(column_comment);
        if(StringUtils.isNotEmpty(column_key) && "PRI".equals(column_key)) {
            column.setIsPk(true);
        }

        boolean isNull = is_nullable.equals("YES");
        column.setIsNull(isNull);
        this.setType(data_type, iLength, iPrecisions, iScale, column);
        return column;
    }

    private void setType(String dbtype, int length, int precision, int scale, Column columnModel) {
        if(dbtype.equals("bigint")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(19);
            columnModel.setDecimalLen(0);
        } else if(dbtype.equals("int")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(10);
            columnModel.setDecimalLen(0);
        } else if(dbtype.equals("mediumint")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(7);
            columnModel.setDecimalLen(0);
        } else if(dbtype.equals("smallint")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(5);
            columnModel.setDecimalLen(0);
        } else if(dbtype.equals("tinyint")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(3);
            columnModel.setDecimalLen(0);
        } else if(dbtype.equals("decimal")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(precision - scale);
            columnModel.setDecimalLen(scale);
        } else if(dbtype.equals("double")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(18);
            columnModel.setDecimalLen(4);
        } else if(dbtype.equals("float")) {
            columnModel.setColumnType("number");
            columnModel.setIntLen(8);
            columnModel.setDecimalLen(4);
        } else if(dbtype.equals("varchar")) {
            columnModel.setColumnType("varchar");
            columnModel.setCharLen(length);
        } else if(dbtype.equals("char")) {
            columnModel.setColumnType("varchar");
            columnModel.setCharLen(length);
        } else if(dbtype.startsWith("date")) {
            columnModel.setColumnType("date");
        } else if(dbtype.startsWith("time")) {
            columnModel.setColumnType("date");
        } else if(dbtype.endsWith("text")) {
            columnModel.setColumnType("clob");
            columnModel.setCharLen('\uffff');
        } else if(dbtype.endsWith("blob")) {
            columnModel.setColumnType("clob");
            columnModel.setCharLen('\uffff');
        } else if(dbtype.endsWith("clob")) {
            columnModel.setColumnType("clob");
            columnModel.setCharLen('\uffff');
        }
    }
}
