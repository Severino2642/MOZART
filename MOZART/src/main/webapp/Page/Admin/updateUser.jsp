<%@ page import="table.Genre" %>
<%@ page import="table.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/19/2024
  Time: 1:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Genre[] allGenre = new Genre().findAll();
    Utilisateur user = (Utilisateur) request.getAttribute("user");
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOZART</title>
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath() %>/fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath() %>/fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath() %>/fontawesome/css/regular.css"  />
    <link rel="stylesheet" type="text/css" href="<%= request.getServletContext().getContextPath() %>/CSS/ajoutUser.css">
</head>
<body>
<%
    if (request.getParameter("alerte")!=null) { %>
<script type='text/javascript'>
    alert('<%= request.getParameter("alerte") %>');
</script>
<%}%>
<div class="container">
    <div class="content">
        <div class="logo">
            <img src="<%= request.getServletContext().getContextPath() %>/image/mozart2.png">
        </div>
        <h1>Ecoutez toutes les music de vos artiste preferer en vous s'inscrivant sur <strong>MOZART</strong></h1>
        <p>Created by <strong>SAMSOUDINE Severino Divaraly Christian</strong> <i class="far fa-copyright"></i> </p>
        <nav>
            <a href="crud_user.jsp"><i class="fa fa-door-open"></i> Back</a>
        </nav>
    </div>
    <div class="formulaire">
        <form action="UpdateUser.UserCrud" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%= user.getId()%>">
            <div class="info-perso">
                <div class="photo">
                    <div class="pdp">
                        <label style="background-image: url('<%= request.getServletContext().getContextPath() %>/pdp/<%= user.getPdp()%>')">
                            <input type="file" name="pdp" style="display: none;" accept="image/*" onchange="showBackground(this)">
                            <i class="fa fa-image" id="pdp"></i>
                        </label>
                    </div>
                    <div class="nom-dtn">
                        <div>
                            <input type="text" name="pseudo" placeholder="Enter your pseudo" value="<%= user.getPseudo()%>" required>
                        </div>
                    </div>
                </div>
                <div>
                    <input type="email" name="email" placeholder="Email adresse" value="<%= user.getEmail()%>" required>
                </div>
                <div>
                    <input type="password" name="mdp" placeholder="Password" value="<%= user.getMdp()%>" required>
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
