var searchArtistes = [];
var searchPlaylists = [];
var searchAlbums = [];
var searchSongs = [];
function GetResultSearchForArtiste(form) {
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Traitement de la réponse du serveur si nécessaire
            // console.log(xhr.responseText);
            searchArtistes = JSON.parse(xhr.responseText);
            generateAllResult();
        }
    };
    xhr.open("POST", "Search.ArtisteList", true);
    xhr.send(formData);
}
function GetResultSearchForAlbums(form) {
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Traitement de la réponse du serveur si nécessaire
            // console.log(xhr.responseText);
            searchAlbums = JSON.parse(xhr.responseText);
            generateAllResult();
        }
    };
    xhr.open("POST", "SearchForUser.AlbumList", true);
    xhr.send(formData);
}
function GetResultSearchForSongs(form) {
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Traitement de la réponse du serveur si nécessaire
            // console.log(xhr.responseText);
            searchSongs = JSON.parse(xhr.responseText);
            generateAllResult();
        }
    };
    xhr.open("POST", "SearchForUser.SongList", true);
    xhr.send(formData);
}
var form_pub = document.getElementById("search-form");
var top = 0;
form_pub.addEventListener("submit", function (event) {
    event.preventDefault(); // évite de faire le submit par défaut
    GetResultSearchForSongs(form_pub);
    GetResultSearchForArtiste(form_pub);
    GetResultSearchForAlbums(form_pub);
});
function generateAllResult(){
    var searchContainer = document.querySelector("#search-container");
    while (searchContainer.firstChild){
        searchContainer.removeChild(searchContainer.firstChild);
    }
    if (searchSongs.length>0){
        searchContainer.innerHTML += "<div class=\"list-title\">\n" +
            "                           <h2>Songs</h2>\n" +
            "            </div>";
        var listSong = "";
        listSong += "<div class=\"list-song\">";
        for (var i = 0 ; i<searchSongs.length && i<4 ; i++){
            listSong += "<a onclick='PrintSong("+searchSongs[i].id+")'>\n" +
                "                    <div class=\"song\">\n" +
                "                        <div class=\"image\" style=\"background-image: url('../../couverture/"+searchSongs[i].couverture+"');\"></div>\n" +
                "                        <div class=\"title\">\n" +
                "                            <h3>"+searchSongs[i].title+"</h3>\n" +
                "                        </div>\n" +
                "                        <div class=\"icon-hover\">\n" +
                "                            <i class=\"fa fa-play-circle\"></i>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </a>\n";
        }
        listSong += "</div>";
        searchContainer.innerHTML += listSong;
    }
    if (searchArtistes.length>0){
        searchContainer.innerHTML += "<div class=\"list-title\">\n" +
            "                <h2>Artists</h2>\n" +
            "            </div>";
        var  listArtiste = "";
        listArtiste += "<div class=\"list-item\">";
        for (var i = 0 ; i<searchArtistes.length && i<4 ; i++){
            listArtiste += "<a onclick='PrintArtiste("+searchArtistes[i].id+")'>\n" +
                "                    <div class=\"item\">\n" +
                "                        <div class=\"image artiste\" style=\"background-image: url('../../pdp/"+searchArtistes[i].pdp+"');\">\n" +
                "        \n" +
                "                        </div>\n" +
                "                        <div class=\"item-info\">\n" +
                "                            <h3>"+searchArtistes[i].pseudo+"</h3>\n" +
                "                            <p>Artist</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </a>";
        }
        listArtiste += "</div>";
        searchContainer.innerHTML += listArtiste;
    }
    if (searchAlbums.length>0){
        searchContainer.innerHTML += "<div class=\"list-title\">\n" +
            "                <h2>Albums</h2>\n" +
            "            </div>";
        var listAlbum = "";
        listAlbum += "<div class=\"list-item\">";
        for (var i = 0 ; i<searchAlbums.length && i<4 ; i++){
            listAlbum += "<a onclick='PrintAlbum("+JSON.stringify(searchAlbums[i].id)+")'>\n" +
                "                    <div class=\"item\">\n" +
                "                        <div class=\"icon-hover\">\n" +
                "                            <i class=\"fa fa-play\"></i>\n" +
                "                        </div>\n" +
                "                        <div class=\"image\" style=\"background-image: url('../../couverture/"+searchAlbums[i].couverture+"');\">\n" +
                "        \n" +
                "                        </div>\n" +
                "                        <div class=\"item-info\">\n" +
                "                            <h3>"+searchAlbums[i].title+"</h3>\n" +
                "                            <p>Shyn</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </a>";
        }
        listAlbum += "</div>";
        searchContainer.innerHTML += listAlbum;
    }
}

