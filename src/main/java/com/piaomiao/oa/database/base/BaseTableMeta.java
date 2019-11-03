package com.piaomiao.oa.database.base;

import com.piaomiao.oa.database.api.ITableMeta;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseTableMeta extends BaseDbType implements ITableMeta {
    public BaseTableMeta() {
    }

    protected abstract Map<String, List<Column>> getColumnsByTableName(List<String> var1);

    protected void setComlumns(List<Table> tableModels) {
        ArrayList tableNames = new ArrayList();

        for (Table table : tableModels) {
            tableNames.add(table.getTableName());
        }

        Map tableColumnsMap1 = this.getColumnsByTableName(tableNames);
        for (Object o : tableColumnsMap1.entrySet()) {
            Entry entry = (Entry) o;

            for (Table table1 : tableModels) {
                if (table1.getTableName().equalsIgnoreCase((String) entry.getKey())) {
                    table1.setColumnList((List) entry.getValue());
                }
            }
        }

    }

    protected void convertToMap(Map<String, List<Column>> map, List<Column> columnModels) {
        for (Column columnModel : columnModels) {
            String tableName = columnModel.getTableName();
            if (map.containsKey(tableName)) {
                (map.get(tableName)).add(columnModel);
            } else {
                ArrayList cols = new ArrayList();
                cols.add(columnModel);
                map.put(tableName, cols);
            }
        }

    }
}
