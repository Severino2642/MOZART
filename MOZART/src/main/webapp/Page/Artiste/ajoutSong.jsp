<%@ page import="table.Artiste" %>
<%@ page import="table.Categorie" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/19/2024
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Artiste artiste = (Artiste) request.getSession().getAttribute("artiste");
    Categorie [] allCategorie = new Categorie().findAll();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOZART</title>
    <link rel="stylesheet" href="../../fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/regular.css"  />
    <link rel="stylesheet" type="text/css" href="../../CSS/ajout_single.css">
</head>
<body>
<div class="container">
    <div class="content">
        <div class="logo">
            <img src="../../image/mozart2.png">
        </div>
        <h1>Publier un nouveau single pour vos followers</h1>
        <p>Created by <strong>SAMSOUDINE Severino Divaraly Christian</strong> <i class="far fa-copyright"></i> </p>
        <nav>
            <a href="crud_song.jsp"><i class="fa fa-door-open"></i> Back</a>
        </nav>
    </div>
    <div class="formulaire">
        <form action="InsertSong.SongCrud" method="post" enctype="multipart/form-data">
            <input type="hidden" name="idArtiste" value="<%=artiste.getId()%>">
            <div class="photo">
                <div class="couverture">
                    <label>
                        <input type="file" name="couverture" required style="display: none;" accept="image/*" onchange="showBackground(this)">
                        <i class="fa fa-image" id="pdp"></i>
                    </label>
                </div>
            </div>
            <div class="music">
                <label>
                    <input type="file" name="contenue" required style="display: none;" accept="audio/*">
                    <p> <i class="fa fa-music" id="music"></i> Your Song</p>
                </label>
            </div>
            <div class="info-perso">
                <div>
                    <input type="text" name="title" placeholder="Title" required>
                    <input type="text" name="author" placeholder="Author" required>
                </div>
                <div>
                    <select name="idCategorie">
                        <% for (int i=0 ; i< allCategorie.length ; i++) { %>
                            <option value="<%=allCategorie[i].getId()%>"><%=allCategorie[i].getNom()%></option>
                        <% } %>
                    </select>
                </div>
            </div>
            <div class="bt-submit">
                <button type="submit">Publier</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="../../JS/showImage.js"></script>
</body>
</html>