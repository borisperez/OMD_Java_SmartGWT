/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.server;

import br.com.etlplus.client.objects.Env;
import br.com.etlplus.client.services.EnvService;
import br.com.etlplus.client.services.EnvService;
import br.com.etlplus.server.sql.SqlActionEnv;
import br.com.etlplus.server.util.Connexion;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.SQLException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class EnvServiceImpl extends RemoteServiceServlet implements EnvService {

    ArrayList<Env> rsEnv;
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }

    @Override
    public ArrayList<Env> getEnvs() throws IllegalArgumentException {
            Connexion con = new Connexion();
            try {
                con.Conect("mysql");
                if (con.getConected()) {
                    Env envteste = new Env();
                    SqlActionEnv sql = new SqlActionEnv(con, envteste);
                    //con.ExpressaoSQL("select UserName from omd.tb_user where UserName = '" + user + "' and 'etlplus' = decode(Password, '"+password+"')");
                    rsEnv = sql.Consulta();
                } else {
                    throw new IllegalArgumentException("SQL invalid");
                }

                try {
                    if (con.getDados().first() == true) {
                        return rsEnv;
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
        
        return null;
    }

}

