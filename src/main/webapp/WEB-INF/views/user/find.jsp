<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="/assets/js/user/find.js"></script>

</head>
<body>
    <main>
        <div class="find_selected">
            <h1>찾을 항목을 선택해주세요.</h1>
            <label><input type="radio" class="find_chk_box" name="chk" data-seq="1" checked>아이디</label>
            <label><input type="radio" class="find_chk_box" name="chk" data-seq="2">비밀번호</label>
            <button id="find_submit">확인</button>
            <button id="find_cancel">돌아가기</button>
        </div>
        <div class="find_id_area">
            <h1>아이디 찾기</h1>
            <h2>해당항목을 입력해주세요.</h2>
            <span>이메일</span><span><input type="text" id="find_id_email"></span><br>
            <span>닉네임</span><span><input type="text" id="find_id_nickname"></span>
            <p><button id="find_id_submit">확인</button></p>

        </div>
        <div class="find_pwd_area">
            <h1>비밀번호 찾기</h1>
            <h2>해당항목을 입력해주세요.</h2>
            <span>아이디</span><span><input type="text" id="find_pwd_id"></span><br>
            <span>이메일</span><span><input type="text" id="find_pwd_email"></span>
            <p><button id="find_pwd_submit">확인</button></p>
        </div>

    </main>
</body>
</html>