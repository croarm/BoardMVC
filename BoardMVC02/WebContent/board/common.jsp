<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.samsungsds.board.util.*,com.samsungsds.member.model.MemberDto"%>
<%
String root = request.getContextPath();
int bcode = StringCheck.stringToZero(request.getParameter("bcode"));
int pg = StringCheck.stringToOne(request.getParameter("pg"));
String key = StringCheck.nullToBlank(request.getParameter("key"));
String word = Encoder.isoToEuc(request.getParameter("word"));
System.out.println("common.jsp word >>>>>>>>>>>> " + word);

MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
%>

<form name="mvform" method="get" action="">

<input type = "hidden" name="act" value="">
<input type = "hidden" name="bcode" value="">
<input type = "hidden" name="pg" value="">
<input type = "hidden" name="key" value="">
<input type = "hidden" name="word" value="">
<input type = "hidden" name="seq" value="">
</form>
