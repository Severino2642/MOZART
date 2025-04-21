<%@ page import="table.Artiste" %>
<%@ page import="table.Album" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/19/2024
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.getSession().setAttribute("listSong",null);
    Artiste artiste = (Artiste) request.getSession().getAttribute("artiste");
    Album[] albums = new Album().findByIdArtiste(artiste.getId());
    if (request.getSession().getAttribute("listAlbum")!=null){
        albums = (Album[]) request.getSession().getAttribute("listAlbum");
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOZART</title>
    <link rel="stylesheet" href="../../fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/regular.css"  />
    <link rel="stylesheet" type="text/css" href="../../CSS/Menu.css">
    <link rel="stylesheet" type="text/css" href="../../CSS/crud_album.css">
</head>
<body>
<div class="split_left">
    <div class="logo">
        <img src="../../image/mozart3.png">
        <h2>MOZART</h2>
    </div>
    <nav>
        <a href="crud_song.jsp"><i class="fa fa-music"></i> My Songs</a>
        <a href="crud_album.jsp" class="active"><i class="fa fa-compact-disc"></i> My Albums</a>
        <a href="../../index.jsp"><i class="fa fa-sign-out-alt"></i> Disconnect</a>
    </nav>
</div>
<div class="split_right">
    <div class="header">
        <div class="formulaire">
            <form class="search-form" action="Search.AlbumList">
                <input type="hidden" name="idArtiste" value="<%=artiste.getId()%>">
                <input type="text" name="search" placeholder="Search">
                <input type="number" name="max" placeholder="Listening max">
                <input type="number" name="min" placeholder="And min">
                <button type="submit"><i class="fa fa-search"></i></button>
            </form>
        </div>
        <nav>
            <a href="ajoutAlbum.jsp"><i class="fa fa-plus"></i> New Album</a>
        </nav>
    </div>
    <hr width="70%">
    <h2 class="container-title">MY Albums</h2>
    <div class="container">
        <div class="list-item">
            <% for (int i=0; i<albums.length ;i++) { %>
                <div class="item">
                    <div class="icon-header">
                        <i class="fa fa-compact-disc"></i>
                    </div>
                    <a href="">
                        <div class="image" style="background-image: url('../../couverture/<%=albums[i].getCouverture()%>')"></div>
                    </a>
                    <div class="item-info">
                        <h3><%=albums[i].getTitle()%></h3>
                        <p><i class="fa fa-music"></i> <%= albums[i].getSong().length%> Single</p>
                        <p><i class="far fa-clock"></i> <%= albums[i].getDt_creation()%></p>
                    </div>
                    <div class="item-controller">
                        <a href="PrepareUpdate.AlbumList?id=<%=albums[i].getId()%>"><i class="fa fa-edit"></i></a>
                        <a href="DeleteAlbum.AlbumCrud?id=<%=albums[i].getId()%>"><i class="fa fa-trash-alt"></i></a>
                    </div>
                </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
