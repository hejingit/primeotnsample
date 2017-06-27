<% String contextPath=request.getContextPath(); %>

<script type="text/javascript" src="<%=contextPath%>/common/nui/nui.js"></script>
<script type="text/javascript" src="<%=contextPath%>/common/nui/locale/zh_CN.js"></script>
<script>
	$(function(){
		nui.context='<%=contextPath %>'
	})
</script>
<link id="css_skin" rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/tools/skins/skin1/css/style.css" />