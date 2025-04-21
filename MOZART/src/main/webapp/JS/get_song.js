function PrintSong(idSong) {
    LoadingPage(1);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetSongDetails.SongList?idSong="+idSong, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            var songDetails = JSON.parse(xhr.responseText);
            generateSongPage(songDetails);
        }
    };
    xhr.send();
}