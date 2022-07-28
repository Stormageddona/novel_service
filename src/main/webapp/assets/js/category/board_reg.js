let img_files = new Array() ;
$(function()
{


    $("#img_add").change(function()
    {
        let form = $(".board_img_form");
        let formData = new FormData(form[0]) ;
        if ($(this).val() == '' || $(this).val() == null) return;
        $.ajax
        ({
            url:"/image/upload?temp=true",
            type:"put",
            data:formData,
            contentType:false,
            processData:false,
            success:function(result) 
            {
                if(!result.status)
                {
                    alert(result.message);
                    return;
                }
                let tag = '<div class="board_img" filename="' + result.file + '" style="background-image:url(/images/'+result.file+'?temp=true)">'+
                '<button onClick=deleteImg("'+result.file+'")>&times;</button>'+
                '</div>';
                img_files.push(result.file) ;
                $(".img_area").append(tag) ;
            }
        })
    })

    $("#reg_btn").click(function()
    {
        if(!confirm("게시글을 등록하시겠습니까?")) return ;

        let data = {detail:$("#reg_content_commu").val(), comment:null }

        $.ajax
        ({
            url:"/text/upload", type:"put", data:JSON.stringify(data), contentType:"application/json",
            success:function(result)
            {
                let data = 
                {
                    cbi_ci_seq:$("#reg_selected option:selected").val(),
                    cbi_name:$("#reg_title").val(),
                    cbi_text:result.file,
                    cbi_imgList:img_files
                }
                console.log(data)
                $.ajax
                ({
                    url:"/api/category/post", type:"put", data:JSON.stringify(data), contentType:"application/json",
                    success:function(result)
                    {
                        alert(result.message)
                        location.href="/category/"+$("#reg_btn").attr("data-str")
                    }
                })
            }
        })



    })
})