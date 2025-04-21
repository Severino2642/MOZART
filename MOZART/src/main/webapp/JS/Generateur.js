var container = document.querySelector("#sac");
var searchForm = document.querySelector(".header form");
let index = 0;
var user = document.querySelector(".user-img");

function LoadingPage(option){
    if (option == 1) {
        document.querySelector(".loader").style.display="flex";
        document.querySelector(".split_right").style.overflowY="hidden";
    }
    if (option ==0) {
        document.querySelector(".loader").style.display="none";
        document.querySelector(".split_right").style.overflowY="auto";
    }
}
function setSearchVisibility(section){
    if(section.getAttribute("cssLink")=="../../CSS/search.css"){
        searchForm.style.display="block";
    }
    if(section.getAttribute("cssLink")!="../../CSS/search.css"){
        searchForm.style.display="none";
    }
}
function activeSwicth(){
    var listSection = document.querySelectorAll("#sac section");
    swicth_count = listSection.length-1;
    swicth_prev.setAttribute("class",".not-active");
    swicth_next.setAttribute("class",".swicth-rigth");
}
function setHidden(){
    var listSection = document.querySelectorAll("#sac section");
    for (var i=0; i<listSection.length ;i++){
        listSection[i].style.display="none";
    }
}
function setCSS(link){
    var cssLink = document.querySelector("#css-link");
    cssLink.setAttribute("href" , link);
}
function removeSection(){
    var listSection = document.querySelectorAll("#sac section");
    for (var i=1;i<listSection.length ; i++){
        container.removeChild(listSection[i]);
    }
}
function generateHome(){
    searchForm.style.display="none";
    removeSection();
    var listSection = document.querySelectorAll("#sac section");
    listSection[0].style.display="block";
    swicth_count=0;
    setCSS(listSection[0].getAttribute("cssLink"));
}
function generateSearch(){
    var new_section = document.createElement("section");
    new_section.setAttribute("cssLink","../../CSS/search.css");
    searchForm.style.display="flex";
    new_section.innerHTML = "<div class=\"container\">\n" +
        "                       <div class=\"container-nav\">\n" +
                    "                <nav>\n" +
                    "                    <a onclick='generateAllResult()'>All</a>\n" +
                    "                    <a onclick='generateArtisteResult()'>Artists</a>\n" +
                    "                    <a onclick='generateAlbumResult()'>Albums</a>\n" +
                    "                    <a onclick='generateSongResult()'>Songs</a>\n" +
                    "                </nav>\n" +
                 "             </div>\ <div id='search-container'></div>"+
            "               </div>";
    removeSection();
    setHidden();
    container.appendChild(new_section);
    activeSwicth();
    setCSS("../../CSS/search.css");
}

