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

<script type="text/javascript">
      mini.parse();

</script>
</body>
</html>