<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/side.jsp"%>
<%@include file="/WEB-INF/includes/content.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/category/community.css">
    <script src="/assets/js/category/community.js"></script>
</head>
<body>
    <div class="main_content_area">
        <div class="board">
            <hgroup><p>감상란</p><a href="/category/bookreview">더보기</a></hgroup>
            <div class="board_content_box"></div>
        </div>
        <div class="board">
            <hgroup><p>비평란</p><a href="/category/bookreport">더보기</a></hgroup>
            <div class="board_content_box"></div>
        </div>
        <div class="board">
            <hgroup><p>강호정담</p><a href="/category/board_free">더보기</a></hgroup>
            <div class="board_content_box"></div>
        </div>
        <div class="board">
            <hgroup><p>논담</p><a href="/category/talk">더보기</a></hgroup>
            <div class="board_content_box"></div>
        </div>
    </div>
</body>
</html>