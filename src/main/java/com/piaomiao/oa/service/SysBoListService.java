package com.piaomiao.oa.service;


import com.piaomiao.oa.entity.SysBoList;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface SysBoListService extends BaseService<SysBoList> {
     String[] genHtmlPage(SysBoList sysBoList, Map<String,Object> params) throws IOException, TemplateException;
}
