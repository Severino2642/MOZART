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
import table.Song;
import table.Utilisateur;

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
@WebServlet(name = "SongCrud", value = "*.SongCrud")
@MultipartConfig
public class SongCrud extends MereController {

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
    public String UploadContenue(Part filePart) throws ServletException, IOException, Exception {
        String fileName = null;
        try{
            String pdp = Connex.createId(Connex.PsqlConnect(),"seq_contenue","Song");

            // Obtenez le nom du fichier
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String [] extension = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("\\.");
            fileName = pdp +"."+extension[extension.length-1];

            // Définissez le répertoire de destination pour le téléchargement
            String uploadDir = getServletContext().getRealPath("/music");
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

    @CtrlAnnotation(name = "InsertSong")
    public void InsertSong() throws SQLException, Exception {
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        Part filePart1 = request.getPart("couverture");
        Part filePart2 = request.getPart("contenue");
        String pochet = UploadCouverture(filePart1);
        String contenue = UploadContenue(filePart2);
        String dt_pub = Connex.getDate(Connex.PsqlConnect()).toString();
        Song music = new Song(idArtiste,idCategorie,pochet,title,author,contenue,dt_pub);
        music.insert();
        response.sendRedirect("crud_song.jsp?alerte=Insertion effectuer");
    }
    @CtrlAnnotation(name = "UpdateSong")
    public void UpdateSong() throws SQLException, Exception {
        Song music = new Song().findById(Integer.parseInt(request.getParameter("id")));
        int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        Part filePart1 = request.getPart("couverture");
        Part filePart2 = request.getPart("contenue");
        String pochet = music.getCouverture();
        String contenue = music.getContenue();
        if (filePart1.getSubmittedFileName().compareToIgnoreCase("")!=0){
            pochet = UploadCouverture(filePart1);
        }
        if (filePart2.getSubmittedFileName().compareToIgnoreCase("") != 0){
            contenue = UploadContenue(filePart2);
        }

        Song new_music = new Song(music.getId(),music.getIdArtiste(),idCategorie,pochet,title,author,contenue,music.getDt_pub());
        new_music.update();
        response.sendRedirect("crud_song.jsp?alerte=Insertion effectuer");
    }
    @CtrlAnnotation(name = "DeleteSong")
    public void DeleteSong() throws SQLException, Exception {
        Song music = new Song().findById(Integer.parseInt(request.getParameter("id")));
        music.delete();
        response.sendRedirect("crud_song.jsp?alerte=Insertion effectuer");
    }
}