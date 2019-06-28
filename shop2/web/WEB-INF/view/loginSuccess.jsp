<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 화면</title>
</head>
<body>
<h2>${sessionScope.loginUser.userName}님 반갑습니다!</h2>

<table border="1" style="border-collapse: collapse">
    <tr>
        <td>아이디</td>
        <td>${loginUser.userId}</td>
    </tr>
    <tr>
        <td>이름</td>
        <td>${loginUser.userName}</td>
    </tr>
    <tr>
        <td>우편번호</td>
        <td>${loginUser.postcode}</td>
    </tr>
    <tr>
        <td>주소</td>
        <td>${loginUser.address}</td>
    </tr>
    <tr>
        <td>전화번호</td>
        <td>${loginUser.phoneNo}</td>
    </tr>
    <tr>
        <td>이메일</td>
        <td>${loginUser.email}</td>
    </tr>
    <tr>
        <td>생년월일</td>
        <td><fmt:formatDate value="${loginUser.birthDay}" pattern="yyyy년 MM월 dd일" /></td>
    </tr>
</table>
</body>
</html>
