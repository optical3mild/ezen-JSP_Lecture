<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<style>
			input[type=text] { /* text 창에만 적용 */
				color : red; 
			}
			input:hover, textarea:hover { /* 마우스 올라 올 때 */
				background : aliceblue;
			}
			input[type=text]:focus, input[type=password]:focus { /* 포커스 받을 때 */
				font-size : 120%; 
			}
			label {
				display : block; /* 새 라인에서 시작 */
				padding : 5px;
			}
			label span {
				display : inline-block;
				width : 90px;
				text-align : right;
				padding : 10px;
			}
	</style>
</head>
<body>
	<div align=center>
	<br>
	<h3>Member Login</h3>
	<hr>
	
	<form name="loginForm" action="MemberProcServlet?action=login" method=post>
		<label><span>ID:</span>
			<input type="text" name="id" size="10">
		</label>
		<label><span>Password:</span>
			<input type="password" name="password" size="10">
		</label>
		<label>
			<span></span>
			<!-- submit버튼 : 입력된 값을 form에 정의된 컨트롤러에 전달. 'doPost()' -->
			<input type="submit" value="로그인" name="B1">&nbsp;&nbsp;
	 		<input type="reset" value="재작성" name="B2">
		</label>
	</form>
	<br><br><button onclick="location.href='register.html'">회원 가입</button>
	</div>
</body>
</html>