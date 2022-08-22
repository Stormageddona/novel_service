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
        let seq = 1 ;
    </script>
    <script src="/assets/js/category/board.js"></script>
    <link rel="stylesheet" href="/assets/css/category/board.css">
</head>
<body>
    <div class="main_content_area">
        <div class="title_area">
            <div class="title_box">
                <span>강호정담</span><h2>추천에 관련된 감상을 쓰는 곳입니다.</h2>
            </div>
        </div>

        <div class="tag_area">태그 검색 에어리어</div>
        <table class="board_table">
            <thead>
                <tr>
                    <td>번호</td>
                    <td>제목</td>
                    <td>글쓴이</td>
                    <td>날짜</td>
                    <td>조회</td>
                    <td>추천</td>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
            
        </table>
        <div class="search_area"></div>
        <c:if test="${user != null}">
            <button class="style_button board_reg_btn" data-seq="1" data-str="board_free">글쓰기</button>
        </c:if>
        <div class="pager_area"></div>
    </div>
    
</body>
</html>