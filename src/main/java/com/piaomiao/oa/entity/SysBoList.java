package com.piaomiao.oa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
@Getter
@Setter
public class SysBoList  extends BaseEntity{

    @Id
    @Column(name = "ID_")
    private String id;


    @Column(
            name = "SOL_ID_"
    )
    private String solId;

    @Column(
            name = "NAME_"
    )
    private String name;

    @Column(
            name = "KEY_"
    )
    private String key;

    @Column(
            name = "ID_FIELD_"
    )
    private String idField = "";

    @Column(
            name = "TEXT_FIELD_"
    )
    private String textField = "";

    @Column(
            name = "PARENT_FIELD_"
    )
    private String parentField = "";

    @Column(
            name = "IS_TREE_DLG_"
    )
    private String isTreeDlg;

    @Column(
            name = "ONLY_SEL_LEAF_"
    )
    private String onlySelLeaf;

    @Column(
            name = "URL_"
    )
    private String url;

    @Column(
            name = "MULTI_SELECT_"
    )
    private String multiSelect = "true";

    @Column(
            name = "DESCP_"
    )
    private String descp;

    @Column(
            name = "IS_LEFT_TREE_"
    )
    private String isLeftTree;

    @Column(
            name = "LEFT_NAV_"
    )
    private String leftNav;
 
    @Column(
            name = "LEFT_TREE_JSON_"
    )
    private String leftTreeJson;

    @Column(
            name = "SQL_"
    )
    private String sql;

    @Column(
            name = "USE_COND_SQL_"
    )
    private String useCondSql;

    @Column(
            name = "COND_SQLS_"
    )
    private String condSqls;
 
    @Column(
            name = "DB_AS_"
    )
    private String dbAs;

    @Column(
            name = "COLS_JSON_"
    )
    private String colsJson;

    @Column(
            name = "FIELDS_JSON_"
    )
    private String fieldsJson;
 
    @Column(
            name = "LIST_HTML_"
    )
    private String listHtml;

    @Column(
            name = "SEARCH_JSON_"
    )
    private String searchJson;

    @Column(
            name = "BODY_SCRIPT_"
    )
    private String bodyScript = "";
 
    @Column(
            name = "WIDTH_"
    )
    private Integer width;

    @Column(
            name = "HEIGHT_"
    )
    private Integer height;

    @Column(
            name = "BPM_SOL_ID_"
    )
    private String bpmSolId;

    @Column(
            name = "FORM_ALIAS_"
    )
    private String formAlias = "";

    @Column(
            name = "TOP_BTNS_JSON_"
    )
    private String topBtnsJson;

    @Column(
            name = "DATA_RIGHT_JSON_"
    )
    private String dataRightJson;

    @Column(
            name = "IS_DIALOG_"
    )
    private String isDialog;

    @Column(
            name = "IS_GEN_"
    )
    private String isGen;

    @Column(
            name = "IS_PAGE_"
    )
    private String isPage;

    @Column(
            name = "IS_EXPORT_"
    )
    private String isExport;

    @Column(
            name = "IS_SHARE_"
    )
    private String isShare = "NO";

    @Column(
            name = "TREE_ID_"
    )
    private String treeId;

    @Column(
            name = "DRAW_CELL_SCRIPT_"
    )
    private String drawCellScript = "";

    @Column(
            name = "ENABLE_FLOW_"
    )
    private String enableFlow;

    @Column(
            name = "MOBILE_HTML_"
    )
    private String mobileHtml;

    @Column(
            name = "DATA_STYLE_"
    )
    private String dataStyle = "list";

    @Column(
            name = "ROW_EDIT_"
    )
    private String rowEdit = "";

    @Column(
            name = "START_FRO_COL_"
    )
    private Integer startFroCol = 0;

    @Column(
            name = "END_FRO_COL_"
    )
    private Integer endFroCol = 0;

    @Column(
            name = "SHOW_SUMMARY_ROW_"
    )
    private String showSummaryRow = "NO";






}
