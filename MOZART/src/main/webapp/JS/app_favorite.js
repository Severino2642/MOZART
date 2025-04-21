function addfavorite(idUser,bt_favorite) {
    var idItem = bt_favorite.getAttribute("iditem");
    var type = bt_favorite.getAttribute("type");
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "InsertFavorite.FavoriteCrud?idUser="+idUser+"&idItem="+idItem+"&type="+type, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            // var artisteDetails = JSON.parse(xhr.responseText);
            // generateArtistePage(artisteDetails);
            bt_favorite.setAttribute("onclick","delfavorite("+idUser+",this)");
            bt_favorite.setAttribute("class","fa fa-heart");
        }
    };
    xhr.send();
}
function delfavorite(idUser,bt_favorite) {
    var idItem = bt_favorite.getAttribute("iditem");
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "DeleteFavorite.FavoriteCrud?idUser="+idUser+"&idItem="+idItem, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            // var artisteDetails = JSON.parse(xhr.responseText);
            // generateArtistePage(artisteDetails);
            bt_favorite.setAttribute("onclick","addfavorite("+idUser+",this)");
            bt_favorite.setAttribute("class","far fa-heart");
        }
    };
    xhr.send();
}
function verifFavoriteSong(section,idUser) {
    var listBt = section.querySelectorAll("#favorite-bt-song");
    var idItem = "";
    for (var i=0 ; i<listBt.length ; i++){
        if(i==0){
            idItem += ""+listBt[i].getAttribute("iditem");
        }
        if(i>0){
            idItem += ";"+listBt[i].getAttribute("iditem");
        }
    }
    console.log(idItem);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetFavoriteByIdItemAndUser.FavoriteList?idUser="+idUser+"&idItem="+idItem, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            var fav = JSON.parse(xhr.responseText);
            console.log(fav.length);
            for (var i=0 ; i<fav.length && listBt.length == fav.length ; i++){
                if (fav[i]== null){
                    listBt[i].setAttribute("onclick","addfavorite("+idUser+",this)");
                    listBt[i].setAttribute("class","far fa-heart");
                }
                if (fav[i]!= null){
                    listBt[i].setAttribute("onclick","delfavorite("+idUser+",this)");
                    listBt[i].setAttribute("class","fa fa-heart");
                }
            }
        }
    };
    xhr.send();
}
function affFavoriteAlbum(favoriteAlbum,nbLikedSong,username){
    var htmlCode = "<a onclick='affFavoriteSong("+JSON.stringify(lfs)+","+JSON.stringify(username)+")'>\n" +
                "                <div class=\"nav-bottom-content\">\n" +
                "                    <div class=\"image\"><i class=\"fa fa-heart\"></i></div>\n" +
                "                    <div class=\"content-info\">\n" +
                "                        <h3>Liked Song</h3>\n" +
                "                        <p><strong><i class=\"fa fa-thumbtack\"></i> </strong> Playlist <i class=\"fa fa-circle\"></i> "+nbLikedSong+" songs</p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "          </a>";
    for (var i = 0 ; i<favoriteAlbum.length ; i++){
        htmlCode += "        <a onclick='PrintAlbum("+JSON.stringify(favoriteAlbum[i].album.id)+")'>\n" +
            "                    <div class=\"nav-bottom-content\">\n" +
            "                        <div class=\"image\" style=\"background-image: url('../../couverture/"+favoriteAlbum[i].album.couverture+"');\"></div>\n" +
            "                        <div class=\"content-info\">\n" +
            "                            <h3>"+favoriteAlbum[i].album.title+"</h3>\n" +
            "                            <p>Album</p>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </a>";
    }
    document.querySelector(".nav-bottom-container").innerHTML = htmlCode;
}

function affFavoriteArtiste(favoriteArtiste,nbLikedSong,username){
    var htmlCode = "<a onclick='affFavoriteSong("+JSON.stringify(lfs)+","+JSON.stringify(username)+")'>\n" +
                "                <div class=\"nav-bottom-content\">\n" +
                "                    <div class=\"image\"><i class=\"fa fa-heart\"></i></div>\n" +
                "                    <div class=\"content-info\">\n" +
                "                        <h3>Liked Song</h3>\n" +
                "                        <p><strong><i class=\"fa fa-thumbtack\"></i> </strong> Playlist <i class=\"fa fa-circle\"></i> "+nbLikedSong+" songs</p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "          </a>";
    for (var i = 0 ; i<favoriteArtiste.length ; i++){
        htmlCode += "       <a onclick='PrintArtiste("+favoriteArtiste[i].artiste.id+")'>\n" +
            "                    <div class=\"nav-bottom-content\">\n" +
            "                        <div class=\"image artiste\" style=\"background-image: url('../../pdp/"+favoriteArtiste[i].artiste.pdp+"');\"></div>\n" +
            "                        <div class=\"content-info\">\n" +
            "                            <h3>"+favoriteArtiste[i].artiste.pseudo+"</h3>\n" +
            "                            <p>Artist</p>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </a>";
    }
    document.querySelector(".nav-bottom-container").innerHTML = htmlCode;
}

