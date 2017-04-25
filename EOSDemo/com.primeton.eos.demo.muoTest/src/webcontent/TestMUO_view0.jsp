<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<html>
<!-- 
  - Author(s): hejin
  - Date: 2017-03-28 21:47:43
  - Description:
-->
<head>
<title>jsp auto create</title> 
</head>
<body>


<form id="form1" action="com.primeton.eos.demo.muoTest.TestMUO.flow"  method="post">
	<h:hidden name="_eosFlowKey" property="_eosFlowKey"></h:hidden>
  <input id="action" type="hidden" name="_eosFlowAction" value="action1">
  
  	<script type="text/javascript">
		function selectAction(action){			
			document.getElementById("action").value=action;
			document.getElementById("form1").submit();
		}		
	</script>
	
  <input type="button" align="left" value="提交"  onclick="selectAction('end1');">
  
  
</form>




</body>
</html>
