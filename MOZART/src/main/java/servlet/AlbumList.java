/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import affData.AlbumDetails;
import affData.ArtisteDetails;
import analyseur.AnalyseAlbum;
import analyseur.AnalyseSong;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Album;
import table.Song;
import table.Utilisateur;

import java.io.PrintWriter;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "AlbumList", value = "*.AlbumList")
@MultipartConfig
public class AlbumList extends MereController {
    @CtrlAnnotation(name = "PrepareUpdate")
    public void PrepareUpdate() throws Exception {
        Album album = new Album().findById(request.getParameter("id"));
        request.setAttribute("album", album);
        RequestDispatcher dispat = request.getRequestDispatcher("updateAlbum.jsp");
        dispat.forward(request,response);
    }

    @CtrlAnnotation(name = "Search")
    public void Search() throws Exception {
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        String search = "";
        int min = -1;
        int max = -1;
        if(request.getParameter("search").compareTo("")!=0){
            search = request.getParameter("search");
        }
        if(request.getParameter("max").compareTo("")!=0){
            max = Integer.parseInt(request.getParameter("max")) ;
        }
        if(request.getParameter("min").compareTo("")!=0){
            min = Integer.parseInt(request.getParameter("min")) ;
        }

        Album[] resultSearch = new Album().searchByTitleMaxMin(idArtiste,search,min,max);

        request.getSession().setAttribute("listAlbum",resultSearch);
        response.sendRedirect("crud_album.jsp");
    }

    @CtrlAnnotation(name = "GetSongForAlbum")
    public void GetSongForAlbum() throws Exception {
        String idAlbum = request.getParameter("id");
        Album album = new Album().findById(idAlbum);
        Song [] resultSearch = album.getSong();

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @CtrlAnnotation(name = "GetRandomForUser")
    public void GetRandomForUser() throws Exception {
        Utilisateur user = new Utilisateur().findById(Integer.parseInt(request.getParameter("idUser")));
        Album[] resultSearch = new AnalyseAlbum(user).getRandom();

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @CtrlAnnotation(name = "SearchForUser")
    public void SearchForUser() throws Exception {
        String search = request.getParameter("search");
        Album[] resultSearch = new Album().searchByTitle(search);

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @CtrlAnnotation(name = "GetAlbumDetails")
    public void GetAlbumDetails() throws Exception {
        Album album = new Album().findById(request.getParameter("idAlbum"));
        AlbumDetails resultSearch = new AlbumDetails().findByAlbum(album);
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