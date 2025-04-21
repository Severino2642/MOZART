<%@ page import="table.Artiste" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/19/2024
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Artiste artiste = (Artiste) request.getSession().getAttribute("artiste");
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
    <link rel="stylesheet" type="text/css" href="../../CSS/ajout_album.css">
</head>
<body>
<div class="split_left">
    <div class="logo">
        <img src="../../image/mozart3.png">
        <h2>MOZART</h2>
    </div>
    <div id="formulaire">
        <form action="InsertAlbum.AlbumCrud" method="post" enctype="multipart/form-data">
            <input type="hidden" id="idSong" name="idSong" value="">
            <input type="hidden" id="idArtiste" name="idArtiste" value="<%= artiste.getId()%>">
            <div class="photo">
                <div class="couverture">
                    <label>
                        <input type="file" required name="couverture" style="display: none;" accept="image/*" onchange="showBackground(this)">
                        <i class="fa fa-image" id="pdp"></i>
                    </label>
                </div>
            </div>
            <div class="info-perso">
                <div>
                    <input type="text" name="title" placeholder="Title" required>
                </div>
            </div>
            <div class="bt-submit">
                <button type="submit">Create</button>
            </div>
        </form>
    </div>
</div>
<div class="split_right">
    <div class="header">
        <div class="formulaire">
<%--            <form id="search-form">--%>
<%--                <input type="hidden" id="idArtiste" name="idArtiste" value="<%= artiste.getId()%>">--%>
<%--                <input type="text" name="search" placeholder="Search" required>--%>
<%--                <button type="submit"><i class="fa fa-search"></i></button>--%>
<%--            </form>--%>
        </div>
        <nav>
            <a href="crud_album.jsp"><i class="fa fa-door-open"></i> Back</a>
        </nav>
    </div>
    <div class="container">
        <div class="content">
            <h2 style="color: rgb(0,191,99);">SINGLE ADDED</h2>
            <div class="table added">
                <table cellspacing="0">
                    <thead>
                    <tr>
                        <th class="id">#</th>
                        <th>TITLE</th>
                        <th>DATE ADDED</th>
                    </tr>
                    </thead>
                    <tbody>
<%--                    <tr>--%>
<%--                        <td class="id">1</td>--%>
<%--                        <td class="title-song">--%>
<%--                            <img src="../../image/lionhill_artiste.jpg">--%>
<%--                            <div>--%>
<%--                                <h3>Tsy kinia</h3>--%>
<%--                                <p>Lion Hill</p>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                        <td>2024-02-09</td>--%>
<%--                    </tr>--%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="content">
            <h2 style="color: red;">SINGLE NOT ADDED</h2>
            <div class="table notAdded">
                <table cellspacing="0">
                    <thead>
                    <tr>
                        <th class="id">#</th>
                        <th>TITLE</th>
                        <th>DATE ADDED</th>
                    </tr>
                    </thead>
                    <tbody>
<%--                    <tr>--%>
<%--                        <td class="id">1</td>--%>
<%--                        <td class="title-song">--%>
<%--                            <img src="../../image/lionhill_artiste.jpg">--%>
<%--                            <div>--%>
<%--                                <h3>Tsy kinia</h3>--%>
<%--                                <p>Lion Hill</p>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                        <td>2024-02-09</td>--%>
<%--                    </tr>--%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../../JS/showImage.js"></script>
<script type="text/javascript" src="../../JS/app_ajoutAlbum.js"></script>
</body>
</html>
