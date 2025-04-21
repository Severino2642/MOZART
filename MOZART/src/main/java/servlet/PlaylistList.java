/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import analyseur.AnalyseAlbum;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Album;
import table.Playlist;
import table.Song;
import table.Utilisateur;

import java.io.PrintWriter;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "PlaylistList", value = "*.PlaylistList")
@MultipartConfig
public class PlaylistList extends MereController {

    @CtrlAnnotation(name = "Search")
    public void Search() throws Exception {
        int idAuthor = Integer.parseInt(request.getParameter("idAuthor"));
        String search = request.getParameter("search");
        Playlist[] resultSearch = new Playlist().searchByTitleAndIdUser(search,idAuthor);

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
        Playlist[] resultSearch = new Playlist().searchByTitle(search);

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