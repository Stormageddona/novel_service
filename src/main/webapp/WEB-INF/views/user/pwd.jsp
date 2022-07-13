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
    
    <span> 새 비밀번호</span><span><input type="password" id="input_newpass"></span><br>
    <span> 새 비밀번호 확인</span><span><input type="password" id="input_newpass_confirm"></span>
    <br>
    <button id="newpass_submit" data-type="${seq}">등록</button><button id="newpass_cancel">취소</button>
</body>
</html>