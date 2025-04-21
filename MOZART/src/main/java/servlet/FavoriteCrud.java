package servlet;

import cnx.Connex;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Favorite;
import table.Follower;

import java.sql.SQLException;

@WebServlet(name = "FavoriteCrud", value = "*.FavoriteCrud")
@MultipartConfig
public class FavoriteCrud extends MereController {
    @CtrlAnnotation(name = "InsertFavorite")
    public void InsertFavorite() throws SQLException, Exception {
        String idItem = request.getParameter("idItem");
        String type = request.getParameter("type");
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        String dt_follow = Connex.getDate(Connex.PsqlConnect()).toString();
        Favorite f = new Favorite(idUser,idItem,type,dt_follow);
        f.insert();
    }
    @CtrlAnnotation(name = "DeleteFavorite")
    public void DeleteFavorite() throws SQLException, Exception {
        String idItem= request.getParameter("idItem");
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        Favorite f = new Favorite().findByIdItemAndUser(idItem,idUser);
        f.delete();
    }

}
