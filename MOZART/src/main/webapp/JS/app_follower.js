function follow(idArtiste,idUser,bt_follow) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "InsertFollower.FollowerCrud?idUser="+idUser+"&idArtiste="+idArtiste, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            // var artisteDetails = JSON.parse(xhr.responseText);
            // generateArtistePage(artisteDetails);
            bt_follow.setAttribute("onclick","infollow("+idArtiste+","+idUser+",this)");
            bt_follow.innerHTML = "Following";
        }
    };
    xhr.send();
}
function infollow(idArtiste,idUser,bt_follow) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "DeleteFollower.FollowerCrud?idUser="+idUser+"&idArtiste="+idArtiste, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            // var artisteDetails = JSON.parse(xhr.responseText);
            // generateArtistePage(artisteDetails);
            bt_follow.setAttribute("onclick","follow("+idArtiste+","+idUser+",this)");
            bt_follow.innerHTML = "Follow";
        }
    };
    xhr.send();
}
function verifFollow(idArtiste,idUser,bt_follow) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetFollowerByIdArtisteAndUser.FollowerList?idUser="+idUser+"&idArtiste="+idArtiste, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            var response = JSON.parse(xhr.responseText);
            if (response == null){
                bt_follow.setAttribute("onclick","follow("+idArtiste+","+idUser+",this)");
                bt_follow.innerHTML = "Follow";
            }
            if (response != null){
                bt_follow.setAttribute("onclick","infollow("+idArtiste+","+idUser+",this)");
                bt_follow.innerHTML = "Following";
            }
        }
    };
    xhr.send();
}