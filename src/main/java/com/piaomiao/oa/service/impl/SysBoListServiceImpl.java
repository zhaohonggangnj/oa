package com.piaomiao.oa.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.piaomiao.oa.entity.SysBoList;
import com.piaomiao.oa.entity.UrlColumn;
import com.piaomiao.oa.entity.grid.GridColEditParseHandler;
import com.piaomiao.oa.service.SysBoListService;
import freemarker.template.TemplateException;
import org.jsoup.parser.Tag;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
@Service
public class SysBoListServiceImpl extends BaseServiceImpl<SysBoList> implements SysBoListService {
    @Override
    public String[] genHtmlPage(SysBoList sysBoList, Map<String, Object> params) throws IOException, TemplateException {
        params.put("name",sysBoList.getName());
        params.put("leftNav",sysBoList.getLeftNav());
        params.put("isLeftTree",sysBoList.getIsLeftTree());
        params.put("enableFlow", "YES".equals(sysBoList.getEnableFlow())?"YES":"NO");
        params.put("isDialog", sysBoList.getIsDialog());
        params.put("isShare", sysBoList.getIsShare());
        if(sysBoList.getStartFroCol() != null && sysBoList.getEndFroCol() != null && sysBoList.getEndFroCol() > 0) {
            params.put("showFroCol", "true");
        } else {
            params.put("showFroCol", "false");
        }

        if("tree".equals(sysBoList.getDataStyle())) {
            params.put("controlClass", "mini-treegrid");
        } else {
            params.put("controlClass", "mini-datagrid");
        }
        ArrayList<UrlColumn> urlColumns = new ArrayList<>();
        return new String[0];
    }

    private String getColumnsHtml(String colJsons, List<UrlColumn> urlColumns, Map<String, Object> params) {
        JSONArray columnJsons = JSONArray.parseArray(colJsons);
        Element el = new Element(Tag.valueOf("div"), "");
        el.attr("property", "columns");
        Element indexCol = new Element(Tag.valueOf("div"), "");
        indexCol.attr("type", "indexcolumn");
        el.appendChild(indexCol);
        Element checkCol = new Element(Tag.valueOf("div"), "");
        checkCol.attr("type", "checkcolumn");
        el.appendChild(checkCol);
        this.genGridColumns(columnJsons, el, urlColumns, params);
        return el.toString();
    }

    private Element genGridColumns(JSONArray columnArr, Element el, List<UrlColumn> urlColumns, Map<String, Object> params) {
        for(int i = 0; i < columnArr.size(); ++i) {
            JSONObject jsonObj = columnArr.getJSONObject(i);
            JSONArray children = jsonObj.getJSONArray("children");
            String header = jsonObj.getString("header");
            String allowSort;
            if(children != null && children.size() > 0) {
                Element var24 = new Element(Tag.valueOf("div"), "");
                var24.attr("header", header);
                allowSort = jsonObj.getString("headerAlign");
                if(!StringUtils.isEmpty(allowSort)) {
                    var24.attr("headerAlign", allowSort);
                }

                Element var25 = new Element(Tag.valueOf("div"), "");
                var25.attr("property", "columns");
                this.genGridColumns(children, var25, urlColumns, params);
                var24.appendChild(var25);
                el.appendChild(var24);
            } else {
                String field = jsonObj.getString("field");
                allowSort = jsonObj.getString("allowSort");
                String width = jsonObj.getString("width");
                String headerAlign = jsonObj.getString("headerAlign");
                String format = jsonObj.getString("format");
                String datatype = jsonObj.getString("dataType");
                String url = jsonObj.getString("url");
                String urlType = jsonObj.getString("linkType");
                String control = jsonObj.getString("control");
                String visible = jsonObj.getString("visible");
                if(StringUtils.isEmpty(urlType)) {
                    urlType = "openWindow";
                }

                Element columnEl = new Element(Tag.valueOf("div"), "");
                if(format != null && datatype != null) {
                    if("date".equals(datatype)) {
                        columnEl.attr("dateFormat", format);
                    } else if("float".equals(datatype) || "int".equals(datatype) || "currency".equals(datatype)) {
                        columnEl.attr("numberFormat", format);
                    }

                    columnEl.attr("dataType", datatype);
                }

                if(!StringUtils.isEmpty(allowSort)) {
                    columnEl.attr("allowSort", allowSort);
                }

                if(!StringUtils.isEmpty(header)) {
                    columnEl.attr("header", header);
                }

                if(!StringUtils.isEmpty(width)) {
                    columnEl.attr("width", width);
                }

                if(!StringUtils.isEmpty(field)) {
                    columnEl.attr("field", field);
                    columnEl.attr("name", field);
                    if("mini-buttonedit".equals(control)) {
                        columnEl.attr("displayField", field);
                    }

                    if(!StringUtils.isEmpty(url)) {
                        UrlColumn controlConf = new UrlColumn(field, url, urlType);
                        urlColumns.add(controlConf);
                    }
                }

                if(!StringUtils.isEmpty(headerAlign)) {
                    columnEl.attr("headerAlign", headerAlign);
                }

                el.appendChild(columnEl);
                String var26 = jsonObj.getString("controlConf");
                if(!StringUtils.isEmpty(control) && !StringUtils.isEmpty(var26)) {
                    JSONObject controlConfJson = JSONObject.parseObject(var26);
                    GridColEditParseHandler handler = this.gridColEditParseConfig.getControlParseHandler(control);
                    Element editor = handler.parse(columnEl, controlConfJson, params);
                    if(editor != null) {
                        columnEl.appendChild(editor);
                    }
                }

                if(!StringUtils.isEmpty(visible)) {
                    columnEl.attr("visible", visible.equals("YES")?"true":"false");
                }
            }
        }

        return el;
    }

}
