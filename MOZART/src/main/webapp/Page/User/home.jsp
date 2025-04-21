<%@ page import="table.Utilisateur" %>
<%@ page import="table.Song" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="table.Artiste" %>
<%@ page import="java.util.Vector" %>
<%@ page import="affData.SongDetails" %>
<%@ page import="table.Album" %>
<%@ page import="affData.PersonnelDetails" %>
<%@ page import="analyseur.AnalyseArtiste" %>
<%@ page import="view.*" %>
<%@ page import="analyseur.AnalyseSong" %>
<%@ page import="analyseur.AnalyseAlbum" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/20/2024
  Time: 5:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = "../../index.jsp";
    if (request.getSession().getAttribute("admin") != null){
        path = "../Admin/crud_user.jsp";
    }
    Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
    FavoriteArtiste [] listFavoriteArtiste = new FavoriteArtiste().findByidUser(user.getId());
    FavoriteAlbum [] listFavoriteAlbum = new FavoriteAlbum().findByidUser(user.getId());
    FavoriteSong[] listFavoriteSong = new FavoriteSong().findByidUser(user.getId());
    // AFFICHAGE PERSONELLE
    Song [] recentPlayed = user.getRecentPlayed();
    Album [] relatedAlbum = new PersonnelDetails().getRelatedAlbum(recentPlayed);
    Artiste [] relatedArtiste = new PersonnelDetails().getRelatedArtiste(relatedAlbum);

    // TOP Artiste,Album,Song
    TopArtiste [] rankArtiste = new TopArtiste().findAll();
    TopAlbum [] rankAlbum = new TopAlbum().findAll();
    TopSong [] rankSong = new TopSong().findAll();

    // RANDOM
    Artiste [] artistDiscovery = new AnalyseArtiste().getRandom();
    Song [] songDiscovery = new AnalyseSong().getRandom();
    Album [] albumDiscovery = new AnalyseAlbum().getRandom();
    int nbSlide = 0;

    Gson gson = new Gson();
%>
<html>
<head>
    <title>MOZART</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/regular.css"  />
    <link rel="stylesheet" type="text/css" href="../../CSS/MenuUser.css">
    <link rel="stylesheet" type="text/css" href="../../CSS/music_player.css">
    <link rel="stylesheet" type="text/css" id="css-link" href="../../CSS/home.css">
</head>
<body>
<div class="load-song">

</div>
<audio src="" id="audio"></audio>
<div class="music-player">
    <div class="music-header">
        <i class="fa fa-chevron-down" onclick="hideMusicPlayer()"></i>
        <div class="source">
            <h1 class="music-source"></h1>
            <p class="source-name"></p>
        </div>
        <i class="fa fa-ellipsis-v"></i>
    </div>
    <div class="disk"></div>
    <div class="music-info">
        <h1 class="music-name"></h1>
        <p class="artiste-name"></p>
    </div>
    <div class="song-slider">
        <input type="range" value="0" class="seck-bar">
        <div class="time">
            <span class="current-time">00:00</span>
            <span class="song-duration">00:00</span>
        </div>
    </div>
    <div class="controls">
        <button style="color: rgb(167, 167, 167);" attr="0" class="btn nextMod"><i class="fa fa-random"></i></button>
        <button class="btn backward-btn"><i class="fa fa-step-backward"></i></button>
        <button class="play-btn"><i class="fa fa-play"></i></button>
        <button class="btn forward-btn"><i class="fa fa-step-forward"></i></button>
        <button style="color: rgb(167, 167, 167);" attr="0" class="btn cycleMod"><i class="fa fa-retweet"></i></button>
    </div>
</div>
<div class="split_left">
    <div class="nav-top">
        <a onclick="generateHome()"><i class="fa fa-home"></i> <strong> Home</strong></a>
        <a onclick="generateSearch()"><i class="fa fa-search"></i> <strong> Search</strong></a>
    </div>
    <div class="nav-bottom">
        <div class="nav-bottom-head">
            <h3>Your Library</h3>
            <div>
                <a><i class="far fa-plus"></i></a>
                <a><i class="fa fa-arrow-right"></i></a>
            </div>
        </div>
        <nav>
            <script>
                var lfal = <%= gson.toJson(listFavoriteAlbum) %>;
                var lfa = <%= gson.toJson(listFavoriteArtiste) %>;
                var lfs = <%= gson.toJson(listFavoriteSong) %>;
            </script>
            <a>Playlists</a>
            <a onclick="affFavoriteAlbum(lfal,lfs.length,'<%=user.getPseudo()%>')">Albums</a>
            <a onclick="affFavoriteArtiste(lfa,lfs.length,'<%=user.getPseudo()%>')">Artists</a>
        </nav>
