$(function()
{
    $(".find_id_area").hide()
    $(".find_pwd_area").hide()

    $("#find_submit").click(function()
    {
        let value = $(".find_selected input:checked").attr("data-seq")
        if (value == 1)
        {
            $(".find_id_area").show()
            $(".find_pwd_area").hide()
        }
        else if (value == 2)
        {
            $(".find_id_area").hide()
            $(".find_pwd_area").show()
        }
    })

    $("#find_id_submit").click(function()
    {
        if (!confirm("아이디를 찾으시겠습니까?")) return ;
        $.ajax
        ({
            url:"api/user/find?value1="+$("#find_id_email").val()+"&value2="+$("#find_id_nickname").val()+"&type=1",
            type:"get",
            success:function(result)
            {
                alert(result.message)
                location.href="/"
            }
        })
    })

    $("#find_pwd_submit").click(function()
    {
        if (!confirm("비밀번호를 찾으시겠습니까?")) return ;

        $.ajax
        ({
            url:"api/user/find?value1="+$("#find_pwd_id").val()+"&value2="+$("#find_pwd_email").val()+"&type=2",
            type:"get",
            success:function(result)
            {
                alert(result.message)
                location.href="user/find/pwd?seq="+result.seq
            }
        })
    })
})