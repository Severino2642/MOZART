/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import cnx.Connex;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Artiste;
import table.Utilisateur;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "UserList", value = "*.UserList")
@MultipartConfig
public class UserList extends MereController {

    @CtrlAnnotation(name = "GetAllUser")
    public void GetAllUser() throws SQLException, Exception {
        Utilisateur [] users = new Utilisateur().findAll();
        request.setAttribute("listUser", users);
        RequestDispatcher dispat = request.getRequestDispatcher("Admin/crud_user.jsp");
        dispat.forward(request,response);
    }
    @CtrlAnnotation(name = "Connecte")
    public void Connecte() throws Exception {
        Utilisateur user = new Utilisateur().findById(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("user", user);
        response.sendRedirect("../User/home.jsp");
    }
    @CtrlAnnotation(name = "LogIn")
    public void LogIn() throws Exception {
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        Utilisateur user = new Utilisateur().findByPwd(email,mdp);
        String path = "loginUser.jsp?alerte=Compte inexistant !";
        if(user != null ){
            request.getSession().setAttribute("user", user);
            path = "User/home.jsp";
        }
        response.sendRedirect(path);
    }
    @CtrlAnnotation(name = "PrepareUpdate")
    public void PrepareUpdate() throws Exception {
        Utilisateur user = new Utilisateur().findById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("user", user);
        RequestDispatcher dispat = request.getRequestDispatcher("updateUser.jsp");
        dispat.forward(request,response);
    }
    @CtrlAnnotation(name = "Search")
    public void Search() throws Exception {
        String search = request.getParameter("search");
        Utilisateur[] resultSearch = new Utilisateur().searchByName(search);

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }
    @CtrlAnnotation(name = "SearchForAdmin")
    public void SearchForAdmin() throws Exception {
        String name = request.getParameter("name");
        int genre = -1;
        if (request.getParameter("genre").compareToIgnoreCase("")!=0){
            genre = Integer.parseInt(request.getParameter("genre"));
        }
        Utilisateur [] resultSearch = new Utilisateur().searchByNameAndGenre(name,genre);
        request.getSession().setAttribute("listUser",resultSearch);
        response.sendRedirect("crud_user.jsp");
    }
}