<%--        <form id="library-search-form">--%>
<%--            <button type="submit"><i class="fa fa-search"></i></button>--%>
<%--            <input type="text" name="search" placeholder="Search in Your Library" required>--%>
<%--        </form>--%>
        <div class="nav-bottom-container">
            <a onclick="affFavoriteSong(lfs,'<%=user.getPseudo()%>')">
                <div class="nav-bottom-content">
                    <div class="image"><i class="fa fa-heart"></i></div>
                    <div class="content-info">
                        <h3>Liked Song</h3>
                        <p><strong><i class="fa fa-thumbtack"></i> </strong> Playlist <i class="fa fa-circle"></i> <%=listFavoriteSong.length%> Songs</p>
                    </div>
                </div>
            </a>
            <% for (int i=0 ; i<listFavoriteAlbum.length && i<3 ; i++) { %>
                <a onclick="PrintAlbum('<%=listFavoriteAlbum[i].getAlbum().getId()%>')">
                    <div class="nav-bottom-content">
                        <div class="image" style="background-image: url('../../couverture/<%=listFavoriteAlbum[i].getAlbum().getCouverture()%>');"></div>
                        <div class="content-info">
                            <h3><%=listFavoriteAlbum[i].getAlbum().getTitle()%></h3>
                            <p>Album</p>
                        </div>
                    </div>
                </a>
            <% } %>
            <% for (int i=0 ; i<listFavoriteArtiste.length && i<2 ; i++) { %>
                <a onclick="PrintArtiste('<%=listFavoriteArtiste[i].getArtiste().getId()%>')">
                    <div class="nav-bottom-content">
                        <div class="image artiste" style="background-image: url('../../pdp/<%=listFavoriteArtiste[i].getArtiste().getPdp()%>');"></div>
                        <div class="content-info">
                            <h3><%=listFavoriteArtiste[i].getArtiste().getPseudo()%></h3>
                            <p>Artist</p>
                        </div>
                    </div>
                </a>
            <% } %>

<%--            <a href="playlist.html">--%>
<%--                <div class="nav-bottom-content">--%>
<%--                    <div class="image" style="background-image: url('../../Music Player/lionhill_artiste.jpg');"></div>--%>
<%--                    <div class="content-info">--%>
<%--                        <h3>Lion Hill</h3>--%>
<%--                        <p>Playlist <i class="fa fa-circle"></i> 4 Songs</p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </a>--%>
        </div>
    </div>
