<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 수정</title>
</head>
<body>
	<div align=center>
	<h3>게시글 수정</h3>
	${memberName} 회원님 반갑습니다.<br>
	<a href="BbsProcServlet?action=getList&page=${currentBbsPage}">게시판으로 되돌아가기</a>&nbsp;&nbsp;&nbsp;
	<a href="MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<c:set var="content" value="${requestScope.selectedContent}"/>
	<form name="contentModiForm" action=BbsProcServlet?action=execute method=post>
		<!--34:form 내에서 값을 주고 받기위해 필요 -->
		<input type="hidden" name="contentId" value="${content.id }">
		<label><span>제목:</span>
			<input type="text" name="title" value="${content.title }" size="50">
		</label><br><br>
		<label><span>내용:</span>
			<textarea name="content" rows="30" cols="51" >${content.content }</textarea>
		</label><br><br>
		<label>
			<span></span>
			<input type="submit" value="저장" name="B1">&nbsp;&nbsp;
 			<input type="reset" value="재작성" name="B2">
		</label>
	</form>
	</div>
</body>
</html>