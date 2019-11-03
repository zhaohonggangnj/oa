package com.piaomiao.oa.database.datasource;

import org.apache.commons.lang3.StringUtils;

public class DbContextHolder {
    private static  final ThreadLocal<String> contextHolderAlias = new ThreadLocal<>();

    private static final ThreadLocal<String> contextHolderDbType = new ThreadLocal<>();

    public DbContextHolder() {
    }

    public static void setDataSource(String dbAlias, String dbType) {
        contextHolderAlias.set(dbAlias);
        contextHolderDbType.set(dbType);
    }


    public static void setDataSource(String dbAlias){
        if(!StringUtils.isEmpty(dbAlias) && !"LOCAL".equals(dbAlias)) {
            String dbType = "mysql";
            contextHolderAlias.set(dbAlias);
            contextHolderDbType.set(dbType);
        } else {
            setDefaultDataSource();
        }
    }

    private static void setDefaultDataSource() {
        String dbType = "mysql";
        contextHolderAlias.set("dataSource_Default");
        contextHolderDbType.set(dbType);
    }

    public static String getDataSource() {
        return contextHolderAlias.get();
    }

    public static String getDbType() {
        String dbType = "mysql";
        String str = contextHolderDbType.get();
        return StringUtils.isEmpty(str)?dbType:str;
    }

    public static void clearDataSource() {
        contextHolderAlias.remove();
        contextHolderDbType.remove();
    }


}
