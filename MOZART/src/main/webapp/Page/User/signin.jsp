<%@ page import="table.Genre" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/25/2024
  Time: 6:29 AM
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
    <title>Document</title>
    <link rel="stylesheet" href="../../fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="../../fontawesome/css/regular.css"  />
    <link rel="stylesheet" type="text/css" href="../../CSS/signUser.css">
</head>
<body>
<div class="logo">
    <img src="../../image/mozart2.png">
</div>
<div class="formulaire">
    <form action="SignUp.UserCrud" method="post" enctype="multipart/form-data">
        <div class="form-header">
            <h1>Sign up for Mozart</h1>
        </div>
        <div class="form-body">
            <div class="photo">
                <div class="pdp">
                    <label>
                        <input type="file" name="pdp" style="display: none;" accept="image/*" onchange="showBackground(this)" required>
                        <i class="fa fa-image" id="pdp"></i>
                    </label>
                </div>
                <div>
                    <label for="nom">Username</label>
                    <input type="text" name="pseudo" placeholder="Username" required>
                </div>
            </div>
            <label for="email">Email</label>
            <input type="email" name="email" placeholder="Email Adress" required>
            <label for="mdp">Password</label>
            <p>
                <input class="mdp" type="password" name="mdp" placeholder="Password" required>
                <i class="far fa-eye-slash" id="show-pwd"></i>
            </p>
            <% for (int i=0;i<allGenre.length;i++) { %>
                <input type="radio" name="idGenre" value="<%=allGenre[i].getId() %>" class="radio" required> <%= allGenre[i].getType()%>
            <%}%>
            <button type="submit">Sign Up</button>
        </div>
        <div class="form-footer">
            <p><a href="../loginUser.jsp">Log in to Mozart</a></p>
        </div>
    </form>
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
<script type="text/javascript" src="../../JS/app_login.js"></script>
<script type="text/javascript" src="../../JS/showImage.js"></script>
</body>
</html>