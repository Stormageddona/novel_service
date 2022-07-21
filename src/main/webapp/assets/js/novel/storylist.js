$(function()
{
    $.ajax
    ({
        url:"/api/novel/storylist?seq="+novel_seq+"&offset=0", type:"get",
        success:function(result)
        {
            console.log(result) ;
            $(".story_list_box tbody").html("") ;
            for (let i = 0 ; i < result.list.length ; i++)
            {
                let tag =
                    '<tr>' +
                        '<td class="table_seq">'+result.list[i].ns_index+'</td>' + 
                        '<td class="table_name"><a href="/novel/story/content/'+result.list[i].ns_seq+'">'+result.list[i].ns_name+'</a></td>' +
                        '<td class="table_dt">'+makeDateString(new Date(result.list[i].ns_reg_dt))+'</td>' +
                        '<td class="table_count">'+result.list[i].ns_count+'</td>' +
                        '<td class="table_like">'+result.list[i].like_count+'</td>' +
                    '<tr>' ;
                $(".story_list_box tbody").append(tag) ;
                localStorage.setItem(result.list[i].ns_seq,result.list[i].ns_seq)
            }
            
        }
    })
})