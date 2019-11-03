package com.piaomiao.oa.database.mybatis.dialect;

import java.util.List;

public interface IDialect {
    boolean supportsLimit();

    boolean supportsLimitOffset();

    String getLimitString(String var1, int var2, int var3);

    String getLimitString(String var1, int var2, String var3, int var4, String var5);

    String getCountString(String var1);

   // String getSortString(String var1, List<SortParam> var2);
}

