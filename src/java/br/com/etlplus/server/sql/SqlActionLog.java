/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.server.sql;

import br.com.etlplus.client.objects.Log;
import br.com.etlplus.client.objects.LogKb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.etlplus.server.util.Connexion;

public class SqlActionLog {

    private Connexion connexion;
    private String message;
    private ArrayList<Log> logList;

    public SqlActionLog(Connexion con) {
        setConexao(con);
    }

    public Connexion getConexao() {
        return connexion;
    }

    public final void setConexao(Connexion con) {
        connexion = con;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String erro) {
        this.message = erro;
    }

    private String QuotedStr(String Item) {
        return "\'" + Item + "\'";
    }

    private String dobleQuotedStr(String Item) {
        return '\"' + Item + '\"';
    }

    public void salvar(LogKb logKb) {
        //   if (logKb.getConsistencia()) {
        String Colunas = "("
                + logKb.getCampos().get(1)
                + "," + logKb.getCampos().get(2)
                + "," + logKb.getCampos().get(3)
                + "," + logKb.getCampos().get(4)
                + "," + logKb.getCampos().get(5)
                + "," + logKb.getCampos().get(6)
                + "," + logKb.getCampos().get(7)
                + "," + logKb.getCampos().get(8)
                + "," + logKb.getCampos().get(9)
                + "," + logKb.getCampos().get(10)
                + "," + logKb.getCampos().get(11)
                + "," + logKb.getCampos().get(12)
                + "," + logKb.getCampos().get(13)
                + "," + logKb.getCampos().get(14)
                + ")";
        String Valores = "("
                + QuotedStr(logKb.getEnvironmentName()) + ","
                + QuotedStr(logKb.getEnvironmentType()) + ","
                + QuotedStr(logKb.getMsgCode()) + ","
                + QuotedStr(logKb.getCalled()) + ","
                + QuotedStr(logKb.getTools()) + ","
                + QuotedStr(logKb.getProcess()) + ","
                + QuotedStr(logKb.getAnalyst()) + ","
                + QuotedStr(logKb.getDate()) + ","
                + dobleQuotedStr(logKb.getMsg()) + ","
                + dobleQuotedStr(logKb.getResolution_Obs()) + ","
                + QuotedStr(logKb.getStatus()) + ","
                + QuotedStr(logKb.getWhoIs()) + ","
                + QuotedStr(logKb.getTime()) + ","
                + logKb.getEnvId() + ")";

        String Comando = "insert into omd.tb_logkb " + Colunas + " values " + Valores;

        try {
            getConexao().ExpressaoSQL(Comando);
            setMessage(getConexao().getMessage());
        } catch (Exception sqle) {
            setMessage("Invalid SQL :" + Comando + "   Os dados não foram salvos!" + sqle.getMessage());
        }
        //    } else {
        //        setMessage("User inconsistent!");
        //   }
    }

//    public void Remover() {
//        try {
//            getConexao().ExpressaoSQL(" Delete from omd.tb_user where UserId = " + user.getUserId());
//            //   result = getConexao().getDados().rowDeleted();
//
//        } catch (Exception sql) {
//            setErro("Os dados não foram removidos!" + sql.getMessage().toString());
//        }
//    }
    public ArrayList<Log> ConsultaLog(String server, String date, String timeB, String timeA) {
        //      String clauWhere = montaClauWhere();
        logList = new ArrayList<Log>();
        boolean retorno = false;
        try {
            String comando = "SELECT omd.tb_log.* "
                    + "FROM omd.tb_log, omd.tb_env "
                    + "WHERE omd.tb_log.EnvId = omd.tb_env.EnvId "
                    + "AND TimeLog between '" + timeB + "' and '" + timeA + "'"
                    + "AND DateLog = DATE_FORMAT('" + date + "','%Y-%m-%d')"
                    + "AND omd.tb_env.SeverName = '" + server + "'";

            getConexao().ExpressaoSQL(comando);
            //    UserList = new User[getConexao().getStatement().getFetchSize()];
            ResultSet rs = getConexao().getDados();
            int contar = 0;
            if (rs != null) {
                while (rs.next()) {
                    Log logNew = new Log();
                    contar = contar++;
                    retorno = true;
                    int Id = rs.getInt("LogId");
                    String EnvironmentName = rs.getString("EnvironmentName");
                    String EnvironmentType = rs.getString("EnvironmentType");
                    String MsgCode = rs.getString("MsgCode");
                    String Tools = rs.getString("Tools");
                    String Process = rs.getString("Process");
                    String DateLog = rs.getString("DateLog");
                    String TimeLog = rs.getString("TimeLog");
                    String Msg = rs.getString("Msg");
                    int EnvId = rs.getInt("EnvId");
                    logNew.setLogId(Id);
                    logNew.setEnvironmentName(EnvironmentName);
                    logNew.setEnvironmentType(EnvironmentType);
                    logNew.setMsgCode(MsgCode);
                    logNew.setTools(Tools);
                    logNew.setProcess(Process);
                    logNew.setDateLog(DateLog + " " + TimeLog);
                    logNew.setMsg(Msg);
                    logNew.setEnvId(EnvId);
                    logList.add(logNew);
                }
                if (!retorno) {
                    setMessage("Return is empty!");
                }
            }
        } catch (SQLException sqle) {
            setMessage("Os dados não foram listados!  " + sqle.getMessage());
        }
        return logList;
    }

