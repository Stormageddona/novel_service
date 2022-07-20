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
})
