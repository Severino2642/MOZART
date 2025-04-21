/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import cnx.Connex;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Album;
import table.Playlist;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "PlaylistCrud", value = "*.PlaylistCrud")
@MultipartConfig
public class PlaylistCrud extends MereController {

    public String UploadCouverture(Part filePart) throws ServletException, IOException, Exception {
        String fileName = null;
        try{
            String pdp = Connex.createId(Connex.PsqlConnect(),"seq_couverture","Pochet");

            // Obtenez le nom du fichier
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String [] extension = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("\\.");
            fileName = pdp +"."+extension[extension.length-1];

            // Définissez le répertoire de destination pour le téléchargement
            String uploadDir = getServletContext().getRealPath("/couverture");
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdir();
            }

            // Copiez le fichier vers le répertoire de destination
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, Paths.get(uploadDir, fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return fileName;
    }
    @CtrlAnnotation(name = "InsertPlaylist")
    public void InsertPlaylist() throws SQLException, Exception {
        String idSong = request.getParameter("idSong");
        String idPlaylist = Connex.createId(Connex.PsqlConnect(),"seq_playlist","PLAYLIST");
        int idAuthor = Integer.parseInt(request.getParameter("idAuthor"));
        String title = request.getParameter("title");
        Part filePart1 = request.getPart("couverture");
        String pochet = UploadCouverture(filePart1);
        String dt_creation = Connex.getDate(Connex.PsqlConnect()).toString();
        Playlist playlist = new Playlist(idPlaylist,idAuthor,pochet,title,dt_creation);
        playlist.insert();
        playlist.addSong(idSong,idPlaylist);
    }
    @CtrlAnnotation(name = "UpdatePlaylist")
    public void UpdatePlaylist() throws SQLException, Exception {
        Playlist playlist = new Playlist().findById(request.getParameter("id"));
        String title = request.getParameter("title");
        Part filePart1 = request.getPart("couverture");
        String pochet = UploadCouverture(filePart1);
        String dt_creation = Connex.getDate(Connex.PsqlConnect()).toString();
        Playlist new_playlist = new Playlist(playlist.getId(),playlist.getIdAuthor(),pochet,title,playlist.getDt_creation());
        new_playlist.update();
    }
    @CtrlAnnotation(name = "AddSongPlaylist")
    public void AddSongPlaylist() throws SQLException, Exception {
        String idSong = request.getParameter("idSong");
        Playlist playlist = new Playlist().findById(request.getParameter("id"));
        playlist.addSong(idSong,playlist.getId());
    }
    @CtrlAnnotation(name = "RemoveSongPlaylist")
    public void RemoveSongPlaylist() throws SQLException, Exception {
        int idSong = Integer.parseInt(request.getParameter("idSong"));
        Playlist playlist = new Playlist().findById(request.getParameter("id"));
        playlist.removeSong(playlist.getId(),idSong);
    }
    @CtrlAnnotation(name = "RemovePlaylist")
    public void RemovePlaylist() throws SQLException, Exception {
        Playlist playlist = new Playlist().findById(request.getParameter("id"));
        playlist.removeAllSong(playlist.getId());
    }
    @CtrlAnnotation(name = "DeletePlaylist")
    public void DeletePlaylist() throws SQLException, Exception {
        Playlist playlist = new Playlist().findById(request.getParameter("id"));
        playlist.delete();
    }
}