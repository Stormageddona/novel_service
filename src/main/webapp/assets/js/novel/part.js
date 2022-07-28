
$(function()
{
    console.log(seq)

    $.ajax
    ({
        url:"/api/novel/story/part?seq="+seq, type:"get",
        success:function(result)
        {
            $(".text_area > .title_area").html(result.title) ;
            $(".text_area > .title_area").append("<p>----------------------------</p>") ;
            $(".text_area > .text_content_area").html(result.content) ;
            $(".text_area > .comment_area").html("작가의 말<br><br>") ;
            $(".text_area > .comment_area").append(result.comment) ;

            getCommentList(seq)
        }
    })

    // $(".user_comment_area").reload(function()
    // {
    //     getCommentList(seq)
    // })


    $("#comment_reg_btn").click(function()
    {
        if (!confirm("댓글을 등록하시겠습니까?")) return ;

        let data = $("#comment_reg_input").val()
        $.ajax
        ({
            url:"/api/user/comment?type=1&seq="+$(this).attr("data-seq"),
            type:"put", data:JSON.stringify(data), contentType:"application/json",
            success:function(result)
            {
                if (result.status == false) alert(result.message)
                getCommentList(seq)
            }
        })
    })
})

function getCommentList(seq)
{
    $.ajax
    ({
        url:"/api/user/comment?type=1&seq="+seq, type:"get",
        success:function(result)
        {
            $(".user_comment_area").html("")
            for (let i = 0 ; i < result.list.length; i++)
            {
                console.log(result.list[i])
                let split = result.list[i].cmti_text.split("\"")
                let tag =
                    '<div class="user_comment_box">' +
                        '<div class="user_info_box">' +
                            '<p class="user_name">' + result.list[i].user_nickname + '</p>' +
                            '<p class="user_comment_dt">' + makeDateString(new Date(result.list[i].cmti_reg_dt)) + '</p>' +
                            '<p class="user_comment_number"> no.' + (i+1) + '</p>' +
                        '</div>' +
                        '<div class="user_content">' + split[1] + '</div>' +
                    '</div>' ;
                $(".user_comment_area").append(tag)
            }
        }

    })
}

