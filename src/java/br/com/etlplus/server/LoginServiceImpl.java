package br.com.etlplus.server;

import br.com.etlplus.client.objects.User;
import java.sql.SQLException;

import br.com.etlplus.client.services.LoginService;
import br.com.etlplus.server.sql.SqlActionUser;
import br.com.etlplus.server.util.Connexion;
import br.com.etlplus.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
        LoginService {

    User rsUser;
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }

    @Override
    public User authenticUser(String user, String password) throws IllegalArgumentException {
        if (!FieldVerifier.isValidName(user)) {
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        } else {
            Connexion con = new Connexion();
            try {
                con.Conect("mysql");
                if (con.getConected()) {
                    SqlActionUser sql = new SqlActionUser(con,null);
                    //con.ExpressaoSQL("select UserName from omd.tb_user where UserName = '" + user + "' and 'etlplus' = decode(Password, '"+password+"')");
                    rsUser = sql.ConsultaLogin(user, password);
                } else {
                    throw new IllegalArgumentException("SQL invalid");
                }

                try {
                    if (con.getDados().first() == true) {
                        return rsUser;
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    //<editor-fold defaultstate="collapsed" desc="comment">
                }
                //</editor-fold>

            } catch (ClassNotFoundException e) {
                //<editor-fold defaultstate="collapsed" desc="comment">
            }
        }
        return null;
    }
}
