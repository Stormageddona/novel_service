$(function()
{
    $.ajax
    ({
        url:"/api/category/free?type=1", type:"get",
        success:function(result)
        {
            // $("#content_table tbody").html("") ;

            for(let i = 0 ; i < result.list.length ; i++)
            {                   
                let tag =
                    '<tr>' +
                        '<td class="title"><a href="/novel/storylist?seq='+result.list[i].no_seq+'">' + result.list[i].no_name + '</a></td>' +
                        '<td class="writer">' + result.list[i].wi_name + '</td>' +
                    '</tr>' ;
                $("#content_table tbody").append(tag) ;
            }
        }
    })
})