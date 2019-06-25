var stompClient = null;

function setConnected(connected) {
    $("#subscribe").prop("disabled", connected);
    $("#unsubscribe").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/falcon');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Subscribed: ' + frame);
        stompClient.subscribe('/topic/message', function (content) {
            showGreeting(JSON.parse(content.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Unsubscribed");
}

function showGreeting(message) {
    $("#messages").append("<tr><td>" + message.timestamp  + ": "+  message.content + "</td></tr>" + "<tr><td>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#subscribe" ).click(function() { connect(); });
    $( "#unsubscribe" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});