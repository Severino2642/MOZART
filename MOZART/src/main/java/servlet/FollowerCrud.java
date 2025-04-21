package servlet;

import cnx.Connex;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Follower;
import table.Utilisateur;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

@WebServlet(name = "FollowerCrud", value = "*.FollowerCrud")
@MultipartConfig
public class FollowerCrud extends MereController {
    @CtrlAnnotation(name = "InsertFollower")
    public void InsertFollower() throws SQLException, Exception {
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        String dt_follow = Connex.getDate(Connex.PsqlConnect()).toString();
        Follower f = new Follower(idArtiste,idUser,dt_follow);
        f.insert();

    }
    @CtrlAnnotation(name = "DeleteFollower")
    public void DeleteFollower() throws SQLException, Exception {
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        Follower f = new Follower().findByIdArtisteAndUser(idArtiste,idUser);
        f.delete();
    }

}
