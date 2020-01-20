package com.piaomiao.oa.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *@author hugo.zhao
 *@since 2020/1/20 16:33
*/
@Setter
@Getter
public class UrlColumn {
    private String field = "";
    private String url = "";
    private String urlType = "";

    public UrlColumn(String field, String url, String urlType) {
        this.field = field;
        this.url = url;
        this.urlType = urlType;
    }
}
