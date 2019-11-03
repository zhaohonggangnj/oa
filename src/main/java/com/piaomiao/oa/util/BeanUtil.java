package com.piaomiao.oa.util;

import java.util.Collection;
import java.util.Map;

public class BeanUtil {
    public static boolean isEmpty(Object o){

        if(o == null) {
            return true;
        } else {
            if(o instanceof String) {
                return ((String) o).trim().length() == 0;
            } else if(o instanceof Collection) {
                return ((Collection) o).size() == 0;
            } else if(o.getClass().isArray()) {
                return ((Object[]) (o)).length == 0;
            } else return o instanceof Map && ((Map) o).size() == 0;

        }

    }


}
