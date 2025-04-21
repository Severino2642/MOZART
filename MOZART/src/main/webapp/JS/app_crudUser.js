var listUser = document.querySelector(".list-item");

function generateItem(listeItem){
    while (listUser.firstChild) {
        listUser.removeChild(listUser.firstChild);
    }
    for (let i=0; i<listeItem.length ; i++){
        // listArtiste.innerHTML = "<div class='item'>" +
        //                             "<a href=''><img src='../../pdp/"+listeItem[i]+"'></a>"
        //                         "</div>";
        listUser.innerHTML += "<div class=\"item\">\n" +
            "                    <a href=\"\">\n" +
            "                        <div class=\"image\" style=\"background-image: url('../../pdp/"+listeItem[i].pdp+"')\"></div>\n" +
            "                    </a>\n" +
            "                    <div class=\"item-info\">\n" +
            "                        <h3>"+listeItem[i].pseudo+"</h3>\n" +
            "                        <p><i class=\"far fa-clock\"></i>"+listeItem[i].dtAjout+"</p>\n" +
            "                    </div>\n" +
            "                    <div class=\"item-controller\">\n" +
            "                        <a href=\"PrepareUpdate.UserList?id="+listeItem[i].id+"\"><i class=\"fa fa-edit\"></i></a>\n" +
            "                        <a href=\"DeleteUser.UserCrud?id="+listeItem[i].id+"\"><i class=\"fa fa-trash-alt\"></i></a>\n" +
            "                    </div>\n" +
            "                </div>";
    }
}
function submitForm(form) {
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Traitement de la réponse du serveur si nécessaire
            // console.log(xhr.responseText);
            var listeItem = JSON.parse(xhr.responseText);
            generateItem(listeItem);
        }
    };
    xhr.open("POST", "Search.UserList", true);
    xhr.send(formData);
}
var form_pub = document.getElementById("search-form");

form_pub.addEventListener("submit", function (event) {
event.preventDefault(); // évite de faire le submit par défaut

submitForm(form_pub);
});