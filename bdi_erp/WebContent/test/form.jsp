<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/views/common/common.jsp"%>
<body>
<form action="<%=rPath %>/test/param.jsp">
	이름:<input type="test" name="name"><br>
	아이디:<input type="test" name="id"><br>
	<button>param.jsp로 전송</button>
</body>
</html>