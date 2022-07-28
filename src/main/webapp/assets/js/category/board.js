$(function()
{
    let split = window.location.href.split("/")

    $.ajax
    ({
        url:"/api/category/board?seq="+seq, type:"get",
        success:function(result)
        {
            console.log(result.list)
            $(".board_table tbody").html("") ;
            for (let i = 0 ; i < result.list.length ; i++)
            {
                let tag =
                    '<tr>' +
                        '<td class="table_seq">'+result.list[i].cbi_seq+'</td>' + 
                        '<td class="table_seq"><a href="/category/post?seq='+result.list[i].cbi_seq+'">'+result.list[i].cbi_name+'</a></td>' + 
                        '<td class="table_seq">'+result.list[i].nickname+'</td>' + 
                        '<td class="table_seq">'+makeDateString(new Date(result.list[i].cbi_reg_dt))+'</td>' + 
                        '<td class="table_seq">'+result.list[i].cbi_seq+'</td>' + 
                        '<td class="table_seq">'+result.list[i].cbi_seq+'</td>' + 
                    '</tr>' ;
                $(".board_table tbody").append(tag) ;
            }
        }
    })
    $(".board_reg_btn").click(function()
    {
        location.href="/category/board_reg?seq="+$(this).attr("data-seq")+"&adr="+$(this).attr("data-str")
    })
})