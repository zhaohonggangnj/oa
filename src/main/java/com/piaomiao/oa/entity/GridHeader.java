package com.piaomiao.oa.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;


public class GridHeader {
    private String fieldLabel;
    private String fieldName;
    private String fieldType;
    private int dbFieldType;
    private String format;
    private int length;
    private int precision;
    private String isNull;
    private String renderType;
    private String renderTypeName;
    private String renderConf;
    private JSONObject renderConfObj = new JSONObject();

    public GridHeader() {
    }

    public GridHeader(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFieldLabel() {
        return StringUtils.isEmpty(this.fieldLabel)?this.fieldName:this.fieldLabel;
    }

    public int getDbFieldType() {
        return this.dbFieldType;
    }

    public void setDbFieldType(int dbFieldType) {
        this.dbFieldType = dbFieldType;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrecision() {
        return this.precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getIsNull() {
        return this.isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getDataType() {
        return 12 != this.dbFieldType && 2005 != this.dbFieldType?(-5 != this.dbFieldType && 4 != this.dbFieldType && 8 != this.dbFieldType && 6 != this.dbFieldType?(91 != this.dbFieldType && 93 != this.dbFieldType?"string":"date"):"number"):"string";
    }

    public String getQueryDataType() {
        return 12 != this.dbFieldType && 2005 != this.dbFieldType?(-5 == this.dbFieldType?"L":(91 != this.dbFieldType && 93 != this.dbFieldType?(3 == this.dbFieldType?"BD":(8 == this.dbFieldType?"DB":(6 == this.dbFieldType?"F":"S"))):"D")):"S";
    }

    public String getRenderType() {
     /*   if(StringUtils.isEmpty(this.renderType)) {
            this.renderType = MiniGridColumnType.COMMON.name();
        }*/

        return this.renderType;
    }

    public void setRenderType(String renderType) {
        this.renderType = renderType;
    }

    public String getRenderConf() {
        return this.renderConf;
    }

    public void setRenderConf(String renderConf) {
        this.renderConf = renderConf;
    }

    public String getRenderTypeName() {
        return this.renderTypeName;
    }

    public void setRenderTypeName(String renderTypeName) {
        this.renderTypeName = renderTypeName;
    }

    public JSONObject getRenderConfObj() {
        return this.renderConfObj;
    }

    public void setRenderConfObj(JSONObject renderConfObj) {
        this.renderConfObj = renderConfObj;
    }
}
