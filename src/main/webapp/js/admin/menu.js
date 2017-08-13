$(document).ready(function(){
    $(window).scroll(function(){
        var top = $(document).scrollTop();          //�����������ȡ�������ĸ߶�
        var menu = $("#menu");                      //���������ץȡ#menu
        var items = $("#content").find(".item");    //�������������.item

        var curId = "";                             //�����������ǰ���ڵ�¥��item #id 

        items.each(function(){
            var m = $(this);                        //�����������ȡ��ǰ��
            var itemsTop = m.offset().top;        //�����������ȡ��ǰ���topƫ����
            if(top > itemsTop-100){
                curId = "#" + m.attr("id");
            }else{
                return false;
            }
        });

        //����Ӧ��¥������cur,ȡ������¥���cur
        var curLink = menu.find(".cur");
        if( curId && curLink.attr("href") != curId ){
            curLink.removeClass("cur");
            menu.find( "[href=" + curId + "]" ).addClass("cur");
        }
        // console.log(top);
    });
});