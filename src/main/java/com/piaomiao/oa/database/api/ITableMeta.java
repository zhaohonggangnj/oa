package com.piaomiao.oa.database.api;

import com.piaomiao.oa.database.api.model.Table;

import java.util.List;
import java.util.Map;

public interface ITableMeta {
    Table getTableByName(String var1);

    Map<String, String> getTablesByName(String var1);

    List<Table> getTableModelByName(String var1) throws Exception;

    Map<String, String> getTablesByName(List<String> var1);
}
