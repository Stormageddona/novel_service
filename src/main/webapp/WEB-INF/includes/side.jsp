<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="/assets/js/side.js"></script>
    <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>

    <link rel="stylesheet" href="/assets/css/side.css">
</head>
<body>
    <aside class="side">
        <div class="login_box">
            <c:if test="${user == null}">    
                <!-- 아래와같이 아이디를 꼭 써준다. -->
                <!-- <a id="naverIdLogin_loginButton" href="javascript:void(0)">
                    <span>네이버 로그인</span>
                </a> -->
                <div class="input_login">                    
                    <input type="text" id="id_input" placeholder="아이디">
                    <input type="password" id="pwd_input" placeholder="비밀번호" class="password">
                </div>
                <button id="login">로그인</button>   
                <button id="join">회원가입</button>
                <button id="find">아이디/비밀번호 찾기</button>
            </c:if>
            <c:if test="${user != null}">    
                <span>${user.user_id}<c:if test="${user.user_grade == 2}"> 작가</c:if>님</span><span><button id="logout">로그아웃</button></span>
                <c:if test="${user.user_grade == 2}"><button id="my_novel">내 소설 관리</button></c:if>
                <c:if test="${user.user_grade == 1}"><p>내가 쓴 소설이 없습니다.</p></c:if>
                <button id="my_status">내 정보</button>
                <button id="write">소설등록</button>
            </c:if>
        </div>
    </aside>
</body>
</html>
