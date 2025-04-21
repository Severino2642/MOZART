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
import table.Favorite;
import table.Follower;

import java.io.PrintWriter;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "FavoriteList", value = "*.FavoriteList")
@MultipartConfig
public class FavoriteList extends MereController {

    @CtrlAnnotation(name = "GetFavoriteByIdItemAndUser")
    public void GetFavoriteByIdItemAndUser() throws Exception {
        String [] idItems = request.getParameter("idItem").split(";");
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        Favorite [] resultSearch = new Favorite[idItems.length];
        for (int i = 0; i<idItems.length ; i++){
            resultSearch[i] = new Favorite().findByIdItemAndUser(idItems[i], idUser);
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultSearch);

        // Configurer la réponse avant d'obtenir le flux d'écriture
        response.setContentType("application/json");

        // Envoyer le JSON au fichier JavaScript
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

}