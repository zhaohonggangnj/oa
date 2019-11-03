package com.piaomiao.oa.database.api;



import com.piaomiao.oa.database.api.model.Table;

import java.sql.SQLException;
import java.util.List;

public interface IViewOperator extends IDbType {
    void createOrRep(String var1, String var2) throws Exception;

    List<String> getViews(String var1) throws Exception;

    Table getModelByViewName(String var1) throws SQLException;

    List<Table> getViewsByName(String var1) throws Exception;
}
