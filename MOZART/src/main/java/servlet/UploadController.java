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

@WebServlet(name = "UploadController", value = "*.UploadController")
@MultipartConfig
public class UploadController extends MereController {
    @CtrlAnnotation(name = "UploadPdpUser")
    public void UploadPdpUser() throws ServletException, IOException , Exception {
        try{
            String pdp = Connex.createId(Connex.PsqlConnect(),"seq_pdp","UPDP");

            // Obtenez le fichier à partir de la requête
            Part filePart = request.getPart("file");

            // Obtenez le nom du fichier
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String [] extension = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("\\.");
            String fileName = pdp +"."+extension[extension.length-1];

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

            String pseudo = request.getParameter("pseudo");
            int idGenre = Integer.parseInt(request.getParameter("idGenre"));
            String email = request.getParameter("email");
            String mdp = request.getParameter("mdp");
            String dtAjout = Connex.getDate(Connex.PsqlConnect()).toString();

            Utilisateur new_user = new Utilisateur(fileName,pseudo,idGenre,email,mdp,dtAjout);
            request.setAttribute("new_user", new_user);

            RequestDispatcher dispat = request.getRequestDispatcher("InsertUser.UserCrud");
            dispat.forward(request,response);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
