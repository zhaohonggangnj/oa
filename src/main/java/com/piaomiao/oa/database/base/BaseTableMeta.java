package com.piaomiao.oa.database.base;

import com.piaomiao.oa.database.api.ITableMeta;
import com.piaomiao.oa.database.api.model.Column;
import com.piaomiao.oa.database.api.model.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseTableMeta extends BaseDbType implements ITableMeta {
    public BaseTableMeta() {
    }

    protected abstract Map<String, List<Column>> getColumnsByTableName(List<String> var1);

    protected void setComlumns(List<Table> tableModels) {
        ArrayList< String> tableNames = new ArrayList<>();

        for (Table table : tableModels) {
            tableNames.add(table.getTableName());
        }

        Map<String, List<Column>>  tableColumnsMap1 = this.getColumnsByTableName(tableNames);
        for (Map.Entry<String, List<Column>> entry: tableColumnsMap1.entrySet()) {
            for (Table table1 : tableModels) {
                if (table1.getTableName().equalsIgnoreCase(entry.getKey())) {
                    table1.setColumnList(entry.getValue());
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
                ArrayList<Column> cols = new ArrayList<>();
                cols.add(columnModel);
                map.put(tableName, cols);
            }
        }

    }
}
