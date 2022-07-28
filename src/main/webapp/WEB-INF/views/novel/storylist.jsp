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
        let novel_seq = '${data.no_seq}' 
    </script>
    <script src="/assets/js/novel/storylist.js"></script>
    <link rel="stylesheet" href="/assets/css/novel/storylist.css">

</head>
<body>
    <div class="main_content_area">
        <div class="novel_info_area">
            <div class="img_area" style="width:150px;height: 220px; background-image: url('/images/${data.img_name}');"></div>
            <div class="info_box">
                <p>${data.no_name}</p>
                <p>장르 : ${data.gen_name}</p>
                <p>글 : ${data.wi_name}</p>
                <p>작품 등록일 : <fmt:formatDate value="${data.no_reg_dt}" pattern="yyyy.MM.dd hh:mm"/></p>
                <p>최근 연재일 : <fmt:formatDate value="${data.no_reg_dt}" pattern="yyyy.MM.dd hh:mm"/></p>
                <p>연재 수 : </p>
                <p>조회 수 : </p>
                <p>추천 수 : </p>
                <p>글자 수 : </p>
                <p>즐겨찾기 수 : ${favorites}</p>
                <c:if test="${favorite == false}">
                    <button class="style_button" id="favorite_reg">즐겨찾기 등록</button>
                </c:if>
                <c:if test="${favorite == true}">
                    <button class="style_button" id="favorite_del">즐겨찾기 해제</button>
                </c:if>
            </div>
            <div class="simple_text_box">${data.no_simple_txt}</div>
        </div>
        <div class="story_list_area">
            <table class="story_list_box">
                <thead>
                    <tr>
                        <td class="table_seq"></td>
                        <td class="table_name">제목</td>
                        <td class="table_dt">날짜</td>
                        <td class="table_count">조회</td>
                        <td class="table_like">추천</td>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</body>
</html>