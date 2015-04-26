<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/board/common.jsp"%>
<%
int seq = StringCheck.stringToZero(request.getParameter("seq"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>글입력 성공</title>

<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="<%=root%>/css/skin.css" type="text/css">
<script type="text/javascript">
function goView(seq) {
	
	document.mvform.act.value = "articleview";
	document.mvform.bcode.value = "<%=bcode%>";
	document.mvform.pg.value = "<%=pg%>";
	document.mvform.key.value = "<%=key%>";
	document.mvform.word.value = "<%=word%>";
	document.mvform.seq.value = seq;	
	document.mvform.action ="<%=root%>/reboard";
	document.mvform.submit();
	
}

function goPage(pg){
	
	document.mvform.act.value = "articlelist";
	document.mvform.bcode.value = "<%=bcode%>";
	document.mvform.pg.value = pg;
	document.mvform.key.value = "<%=key%>";
	document.mvform.word.value = "<%=word%>";
	
	document.mvform.action ="<%=root%>/reboard";
	document.mvform.submit();
}
</script>
</head>

<body>
<form name="a" method="" action="">
<input type="hidden" name="" value="">
</form>

<table width="100%" cellpadding="6" cellspacing="2" border="0"
	bgcolor="#ffffff" style="border: #e1e1e1 solid 1px">
	<tr>
		<td class="bg_board_title" width="100%"><img
			src="<%=root%>/img/board/icon_arrow_08.gif" width="3" height="11"
			border="0" align="absmiddle" hspace="6" vspace="6"> <b>게시판</b>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#e1e1e1"
			style="overflow: hidden; padding: 0px;"></td>
	</tr>
	<tr>
		<td class="bg_menu" width="100%" style="padding: 25px" height="35"
			align="center"><b>게시물이 등록되었습니다.</b><br>
		<br>

		<div align="center">
			<a href="javascript:goView('<%=seq%>');"><img
			src="<%=root%>/img/board/b_wirtecf.gif" width="91" height="21"
			border="0" align="absmiddle" alt="작성한 글 확인" hspace="10"></a>
			
			<a href="javascript:goPage('<%=pg%>');"><img src="<%=root%>/img/board/poll_listbu1.gif"
			width="62" height="21" border="0" align="absmiddle" alt="목록보기"
			hspace="10"></a>
		</td>
	</tr>
</table>
<br>
</body>
</html>