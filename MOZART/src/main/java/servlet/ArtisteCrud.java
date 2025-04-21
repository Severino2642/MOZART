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
import table.Artiste;
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
@WebServlet(name = "ArtisteCrud", value = "*.ArtisteCrud")
@MultipartConfig
public class ArtisteCrud extends MereController {

    public String UploadPdp(Part filePart) throws ServletException, IOException, Exception {
        String fileName = null;
        try{
            String pdp = Connex.createId(Connex.PsqlConnect(),"seq_pdp","APDP");

            // Obtenez le nom du fichier
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String [] extension = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("\\.");
            fileName = pdp +"."+extension[extension.length-1];

            // Définissez le répertoire de destination pour le téléchargement
            String uploadDir = getServletContext().getRealPath("/pdp");
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
    public String UploadPdc(Part filePart) throws ServletException, IOException, Exception {
        String fileName = null;
        try{
            String pdp = Connex.createId(Connex.PsqlConnect(),"seq_pdc","APDC");

            // Obtenez le nom du fichier
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String [] extension = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("\\.");
            fileName = pdp +"."+extension[extension.length-1];

            // Définissez le répertoire de destination pour le téléchargement
            String uploadDir = getServletContext().getRealPath("/pdc");
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
    @CtrlAnnotation(name = "InsertArtiste")
    public void InsertArtiste() throws SQLException, Exception {
        String pseudo = request.getParameter("pseudo");
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String dtAjout = Connex.getDate(Connex.PsqlConnect()).toString();
        Part filePart1 = request.getPart("pdp");
        Part filePart2 = request.getPart("pdc");
        String pdp = UploadPdp(filePart1);
        String pdc = UploadPdc(filePart2);
        Artiste new_user = new Artiste(pdp,pdc,pseudo,idGenre,email,mdp,dtAjout);
        new_user.insert();
        response.sendRedirect("ajoutArtiste.jsp?alerte=Inscription valider");
    }
    @CtrlAnnotation(name = "UpdateArtiste")
    public void UpdateArtiste() throws SQLException, Exception {
        Artiste user = new Artiste().findById(Integer.parseInt(request.getParameter("id")));
        String pseudo = request.getParameter("pseudo");
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        Part filePart1 = request.getPart("pdp");
        Part filePart2 = request.getPart("pdc");
        String pdp = user.getPdp();
        String pdc = user.getPdc();
        if (filePart1.getSubmittedFileName().compareToIgnoreCase("")!=0){
            pdp = UploadPdp(filePart1);
        }
        if (filePart2.getSubmittedFileName().compareToIgnoreCase("")!=0){
            pdc = UploadPdc(filePart2);
        }
        Artiste new_user = new Artiste(user.getId(),pdp,pdc,pseudo,idGenre,email,mdp, user.getDtAjout());
        new_user.update();
        response.sendRedirect("crud_artiste.jsp?alerte=Modification effectuer");
    }
    @CtrlAnnotation(name = "DeleteArtiste")
    public void DeleteArtiste() throws SQLException, Exception {
        Artiste user = new Artiste().findById(Integer.parseInt(request.getParameter("id")));
        user.delete();
        response.sendRedirect("crud_artiste.jsp?alerte=Suppression effectuer");
    }

}
