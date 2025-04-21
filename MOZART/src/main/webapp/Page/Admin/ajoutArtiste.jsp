<%@ page import="table.Genre" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/19/2024
  Time: 3:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Genre[] allGenre = new Genre().findAll();
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
    <link rel="stylesheet" type="text/css" href="../../CSS/ajoutArtiste.css">
</head>
<body>
<div class="container">
    <div class="content">
        <div class="logo">
            <img src="../../image/mozart2.png">
        </div>
        <h1>Faite decouvir a tout le monde votre music en tant qu'artiste sur <strong>MOZART</strong></h1>
        <p>Created by <strong>SAMSOUDINE Severino Divaraly Christian</strong> <i class="far fa-copyright"></i> </p>
        <nav>
            <a href="crud_artiste.jsp"><i class="fa fa-door-open"></i> Back</a>
        </nav>
    </div>
    <div class="formulaire">
        <form action="InsertArtiste.ArtisteCrud" method="post" enctype="multipart/form-data">
            <div class="photo">
                <div class="pdc">
                    <label>
                        <div class="pdc-fond"><i class="fa fa-image" id="pdc"></i></div>
                        <input type="file" required name="pdc" style="display: none;" accept="image/*" onchange="showBackground(this)">
                    </label>
                </div>
                <div class="pdp">
                    <label>
                        <input type="file" required name="pdp" style="display: none;" accept="image/*" onchange="showBackground(this)">
                        <i class="fa fa-image" id="pdp"></i>
                    </label>
                </div>
            </div>
            <div class="info-perso">
                <div>
                    <input type="text" name="pseudo" placeholder="Enter your pseudo" required>
                </div>
                <div>
                    <input type="email" name="email" placeholder="Email adresse" required>
                    <input type="password" name="mdp" placeholder="Password" required>
                </div>

                <div>
                    <% for (int i=0;i<allGenre.length;i++) { %>
                        <input type="radio" name="idGenre" value="<%=allGenre[i].getId() %>" class="radio" required> <%= allGenre[i].getType()%>
                    <%}%>
                </div>
            </div>
            <div class="bt-submit">
                <button type="submit">Valider</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="../../JS/showImage.js"></script>
</body>
</html>