$(function()
{
    $.ajax
    ({
        url:"/api/category/community/list", type:"get",
        success:function(result)
        {
            console.log(result)
        }
    })
})