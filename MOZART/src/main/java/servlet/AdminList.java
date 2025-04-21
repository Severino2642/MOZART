/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import affData.ArtisteDetails;
import analyseur.AnalyseArtiste;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Admin;
import table.Artiste;
import table.Utilisateur;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "AdminList", value = "*.AdminList")
@MultipartConfig
public class AdminList extends MereController {

    @CtrlAnnotation(name = "LogIn")
    public void LogIn() throws Exception {
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        Admin user = new Admin().findByPwd(email,mdp);
        String path = "loginAdmin.jsp?alerte=Compte inexistant !";
        if(user != null ){
            request.getSession().setAttribute("admin", user);
            path = "Admin/crud_artiste.jsp";
        }
        response.sendRedirect(path);
    }
}
