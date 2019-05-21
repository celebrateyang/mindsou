/**
 * Created by 竹庆 on 2016/9/17.
 */

$(function(){
    var path = fullPath;
    console.log("path=====>"+path);
    var websocket;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + path + "ws");
        console.log(websocket.url);
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + path + "/ws");
    } else {
        websocket = new SockJS("http://" + path + "/ws/sockjs");
    }
    websocket.onopen = function(event) {
        console.log("WebSocket:已连接");
        console.log(event);
    };
    websocket.onmessage = function(event){
       // alert("有消息来！");
        console.log("before json==>" +event.data);
        var data = JSON.parse(event.data);
        console.log("after json==>"+data);
        var randomId =Math.floor(Math.random()*100000);
        var appendHtml = '<div class="accordion-group">'+
            '<div class="accordion-heading">'+
            '<a class="accordion-toggle" data-toggle="collapse" data-parent="message" href="#accordion-element-'+randomId+'"><span class="caret"></span>Mind搜发现一条新的匹配，单击打开！</a>'+
            '</div>'+
            '<div id="accordion-element-'+randomId+'" class="accordion-body collapse">'+
            '<div class="accordion-inner">'+
            data.text+
            '</div>'+
            '</div>'+
            '</div>';
        $("#message").append(appendHtml);
    };
    }
);