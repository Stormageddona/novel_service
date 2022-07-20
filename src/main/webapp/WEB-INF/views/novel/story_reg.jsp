<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/side.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <style>
        #story_name {width: 590px;height: 40px;}
        .text_area {float: right;}
    </style>
    <script src="/assets/js/novel/story_reg.js"></script>

</head>
<body>
    <main>
        <div class="text_area">
            <input type="text" id="story_name" placeholder="제목을 입력하세요"><br>
            <textarea id="story_detail" style="width: 592px;height: 600px;resize: none;"></textarea><br>
            <textarea id="writer_text" placeholder="작가의 말" style="width: 592px;height: 60px;resize: none;"></textarea><br>
            <button id="reg_text_btn" data-seq="${seq}">등록</button>
            <button id="cancel_text_btn">취소</button>
        </div>
    </main>
</body>
</html>