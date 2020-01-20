package com.piaomiao.oa.entity.grid;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Element;

import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2020/1/20 17:25
*/
public interface GridColEditParseHandler {
    String getPluginName();

    Element parse(Element var1, JSONObject var2, Map<String, Object> var3);
}
