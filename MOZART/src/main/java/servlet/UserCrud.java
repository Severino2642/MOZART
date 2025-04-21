/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import cnx.Connex;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
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
@WebServlet(name = "UserCrud", value = "*.UserCrud")
@MultipartConfig
public class UserCrud extends MereController {

    public String UploadPdp(Part filePart) throws ServletException, IOException, Exception {
        String fileName = null;
        try{
            String pdp = Connex.createId(Connex.PsqlConnect(),"seq_pdp","UPDP");

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
    @CtrlAnnotation(name = "InsertUser")
    public void InsertUser() throws SQLException, Exception {
        String pseudo = request.getParameter("pseudo");
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String dtAjout = Connex.getDate(Connex.PsqlConnect()).toString();
        Part filePart = request.getPart("pdp");
        String pdp = UploadPdp(filePart);
        Utilisateur new_user = new Utilisateur(pdp,pseudo,idGenre,email,mdp,dtAjout);
        new_user.insert();
        response.sendRedirect("ajoutUser.jsp?alerte=Inscription valider");
    }
    @CtrlAnnotation(name = "SignUp")
    public void SignUp() throws SQLException, Exception {
        String pseudo = request.getParameter("pseudo");
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String dtAjout = Connex.getDate(Connex.PsqlConnect()).toString();
        Part filePart = request.getPart("pdp");
        String pdp = UploadPdp(filePart);
        Utilisateur new_user = new Utilisateur(pdp,pseudo,idGenre,email,mdp,dtAjout);
        new_user.insert();
        response.sendRedirect("../loginUser.jsp?alerte=Inscription valider");
    }
    @CtrlAnnotation(name = "UpdateUser")
    public void UpdateUser() throws SQLException, Exception {
        Utilisateur user = new Utilisateur().findById(Integer.parseInt(request.getParameter("id")));
        String pseudo = request.getParameter("pseudo");
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String pdp = user.getPdp();
        Part filePart = request.getPart("pdp");
        if (filePart.getSubmittedFileName().compareToIgnoreCase("")!=0){
            pdp = UploadPdp(filePart);
        }
        Utilisateur new_user = new Utilisateur(user.getId(),pdp,pseudo,idGenre,email,mdp, user.getDtAjout());
        new_user.update();
        response.sendRedirect("crud_user.jsp?alerte=Modification effectuer");
    }
    @CtrlAnnotation(name = "DeleteUser")
    public void DeleteUser() throws SQLException, Exception {
        Utilisateur user = new Utilisateur().findById(Integer.parseInt(request.getParameter("id")));
        user.delete();
        response.sendRedirect("crud_user.jsp?alerte=Suppression effectuer");
    }
}