function generateSongPage(songDetails){
    var new_section = document.createElement("section");
    new_section.setAttribute("cssLink","../../CSS/playlist.css");
    searchForm.style.display="none";
    var htmlCode = "<div class=\"playlist\">\n" +
                    "            <div class=\"playlist-info\">\n" +
                    "                <div class=\"image\" style=\"background-image: url('../../couverture/"+songDetails.music.couverture+"');\"></div>\n" +
                    "                <div class=\"title\">\n" +
                    "                    <p>Single</p>\n" +
                    "                    <h1>"+songDetails.music.title+"</h1>\n" +
                    "                    <p><strong>"+songDetails.music.Author+" <i class=\"fa fa-circle\"></i> "+songDetails.music.dt_pub.split('-')[0]+" </strong>, "+songDetails.nbListener+" listeners</p>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div style=\"background-image: linear-gradient(to top , transparent , rgba(0, 0, 0, 0.37));\">\n" +
                    "                <div class=\"playlist-controller\">\n" +
                    "                    <nav>\n" +
                    "                        <a onclick='addSong("+JSON.stringify(songDetails)+")' class=\"play-btn\"><i class=\"fa fa-play\"></i></a>\n" +
                    "                        <a><i id='favorite-bt-song' type='SONG' iditem='"+songDetails.music.id+"' class=\"far fa-heart\"></i></a>\n" +
                    "                        <a><i class=\"fa fa-ellipsis-h\"></i></a>\n" +
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
                    "                                    <th class=\"time\"><i class=\"far fa-clock\"></i></th>\n" +
                    "                                </tr>\n" +
                    "                            </thead>";
    var titleAlbum = "none";
    if (songDetails.album != null){
        titleAlbum = songDetails.album.title;
    }
    htmlCode += "                                <tbody>\n" +
                    "                                <tr onclick='addSong("+JSON.stringify(songDetails)+")'>\n" +
                    "                                    <td class=\"id\">\n" +
                    "                                        <p>1</p>\n" +
                    "                                        <i class=\"fa fa-play\"></i>\n" +
                    "                                    </td>\n" +
                    "                                    <td class=\"title-song\">\n" +
                    "                                        <div class=\"image\" style=\"background-image: url('../../couverture/"+songDetails.music.couverture+"');\"></div>\n" +
                    "                                        <div>\n" +
                    "                                            <h3>"+songDetails.music.title+"</h3>\n" +
                    "                                            <p>"+songDetails.music.Author+"</p>\n" +
                    "                                        </div>\n" +
                    "                                    </td>\n" +
                    "                                    <td>"+titleAlbum+"</td>\n" +
                    "                                    <td songLink='"+songDetails.music.contenue+"' class=\"time\">2:30</td>\n" +
                    "                                </tr>\n" +
                    "                            </tbody> "
                    "                        </table>" +
                    "                   </div>";
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
    LoadingPage(0);
}

