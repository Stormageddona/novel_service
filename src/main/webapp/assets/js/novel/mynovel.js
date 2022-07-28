$(function()
{
    
    $.ajax
    ({
        url:"/api/novel/list",type:"get",
        success:function(result)
        {
            console.log(result)
            if (result.status == false) return ;
            console.log(result.message);
            $(".novel_list tbody").html("") ;
            for(let i = 0; i < result.list.length;i++)
            {
                let status ;
                if (result.list[i].no_status == 2) status = "완결"
                else if (result.list[i].no_status == 3) status = "연재중단"
                else status = "연재중" 
                let type = null ;
                if (result.list[i].no_serial_type == 1) type = "자유연재"
                else if (result.list[i].no_serial_type == 2) type = "일반연재"
                else if (result.list[i].no_serial_type == 3) type = "독점연재"
                else if (result.list[i].no_serial_type == 4) type = "작가연재"
                let age = null ;
                if (result.list[i].no_age_status == 1) age = "전 연령"
                else if (result.list[i].no_age_status == 2) age = "15세"
                else if (result.list[i].no_age_status == 3) age = "성인"
                $.ajax
                ({
                    url:"/api/novel/storyinfo?seq="+result.list[i].no_seq,type:"get",
                    success:function(r)
                    {
                        let last_dt = "-";
                        if (r.data.last_dt != null) last_dt = (makeDateString(new Date(r.data.last_dt))) ;
                    let tag = 
                        '<tr>' +
                            '<td rowspan="2" class="img_area"><a href="/novel/storylist?seq='+result.list[i].no_seq+'"><div class="img_box" style="background-image:url(\'/images/'+result.list[i].img_name+'\')"></div></a></td>'+
                            '<td class="list_name"><a href="/novel/storylist?seq='+result.list[i].no_seq+'">'+ result.list[i].no_name +'</a></td>'+
                            '<td>'+ result.list[i].gen_name+'</td>'+
                            '<td>'+ status +'</td>'+
                            '<td>'+ type +'</td>'+
                            '<td class="list_age">'+ age +'</td>'+
                            '<td><button class="style_button story_reg_btn" data-seq="'+ result.list[i].no_seq +'">신규 회차 등록</button></td>'+
                        '</tr>'+
                        '<tr class="tr_end_box">'+
                            '<td>찜 : '+ (r.data.favoritecount==null?0:r.data.favoritecount)  +'</td>'+
                            '<td>총 조회수 : '+ (r.data.totalcount==null?0:r.data.totalcount) +'</td>'+
                            
                            '<td class="list_date">최근 연재일<br> '+last_dt+'</td>'+
                            '<td>연재화수 : '+ (r.data.storycount==null?0:r.data.storycount) +'</td>'+
                            '<td></td>'+
                            '<td><button class="style_button modify_btn" data-seq="'+ result.list[i].no_seq +'">소설 정보 수정</button></td>' +
                        '</tr>';
                        
                        $(".novel_list tbody").append(tag) ;
                        $(".novel_list tbody td").css("height","35px") ;
                        $(".img_area").css("width","70px") ;
                        $(".img_area").css("height","100px") ;
                        $(".img_box").css("width","70px") ;
                        $(".img_box").css("height","100px") ;
                        $(".img_box").css("background-size","100% 100%") ;
                        $(".img_box").css("background-repeat","no-repeat") ;
                        // $(".img_box").css("width","40px") ;
                        $(".story_reg_btn").click(function()
                        {
                            location.href="/novel/story/reg?seq="+$(this).attr("data-seq") ;
                        })
                    }
                })
            }
        }
    })

})