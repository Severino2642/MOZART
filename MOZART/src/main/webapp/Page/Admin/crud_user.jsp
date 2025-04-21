<%@ page import="table.Utilisateur" %>
<%@ page import="table.Genre" %><%--
  Created by IntelliJ IDEA.
  User: DIVA
  Date: 2/19/2024
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.getSession().setAttribute("listArtiste",null);
    Utilisateur [] allUsers = new Utilisateur().findAll();
    if (request.getSession().getAttribute("listUser")!=null){
        allUsers = (Utilisateur[])request.getSession().getAttribute("listUser");
    }
    Genre [] genres = new Genre().findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOZART</title>
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath() %>/fontawesome/css/all.min.css"  />
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath() %>/fontawesome/css/fontawesome.css"  />
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath() %>/fontawesome/css/regular.css"  />
    <link rel="stylesheet" type="text/css" href="<%= request.getServletContext().getContextPath() %>/CSS/Menu.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getServletContext().getContextPath() %>/CSS/crud_artiste.css">
</head>
<body>
<div class="split_left">
    <div class="logo">
        <img src="<%= request.getServletContext().getContextPath() %>/image/mozart3.png">
        <h2>MOZART</h2>
    </div>
    <nav>
        <a href="crud_artiste.jsp"><i class="fa fa-user-shield"></i> Artiste</a>
        <a href="crud_user.jsp" class="active"><i class="fa fa-user-circle"></i> User</a>
        <a href="../../index.jsp"><i class="fa fa-sign-out-alt "></i> Disconnect</a>
    </nav>
</div>
<div class="split_right">
    <div class="header">
        <div class="formulaire">
            <form id="search-form" action="SearchForAdmin.UserList" method="post">
                <input type="text" name="name" placeholder="Search">
                <select name="genre">
                    <option value="">All</option>
                    <% for (Genre g: genres) {%>
                       <option value="<%=g.getId()%>"><%=g.getType()%></option>
                    <%}%>
                </select>
                <button type="submit"><i class="fa fa-search"></i></button>
            </form>
        </div>
        <nav>
            <a href="ajoutUser.jsp"><i class="fa fa-user-plus"></i> Add User</a>
        </nav>
    </div>
    <hr width="70%">
    <h2 class="container-title">User List</h2>
    <div class="container">
        <div class="list-item">
            <% for (int i=0;i<allUsers.length;i++) { %>
                <div class="item">
                    <a href="Connecte.UserList?id=<%=allUsers[i].getId()%>">
                        <div class="image" style="background-image: url('../../pdp/<%= allUsers[i].getPdp()%>')">

                        </div>
                    </a>
                    <div class="item-info">
                        <h3><%= allUsers[i].getPseudo()%></h3>
                        <p><i class="far fa-clock"></i> <%= allUsers[i].getDtAjout()%></p>
                    </div>
                    <div class="item-controller">
                        <a href="PrepareUpdate.UserList?id=<%= allUsers[i].getId()%>"><i class="fa fa-edit"></i></a>
                        <a href="DeleteUser.UserCrud?id=<%= allUsers[i].getId()%>"><i class="fa fa-trash-alt"></i></a>
                    </div>
                </div>
            <% } %>
        </div>
    </div>
</div>
<%--<script type="text/javascript" src="../../JS/app_crudUser.js"></script>--%>
</body>
</html>
