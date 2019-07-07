<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>�ֹ� Ȯ��</title>
</head>
<body>
<h2>${loginUser.userName}���� �ֹ��Ͻ� �����Դϴ�.</h2>
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
<h2>�ֹ� �Ϸ� ��ǰ ���</h2>
<table>
    <tr>
        <th>��ǰ��</th>
        <th>����</th>
        <th>����</th>
        <th>�հ�</th>
    </tr>
    <c:forEach items="${sale.itemList}" var="saleItem" varStatus="stat">
        <tr>
            <td>${saleItem.item.name}</td>
            <td>${saleItem.item.price}</td>
            <td>${saleItem.quantity}</td>
            <td>${saleItem.item.price * saleItem.quantity}</td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="4" align="right">
            �� ���� �ݾ�: <fmt:formatNumber value="${tot}" pattern="###,###" />��
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <a href="../item/list.shop">��ǰ ���</a>&nbsp;
        </td>
    </tr>
</table>
</body>
</html>