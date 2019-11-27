<!doctype html>
<html lang="en">
<#include "/commons/header.ftl">
<body>
<div id="toolbar1" class="mini-toolbar mini-toolbar-bottom">
      <a class="mini-button"   plain="true">上一步</a>
      <a class="mini-button" iconCls="icon-save"  plain="true">保存</a>
      <a class="mini-button" iconCls="icon-start" plain="true">生成页面</a>
      <a class="mini-button" iconCls="icon-edit"  plain="true">编辑页面代码</a>
</div>
<div class="mini-fit">
      <div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height: 100%;">
            <div title="Tab1">
                  1
            </div>
            <div title="Tab2" iconCls="icon-cut" >
                  2
            </div>
            <div title="Tab3" showCloseButton="true">
                  3
            </div>
            <div title="Tab4" showCloseButton="true" enabled="false">
                  4
            </div>
      </div>
</div>

<input class="mini-combobox" textField="text" valueField="id" data="[{id:'string',text:'字符'},{id:'int',text:'整型'},{id:'float',text:'浮点型'},{id:'date',text:'日期型'},{id:'boolean',text:'布尔型'},{id:'currency',text:'货币型'}]">

<div class="mini-treegrid" id="headerGrid">
      <div property="columns">
            <div type="indexcolumn" width="20"></div>
            <div type="checkColumn" width="20"></div>
            <div name="header" field="header" width="150" headerAlign="center">字段名称(*)
                  <input property="editor" class="mini-textbox" style="width:100%;" />
            </div>
            <div field="field" width="120" headerAlign="center">字段Key(*)
                  <input property="editor" class="mini-combobox" allowInput="true"
                         textField="header" valueField="field" required="true" onvaluechanged="changeColumnFieldName"
                         style="width:100%;" data="fieldDatas" />
            </div>
            <div field="width" width="80" headerAlign="center">宽度
                  <input property="editor" class="mini-spinner"  style="width:100%;" minValue="50" maxValue="1200"/>
            </div>

      </div>



</div>



<script type="text/javascript">
      mini.parse();
/*      var headerGrid = mini.get('headerGrid');
      $.post("/sysBoList/aaa",{},function (result) {
            headerGrid.setData(mini.decode(result));
      })*/
</script>
</body>
</html>