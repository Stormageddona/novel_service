<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/side.jsp"%>
<%@include file="/WEB-INF/includes/content.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script>
        let seq = "${seq}"
    </script>
    <link rel="stylesheet" href="/assets/css/category/post.css">
    <script src="/assets/js/category/post.js"></script>
</head>
<body>
    <div class="main_content_area">
        <div class="title_area">
            <div class="title_box">
                <span>강호정담</span><h2>자유로이 이야기 하는 곳입니다.</h2>
            </div>
        </div>

        <div class="tag_area">태그 검색 에어리어</div>
        <div class="post_info">
            <div class="post_title"></div>
            <div class="post_info_area_box">
                <div class="post_writer"></div>
                <div class="post_reg_dt"></div>
                <div class="post_count">카운트</div>
            </div>
        </div>
        <div class="post_content_area">
            <div class="post_img"></div>
            <div class="post_content"></div>
        </div>
    </div>
</body>
</html>