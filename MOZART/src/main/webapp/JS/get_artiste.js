function PrintArtiste(idArtiste) {
    LoadingPage(1);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GetArtisteDetails.ArtisteList?id="+idArtiste, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parsez la réponse JSON en objet JavaScript
            var artisteDetails = JSON.parse(xhr.responseText);
            generateArtistePage(artisteDetails);
        }
    };
    xhr.send();
}