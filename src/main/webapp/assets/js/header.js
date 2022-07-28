$(function()
{
    $("#nav_free").mouseover(function()
    {
        $("#nav_help_area").hide()
        $("#nav_free_area").show()
    })
    $("#nav_show").mouseover(function()
    {
        $("#nav_help_area").hide()
        $("#nav_free_area").hide()
    })
    $("#nav_commu").mouseover(function()
    {
        $("#nav_help_area").hide()
        $("#nav_free_area").hide()
    })
    $("#nav_help").mouseover(function()
    {
        $("#nav_help_area").show()
        $("#nav_free_area").hide()
    })
    $("#search_btn_header, #search_btn_content").click(function()
    {
        let searchKeyword = null
        let type = 0
        if ($(this).attr("data-seq") == 1) 
        {
            searchKeyword = $("#search_input_header").val()
            $("#search_input_content").val(searchKeyword)
        }
        else if ($(this).attr("data-seq") ==2) 
        {
            type = 1 ;
            searchKeyword = $("#search_input_content").val() ;
        }
        $.ajax
        ({
            url:"/api/category/free?type="+type+"&keyword="+searchKeyword, type:"get",
            success:function(result)
            {
                console.log(result.message)
                $("#content_table tbody").html("") ;

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
    $("#search_input_header").keydown(function(e) {
        if(e.keyCode == 13) {
            $("#search_btn_header").trigger("click");
        }
    })
    $("#search_input_content").keydown(function(e) {
        if(e.keyCode == 13) {
            $("#search_btn_content").trigger("click");
        }
    })



})