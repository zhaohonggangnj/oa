<!doctype html>
<html lang="en">
<#include "/commons/header.ftl">
<body>
  <div id="toolbar1" class="mini-toolbar">
      <a class="mini-button" <#--iconCls="icon-prev"-->  plain="true" onclick="onPre">上一步</a>
      <a class="mini-button" iconCls="icon-save"  plain="true" onclick="onSaveConfigJson">保存</a>
      <a class="mini-button" <#--iconCls="icon-start"-->  plain="true" onclick="onGenHtml">生成页面</a>
      <a class="mini-button" iconCls="icon-edit"  plain="true" onclick="onEditHtml">编辑页面代码</a>
  </div>
  <div>
      <div class="mini-tabs">
          <div id="layout1" class="mini-layout" style="width:100%;height:100%;" borderStyle="border:none">
              <div
                      title="表头字段设置"
                      region="center"
                      showSplitIcon="true"
                      bodyStyle="border:none;background-color:#f7f7f7;"
                      showCollapseButton="false"
                      showHeader="true"
              >
                  <div class="titleBar mini-toolbar" >
                      <ul>
                          <li>
                                        <span class="starBox">
                                            主键字段<span class="star">*</span>
                                        </span>
                              <input
                                      id="idField"
                                      name="idField"
                                      class="mini-combobox"
                                      allowInput="true"
                                      textField="header"
                                      valueField="field"
                                      required="true"
                                      value="${sysBoList.idField}"
                                      data="fieldDatas"
                                      showNullItem="true"

                              />
                          </li>
                          <li>
                              树节点字段
                              <input
                                      id="textField"
                                      name="textField"
                                      class="mini-combobox"
                                      allowInput="true"
                                      textField="header"
                                      valueField="field"
                                      value="${sysBoList.textField}"
                                      data="fieldDatas"
                                      showNullItem="true"
                              />
                          </li>
                          <li>
                              父Id字段
                              <input
                                      id="parentField"
                                      name="parentField"
                                      class="mini-combobox"
                                      allowInput="true"
                                      textField="header"
                                      valueField="field"
                                      value="${sysBoList.parentField}"
                                      data="fieldDatas"
                                      showNullItem="true"
                              />
                          </li>
                          <li>
                              锁定列第
                              <input
                                      id="startFroCol"
                                      name="startFroCol"
                                      class="mini-spinner"
                                      minValue="0"
                                      value="${sysBoList.startFroCol}"
                                      maxValue="300"
                                      style="width:50px"
                              >
                              列至
                              <input
                                      id="endFroCol"
                                      name="endFroCol"
                                      class="mini-spinner"
                                      minValue="0"
                                      value="${sysBoList.endFroCol}"
                                      maxValue="300"
                                      style="width:50px"
                              >
                              列
                          </li>
                          <li class="clearfix"></li>
                      </ul>
                      <ul class="toolBtnBox">
                          <li>
                              <a class="mini-button" iconCls="icon-save" plain="true" onclick="reloadColumns">重新合并表头</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-add" plain="true" onclick="addRow('headerGrid')">添加</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeRow('headerGrid')">删除</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-up" plain="true" onclick="upRow('headerGrid')">向上</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-down" plain="true" onclick="downRow('headerGrid')">向下</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-left" plain="true" onclick="topRow('headerGrid')">上升一级</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-right" plain="true" onclick="subRow('headerGrid')">下降一级</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-expand" plain="true" onclick="expand();">展开</a>
                          </li>
                          <li>
                              <a class="mini-button" iconCls="icon-collapse" plain="true" onclick="collapse();">收起</a>
                          </li>
                        <#if sysBoList.rowEdit == "YES">
                              <li>
                                  <a class="mini-button" iconCls="icon-edit" plain="true" onclick="rowControlConfig">编辑控件配置</a>
                              </li>
                          </#if>
                          <li>
                              <a class="mini-button" iconCls="icon-edit" plain="true" onclick="cellRenderConfig">字段渲染</a>
                          </li>
                          <li class="clearfix"></li>
                      </ul>
                  </div>
                      <div id="headerGrid" class="mini-treegrid" multiSelect="true"  expandOnLoad="true"  allowAlternating="true" allowCellSelect="true" allowCellValid="true" allowCellEdit="true">
                          <div property="columns" class="border-right">
                              <div type="indexcolumn" width="20">序号</div>
                              <div type="checkcolumn" width="20"></div>
                              <div name="header" field="header" width="150" headerAlign="center">字段名称(*)
                                  <input property="editor" class="mini-textbox" style="width:100%;" />
                              </div>
                              <div field="field" width="120" headerAlign="center">字段Key(*)
                                  <input property="editor" class="mini-combobox" allowInput="true"
                                         textField="header" valueField="field" required="true" onvaluechanged="changeColumnFieldName"
                                         style="width:100%;" data="fieldDatas" />
                              </div>
                            <#--  <div type="checkboxcolumn" field="allowSort" trueValue="true" falseValue="false" width="60" headerAlign="center">可排序</div>-->

                              <div field="width" width="80" headerAlign="center">宽度
                                  <input property="editor" class="mini-spinner"  style="width:100%;" minValue="50" maxValue="1200"/>
                              </div>
                              <div field="dataType" width="80">数据类型
                                  <input
                                          property="editor"
                                          class="mini-combobox"
                                          value="string"
                                          textField="text"
                                          valueField="id"
                                          data="[{id:'string',text:'字符'},{id:'int',text:'整型'},{id:'float',text:'浮点型'},{id:'date',text:'日期型'},{id:'boolean',text:'布尔型'},{id:'currency',text:'货币型'}]"
                                  >
                              </div>
                              <div field="format" width="80" headerAlign="center">格式化
                                  <input property="editor" class="mini-textbox" style="width:100%;" />
                              </div>
          <#--                    <c:if test="${sysBoList.rowEdit=='YES'}">
                                  <div displayField="controlName" width="80" field="control">字段控件
                                      <input property="editor" class="mini-combobox" style="width:100%" data="rowControls" valueField="id" textField="text" allowInput="false" showNullItem="true" nullItemText="请选择"/>
                                  </div>
                              </c:if>-->
                              <div field="headerAlign" width="80">文本位置
                                  <input property="editor" class="mini-combobox" value="center"  textField="text" valueField="id" data="[{id:'center',text:'居中'},{id:'left',text:'居左'},{id:'right',text:'居右'}]">
                              </div>
                              <!--div displayfield="linkField" field="linkFieldConf">关联字段
                                  <input property="editor" class="mini-buttonedit" onbuttonclick="linkButtonField">
                              </div-->
                              <div field="url" width="180" headerAlign="center">URL
                                  <input property="editor" class="mini-textbox" value="center" >
                              </div>
                              <div field="linkType" displayfield="linkTypeName" width="80">URL方式
                                  <input property="editor" class="mini-combobox" value="center"  textField="linkTypeName" valueField="linkType" data="[{linkType:'newWindow',linkTypeName:'新浏览器窗口'},{linkType:'openWindow',linkTypeName:'弹出窗口'},{linkType:'tabWindow',linkTypeName:'Tab窗口'}]">
                              </div>
                              <div field="renderType" displayfield="renderTypeName" width="100">渲染方式
                                  <input property="editor" class="mini-combobox"
                                         value="center"  textField="renderTypeName" valueField="renderType" data="cololumRenderTypes">
                              </div>
                          </div>
                      </div>
              </div>
              <div title="查询数据" region="south" showSplitIcon="true" showHeader="true" height="280" expanded="false">
                  <div id="sampleDataGrid" class="mini-datagrid" style="width:100%;height:100%;" allowCellEdit="true"
                       url="/sys/core/sysBoList/getSampleData.doallowCellSelect=true" >
                  </div>
              </div>
          </div>

      </div>
  </div>
      <textarea id="headerColumns" style="display:none">${headerColumns}</textarea>
      <textarea id="fieldColumns"  style="display:none">${fieldColumns}</textarea>
     <#-- <textarea id="searchJson" style="display:none">${sysBoList.searchJson}</textarea>-->
<script type="text/javascript">
    mini.parse();
    var headerGrid = mini.get('headerGrid');
    var fieldDatas = null;
    var curField = null;
    $(function () {
      var headerColumns=$('#headerColumns').val();
      var fieldColumns = $('#fieldColumns').val();

      console.log($('#fieldColumns').val());
        fieldDatas=mini.decode(fieldColumns);
        headerGrid.setData(mini.decode(headerColumns));
        mini.get('textField').setData(fieldDatas);
    })




</script>
</body>
</html>