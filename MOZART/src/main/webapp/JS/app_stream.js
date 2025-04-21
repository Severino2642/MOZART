function addStream(idSong,idUser) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "InsertStream.StreamCrud?idUser="+idUser+"&idSong="+idSong, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("Stream inserted");
        }
    };
    xhr.send();
}