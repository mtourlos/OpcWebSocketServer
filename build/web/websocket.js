var socket = new WebSocket("ws://10.10.10.62:8080/OpcWebSockets/actions");
//alert("connected");
socket.onmessage = onMessage;


function onMessage(event) {
    //alert("message received");
    var data = JSON.parse(event.data);
    document.getElementById("alarmOverFrequency").innerHTML=data.alarmOverFrequency;
    document.getElementById("alarmOverVoltage").innerHTML=data.alarmOverVoltage;
    document.getElementById("alarmUnderFrequency").innerHTML=data.alarmUnderFrequency;
    document.getElementById("alarmUnderVoltage").innerHTML=data.alarmUnderVoltage;
    document.getElementById("mvBreakerClosed").innerHTML=data.mvBreakerClosed;
    document.getElementById("towerBreakerClosed").innerHTML=data.towerBreakerClosed;
    document.getElementById("kw").innerHTML=data.kw;
    document.getElementById("setpoint").innerHTML=data.setpoint;
    document.getElementById("setpointMode").innerHTML=data.setpointMode;
    document.getElementById("v1").innerHTML=data.v1;
    document.getElementById("windSpeed").innerHTML=data.windSpeed;
    document.getElementById("wg1Run").innerHTML=data.wg1Run;
    document.getElementById("wg1V1").innerHTML=data.wg1V1;
    document.getElementById("wg2Run").innerHTML=data.wg2Run;
    document.getElementById("wg2V1").innerHTML=data.wg2V1;
    var SendMessage = {
        action:"ok"
    };
    socket.send(JSON.stringify(SendMessage));
}

function stop(){
    var StopMessage = {
        action:"stop"
    };
    socket.send(JSON.stringify(StopMessage));
    action("Stop sended");
}

function ok(){
    var OkMessage = {
        action:"ok"
    };
    socket.send(JSON.stringify(OkMessage));
}