    public ArrayList<LogKb> ConsultaLogKb(LogKb logKb) {
        String whereKB = montaWhereKB(logKb);
        ArrayList<LogKb> logListKb = new ArrayList<LogKb>();
        boolean retorno = false;
        try {
            String comando = "SELECT * "
                    + "FROM omd.tb_logKb " + whereKB;
            getConexao().ExpressaoSQL(comando);
            //    UserList = new User[getConexao().getStatement().getFetchSize()];
            ResultSet rs = getConexao().getDados();
            int contar = 0;
            if (rs != null) {
                while (rs.next()) {
                    LogKb logKbNew = new LogKb();
                    contar = contar++;
                    retorno = true;
                    int Id = rs.getInt("LogKbId");
                    String EnvironmentName = rs.getString("EnvironmentName");
                    String EnvironmentType = rs.getString("EnvironmentType");
                    String Tools = rs.getString("Tools");
                    String Process = rs.getString("Process");
                    String Analyst = rs.getString("Analyst");
                    String Date = rs.getString("Date");
                    String Time = rs.getString("Time");
                    String MsgCode = rs.getString("MsgCode");
                    String Called = rs.getString("Called");
                    String Status = rs.getString("Status");
                    String WhoIs = rs.getString("WhoIs");
                    String Msg = rs.getString("Msg");
                    String Resolution = rs.getString("Resolution_Obs");
                    int EnvId = rs.getInt("EnvId");
                    logKbNew.setLogKbId(Id);
                    logKbNew.setEnvironmentName(EnvironmentName);
                    logKbNew.setEnvironmentType(EnvironmentType);
                    logKbNew.setTools(Tools);
                    logKbNew.setProcess(Process);
                    logKbNew.setAnalyst(Analyst);
                    logKbNew.setDate(Date);
                    logKbNew.setTime(Time);
                    logKbNew.setMsgCode(MsgCode);
                    logKbNew.setCalled(Called);
                    logKbNew.setStatus(Status);
                    logKbNew.setWhoIs(WhoIs);
                    logKbNew.setMsg(Msg);
                    logKbNew.setResolution_Obs(Resolution);
                    logKbNew.setEnvId(EnvId);
                    logListKb.add(logKbNew);
                }
                if (!retorno) {
                    setMessage("Return is empty!");
                }
            }
        } catch (SQLException sqle) {
            setMessage("Os dados não foram listados!  " + sqle.getMessage());
        }
        return logListKb;
    }

