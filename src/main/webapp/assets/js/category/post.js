$(function()
{
    $.ajax
    ({
        url:"/api/category/post?seq="+seq, type:"get",
        success:function(result)
        {
            $(".post_title").html(result.data.cbi_name)
            $(".post_writer").html(result.data.nickname)
            $(".post_reg_dt").html(makeDateString(new Date(result.data.cbi_reg_dt)))
            // $(".post_count").html(result.data.cbi_count)

            if (result.imgs != null)
            {
                $(".post_img").html("")
                for (let i = 0;i < result.imgs.length ; i++ )
                {
                    $(".post_img").append('<div class="img_box" style="background-image:url(\'/images/'+result.imgs[i]+'\')"></div>')
                }
            }
            
            console.log(result)
            $(".post_content").html(result.content)
        }
    })
})