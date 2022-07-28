$(function()
{
    $("#join").click(function()
    {
        location.href="/user/join"
    })
    
    $("#find").click(function()
    {
        location.href="/user/find"
    })

    $("#pwd_input").keydown(function(e) {
        if(e.keyCode == 13) {
            $("#login").trigger("click");
        }
    })
    $("#login").click(function()
    {
        let id = $("#id_input").val()
        let pwd = $("#pwd_input").val()
        if (isEmpty(id) || isEmpty(pwd))
        {
            alert("아이디와 비밀번호를 입력해주세요.")
            return;
        }
        $.ajax
        ({
            url:"/api/user/login?id="+id+"&pwd="+pwd,
            type:"get",
            success:function(result)
            {
                alert(result.message) ;
                if (!result.status) return ;
                location.reload()
            }
        })
    })

    $("#logout").click(function()
    {
        $.ajax
        ({
            url:"/api/user/logout", type:"get",
            success:function()
            {
                location.href="/"
            }
        })

    })


    $("#write").click(function()
    {
        location.href="/novel/reg"
    })

    $("#my_novel").click(function()
    {
        location.href="/novel/mynovel"
    })

    // var naverLogin = new naver.LoginWithNaverId(
    //     {
    //         clientId: "zf4iK0R1Sv_GdM7R0SX5", //내 애플리케이션 정보에 cliendId를 입력해줍니다.
    //         callbackUrl: "http://localhost:8088", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
    //         isPopup: false,
    //         callbackHandle: true
    //     }
    // );	
    
    // naverLogin.init();
    
    // window.addEventListener('load', function () {
    // naverLogin.getLoginStatus(function (status) {
    //     console.log(naverLogin.user); 
    // });
    // });
})