</div>
<div class="split_right">
    <div class="loader">
        <div class="animation-fond"></div>
        <div class="animation-container"></div>
    </div>
    <div class="header">
        <nav>
            <a class="swicth-left"><i class="fa fa-chevron-left"></i></a>
            <a class="swicth-rigth"><i class="fa fa-chevron-right"></i></a>
        </nav>
        <form style="display: none" id="search-form">
            <button type="submit"><i class="fa fa-search"></i></button>
            <input type="text" name="search" placeholder="What do you want to play ?">
        </form>
        <nav style="width: 29%;">
            <a class="bt-music-player" onclick="affMusicPlayer()"><i class="fab fa-spotify"></i> Music Player</a>
            <a href="<%=path%>"><i class="fa fa-sign-out-alt"></i></a>
            <div attr="<%=user.getId()%>" class="user-img" style="background-image: url('../../pdp/<%=user.getPdp()%>');">
            </div>
        </nav>
    </div>
    <div id="sac">
        <section cssLink="../../CSS/home.css" class="section-1">
            <div style="width: 100%;display: flex;">
                <div class="slider">
                    <div class="slider-container">
                        <% if (rankArtiste.length > 0) {
                            nbSlide++;
                        %>
                            <div class="slider-item active artiste" style="background-image: url('../../pdc/<%=rankArtiste[0].getArtiste().getPdc()%>');">
                            <div style="width: 100%;background-color: rgba(0, 0, 0, 0.445);height: 100%;padding: 10px;">
                                <div class="slider-info">
                                    <h3><i class="fa fa-check-circle" style="color: rgb(0, 62, 155);"></i> Verified Artist</h3>
                                    <h1><%=rankArtiste[0].getArtiste().getPseudo()%></h1>
                                    <p><i class="fa fa-headphones-alt"></i> <strong><%=rankArtiste[0].getNbListener()%></strong> listeners</p>
                                </div>
                                <nav>
                                    <a onclick="PrintArtiste('<%=rankArtiste[0].getArtiste().getId()%>')" class="play-btn"><strong>Listen Now </strong><i class="fa fa-play-circle"></i></a>
                                </nav>
                            </div>
                        </div>
                        <% } %>
                        <% if (rankAlbum.length > 0) {
                            nbSlide++;
                        %>
                            <div class="slider-item album">
                                <div class="slider-info">
                                    <h3><i class="fa fa-compact-disc" style="color: rgb(255, 226, 63);"></i> Top Album</h3>
                                    <h1><%=rankAlbum[0].getAlbum().getTitle()%></h1>
                                    <p> <strong><%=rankAlbum[0].getAlbum().getArtiste().getPseudo()%></strong> <i class="fa fa-circle"></i> <strong><%=rankAlbum[0].getNbListener()%></strong> listeners</p>
                                </div>
                                <nav>
                                    <a onclick="PrintAlbum('<%=rankAlbum[0].getAlbum().getId()%>')" class="play-btn"><strong>Listen Now </strong><i class="fa fa-play-circle"></i></a>
                                </nav>
                        </div>
                        <% } %>
                        <% if (rankSong.length>0) {
                            nbSlide++;
                        %>
                            <div class="slider-item song">
                                <div class="slider-info">
                                    <h3><i class="fa fa-check-circle" style="color: rgb(38, 104, 202);"></i> Certified Single</h3>
                                    <h1><%=rankSong[0].getSong().getTitle()%></h1>
                                    <p> <strong><%=rankSong[0].getSong().getAuthor()%></strong> <i class="fa fa-circle"></i> <strong><%=rankSong[0].getNbListener()%></strong> listeners</p>
                                </div>
                                <nav>
                                    <a onclick="PrintSong('<%=rankSong[0].getSong().getId()%>')" class="play-btn"><strong>Listen Now </strong><i class="fa fa-play-circle"></i></a>
                                </nav>
                            </div>
                        <% } %>

                    </div>
                    <div class="slider-nav">
                        <div class="slider-range">
                            <% for (int i=0 ; i<nbSlide ; i++) { %>
                                <% if (i==0) { %>
                                    <i class="fa fa-circle active" attr="0" onclick="switchSlide(this)"></i>
                                <% } %>
                                <% if (i>0) { %>
                                    <i class="fa fa-circle" attr="<%=i%>" onclick="switchSlide(this)"></i>
                                <% } %>
                            <% } %>
                        </div>
                        <div class="slider-controller">
                            <i class="fa fa-chevron-circle-left" id="bt_prev"></i>
                            <i class="fa fa-chevron-circle-right" id="bt_next"></i>
                        </div>
                    </div>
                </div>
            </div>
            <% if (recentPlayed.length > 0) { %>
                <div class="list-title">
                    <h2>Recently Played</h2>
                </div>
                <div class="list-song">
                    <% for (int i=0 ; i<recentPlayed.length && i<8 ; i++){ %>
                        <a onclick="PrintSong('<%=recentPlayed[i].getId()%>')">
                            <div class="song">
                                <div class="image" style="background-image: url('../../couverture/<%=recentPlayed[i].getCouverture()%>');"></div>
                                <div class="title">
                                    <h3><%=recentPlayed[i].getTitle()%></h3>
                                </div>
                                <div class="icon-hover">
                                    <i class="fa fa-play-circle"></i>
                                </div>
                            </div>
                        </a>
                    <% } %>
                </div>
                <% if (relatedAlbum.length > 0) {%>
                    <div class="list-title">
                        <h2>Related to your recent listens</h2>
                    </div>
                <%}%>
                <div class="list-item">
                    <% for (int i=0 ; i<relatedAlbum.length && i<4 ; i++){ %>
                        <a onclick="PrintAlbum('<%=relatedAlbum[i].getId()%>')">
                            <div class="item">
                                <div class="icon-hover">
                                    <i class="fa fa-play"></i>
                                </div>
                                <div class="image" style="background-image: url('../../couverture/<%=relatedAlbum[i].getCouverture()%>');">

                                </div>
                                <div class="item-info">
                                    <h3><%=relatedAlbum[i].getTitle()%></h3>
                                    <p><%=relatedAlbum[i].getArtiste().getPseudo()%></p>
                                </div>
                            </div>
                        </a>
                    <% } %>
                </div>
                <% if (relatedArtiste.length > 0) {%>
                <div class="list-title">
                    <h2>Place the artist you like</h2>
                </div>
                <%}%>
                <div class="list-item">
                    <% for (int i=0 ; i<relatedArtiste.length && i<4 ; i++){ %>
                        <a onclick="PrintArtiste('<%=relatedArtiste[i].getId()%>')">
                            <div class="item">
                                <div class="image artiste" style="background-image: url('../../pdp/<%= relatedArtiste[i].getPdp()%>');">

                                </div>
                                <div class="item-info">
                                    <h3><%= relatedArtiste[i].getPseudo()%></h3>
                                    <p>Artist</p>
                                </div>
                            </div>
                        </a>
                    <% } %>
                </div>
            <% } %>
            <% if (artistDiscovery.length > 0) {%>
            <div class="list-title">
                <h2>Artist discovery for you</h2>
            </div>
            <%}%>
            <div class="list-item">
                <div class="list-item">
                    <% for (int i=0 ; i<artistDiscovery.length && i<4 ; i++){ %>
                        <a onclick="PrintArtiste('<%=artistDiscovery[i].getId()%>')">
                            <div class="item">
                                <div class="image artiste" style="background-image: url('../../pdp/<%=artistDiscovery[i].getPdp()%>');">

                                </div>
                                <div class="item-info">
                                    <h3><%= artistDiscovery[i].getPseudo()%></h3>
                                    <p>Artist</p>
                                </div>
                            </div>
                        </a>
                    <% } %>
                </div>
            </div>
            <% if (albumDiscovery.length > 0) {%>
            <div class="list-title">
                <h2>Some Album suggestion for you</h2>
            </div>
            <%}%>
            <div class="list-item">
                <% for (int i=0 ; i<albumDiscovery.length && i<4 ; i++){ %>
                <a onclick="PrintAlbum('<%=albumDiscovery[i].getId()%>')">
                    <div class="item">
                        <div class="icon-hover">
                            <i class="fa fa-play"></i>
                        </div>
                        <div class="image" style="background-image: url('../../couverture/<%=albumDiscovery[i].getCouverture()%>');">

                        </div>
                        <div class="item-info">
                            <h3><%=albumDiscovery[i].getTitle()%></h3>
                            <p><%=albumDiscovery[i].getArtiste().getPseudo()%></p>
                        </div>
                    </div>
                </a>
                <% } %>
            </div>
        </section>
    </div>
    <footer>
        <h2>
            <strong><i class="fab fa-instagram"></i></strong>
            <strong><i class="fab fa-facebook"></i></strong>
            <strong><i class="fab fa-twitter"></i></strong>
        </h2>
        <div>
            <p>Created by <strong>SAMSOUDINE Severino Divaraly Christian</strong></p>
            <p><i class="far fa-copyright"></i> 2024 Mozart</p>
        </div>
    </footer>
</div>
<script type="text/javascript" src="../../JS/slider.js"></script>
<script type="text/javascript" src="../../JS/loader.js"></script>
<script type="text/javascript" src="../../JS/Generateur.js"></script>
<script type="text/javascript" src="../../JS/MusicPlayer.js"></script>
<script type="text/javascript" src="../../JS/Swicth.js"></script>
<script type="text/javascript" src="../../JS/app_search.js"></script>
<script type="text/javascript" src="../../JS/get_artiste.js"></script>
<script type="text/javascript" src="../../JS/get_album.js"></script>
<script type="text/javascript" src="../../JS/get_song.js"></script>
<script type="text/javascript" src="../../JS/app_follower.js"></script>
<script type="text/javascript" src="../../JS/app_favorite.js"></script>
<script type="text/javascript" src="../../JS/app_stream.js"></script>

</body>
</html>
