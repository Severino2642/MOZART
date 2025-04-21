/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import affData.SongDetails;
import analyseur.AnalyseSong;
import cnx.Connex;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Song;
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
@WebServlet(name = "SongList", value = "*.SongList")
@MultipartConfig
public class SongList extends MereController {
    @CtrlAnnotation(name = "PrepareUpdate")
    public void PrepareUpdate() throws Exception {
        Song music = new Song().findById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("song", music);
        RequestDispatcher dispat = request.getRequestDispatcher("updateSong.jsp");
        dispat.forward(request,response);
    }
    @CtrlAnnotation(name = "Search")
    public void Search() throws Exception {
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        String search = "";
        int min = -1;
        int max = -1;
        int idCateg = -1;
        if(request.getParameter("search").compareTo("")!=0){
            search = request.getParameter("search");
        }
        if(request.getParameter("max").compareTo("")!=0){
            max = Integer.parseInt(request.getParameter("max")) ;
        }
        if(request.getParameter("min").compareTo("")!=0){
            min = Integer.parseInt(request.getParameter("min")) ;
        }
        if(request.getParameter("categorie").compareTo("")!=0){
            idCateg = Integer.parseInt(request.getParameter("categorie")) ;
        }

        Song[] resultSearch = new Song().searchByTitleMaxMin(idArtiste,search,min,max,idCateg);

        request.getSession().setAttribute("listSong",resultSearch);
        response.sendRedirect("crud_song.jsp");
    }

    @CtrlAnnotation(name = "SearchForUser")
    public void SearchForUser() throws Exception {
        String search = request.getParameter("search");
        Song[] resultSearch = new Song().searchByTitle(search);

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @CtrlAnnotation(name = "GetSingleForArtiste")
    public void GetSingleForArtiste() throws Exception {
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        Song[] resultSearch = new Song().GetSingle(idArtiste);

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
        Song[] resultSearch = new AnalyseSong(user).getRandom();

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);
        // Configurer la réponse
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @CtrlAnnotation(name = "GetSongDetails")
    public void GetSongDetails() throws Exception {
        Song music = new Song().findById(Integer.parseInt(request.getParameter("idSong")));
        SongDetails resultSearch = new SongDetails().findBySong(music);

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