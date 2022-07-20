let img

$(function()
{
    $("#novel_reg_submit").click(function()
    {
        if (!confirm("소설을 등록 하시겠습니까?")) return ;
        
        let day = null ;
        let temp = []; 
        for(let i = 0 ; i < 8 ; i++)
        {
            temp.push($(".novel_day input").eq(i))
            if (temp[i].is(":checked"))
            {
                if (day == null)  day = temp[i].attr("data-seq")
                else day = day + "," + temp[i].attr("data-seq")
            }
        }
        
        let data = 
        {
            no_name:$("#novel_name").val(),
            no_gen_seq:$("#novel_genre option:selected").val(),
            no_simple_txt:$("#novel_text").val(),
            no_seday: day,
            no_age_status:$(".novel_age input:checked").attr("data-seq"),
            novel_img:img

        }
        console.log(data)
        $.ajax
        ({
            url:"/api/novel/reg",
            type:"put",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(result)
            {
                alert(result.message)
                location.href="/novel/mynovel"
            }
        })
    })

    $(".novel_day input").click(function()
    {
        if ($(this).attr("data-seq") == 0)
        {
            for(let i = 0 ; i < 7 ; i++)
            {
                $(".novel_day input").eq(i).prop("checked",false)
            }
        }
        else $(".novel_day input").eq(7).prop("checked",false)
    })

    $("#input_image").change(function()
    {
        let form = $(".novel_img_form");
        let formData = new FormData(form[0]) ;
        if ($(this).val() == '' || $(this).val() == null) return;
        console.log(formData)
        $.ajax
        ({
            url:"/image/upload",
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
                let tag = '<div class="novel_image" filename="' + result.file + '">'+ 
                    '<img src="/images/'+result.file+'">' +
                    '<button onClick=deleteImg("'+result.file+'")>&times;</button>'+
                '</div>';
                if (img != null)  deleteImg(img,true)
                img = result.file ;
                $(".novel_image").html(tag) ;
            }
        })
    })
})


function deleteImg(filename,change = false)
{
    if (!change) if (!confirm("표지를 삭제하시겠습니까?\n(주의 : 삭제된 데이터는 되돌릴 수 없습니다.)")) return ;

    $.ajax
    ({
        url:"/image/delete/"+filename,
        type:"delete",
        success:function(result)
        {
            $(".novel_image").html("") ;
            if(result.status)
            {
                let tag = '<div class="novel_image" filename="' + img + '">'+ 
                '<img src="/images/'+img+'">' +
                '<button onClick=deleteImg("'+img+'")>&times;</button>'+
                '</div>';
            $(".novel_image").append(tag) ;
            }
        }
    })

}