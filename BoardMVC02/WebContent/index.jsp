<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.samsungsds.member.model.MemberDto"%>
<%
String root = request.getContextPath();

//�ӽ� �α��� ����.
MemberDto memberDto = new MemberDto();
memberDto.setName("��ȿ��");
memberDto.setId("java2");
memberDto.setEmail1("java2");
memberDto.setEmail2("naver.com");

session.setAttribute("userInfo", memberDto);

response.sendRedirect(root + "/admin?act=boardlist");
%>    