    private String montaWhereKB(LogKb logKb) {
        String clauWhere = new String();
        ArrayList<String> arrayWhere;
        arrayWhere = new ArrayList<String>();
        if (logKb.getMsgCode().length() > 0 || !logKb.getMsgCode().contains("")) {
            arrayWhere.add(" " + logKb.getCampos().get(3) + "=" + "'" + logKb.getMsgCode().toString() + "'");
        }
        if (logKb.getTools().length() > 0 || !logKb.getTools().contains("")) {
            arrayWhere.add(" " + logKb.getCampos().get(5) + "=" + "'" + logKb.getTools().toString() + "'");
        }
        if (logKb.getAnalyst().length() > 0 || !logKb.getAnalyst().contains("")) {
            arrayWhere.add(" " + logKb.getCampos().get(7) + "=" + "'" + logKb.getAnalyst().toString() + "'");
        }
        if (logKb.getMsg().length() > 0 || !logKb.getMsg().contains("")) {
            arrayWhere.add(moutMsgLike(logKb.getMsg().toString(), logKb.getCampos().get(9)));
        }

        if (arrayWhere.size() > 0) {
            for (int i = 0; i < arrayWhere.size(); i++) {
                if (i != arrayWhere.size() - 1) {
                    clauWhere = clauWhere + arrayWhere.get(i) + " and ";
                } else {
                    clauWhere = clauWhere + arrayWhere.get(i);
                }
            }
            clauWhere = "Where " + clauWhere;
        } else {
            clauWhere = "";
        }
        return clauWhere;
    }

    private String moutMsgLike(String msg, String campo) {
        String[] arrayTags = msg.split("\\,");
        String addWhere = new String();
        for (int i = 0; i < arrayTags.length; i++) {
            if (i != arrayTags.length - 1) {
                addWhere = addWhere + campo + " LIKE " + QuotedStr("%" + arrayTags[i].trim() + "%") + " OR ";
            } else {
                addWhere = addWhere + campo + " LIKE " + QuotedStr("%" + arrayTags[i].trim() + "%");
            }
        }
        return addWhere;
    }

    public ArrayList<LogKb> ConsultaCBKB() {
        //  String clauWhere = montaClauWhere(logKb);
        ArrayList<LogKb> logListCBKb = new ArrayList<LogKb>();
        boolean retorno = false;
        try {
            String comando = "SELECT distinct MsgCode,Tools,Analyst  FROM tb_logkb";
            getConexao().ExpressaoSQL(comando);
            ResultSet rs = getConexao().getDados();
            int contar = 0;
            if (rs != null) {
                while (rs.next()) {
                    LogKb logCBKB = new LogKb();
                    retorno = true;
                    int Id = contar;
                    String MsgCode = rs.getString("MsgCode");
                    String Tools = rs.getString("Tools");
                    String Analyst = rs.getString("Analyst");
                    logCBKB.setLogKbId(Id);
                    logCBKB.setMsgCode(MsgCode);
                    logCBKB.setTools(Tools);
                    logCBKB.setAnalyst(Analyst);
                    logListCBKb.add(logCBKB);
                    contar++;
                }
                if (!retorno) {
                    setMessage("Return is empty!");
                }
            }
        } catch (SQLException sqle) {
            setMessage("Os dados não foram listados!  " + sqle.getMessage());
        }
        return logListCBKb;

    }

    public void update(LogKb logkb) {
        String Comando = " Update omd.tb_logkb SET ";
        for (int i = 1; i < logkb.getCampos().size(); i++) {
            switch (i) {
                case 4:
                    Comando += ((String) logkb.getCampos().get(i)) + "=" + "'" + logkb.getCalled().toString() + "'" + ",";
                    break;
                case 6:
                    Comando += ((String) logkb.getCampos().get(i)) + "=" + "'" + logkb.getProcess().toString() + "'" + ",";
                    break;
                case 7:
                    Comando += ((String) logkb.getCampos().get(i)) + "=" + "'" + logkb.getAnalyst().toString() + "'" + ",";
                    break;
                case 10:
                    Comando += ((String) logkb.getCampos().get(i)) + "=" + "'" + logkb.getResolution_Obs().toString() + "'" + ",";
                    break;
                case 11:
                    Comando += ((String) logkb.getCampos().get(i)) + "=" + "'" + logkb.getStatus().toString() + "'" + ",";
                    break;
                case 12:
                    Comando += ((String) logkb.getCampos().get(i)) + "=" + "'" + logkb.getWhoIs().toString() + "'" + ",";
                    break;
            }
        }
        if (Comando.length() > 0 && Comando.charAt(Comando.length() - 1) == ',') {
            Comando = Comando.substring(0, Comando.length() - 1);
        }
        Comando += " where LogKbId = " + logkb.getLogId();
        try {
            getConexao().ExpressaoSQL(Comando);
            setMessage(getConexao().getMessage());
        } catch (Exception sqle) {
            setMessage("SQL Invalid Comando = " + Comando + " the data didn't updated! " + sqle.getMessage());
        }

    }
//    public void Atualizar() {
//    }
}
