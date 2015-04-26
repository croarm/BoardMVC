<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.samsungsds.member.model.MemberDto"%>
<%
String root = request.getContextPath();

//임시 로그인 세팅.
MemberDto memberDto = new MemberDto();
memberDto.setName("안효인");
memberDto.setId("java2");
memberDto.setEmail1("java2");
memberDto.setEmail2("naver.com");

session.setAttribute("userInfo", memberDto);

response.sendRedirect(root + "/admin?act=boardlist");
%>    
