<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <script src="https://cdn.ckeditor.com/4.5.7/full-all/ckeditor.js"></script>
</head>
<body>
<form:form modelAttribute="board" action="write.shop" enctype="multipart/form-data" name="f">
    <table>
        <tr>
            <td>글쓴이</td>
            <td>
                <form:input path="name" />
                <font color="red"><form:errors path="name" /></font>
            </td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td>
                <form:password path="pass" />
                <font color="red"><form:errors path="pass" /></font>
            </td>
        </tr>
        <tr>
            <td>제목</td>
            <td>
                <form:input path="subject" />
                <font color="red"><form:errors path="subject" /></font>
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                <form:textarea path="content" rows="15" cols="80" />
                <font color="red"><form:errors path="content" /></font>
                <script type="text/javascript">
                    CKEDITOR.replace("content", {filebrowserImageUploadUrl : "imgupload.shop"});
                </script>
            </td>
        </tr>
        <tr>
            <td>첨부파일</td>
            <td>
                <input type="file" name="file1">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="javascript:document.f.submit()">[게시글 등록]</a>
                <a href="list.shop">[게시글 목록]</a>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
