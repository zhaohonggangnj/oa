package com.piaomiao.oa.database.api;

import com.piaomiao.oa.database.api.model.Table;

import java.util.List;

public interface IDbMeta {
    List<Table> getObjectsByName(String var1) throws Exception;

    Table getByName(String var1);

    Table getModelByName(String var1) throws Exception;
}