<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 목록을 조회합니다.</h1>
<table border="1" width="1000px">
	
	<tr>
	 <th>아이디</th>
	 <th>이름</th>
	 <th>이메일</th>
	 <th>주소</th>
	 <th>가입날짜</th>
	</tr>
	
	<!-- JSTL 사용 -->
	<c:forEach var="member" items="${members }"> 
	<tr>
	 <td>${member.id }</td>
	 <td>${member.name }</td>
	 <td>${member.email }</td>
	 <td>${member.address }</td>
	 <td>${member.rdate }</td>
	</tr>
	</c:forEach>

</table>

<a href="../">메인화면으로</a>

</body>
</html>