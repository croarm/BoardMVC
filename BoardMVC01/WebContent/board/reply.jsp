<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.samsungsds.board.model.*"%>
	<%@ include file="/board/common.jsp"%>
<%
if(memberDto == null) {
%>
<script>
alert("회원전용 게시판입니다.\n로그인후 글작성 가능합니다.");
document.location.href="<%=root%>";
</script>
<%		
} else {
	ReboardDto reboardDto = (ReboardDto) request.getAttribute("reboardArticle");
	if(reboardDto == null) {
%>
<script>
alert("글이 존재하지 않습니다.\n글번호를 확인하세요.");
document.location.href="<%=root%>";
</script>
<%	
	} else {
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>게시판 글쓰기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="<%=root%>/css/skin.css" type="text/css">
<link rel="stylesheet" href="<%=root%>/css/skin.css" type="text/css">
<link rel="stylesheet" type="text/css" HREF="<%=root%>/css/alice.css">
<link rel="stylesheet" type="text/css" HREF="<%=root%>/css/oz.css">
<script type="text/javascript" src="<%=root%>/js/prototype.js"></script>
<script type="text/javascript" src="<%=root%>/js/extprototype.js"></script>	
<script type="text/javascript" src="<%=root%>/js/oz.js"></script>	
<script type="text/javascript" src="<%=root%>/js/alice.js"></script>
<script type="text/javascript">

var alice;
Event.observe(window, "load", function() {

	alice = Web.EditorManager.instance("test",{type:'detail',width:600,height:300,limit:10,family:'돋움',size:'13px'});

});	

function writeArticle(){
	if(document.writeForm.subject.value == ""){
		alert("제목을 입력하세요");
		return;
	}else if(alice.getContent() == ""){
		alert("내용을 입력하세요");
		return;
	}else{
		document.writeForm.act.value = "replywrite";
		document.writeForm.bcode.value = "<%=bcode%>";
		document.writeForm.pg.value = "<%=pg%>";
		document.writeForm.key.value = "<%=key%>";
		document.writeForm.word.value = "<%=word%>";
		document.writeForm.ref.value = "<%=reboardDto.getRef() %>";
		document.writeForm.lev.value = "<%=reboardDto.getLev()%>";
		document.writeForm.step.value = "<%=reboardDto.getStep()%>";
		document.writeForm.pseq.value = "<%=reboardDto.getSeq()%>";
		document.writeForm.content.value = alice.getContent();
		
		document.writeForm.action = "<%=root%>/reboard";
		document.writeForm.submit();
	}
}
</script>
</head>

<body>
<!-- title -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><img src="<%=root%>/img/board/m_icon_board.gif" width="9"
			height="9" border="0" align="absmiddle" style="margin-top: -2px">
		<b>자유게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운 글을
		올리는 공간입니다<br>
		</td>
		<td align="right"></td>
	</tr>
	<tr>
		<td colspan="2" height="19"></td>
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="630">
	<tr>
		<td><img src="<%=root%>/img/board/icon_arrow_04.gif" width="4"
			height="11" border="0" align="absmiddle" vspace="4"></td>
		<td width="100%" style="padding-left: 4px"><b>답글쓰기</b></td>
	</tr>
	<tr>
		<td width="630" colspan="2" height="2" class="bg_board_title_02"></td>
	</tr>
</table>
<br>

<form id="writeForm" name="writeForm" method="post" action=""
	style="margin: 0px">
<div id="attach_file_hdn"></div>

<input type="hidden" name="act" value="">
<input type="hidden" name="bcode" value="">
<input type="hidden" name="pg" value="">
<input type="hidden" name="key" value="">
<input type="hidden" name="word" value="">
<input type="hidden" name="ref" value="">
<input type="hidden" name="lev" value="">
<input type="hidden" name="step" value="">
<input type="hidden" name="pseq" value="">
<input type="hidden" name="content" value="">

<table border="0" cellpadding="5" cellspacing="0" width="630"
	style="table-layout: fixed">

	<tr valign="top">
		<td width="95" nowrap style="padding-left: 8px; padding-top: 10px"><img
			src="<%=root%>/img/board/e_dot.gif" width="4" height="4" border="0"
			align="absmiddle"> <b>제목</b></td>
		<td colspan="5"><input name="subject" id="subject" type="text"
			size="76" maxlength="150" class="inp_02" style="width: 300px"
			value="Re : <%=reboardDto.getSubject()%>"><img src="<%=root%>/img/board/i_info.gif" width="12"
			height="11" border="0" align="absmiddle" vspace="8"
			style="margin: 3 3 0 6"><font class="stext">최대 한글 75자,
		영문 150자</font><br>
	</tr>
	<tr>
		<td width="620" nowrap style="padding-left: 8px; padding-top: 10px"
			colspan="5"><img src="<%=root%>/img/board/e_dot.gif" width="4"
			height="4" border="0" align="absmiddle"> <b>글내용</b> <textarea
			name="test" class="inp_02" cols="67" rows="25" scrollbars="no">


--------------------------------------------------------[원글]

<%=reboardDto.getContent() %>
			</textarea>
		</td>
	</tr>
</table>
<table width="630" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" height="1"></td>
	</tr>
</table>

<table border="0" cellpadding="2" cellspacing="0" width="630">
	<tr>
		<td height="10" style="padding: 0px"></td>
	</tr>
	<tr>
		<td align="center"><a href="javascript:writeArticle();"><img
			src="<%=root%>/img/board/btn_register.gif" width="42" height="21"
			border="0" name="register" value="" alt="등록"></a> <a
			href="javascript:history.back();"><img
			src="<%=root%>/img/board/b_cancel.gif" width="42" height="21"
			border="0" name="cencel" value="" alt="취소"></a></td>
	</tr>
</table>
</form>
<br>
<br>
</body>
</html>
<%
}
}
%>