var tableAdded = document.querySelector(".table.added table tbody");
var listSong = document.querySelector(".table.notAdded table tbody");

var allSong = [];
var albumSong = [];
var allSingle = [];
var idSongAdded = document.querySelector("#idSong");
function addIdSong(){
    var allrow = tableAdded.getElementsByTagName('tr');
    idSongAdded.value="";
    for (var i = 0; i < allrow.length; i++) {
        if (idSongAdded.value != ""){
            idSongAdded.value += ";"+allrow[i].cells[0].textContent;
        }
        if (idSongAdded.value == ""){
            idSongAdded.value += ""+allrow[i].cells[0].textContent;
        }
    }
}
function RemoveSong(row){
    var idSong = row.getAttribute("attr");

    listSong.innerHTML += "<tr onclick='AddSong(this)' attr='"+idSong+"'>\n" +
        "                        <td class=\"id\">"+allSong[idSong].id+"</td>\n" +
        "                        <td class=\"title-song\">\n" +
        "                            <img src=\"../../couverture/"+allSong[idSong].couverture+"\">\n" +
        "                            <div>\n" +
        "                                <h3>"+allSong[idSong].title+"</h3>\n" +
        "                                <p>"+allSong[idSong].Author+"</p>\n" +
        "                            </div>\n" +
        "                        </td>\n" +
        "                        <td>"+allSong[idSong].dt_pub+"</td>\n" +
        "                   </tr>";

    generateAdded(allSong);
    addIdSong();
}

function AddSong(row){
    var idSong = row.getAttribute("attr");

    tableAdded.innerHTML += "<tr onclick='RemoveSong(this)' attr='"+idSong+"'>\n" +
        "                        <td class=\"id\">"+allSong[idSong].id+"</td>\n" +
        "                        <td class=\"title-song\">\n" +
        "                            <img src=\"../../couverture/"+allSong[idSong].couverture+"\">\n" +
        "                            <div>\n" +
        "                                <h3>"+allSong[idSong].title+"</h3>\n" +
        "                                <p>"+allSong[idSong].Author+"</p>\n" +
        "                            </div>\n" +
        "                        </td>\n" +
        "                        <td>"+allSong[idSong].dt_pub+"</td>\n" +
        "                   </tr>";
    generateNotAdded(allSong);
    addIdSong();
}
function generateAdded(allSong){
    while (tableAdded.firstChild) {
        tableAdded.removeChild(tableAdded.firstChild);
    }
    var allrow = listSong.getElementsByTagName('tr');
    for (var i = 0;i<allSong.length ;i++){
        var isset = 0;
        for (var j = 0; j < allrow.length; j++) {
            if ( allrow[j].cells[0].textContent == allSong[i].id){
                isset = 1;
            }
        }
        if (isset == 0){
            tableAdded.innerHTML += "<tr onclick='RemoveSong(this)' attr='"+i+"'>\n" +
                "                        <td class=\"id\">"+allSong[i].id+"</td>\n" +
                "                        <td class=\"title-song\">\n" +
                "                            <img src=\"../../couverture/"+allSong[i].couverture+"\">\n" +
                "                            <div>\n" +
                "                                <h3>"+allSong[i].title+"</h3>\n" +
                "                                <p>"+allSong[i].Author+"</p>\n" +
                "                            </div>\n" +
                "                        </td>\n" +
                "                        <td>"+allSong[i].dt_pub+"</td>\n" +
                "                   </tr>";
        }
    }
}

function generateNotAdded(allSong){
    while (listSong.firstChild) {
        listSong.removeChild(listSong.firstChild);
    }
    var allrow = tableAdded.getElementsByTagName('tr');
    for (var i = 0;i<allSong.length ;i++){
        var isset = 0;
        for (var j = 0; j < allrow.length; j++) {
            if ( allrow[j].cells[0].textContent == allSong[i].id){
                isset = 1;
            }
        }
        if (isset == 0){
            listSong.innerHTML += "<tr onclick='AddSong(this)' attr='"+i+"'>\n" +
                "                        <td class=\"id\">"+allSong[i].id+"</td>\n" +
                "                        <td class=\"title-song\">\n" +
                "                            <img src=\"../../couverture/"+allSong[i].couverture+"\">\n" +
                "                            <div>\n" +
                "                                <h3>"+allSong[i].title+"</h3>\n" +
                "                                <p>"+allSong[i].Author+"</p>\n" +
                "                            </div>\n" +
                "                        </td>\n" +
                "                        <td>"+allSong[i].dt_pub+"</td>\n" +
                "                   </tr>";
        }
    }
}
function fetchDataSingle(idArtiste) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetSingleForArtiste.SongList?idArtiste="+idArtiste, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            allSingle = JSON.parse(xhr.responseText);
            allSong.push(...allSingle);
            console.log("isa : "+allSong.length);
            generateNotAdded(allSong);
        }
    };
    xhr.send();
}

function fetchDataAlbumSong(idAlbum) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetSongForAlbum.AlbumList?id="+idAlbum, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            albumSong = JSON.parse(xhr.responseText);
            allSong.push(...albumSong);
            console.log("isa : "+allSong.length);
            generateAdded(allSong);
            addIdSong();
        }
    };
    xhr.send();
}

// Appeler la fonction pour récupérer les données lors du chargement de la page, par exemple
document.addEventListener("DOMContentLoaded", function() {
    var idArtiste = document.querySelector("#idArtiste");
    fetchDataSingle(idArtiste.value);
    var idAlbum = document.querySelector("#idAlbum");
    fetchDataAlbumSong(idAlbum.value);
    // allSong = [...allSingle,...albumSong];


});
