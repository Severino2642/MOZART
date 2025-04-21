<%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/25/2024
  Time: 6:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="../fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="../fontawesome/css/regular.css"  />
    <link rel="stylesheet" type="text/css" href="../CSS/login.css">
</head>
<body>
<div class="logo">
    <img src="../image/mozart2.png">
</div>
<div class="formulaire">
    <form action="LogIn.AdminList" method="post">
        <div class="form-header">
            <h1>Log in to Mozart</h1>
            <nav>
                <a href="loginArtiste.jsp">
                    <p><i class="fa fa-user-tie"></i> <strong>Login as artist</strong></p>
                </a>
                <a href="loginUser.jsp">
                    <p><i class="fa fa-user"></i> <strong>Login as user</strong></p>
                </a>
            </nav>
        </div>
        <div class="form-body">
            <label for="email">Email</label>
            <input type="email" name="email" placeholder="Email Adress" required>
            <label for="mdp">Password</label>
            <p>
                <input class="mdp" type="password" name="mdp" placeholder="Password" required>
                <i class="far fa-eye-slash" id="show-pwd"></i>
            </p>
            <button type="submit">Log In</button>
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
<script type="text/javascript" src="../JS/app_login.js"></script>
</body>
</html>