function affFavoriteSong(favoriteSong,username){
    var new_section = document.createElement("section");
    new_section.setAttribute("cssLink","../../CSS/playlist.css");
    searchForm.style.display="none";
    var htmlCode = "<div class=\"playlist\">\n" +
        "            <div class=\"playlist-info\">\n" +
        "                <div class=\"image\" style=\"display: flex;justify-content:center;align-items: center;color: #eeeeee;font-size: 80px;background-image: linear-gradient(to left , white , rgb(89, 53, 196));\"><i class=\"fa fa-heart\"></i></div>\n" +
        "                <div class=\"title\">\n" +
        "                    <p>Playlist</p>\n" +
        "                    <h1>Liked Songs</h1>\n" +
        "                    <p><strong>"+username+" <i class=\"fa fa-circle\"></i> </strong> "+favoriteSong.length+" songs</p>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div style=\"background-image: linear-gradient(to top , transparent , rgba(0, 0, 0, 0.37));\">\n" +
        "                <div class=\"playlist-controller\">\n" +
        "                    <nav>\n" +
        "                        <a onclick='playLikedSong("+JSON.stringify(favoriteSong)+")' class=\"play-btn\"><i class=\"fa fa-play\"></i></a>\n" +
        "                    </nav>\n" +
        "                </div>\n" +
        "                <div class=\"playlist-container\">\n" +
        "                    <div class=\"table\">\n" +
        "                        <table cellspacing=\"0\">\n" +
        "                            <thead>\n" +
        "                                <tr>\n" +
        "                                    <th class=\"id\">#</th>\n" +
        "                                    <th>Title</th>\n" +
        "                                    <th>Album</th>\n" +
        "                                    <th></th>\n" +
        "                                    <th class=\"time\"><i class=\"far fa-clock\"></i></th>\n" +
        "                                    <th></th>" +
        "                                </tr>\n" +
        "                            </thead>";
    htmlCode += "<tbody>\n";
    for (var i=0 ; i<favoriteSong.length ; i++){
        var titleAlbum = "none";
        if (favoriteSong[i].music.album != null){
            titleAlbum = favoriteSong[i].music.album.title;
        }
        htmlCode += "<tr onclick='addSong("+JSON.stringify(favoriteSong[i].music)+")'>\n" +
            "             <td class=\"id\">\n" +
            "                   <p>1</p>\n" +
            "                   <i class=\"fa fa-play\"></i>\n" +
            "             </td>\n" +
            "             <td class=\"title-song\">\n" +
            "                <div class=\"image\" style=\"background-image: url('../../couverture/"+favoriteSong[i].music.music.couverture+"');\"></div>\n" +
            "                         <div>\n" +
            "                              <h3>"+favoriteSong[i].music.music.title+"</h3>\n" +
            "                              <p>"+favoriteSong[i].music.music.Author+"</p>\n" +
            "                         </div>\n" +
            "             </td>\n" +
            "             <td>"+titleAlbum+"</td>\n" +
            "             <td class=\"like-bt\"><i id='favorite-bt-song' type='SONG' iditem='"+favoriteSong[i].music.music.id+"' class=\"far fa-heart\"></i></td>\n" +
            "             <td songLink='"+favoriteSong[i].music.music.contenue+"' class=\"time\">2:30</td>\n" +
            "            <td class=\"option-bt\"><i class=\"fa fa-ellipsis-h\"></i></td>\n" +
            "         </tr>\n";
    }
    htmlCode += "</tbody> " +
    "            </table>" +
    "            </div>";
    htmlCode += "           </div>\n" +
        "               </div>\n" +
        "           </div>";
    new_section.innerHTML = htmlCode;
    setHidden();
    container.appendChild(new_section);
    addMusicDuration(new_section);
    verifFavoriteSong(new_section,user.getAttribute("attr"));
    activeSwicth();
    setCSS("../../CSS/playlist.css");
}