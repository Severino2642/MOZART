package servlet;

import cnx.Connex;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import simpleController.CtrlAnnotation;
import simpleController.MereController;
import table.Follower;
import table.Stream;

import java.sql.SQLException;

@WebServlet(name = "StreamCrud", value = "*.StreamCrud")
@MultipartConfig
public class StreamCrud extends MereController {
    @CtrlAnnotation(name = "InsertStream")
    public void InsertStream() throws SQLException, Exception {
        int idSong = Integer.parseInt(request.getParameter("idSong"));
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        String dt_listening = Connex.getDate(Connex.PsqlConnect()).toString();
        if(new Stream().findByIdUserAndSong(idUser,idSong) == null){
            Stream s = new Stream(idSong,idUser,dt_listening);
            s.insert();
        }
    }
//    @CtrlAnnotation(name = "DeleteFollower")
//    public void DeleteFollower() throws SQLException, Exception {
//        int idArtiste = Integer.parseInt(request.getParameter("idArtiste"));
//        int idUser = Integer.parseInt(request.getParameter("idUser"));
//        Follower f = new Follower().findByIdArtisteAndUser(idArtiste,idUser);
//        f.delete();
//    }

}
