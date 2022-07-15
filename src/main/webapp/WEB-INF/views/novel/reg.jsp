<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/side.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/novel/reg.js"></script>

</head>
<body>
    <main>
        <h1>소설 등록</h1>
        <table>
            <tr>
                <td>제목</td>
                <td><input type="text" id="novel_name"></td>
            </tr>
            <tr>
                <td>장르</td>
                <td>
                    <select id="novel_genre">
                        <c:forEach items="${genre_list}" var="item">
                            <option value="${item.gen_seq}">${item.gen_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>표지</td>
                <form class="novel_img_form" hidden>
                    <td><input type="file" id="input_image" name="file" accept="image/gif,image/jpeg,image/png"></td>
                </form>
                <div class="novel_image"></div>
            </tr>
            <tr>
                <td>설명</td>
                <td><textarea id="novel_text" cols="30" rows="10"></textarea></td>
            </tr>
            <tr>
                <td>연재주기</td>
                <td class="novel_day">
                    <label><input type="checkbox" data-seq="1">일</label>
                    <label><input type="checkbox" data-seq="2">월</label>
                    <label><input type="checkbox" data-seq="3">화</label>
                    <label><input type="checkbox" data-seq="4">수</label>
                    <label><input type="checkbox" data-seq="5">목</label>
                    <label><input type="checkbox" data-seq="6">금</label>
                    <label><input type="checkbox" data-seq="7">토</label>
                    <label><input type="checkbox" data-seq="0">비정기 연재</label>
                </td>
            </tr>
            <tr>
                <td>연령제한</td>
                <td class="novel_age">
                    <label><input type="radio" name="age" data-seq="1" checked>전연령</label>
                    <label><input type="radio" name="age" data-seq="2">15세 이상 권장</label>
                    <label><input type="radio" name="age" data-seq="3">청소년 열람불가</label>
                </td>
            </tr>
        </table>
        <button id="novel_reg_submit">소설 등록하기</button>
        <button id="novel_reg_cancel">취소하기</button>
    </main>
</body>
</html>