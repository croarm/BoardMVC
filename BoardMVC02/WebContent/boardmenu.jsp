<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.List,com.samsungsds.board.model.BoardKindDto"%>
<%
String root = request.getContextPath();

List<BoardKindDto> list = (List<BoardKindDto>) request.getAttribute("boardList");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>게시판 리스트</h3>
<table width="200">
<%
int cflag = 1;
int size = list.size();
for(int i=0;i<size;i++) {
	BoardKindDto dto = list.get(i);
%>
<%
	if(cflag == dto.getCcode()) {
%>
<tr>
	<td align="left" bgcolor="#6699ff">
	<%=dto.getCname() %>
<%	
		cflag++;
%>
	</td>
</tr>
<%
	}
%>
<tr>
	<td align="left" bgcolor="#66ffff">
&nbsp;&nbsp;&nbsp;
<a href="<%=root%>/reboard?act=articlelist&bcode=<%=dto.getBcode() %>&pg=1&key=&word=">
<%=dto.getBname() %>
</a>
	</td>
</tr>
<%
}
%>
</table>
</center>
</body>
</html>