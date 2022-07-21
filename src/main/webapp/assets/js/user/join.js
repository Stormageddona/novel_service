let condition = false
$(function()
{
    $("#input_id, #input_email, #input_nickname").change(function()
    {
        type = $(this).attr("data-type")
        $.ajax
        ({
            url:"/api/user/join?value="+$(this).val()+"&type="+type,
            type:"get",
            success:function(result)
            {
                $("#err_msg_"+type).html(result.message) ;
            }
        })
        

    })

    $("#input_pwd_confirm, #input_pwd ").keyup(function()
    {
        if ($("#input_pwd").val() == $("#input_pwd_confirm").val() && !isEmpty($("#input_pwd").val()))
        {
            condition = true ;
            $("#msg_pwd_confirm").html("비밀번호가 확인되었습니다.") ;
        } 
        else
        {
            condition = false ;
            $("#msg_pwd_confirm").html("비밀번호를 정확히 입력해주세요.") ;
        }

    })

    $("#reg_user").click(function()
    {

        if(!confirm("가입하시겠습니까?")) return ;
        if (!condition)
        {
            alert("비밀번호를 정확히 입력해주세요.")
            return ;
        }
        let filter = true ;

        $("#err_msg_id").html("")
        $("#err_msg_pwd").html("")
        $("#err_msg_name").html("")
        $("#err_msg_nickname").html("")
        $("#err_msg_email").html("")
        $("#err_msg_birth").html("")
        if (isEmpty($("#input_id").val()))
        {
            $("#err_msg_id").html("아이디엔 공백이 들어갈수 없습니다.") ;
            filter = false ;
        }
        if (isEmpty($("#input_pwd").val()))
        {
            $("#err_msg_pwd").html("비밀번호엔 공백이 들어갈수 없습니다.") ;
            filter = false ;
        }
        if (isEmpty($("#input_name").val()))
        {
            $("#err_msg_name").html("이름엔 공백이 들어갈수 없습니다.") ;
            filter = false ;
        }
        // if (isEmpty($("#input_nickname").val()))
        // {
        //     $("#err_msg_nickname").html("닉네임엔 공백이 들어갈수 없습니다.") ;
        //     filter = false ;
        // }
        if (isEmpty($("#input_email").val()))
        {
            $("#err_msg_email").html("이메일엔 공백이 들어갈수 없습니다.") ;
            filter = false ;

        }
        if (isEmpty($("#input_birth").val()))
        {
            $("#err_msg_birth").html("생일엔 공백이 들어갈수 없습니다.") ;
            filter = false ;

        }
        if (!numberValidate($("#input_birth").val()))
        {
            $("#err_msg_birth").html("생일은 8자리의 숫자로만 입력해주세요.") ;
            filter = false ;
        }
        if (!filter) return ;
        let birth = $("#input_birth").val()
        let year = birth.substring(0,4)
        if (year < 1800) filter = false
        let month = birth.substring(4,6)
        if (month < 1 || month > 12) filter = false
        let day = birth.substring(6,8)
        if (day < 1 || day > 31) filter = false
        if (!filter)
        {
            $("#err_msg_birth").html("정상적인 날짜를 입력해주세요.") ;
            return ;
        }
        let birthtext = year+"-"+month+"-"+day
        let date = new Date(birthtext+"T00:00:00")

        let data =
        {
            user_id:$("#input_id").val(),
            user_pwd:$("#input_pwd").val(),
            user_name:$("#input_name").val(),
            user_nickname:$("#input_nickname").val(),
            user_email:$("#input_email").val(),
            user_birth:date
        }
        $.ajax
        ({
            url:"/api/user/join",
            type:"put",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(result)
            {
                alert(result.message)
                if (result.status) location.href="/"  
            }

        })
        
    })

    $("#cancel").click(function()
    {
        if(!confirm("회원가입을 취소하시겠습니끼?")) return ;
        location.href="/"
        
    })
})