function generateArtistePage(artisteDetails){
    var new_section = document.createElement("section");
    new_section.setAttribute("cssLink","../../CSS/artiste.css");
    searchForm.style.display="none";
    var htmlCode = "";
    htmlCode += "<div class=\"artiste\">" +
        "            <div class=\"artiste-info\" style=\"background-image: url('../../pdc/"+artisteDetails.artiste.pdc+"');\">\n" +
            "                <div class=\"title\">\n" +
            "                    <div>\n" +
            "                        <p><i class=\"fa fa-check-circle\" style=\"color: rgb(0, 62, 155);\"></i> Verified Artist</p>\n" +
            "                        <h1>"+artisteDetails.artiste.pseudo+"</h1>\n" +
            "                        <p><strong>"+artisteDetails.fans.length+"</strong> followers</p>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "         </div>"+
    "                 <div style=\"background-image: linear-gradient(to top , transparent , rgb(33, 33, 33));\">\n" +
            "                <div class=\"artiste-controller\">\n" +
            "                    <nav>\n" +
            "                        <a onclick='addListSong("+JSON.stringify(artisteDetails.songs)+")' class=\"play-btn\"><i class=\"fa fa-play\"></i></a>\n" +
            "                        <a id='bt-follow-artiste'>Follow</a>\n" +
            "                    </nav>\n" +
            "                </div>"+
    "                       <div class=\"artiste-container\">";
    var nbSingle = 0;
    if (artisteDetails.songs.length>0){
        htmlCode += "<div class=\"list-title\">\n" +
"                        <h2>Popular</h2>\n" +
"                        <a>Show all</a>\n" +
            "        </div>"+
        "            <div class=\"table\">\n" +
            "             <table cellspacing=\"0\">"+
        "                       <tbody>";
        for (var i=0 ; i<artisteDetails.songs.length && i<4 ; i++){
            if (artisteDetails.songs[i].album == null ){
                nbSingle++
            }
            htmlCode += "<tr onclick='addSong("+JSON.stringify(artisteDetails.songs[i])+")'>\n" +
                "                             <td class=\"id\">\n" +
                "                                 <p>"+(i+1)+"</p>\n" +
                "                                  <i class=\"fa fa-play\"></i>\n" +
                "                              </td>\n" +
                "                              <td class=\"title-song\">\n" +
                "                                  <div class=\"image\" style=\"background-image: url('../../couverture/"+artisteDetails.songs[i].music.couverture+"');width: 45px;height: 45px;border-radius: 5px;background-position: center;background-size: cover;\"></div>\n" +
                "                                  <div>\n" +
                "                                       <h3>"+artisteDetails.songs[i].music.title+"</h3>\n" +
                "                                  </div>\n" +
                "                               </td>\n" +
                "                               <td>"+artisteDetails.songs[i].nbListener+"</td>\n" +
                "                               <td class=\"like-bt\"><i id='favorite-bt-song' type='SONG' iditem='"+artisteDetails.songs[i].music.id+"' class=\"far fa-heart\"></i></td>\n" +
                "                               <td class=\"time\" songLink='"+artisteDetails.songs[i].music.contenue+"'></td>\n" +
                "                               <td class=\"option-bt\"><i class=\"fa fa-ellipsis-h\"></i></td>\n" +
                "          </tr>";
        }
        htmlCode += "           </tbody> \n" +
            "               </table>\n" +
            "         </div>";
    }

    if (artisteDetails.Albums.length>0){
        htmlCode += "<div class=\"list-title\">\n" +
            "             <h2>Albums</h2>\n" +
            "         </div>\n" +
            "         <div class=\"list-item\">";
        for (var i=0 ; i<artisteDetails.Albums.length ; i++){
            htmlCode += "<a onclick='PrintAlbum("+JSON.stringify(artisteDetails.Albums[i].id)+")'>\n" +
                "                            <div class=\"item\">\n" +
                "                                <div class=\"icon-hover\">\n" +
                "                                    <i class=\"fa fa-play\"></i>\n" +
                "                                </div>\n" +
                "                                <div class=\"image\" style=\"background-image: url('../../couverture/"+artisteDetails.Albums[i].couverture+"');\">\n" +
                "                \n" +
                "                                </div>\n" +
                "                                <div class=\"item-info\">\n" +
                "                                    <h3>"+artisteDetails.Albums[i].title+"</h3>\n" +
                "                                    <p>"+artisteDetails.artiste.pseudo+"</p>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "         </a>";
        }
        htmlCode += "</div>";
    }
    if (nbSingle>0){
        htmlCode += "<div class=\"list-title\">\n" +
            "             <h2>Singles</h2>\n" +
            "         </div>\n" +
            "         <div class=\"list-item\">";
        for (var i=0 ; i<artisteDetails.songs.length ; i++){
            if (artisteDetails.songs[i].album == null){
                htmlCode += "<a onclick='PrintSong("+artisteDetails.songs[i].music.id+")'>\n" +
                    "                            <div class=\"item\">\n" +
                    "                                <div class=\"icon-hover\">\n" +
                    "                                    <i class=\"fa fa-play\"></i>\n" +
                    "                                </div>\n" +
                    "                                <div class=\"image\" style=\"background-image: url('../../couverture/"+artisteDetails.songs[i].music.couverture+"');\">\n" +
                    "                \n" +
                    "                                </div>\n" +
                    "                                <div class=\"item-info\">\n" +
                    "                                    <h3>"+artisteDetails.songs[i].music.title+"</h3>\n" +
                    "                                    <p>"+artisteDetails.songs[i].music.Author+"</p>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "         </a>";
            }
        }
        htmlCode += "</div>";
    }
    htmlCode += "</div>\n" +
        "        </div>\n" +
        "        </div>";
    new_section.innerHTML = htmlCode;
    setHidden();
    container.appendChild(new_section);
    addMusicDuration(new_section);
    verifFavoriteSong(new_section,user.getAttribute("attr"));
    verifFollow(artisteDetails.artiste.id,user.getAttribute("attr"),new_section.querySelector("#bt-follow-artiste"));
    activeSwicth();
    setCSS("../../CSS/artiste.css");
    LoadingPage(0);
}
function addMusicDuration(section){
    var allRows = section.querySelectorAll(".table tbody tr .time")
    var allSong = [];
    var body = document.querySelector(".load-song");
    for (var i=0 ; i<allRows.length ; i++){
        body.innerHTML += "<audio src='../../music/"+allRows[i].getAttribute("songLink")+"'></audio>";
    }
    setTimeout(() => {
        var allSong = document.querySelectorAll(".load-song audio");
        for (var i = 0; i<allSong.length ; i++){
            var musicDuration = formatTime(allSong[i].duration);
            allRows[i].innerHTML=musicDuration;
        }
        body.innerHTML = "";
    }, 300)
}
function generateAlbumPage(albumDetails){
    var new_section = document.createElement("section");
    new_section.setAttribute("cssLink","../../CSS/album.css");
    searchForm.style.display="none";
    var htmlCode = "<div class=\"album\">\n" +
                    "            <div class=\"album-info\">\n" +
                    "                <div class=\"image\" style=\"background-image: url('../../couverture/"+albumDetails.album.couverture+"');\"></div>\n" +
                    "                <div class=\"title\">\n" +
                    "                    <p>Album</p>\n" +
                    "                    <h1>"+albumDetails.album.title+"</h1>\n" +
                    "                    <p><strong>"+albumDetails.artiste.pseudo+" </strong> <i class=\"fa fa-circle\"></i> "+albumDetails.album.dt_creation.split('-')[0]+" <i class=\"fa fa-circle\"></i> "+albumDetails.songs.length+" songs,about 2hr 30min</p>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div style=\"background-image: linear-gradient(to top , transparent , rgba(0, 0, 0, 0.37));\">\n" +
                    "                <div class=\"album-controller\">\n" +
                    "                    <nav>\n" +
                    "                        <a onclick='addListSong("+JSON.stringify(albumDetails.songs)+")' class=\"play-btn\"><i class=\"fa fa-play\"></i></a>\n" +
                    "                        <a><i id='favorite-bt-song' type='ALBUM' iditem='"+albumDetails.album.id+"' class=\"far fa-heart\"></i></a>\n" +
                    "                        <a href=\"\"><i class=\"fa fa-ellipsis-h\"></i></a>\n" +
                    "                    </nav>\n" +
                    "                </div>\n" +
                    "                <div class=\"album-container\">\n" +
                    "                    <div class=\"table\">\n" +
                    "                        <table cellspacing=\"0\">\n" +
                    "                            <thead>\n" +
                    "                                <tr>\n" +
                    "                                    <th class=\"id\">#</th>\n" +
                    "                                    <th>Title</th>\n" +
                    "                                    <th></th>\n" +
                    "                                    <th class=\"time\"><i class=\"far fa-clock\"></i></th>\n" +
                    "                                    <th></th>\n" +
                    "                                </tr>\n" +
                    "                            </thead>\n" +
                    "                            <tbody>\n";
    for (var i = 0 ; i<albumDetails.songs.length ; i++){
        htmlCode += "<tr onclick='addSong("+JSON.stringify(albumDetails.songs[i])+")'>\n" +
            "            <td class=\"id\">\n" +
            "                <p>"+(i+1)+"</p>\n" +
            "                <i class=\"fa fa-play\"></i>\n" +
            "            </td>\n" +
            "            <td class=\"title-song\">\n" +
            "                <div class=\"image\" style=\"background-image: url('../../couverture/"+albumDetails.songs[i].music.couverture+"');\"></div>\n" +
            "                <div>\n" +
            "                    <h3>"+albumDetails.songs[i].music.title+"</h3>\n" +
            "                    <p>"+albumDetails.songs[i].music.Author+"</p>\n" +
            "                </div>\n" +
            "            </td>\n" +
            "            <td class=\"like-bt\"><i id='favorite-bt-song' type='SONG' iditem='"+albumDetails.songs[i].music.id+"' class=\"far fa-heart\"></i></td>\n" +
            "            <td class=\"time\" songLink='"+albumDetails.songs[i].music.contenue+"'>00:00</td>\n" +
            "            <td class=\"option-bt\"><i class=\"fa fa-ellipsis-h\"></i></td>\n" +
            "            </tr>";
    }
    htmlCode += "       </tbody> \n" +
        "           </table>\n" +
        "        </div>";

    htmlCode += "       </div>\n" +
        "            </div>\n" +
        "        </div>";
    new_section.innerHTML = htmlCode;
    setHidden();
    container.appendChild(new_section);
    addMusicDuration(new_section);
    verifFavoriteSong(new_section,user.getAttribute("attr"));
    activeSwicth();
    setCSS("../../CSS/album.css");
    LoadingPage(0);
}
