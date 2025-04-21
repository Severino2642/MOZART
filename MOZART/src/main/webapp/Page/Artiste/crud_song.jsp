<%@ page import="table.Artiste" %>
<%@ page import="table.Song" %>
<%@ page import="table.Categorie" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/19/2024
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.getSession().setAttribute("listAlbum",null);
    Artiste artiste = (Artiste) request.getSession().getAttribute("artiste");
    Song[] allSong = new Song().findByIdArtiste(artiste.getId());
    if(request.getSession().getAttribute("listSong") != null){
        allSong = (Song[]) request.getSession().getAttribute("listSong");
    }
    Categorie [] allcateg = new Categorie().findAll();
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
    <link rel="stylesheet" type="text/css" href="../../CSS/crud_single.css">
</head>
<body>
<div class="split_left">
    <div class="logo">
        <img src="../../image/mozart3.png">
        <h2>MOZART</h2>
    </div>
    <nav>
        <a href="crud_song.jsp" class="active"><i class="fa fa-music"></i> My Songs</a>
        <a href="crud_album.jsp"><i class="fa fa-compact-disc"></i> My Albums</a>
        <a href="../../index.jsp"><i class="fa fa-sign-out-alt"></i> Disconnect</a>
    </nav>
</div>
<div class="split_right">
    <div class="header">
        <div class="formulaire">
            <form id="search-form" action="Search.SongList" method="post">
                <input type="hidden" name="idArtiste" value="<%=artiste.getId()%>">
                <input type="text" name="search" placeholder="Search">
                <input type="number" name="max" placeholder="Listening max">
                <input type="number" name="min" placeholder="And min">
                <select name="categorie">
                    <option value="">All</option>
                    <% for (Categorie categ: allcateg) {%>
                    <option value="<%=categ.getId()%>"><%=categ.getNom()%></option>
                    <%}%>
                </select>
                <button type="submit"><i class="fa fa-search"></i></button>
            </form>
        </div>
        <nav>
            <a href="ajoutSong.jsp"><i class="fa fa-plus"></i> New Single</a>
        </nav>
    </div>
    <hr width="70%">
    <h2 class="container-title">MY Songs</h2>
    <div class="container">
        <div class="list-item">
            <% for (int i=0;i<allSong.length;i++) {%>
                <div class="item">
                <div class="icon-header">
                    <i class="fa fa-music"></i>
                </div>
                <a href="">
                    <div class="image" style="background-image: url('../../couverture/<%=allSong[i].getCouverture()%>')"></div>
                </a>
                <div class="item-info">
                    <h3><%=allSong[i].getTitle()%></h3>
                    <p><i class="fa fa-headphones-alt"></i> <%= allSong[i].getNbStream()%> listeners</p>
                    <p><i class="fa fa-clock"></i> <%= allSong[i].getDt_pub()%></p>
                </div>
                <div class="item-controller">
                    <a href="PrepareUpdate.SongList?id=<%=allSong[i].getId()%>"><i class="fa fa-edit"></i></a>
                    <a href="DeleteSong.SongCrud?id=<%=allSong[i].getId()%>"><i class="fa fa-trash-alt"></i></a>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</div>
<%--<script type="text/javascript" src="../../JS/app_crudSong.js"></script>--%>
</body>
</html>
