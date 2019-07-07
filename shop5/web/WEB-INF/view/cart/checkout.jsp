<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>�ֹ� �� ��ǰ ��� ����</title>
</head>
<body>
<h2>����� ����</h2>
<table>
    <tr>
        <td width="30%">������ ID</td>
        <td width="70%">${sessionScope.loginUser.userId}</td>
    </tr>
    <tr>
        <td width="30%">������ �̸�</td>
        <td width="70%">${sessionScope.loginUser.userName}</td>
    </tr>
    <tr>
        <td width="30%">���� ��ȣ</td>
        <td width="70%">${sessionScope.loginUser.postcode}</td>
    </tr>
    <tr>
        <td width="30%">�ּ�</td>
        <td width="70%">${sessionScope.loginUser.address}</td>
    </tr>
    <tr>
        <td width="30%">��ȭ ��ȣ</td>
        <td width="70%">${sessionScope.loginUser.phoneNo}</td>
    </tr>
    <tr>
        <td width="30%">�̸���</td>
        <td width="70%">${sessionScope.loginUser.email}</td>
    </tr>
</table>
<h2>���� ��ǰ ���</h2>
<table>
    <tr>
        <th>��ǰ��</th>
        <th>����</th>
        <th>����</th>
        <th>�հ�</th>
    </tr>
    <c:forEach items="${sessionScope.CART.itemSetList}" var="itemSet" varStatus="stat">
        <tr>
            <td>${itemSet.item.name}</td>
            <td>${itemSet.item.price}</td>
            <td>${itemSet.quantity}</td>
            <td>${itemSet.item.price *itemSet.quantity}</td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="4" align="right">
            �� ���� �ݾ�: <fmt:formatNumber value="${sessionScope.CART.total}" pattern="###,###" />��
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <a href="end.shop">�ֹ�Ȯ��</a>&nbsp;
            <a href="../item/list.shop">��ǰ ���</a>&nbsp;
        </td>
    </tr>
</table>
</body>
</html>