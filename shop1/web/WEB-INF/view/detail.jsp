<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품상세 보기</title>
</head>
<body>
<table>
    <tr>
        <td>
            <img src="img/${item.pictureUrl}">
        </td>
        <td>
            <table>
                <tr>
                    <td>상품명</td>
                    <td>${item.name}</td>
                </tr>
                <tr>
                    <td>가격</td>
                    <td>${item.price}원</td>
                </tr>
                <tr>
                    <td>상품설명.description}원</td>
                </tr>
                <tr>
                    <td>상품명</td>
                    <td>${item.name}</td>
                </tr>
                <tr>
                    <td colspan="2"><a href="index.shop">상품목록</a></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
