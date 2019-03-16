/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.server;

import java.sql.SQLException;
import br.com.etlplus.client.services.LogService;
import br.com.etlplus.client.objects.Log;
import br.com.etlplus.client.objects.LogKb;
import br.com.etlplus.client.services.LogService;
import br.com.etlplus.server.sql.SqlActionLog;
import br.com.etlplus.server.util.Connexion;
import br.com.etlplus.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class LogServiceImpl extends RemoteServiceServlet implements LogService {

    ArrayList<Log> rsLog;
    ArrayList<LogKb> rsHashMap;
    Connexion con;

    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }

    @Override
    public ArrayList<Log> getLogs(String server, int maxLog, String date, String timeFrom, String timeTo) throws IllegalArgumentException {
        if (!FieldVerifier.isValidName(server)) {
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        } else {
            con = new Connexion();
            try {
                con.Conect("mysql");
                if (con.getConected()) {
                    SqlActionLog sql = new SqlActionLog(con);
                    rsLog = sql.ConsultaLog(server, formatMySqlDate(date), timeFrom, timeTo);
                } else {
                    throw new IllegalArgumentException("SQL invalid");
                }

                try {
                    if (con.getDados().first() == true) {
                        return rsLog;
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

    private String formatMySqlDate(String date) {
        String[] dateFormated = date.split("\\/");
        return dateFormated[2] + "-" + dateFormated[1] + "-" + dateFormated[0];
    }

    @Override
    public String setLog(LogKb logkb) throws IllegalArgumentException {
        SqlActionLog sql = null;
        if (!FieldVerifier.isValidName(logkb.getMsg())) {
            throw new IllegalArgumentException("Msg must be at least 4 characters long");
        } else {
            con = new Connexion();
            try {
                con.Conect("mysql");
                if (con.getConected()) {
                    sql = new SqlActionLog(con);
                    sql.salvar(logkb);
                } else {
                    throw new IllegalArgumentException("SQL invalid");
                }
            } catch (ClassNotFoundException e) {
                //<editor-fold defaultstate="collapsed" desc="comment">
            }
        }
        return sql.getMessage();
    }

    @Override
    public ArrayList<LogKb> getLogsKb(LogKb logKb) throws IllegalArgumentException {
        con = new Connexion();
        ArrayList<LogKb> rsLogKb;
        try {
            con.Conect("mysql");
            if (con.getConected()) {
                SqlActionLog sql = new SqlActionLog(con);
                rsLogKb = sql.ConsultaLogKb(logKb);
            } else {
                throw new IllegalArgumentException("SQL invalid");
            }

            try {
                if (con.getDados().first() == true) {
                    return rsLogKb;
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

    @Override
    public ArrayList<LogKb> getDistinctCbValues() throws IllegalArgumentException {
        Connexion con = new Connexion();
        try {
            con.Conect("mysql");
            if (con.getConected()) {
                SqlActionLog sql = new SqlActionLog(con);
                //con.ExpressaoSQL("select UserName from omd.tb_user where UserName = '" + user + "' and 'etlplus' = decode(Password, '"+password+"')");
                rsHashMap = sql.ConsultaCBKB();
            } else {
                throw new IllegalArgumentException("SQL invalid");
            }

            try {
                if (con.getDados().first() == true) {
                    return rsHashMap;
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

    @Override
    public String updateLogKb(LogKb logkb) throws IllegalArgumentException {
        SqlActionLog sql = null;
        if (!FieldVerifier.isValidName(logkb.getMsg())) {
            throw new IllegalArgumentException("Msg must be at least 4 characters long");
        } else {
            con = new Connexion();
            try {
                con.Conect("mysql");
                if (con.getConected()) {
                    sql = new SqlActionLog(con);
                    sql.update(logkb);
                } else {
                    throw new IllegalArgumentException("SQL invalid");
                }
            } catch (ClassNotFoundException e) {
                //<editor-fold defaultstate="collapsed" desc="comment">
            }
        }
        return sql.getMessage();
    }
//    private Date getDate(String date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        if ("".equals(date)) {
//            date = new Date().toString();
//        }
//        java.util.Date dateReturn = new Date();
//        try {
//            dateReturn = sdf.parse(date);
//        } catch (ParseException ex) {
//            Logger.getLogger(LogServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return new java.sql.Date(dateReturn.getTime());
//    }
//
//    private java.sql.Time getTime(Object timeO) {
//        if (timeO.toString().length() > 0) {
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            if (timeO instanceof String) {
//                java.sql.Time time = null;
//                try {
//                    java.util.Date dateO = sdf.parse(timeO.toString());
//                    time = new java.sql.Time(dateO.getTime());
//                } catch (ParseException ex) {
//                    Logger.getLogger(LogServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                if (ehValidTime(time)) {
//                    return time;
//                } else {
//                    Date dateGc = zeroTime(time);
//                    return new java.sql.Time(dateGc.getTime());
//                }
//            } else if (timeO instanceof Date) {
//                java.util.Date dateO;
//                dateO = (java.util.Date) timeO;
//                return new java.sql.Time(dateO.getTime());
//            } else {
//                Date zeroDate = zeroTime(new Date());
//                return new java.sql.Time(zeroDate.getTime());
//            }
//        } else {
//            Date zeroDate = zeroTime(new Date());
//            return new java.sql.Time(zeroDate.getTime());
//        }
//    }
//
//    private Time workTime(Date time, Date timeWork, String sinal) {
//        GregorianCalendar gcD = new GregorianCalendar();
//        gcD.setTime(time);
//        GregorianCalendar gcDWork = new GregorianCalendar();
//        gcDWork.setTime(timeWork);
//        int hora = Integer.parseInt(timeWork.toString().substring(0, 2));
//        int min = Integer.parseInt(timeWork.toString().substring(3, 5));
//        int seg = Integer.parseInt(timeWork.toString().substring(6, 8));
//        if ("sum".equals(sinal)) {
//            java.sql.Time timeEnd = getTime(addTime(gcD, hora, min, seg));
//            return timeEnd;
//        } else if ("minus".equals(sinal)) {
//            java.sql.Time timeEnd = getTime(deductTime(gcD, hora, min, seg));
//            return timeEnd;
//        } else {
//            return null;
//        }
//    }
//
//    public String addTime(GregorianCalendar gcD, int hora, int min, int seg) {
//        gcD.add(Calendar.HOUR, hora);
//        gcD.add(Calendar.MINUTE, min);
//        gcD.add(Calendar.SECOND, seg);
//        return gcD.getTime().toString();
//    }
//
//    public String deductTime(GregorianCalendar gcD, int hora, int min, int seg) {
//        gcD.add(Calendar.HOUR, -hora);
//        gcD.add(Calendar.MINUTE, -min);
//        gcD.add(Calendar.SECOND, -seg);
//        return gcD.getTime().toString();
//    }
//
//    private boolean ehValidTime(Date time) {
//        if ("23:00:00".equals(time.toString())) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }
//
//    public Date zeroTime(Date time) {
//        GregorianCalendar gc = new GregorianCalendar();
//        gc.setTime(time);
//        gc.set(Calendar.HOUR_OF_DAY, 0);
//        gc.set(Calendar.MINUTE, 0);
//        gc.set(Calendar.SECOND, 0);
//        gc.set(Calendar.MILLISECOND, 0);
//        Date dateGc = gc.getTime();
//        return dateGc;
//    }
}
