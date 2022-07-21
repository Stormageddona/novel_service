
$(function()
{
    console.log(seq)

    $.ajax
    ({
        url:"/api/novel/story/part?seq="+seq, type:"get",
        success:function(result)
        {
            $(".text_area > .title_area").html(result.title) ;
            $(".text_area > .title_area").append("<br>----------------------------------------------------") ;
            $(".text_area > .text_content_area").html(result.content) ;
            $(".text_area > .comment_area").html(result.comment) ;
            
        }
    })
})

