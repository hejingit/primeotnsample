<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): train
  - Date: 2016-12-05 09:59:59
  - Description:
-->
<head>
<title>helloworld案例</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript"></script>
    
</head>
<body>
<div  class="" id="form1">
<input class="nui-textbox" name="myname"/>
<a  class="nui-button" onclick="sayhello()" iconCls="icon-edit">提交</a>
</div>
<div  class="" id="info"></div>

	<script type="text/javascript">
    	nui.parse();//nui控件解析
    	function sayhello() {
            
            //提交表单数据
            var form = new nui.Form("form1");            
            var data = form.getData(); //获取表单多个控件的数据
            var json = nui.encode(data);   //序列化成JSON
            nui.ajax({
                url: "com.primeton.helloworld.helloworld.join.biz.ext",
                type: "post",
                contentType:'text/json',
                data:json ,
                success: function (text) {
                    alert("提交成功，返回结果:" + nui.encode(text.info));
                    document.getElementById("info").innerHTML=text.info;
                }
            });

        }
    </script>
</body>
</html>