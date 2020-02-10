function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    var root = document.getElementById('anchor');
    console.log(xmlHttp.responseText);
    var items = JSON.parse(xmlHttp.responseText);
    for(var i = 0; i < items.length; i++) {
        var div = document.createElement("div");
        div.innerHTML = items[i].roomName + '                  (' +items[i].players.length+'/'+ items[i].capacity + ')';
        div.classList.add('roomList');
        var id = items[i].id;
        div.onclick= function( id ){
            return function (){addRoomHandler(id)}}(id);
        root.appendChild(div);
    }
    return xmlHttp.responseText;
};


addRoomHandler = function addPlayerToRoom(roomId){
    var targetUrl = 'http://localhost:7788/v1/room/'+roomId+'/addPlayer';
    console.log('Making request to '+ targetUrl + ' RoomId= ' + roomId );
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "PUT", targetUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    console.log('Response: ' + xmlHttp.responseText);
    console.log(xmlHttp.status);
    if (xmlHttp.status === 200){
        window.location.replace("http://localhost:7788/chat.html?roomId="+roomId);
    }
};
