/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Follower;
import table.Playlist;

import java.io.PrintWriter;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "FollowerList", value = "*.FollowerList")
@MultipartConfig
public class FollowerList extends MereController {

    @CtrlAnnotation(name = "GetFollowerByIdArtisteAndUser")
    public void GetFollowerByIdArtisteAndUser() throws Exception {
        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        Follower resultSearch = new Follower().findByIdArtisteAndUser(idArtiste,idUser);

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