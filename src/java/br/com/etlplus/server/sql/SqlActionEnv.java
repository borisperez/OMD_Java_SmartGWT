/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.server.sql;

import br.com.etlplus.client.objects.Env;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.etlplus.server.util.Connexion;

public class SqlActionEnv {

    private Connexion connexion;
    //  private boolean result;
    private String erro;
    private ArrayList<Env> envList;
    Env env;

    public SqlActionEnv(Connexion con, Env Env) {
        setConexao(con);
        this.env = Env;
    }

    public Connexion getConexao() {
        return connexion;
    }

    public final void setConexao(Connexion con) {
        connexion = con;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    private String QuotedStr(String Item) {
        return "\'" + Item + "\'";
    }

//    public void Salvar() {
//        if (user.getConsistencia()) {
//            String Colunas = "(" + user.getCampos().get(1)
//                    + "," + user.getCampos().get(2)
//                    + "," + user.getCampos().get(3)
//                    + "," + user.getCampos().get(4)
//                    + "," + user.getCampos().get(5)
//                    + "," + user.getCampos().get(6)
//                    + "," + user.getCampos().get(7)
//                    + "," + user.getCampos().get(8)
//                    + "," + user.getCampos().get(9)
//                    + "," + user.getCampos().get(10) + ")";
//            String Valores = "(" + QuotedStr(user.getUserName()) + ","
//                    + "password(" + QuotedStr(user.getPassword()) + "),"
//                    + "password(" + QuotedStr(user.getConfPassword()) + "),"
//                    + QuotedStr(user.getCourtesyTitle()) + ","
//                    + QuotedStr(user.getFirstName()) + ","
//                    + QuotedStr(user.getLastName()) + ","
//                    + QuotedStr(user.getJobTitle()) + ","
//                    + QuotedStr(user.getOfficePhone()) + ","
//                    + QuotedStr(user.getMobilePhone()) + ","
//                    + QuotedStr(user.getEmail()) + ")";
//
//            String Comando = "insert into omd.tb_user " + Colunas + " values " + Valores;
//            // String Comando = "insert into omdgove.TB_QA (NOMEJOB,NOMEPROJETO,NOMEAMBIENTE,DUREXECUCAO,DATAINI,DATAFIM,STATUSEXECUCAO,DATAENVIO,ANALISTA,STATUSQA) values " + Valores ; 
//
//            try {
//                getConexao().ExpressaoSQL(Comando);
//
//            } catch (Exception sqle) {
//                setErro("Invalid SQL :" + Comando + "   Os dados não foram salvos!" + sqle.getMessage());
//            }
//        } else {
//            setErro("User inconsistent!");
//        }
//    }
//
//    public void Remover() {
//        try {
//            getConexao().ExpressaoSQL(" Delete from omd.tb_user where UserId = " + user.getUserId());
//            //   result = getConexao().getDados().rowDeleted();
//
//        } catch (Exception sql) {
//            setErro("Os dados não foram removidos!" + sql.getMessage().toString());
//        }
//    }
    public ArrayList<Env> Consulta() {
     //   String clauWhere = montaClauWhere();
        envList = new ArrayList<Env>();
        boolean retorno = false;
        try {
            String comando = "Select * from omd.tb_env ";
            getConexao().ExpressaoSQL(comando);
            //    UserList = new User[getConexao().getStatement().getFetchSize()];
            ResultSet rs = getConexao().getDados();
            int contar = 0;
            if (rs != null) {
                while (rs.next()) {
                    Env envNew = new Env();
                    contar = contar++;
                    retorno = true;
                    int Id = rs.getInt("EnvId");
                    int EnvParentId = rs.getInt("EnvParentId");
                    String SeverName = rs.getString("SeverName");
                    String EnvironmentName = rs.getString("EnvironmentName");
                    envNew.setEnvId(Id);
                    envNew.setEnvParentId(EnvParentId);
                    envNew.setSeverName(SeverName);
                    envNew.setEnvironmentName(EnvironmentName);
                    envList.add(envNew);
                }
                if (!retorno) {
                    setErro("Return is empty!");
                }
            }
        } catch (SQLException sqle) {
            setErro("Os dados não foram listados!  " + sqle.getMessage());
        }
        return envList;
    }

//    private String montaClauWhere() {
//        String clauWhere = new String();
//        ArrayList<String> arrayWhere;
//        arrayWhere = new ArrayList<String>();
//        if (env.getEnvironmentName().length() > 0 || !env.getEnvironmentName().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(1) + "=" + "'" + env.getEnvironmentName().toString() + "'");
//        }
//        if (env.getEnvironmentType().length() > 0 || !env.getEnvironmentType().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(2) + "=" + "'" + env.getEnvironmentType().toString() + "'");
//        }
//        if (env.getMsgCode().length() > 0 || !env.getMsgCode().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(3) + "=" + "'" + env.getMsgCode().toString() + "'");
//        }
//        if (env.getCalled().length() > 0 || !env.getCalled().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(4) + "=" + "'" + env.getCalled().toString() + "'");
//        }
//        if (env.getTools().length() > 0 || !env.getTools().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(4) + "=" + "'" + env.getTools().toString() + "'");
//        }
//        if (env.getProcess().length() > 0 || !env.getProcess().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(5) + "=" + "'" + env.getProcess().toString() + "'");
//        }
//        if (env.getAnalyst().length() > 0 || !env.getAnalyst().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(6) + "=" + "'" + env.getAnalyst().toString() + "'");
//        }
//        if (env.getDateLoad().length() > 0 || !env.getDateLoad().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(7) + "=" + "'" + env.getDateLoad().toString() + "'");
//        }
//        if (env.getMsg().length() > 0 || !env.getMsg().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(8) + "=" + "'" + env.getMsg().toString() + "'");
//        }
//        if (env.getResolution_Obs().length() > 0 || !env.getResolution_Obs().contains("")) {
//            arrayWhere.add(" " + env.getCampos().get(9) + "=" + "'" + env.getResolution_Obs().toString() + "'");
//        }
//
//        if (arrayWhere.size() > 0) {
//            for (int i = 0; i < arrayWhere.size(); i++) {
//                if (i != arrayWhere.size() - 1) {
//                    clauWhere = clauWhere + arrayWhere.get(i) + " and ";
//                } else {
//                    clauWhere = clauWhere + arrayWhere.get(i);
//                }
//            }
//            clauWhere = "Where " + clauWhere;
//        } else {
//            clauWhere = "";
//        }
//        return clauWhere;
//    }
//
//    public void Atualizar() {
//        String Comando = " Update omd.tb_user SET ";
//        for (int i = 1; i < user.getCampos().size(); i++) {
//            switch (i) {
//                case 1:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getUserName().toString() + "'" + ",";
//                    break;
//                case 2:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getPassword().toString() + "'" + ",";
//                    break;
//                case 3:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getConfPassword().toString() + "'" + ",";
//                    break;
//                case 4:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getCourtesyTitle().toString() + "'" + ",";
//                    break;
//                case 5:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getFirstName().toString() + "'" + ",";
//                    break;
//                case 6:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getLastName().toString() + "'" + ",";
//                    break;
//                case 7:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getJobTitle().toString() + "'" + ",";
//                    break;
//                case 8:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getOfficePhone().toString() + "'" + ",";
//                    break;
//                case 9:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getMobilePhone().toString() + "'" + ",";
//                    break;
//                case 10:
//                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getEmail().toString() + "'" + ",";
//                    break;
//            }
//        }
//        if (Comando.length() > 0 && Comando.charAt(Comando.length() - 1) == ',') {
//            Comando = Comando.substring(0, Comando.length() - 1);
//        }
//        Comando += " where UserId = " + user.getUserId();
//        try {
//            getConexao().ExpressaoSQL(Comando);
//        } catch (Exception sqle) {
//            setErro("SQL Invalid Comando = " + Comando + " the data didn't updated! " + sqle.getMessage());
//        }
//    }
}
