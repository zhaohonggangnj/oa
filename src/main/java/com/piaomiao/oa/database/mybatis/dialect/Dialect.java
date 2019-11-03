package com.piaomiao.oa.database.mybatis.dialect;

import org.apache.commons.lang3.StringUtils;


public class Dialect implements IDialect {
    public Dialect() {
    }

    public boolean supportsLimit() {
        return false;
    }

    public boolean supportsLimitOffset() {
        return this.supportsLimit();
    }

    public String getLimitString(String sql, int offset, int limit) {
        return this.getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
    }

    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        throw new UnsupportedOperationException("paged queries not supported");
    }

    public String getCountString(String sql) {
        String orderByPart = this.getOrderByPart(sql);
        if (StringUtils.isNotEmpty(orderByPart)) {
            sql = sql.replace(orderByPart, "");
        }

        return "select count(1) from (" + sql + ") tmp_count";
    }

    String getOrderByPart(String sql) {
        String loweredString = sql.toLowerCase();
        int orderByIndex = loweredString.indexOf("order by");
        return orderByIndex != -1 ? sql.substring(orderByIndex) : "";
    }

/*    public String getSortString(String sql, List<SortParam> orders) {
        if (orders != null && !orders.isEmpty()) {
            StringBuffer buffer = (new StringBuffer("select * from (")).append(sql).append(") temp_order order by ");

            for (SortParam order : orders) {
                if (order != null) {
                    buffer.append(order.toString()).append(", ");
                }
            }

            buffer.delete(buffer.length() - 2, buffer.length());
            return buffer.toString();
        } else {
            return sql;
        }
    }*/
}
