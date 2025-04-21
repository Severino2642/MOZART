/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import affData.ArtisteDetails;
import analyseur.AnalyseAlbum;
import analyseur.AnalyseArtiste;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Album;
import table.Artiste;
import table.Utilisateur;

import java.io.PrintWriter;
import java.sql.SQLException;
/**
 *
 * @author Sanda
 */
@WebServlet(name = "ArtisteList", value = "*.ArtisteList")
@MultipartConfig
public class ArtisteList extends MereController {

    @CtrlAnnotation(name = "GetAllArtiste")
    public void GetAllArtiste() throws SQLException, Exception {
        Artiste[] users = new Artiste().findAll();
        request.setAttribute("listArtiste", users);
        RequestDispatcher dispat = request.getRequestDispatcher("Admin/crud_artiste.jsp");
        dispat.forward(request,response);
    }


    @CtrlAnnotation(name = "Connecte")
    public void Connecte() throws Exception {
        Artiste user = new Artiste().findById(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("artiste", user);
        response.sendRedirect("../Artiste/crud_song.jsp");
    }
    @CtrlAnnotation(name = "LogIn")
    public void LogIn() throws Exception {
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        Artiste user = new Artiste().findByPwd(email,mdp);
        String path = "loginArtiste.jsp?alerte=Compte inexistant !";
        if(user != null ){
            request.getSession().setAttribute("artiste", user);
            path = "Artiste/crud_song.jsp";
        }
        response.sendRedirect(path);
    }
    @CtrlAnnotation(name = "PrepareUpdate")
    public void PrepareUpdate() throws Exception {
        Artiste user = new Artiste().findById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("artiste", user);
        RequestDispatcher dispat = request.getRequestDispatcher("updateArtiste.jsp");
        dispat.forward(request,response);
    }
    @CtrlAnnotation(name = "Search")
    public void Search() throws Exception {
        String search = request.getParameter("search");
        Artiste [] resultSearch = new Artiste().searchByName(search);

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
        Artiste [] resultSearch = new Artiste().searchByNameAndGenre(name,genre);
        request.getSession().setAttribute("listArtiste",resultSearch);
        response.sendRedirect("crud_artiste.jsp");
    }

    @CtrlAnnotation(name = "GetRandomForUser")
    public void GetRandomForUser() throws Exception {
        Utilisateur user = new Utilisateur().findById(Integer.parseInt(request.getParameter("idUser")));
        Artiste[] resultSearch = new AnalyseArtiste(user).getRandom();

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @CtrlAnnotation(name = "GetArtisteDetails")
    public void GetArtisteDetails() throws Exception {
        ArtisteDetails resultSearch = new ArtisteDetails().findByIdArtiste(Integer.parseInt(request.getParameter("id")));
        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }
}
