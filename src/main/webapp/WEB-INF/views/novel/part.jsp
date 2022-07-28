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
        let seq = "${ns_seq}" ;
    </script>
    <script src="/assets/js/novel/part.js"></script>
    <link rel="stylesheet" href="/assets/css/novel/part.css">
</head>
<body>
    <div class="main_content_area">
        <div class="text_area">
            <div class="info_area">
        
            </div>
            <div class="title_area">
            </div>

            <div class="text_content_area">

            </div>
            <div class="comment_area">
        
            </div>
        </div>
        <div class="user_area">
            comment
            <div class="user_comment_area">
                <div class="user_comment_box">
                    <div class="user_info_box">
                        <p class="user_name">이름</p>
                        <p class="user_comment_dt">작성날자</p>
                        <p class="user_comment_number">작성번호</p>
                    </div>
                    <div class="user_content">댓글내용</div>
                </div>
                

            </div>
            <textarea name="" id="comment_reg_input" cols="30" rows="10" style="width: 642px;height: 120px; resize: none;" <c:if test="${user == null}">disabled </c:if>><c:if test="${user == null}">로그인 해주세요</c:if> </textarea>
            <button id="comment_reg_btn" data-seq="${ns_seq}"<c:if test="${user == null}">disabled</c:if>>댓글 등록</button>
        </div>
    </div>
</body>
</html>