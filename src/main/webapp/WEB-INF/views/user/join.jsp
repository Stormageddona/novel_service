<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="/assets/js/util.js"></script>
    <script src="/assets/js/user/join.js"></script>
</head>
<body>
    <main>
        <h1>회원가입</h1>
        <table>
            <tr>
                <td>아이디</td>
                <td><input type="text" id="input_id" data-type="id"></td>
                <td id="err_msg_id"></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" id="input_pwd"></td>
                <td id="msg_pwd"></td>

            </tr>
            <tr>
                <td>비밀번호 확인</td>
                <td><input type="password" id="input_pwd_confirm"></td>
                <td id="msg_pwd_confirm"></td>

            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" id="input_name"></td>
                <td id="err_msg_name"></td>

            </tr>
            <tr>
                <td>닉네임</td>
                <td><input type="text" id="input_nickname" data-type="nickname"></td>
                <td id="err_msg_nickname"></td>

            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="text" id="input_email" data-type="email"></td>
                <td id="err_msg_email"></td>

            </tr>
            <tr>
                <td>생일</td>
                <td><input type="text" id="input_birth"></td>
                <td id="err_msg_birth"></td>

            </tr>
        </table>
        <button id="reg_user">회원가입</button>
        <button id="cancel">돌아가기</button>
    </main>
</body>
</html>