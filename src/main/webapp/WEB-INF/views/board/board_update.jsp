<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 수정하기 -->
<form action="." method="post">
	<input type="hidden" name="boardnum" value="${board.boardnum}" />
	<input type="hidden" name="name" value="${board.name}" />
	<table width="500px" border="1">
		<tr>
			<td>작성자</td>
			<td>
			${board.name }
			</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td>
				<input type="text" name="title" value="${board.title }" size="20" />
			</td>
		</tr>
		<tr>
			<td>글내용</td>
			<td>
				<textarea name="content" cols="50" rows="10">${board.content }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			    <input type="submit" value="완료" />
				<a href="main">게시글 목록으로</a>
			</td>
		</tr>
		
	</table>

</form>

</body>
</html>