function generateSongResult(){
    var searchContainer = document.querySelector("#search-container");
    while (searchContainer.firstChild){
        searchContainer.removeChild(searchContainer.firstChild);
    }
    if (searchSongs.length>0){
        searchContainer.innerHTML += "<div class=\"list-title\">\n" +
            "                           <h2>Songs</h2>\n" +
            "            </div>";
        var listSong = "";
        listSong += "<div class=\"list-song\">";
        for (var i = 0 ; i<searchSongs.length ; i++){
            listSong += "<a onclick='PrintSong("+searchSongs[i].id+")'>\n" +
                "                    <div class=\"song\">\n" +
                "                        <div class=\"image\" style=\"background-image: url('../../couverture/"+searchSongs[i].couverture+"');\"></div>\n" +
                "                        <div class=\"title\">\n" +
                "                            <h3>"+searchSongs[i].title+"</h3>\n" +
                "                        </div>\n" +
                "                        <div class=\"icon-hover\">\n" +
                "                            <i class=\"fa fa-play-circle\"></i>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </a>\n";
        }
        listSong += "</div>";
        searchContainer.innerHTML += listSong;
    }
}
function generateArtisteResult(){
    var searchContainer = document.querySelector("#search-container");
    while (searchContainer.firstChild){
        searchContainer.removeChild(searchContainer.firstChild);
    }
    if (searchArtistes.length>0){
        searchContainer.innerHTML += "<div class=\"list-title\">\n" +
            "                <h2>Artists</h2>\n" +
            "            </div>";
        var  listArtiste = "";
        listArtiste += "<div class=\"list-item\">";
        for (var i = 0 ; i<searchArtistes.length ; i++){
            listArtiste += "<a onclick='PrintArtiste("+searchArtistes[i].id+")'>\n" +
                "                    <div class=\"item\">\n" +
                "                        <div class=\"image artiste\" style=\"background-image: url('../../pdp/"+searchArtistes[i].pdp+"');\">\n" +
                "        \n" +
                "                        </div>\n" +
                "                        <div class=\"item-info\">\n" +
                "                            <h3>"+searchArtistes[i].pseudo+"</h3>\n" +
                "                            <p>Artist</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </a>";
        }
        listArtiste += "</div>";
        searchContainer.innerHTML += listArtiste;
    }
}
function generateAlbumResult(){
    var searchContainer = document.querySelector("#search-container");
    while (searchContainer.firstChild){
        searchContainer.removeChild(searchContainer.firstChild);
    }
    if (searchAlbums.length>0){
        searchContainer.innerHTML += "<div class=\"list-title\">\n" +
            "                <h2>Albums</h2>\n" +
            "            </div>";
        var listAlbum = "";
        listAlbum += "<div class=\"list-item\">";
        for (var i = 0 ; i<searchAlbums.length; i++){
            listAlbum += "<a onclick='PrintAlbum("+JSON.stringify(searchAlbums[i].id)+")'>\n" +
                "                    <div class=\"item\">\n" +
                "                        <div class=\"icon-hover\">\n" +
                "                            <i class=\"fa fa-play\"></i>\n" +
                "                        </div>\n" +
                "                        <div class=\"image\" style=\"background-image: url('../../couverture/"+searchAlbums[i].couverture+"');\">\n" +
                "        \n" +
                "                        </div>\n" +
                "                        <div class=\"item-info\">\n" +
                "                            <h3>"+searchAlbums[i].title+"</h3>\n" +
                "                            <p>Shyn</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </a>";
        }
        listAlbum += "</div>";
        searchContainer.innerHTML += listAlbum;
    }
}
