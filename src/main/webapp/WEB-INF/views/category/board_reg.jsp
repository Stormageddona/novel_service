<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/side.jsp"%>
<%@include file="/WEB-INF/includes/content.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="/assets/js/category/board_reg.js"></script>
    <link rel="stylesheet" href="/assets/css/category/board_reg.css">
    <title>Document</title>
</head>
<body>
    <div class="main_content_area">
        <div class="text_area">

        게시글 작성<br>
        <select name="" id="reg_selected">
            <c:forEach items="${category}" var="item">
                <option value="${item.ci_seq}" <c:if test="${item.ci_seq == seq}">selected</c:if>>${item.ci_name}</option>    
            </c:forEach>
        </select>
        <input id="reg_title" type="text" placeholder="제목을 입력하세요">
        <br>
        <form class="board_img_form" hidden>
            <input type="file" id="img_add" name="file" accept="image/gif,image/jpeg,image/png">
        </form>
        <button id="add_image" onclick="document.getElementById('img_add').click()"><i class="fas fa-image"></i></i></button>
            <textarea name="" id="reg_content_commu"></textarea>
            <br>
            <div class="img_area"></div>
            <button class="style_button" id="reg_btn" data-str="${adr}">등록</button>
            <button class="style_button" id="cancel_btn">취소</button>
        </div>
    </div>
</body>
</html>