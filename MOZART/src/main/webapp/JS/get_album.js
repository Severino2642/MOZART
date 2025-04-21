function PrintAlbum(idAlbum) {
    LoadingPage(1);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetAlbumDetails.AlbumList?idAlbum="+idAlbum, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            var albumDetails = JSON.parse(xhr.responseText);
            generateAlbumPage(albumDetails);
        }
    };
    xhr.send();
}