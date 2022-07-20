$(function()
{
    $("#reg_text_btn").click(function()
    {
        if(!confirm("소설을 등록하시겠습니까?")) return ;

        let data = 
        {detail:$("#story_detail").val(),
        comment:$("#writer_text").val()}

        $.ajax
        ({
            url:"/text/upload", type:"put", data:JSON.stringify(data), contentType:"application/json",
            success:function(result)
            {
                console.log(result)
                if(!result.status) return ;
                let data2 =
                {
                    ns_name:$("#story_name").val(),
                    ns_file_name:result.file,
                    ns_writer_comment:result.comment
                }
                $.ajax
                ({
                    url:"/api/novel/story?seq="+$("#reg_text_btn").attr("data-seq"), type:"put",data:JSON.stringify(data2), contentType:"application/json",
                    success:function(r)
                    {

                    }
                })
            }
        })
    })
})