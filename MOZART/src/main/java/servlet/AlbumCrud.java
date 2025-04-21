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
import table.Song;

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
@WebServlet(name = "AlbumCrud", value = "*.AlbumCrud")
@MultipartConfig
public class AlbumCrud extends MereController {

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
    @CtrlAnnotation(name = "InsertAlbum")
    public void InsertAlbum() throws SQLException, Exception {
        String [] idSong = request.getParameter("idSong").split(";");
        String idAlbum = Connex.createId(Connex.PsqlConnect(),"seq_album","ALBUM");
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        String title = request.getParameter("title");
        Part filePart1 = request.getPart("couverture");
        String pochet = UploadCouverture(filePart1);
        String dt_creation = Connex.getDate(Connex.PsqlConnect()).toString();
        Album album = new Album(idAlbum,idArtiste,pochet,title,dt_creation);
        album.insert();
        album.addSong(idSong,idAlbum);
        response.sendRedirect("crud_album.jsp?alerte=Insertion effectuer");
    }
    @CtrlAnnotation(name = "UpdateAlbum")
    public void UpdateAlbum() throws SQLException, Exception {
        String [] idSong = request.getParameter("idSong").split(";");
        Album album = new Album().findById(request.getParameter("id"));
        String title = request.getParameter("title");
        Part filePart1 = request.getPart("couverture");
        String pochet = album.getCouverture();
        if (filePart1.getSubmittedFileName().compareToIgnoreCase("")!=0){
            pochet = UploadCouverture(filePart1);
        }
        String dt_creation = Connex.getDate(Connex.PsqlConnect()).toString();
        Album new_album = new Album(album.getId(),album.getIdArtiste(),pochet,title,album.getDt_creation());
        new_album.update();
        new_album.removeSong(new_album.getId());
        new_album.addSong(idSong,new_album.getId());
        response.sendRedirect("crud_album.jsp?alerte=Insertion effectuer");
    }
    @CtrlAnnotation(name = "DeleteAlbum")
    public void DeleteAlbum() throws SQLException, Exception {
        Album album = new Album().findById(request.getParameter("id"));
        album.removeSong(album.getId());
        album.delete();
        response.sendRedirect("crud_album.jsp?alerte=Insertion effectuer");
    }
}