/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.server.sql;

import br.com.etlplus.client.objects.User;
import br.com.etlplus.server.util.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlActionUser {

    private Connexion connexion;
    private User user;
  //  private boolean result;
    private String erro;
    private ArrayList<User> UserList;

    public SqlActionUser(Connexion con, User User) {
        setConexao(con);
        this.user = User;
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

    public void Salvar() {
        if (user.getConsistencia()) {
            String Colunas = "(" + user.getCampos().get(1)
                    + "," + user.getCampos().get(2)
                    + "," + user.getCampos().get(3)
                    + "," + user.getCampos().get(4)
                    + "," + user.getCampos().get(5)
                    + "," + user.getCampos().get(6)
                    + "," + user.getCampos().get(7)
                    + "," + user.getCampos().get(8)
                    + "," + user.getCampos().get(9)
                    + "," + user.getCampos().get(10) + ")";
            String Valores = "(" + QuotedStr(user.getUserName()) + ","
                    + "password(" + QuotedStr(user.getPassword()) + "),"
                    + "password(" + QuotedStr(user.getConfPassword()) + "),"
                    + QuotedStr(user.getCourtesyTitle()) + ","
                    + QuotedStr(user.getFirstName()) + ","
                    + QuotedStr(user.getLastName()) + ","
                    + QuotedStr(user.getJobTitle()) + ","
                    + QuotedStr(user.getOfficePhone()) + ","
                    + QuotedStr(user.getMobilePhone()) + ","
                    + QuotedStr(user.getEmail()) + ")";

            String Comando = "insert into omd.tb_user " + Colunas + " values " + Valores;
            // String Comando = "insert into omdgove.TB_QA (NOMEJOB,NOMEPROJETO,NOMEAMBIENTE,DUREXECUCAO,DATAINI,DATAFIM,STATUSEXECUCAO,DATAENVIO,ANALISTA,STATUSQA) values " + Valores ; 

            try {
                getConexao().ExpressaoSQL(Comando);

            } catch (Exception sqle) {
                setErro("Invalid SQL :" + Comando + "   Os dados não foram salvos!" + sqle.getMessage());
            }
        } else {
            setErro("User inconsistent!");
        }
    }

    public void Remover() {
        try {
            getConexao().ExpressaoSQL(" Delete from omd.tb_user where UserId = " + user.getUserId());
            //   result = getConexao().getDados().rowDeleted();

        } catch (Exception sql) {
            setErro("Os dados não foram removidos!" + sql.getMessage().toString());
        }
    }

    public ArrayList<User> Consulta() {
        String clauWhere = montaClauWhere();
        UserList = new ArrayList<User>();
        boolean retorno = false;
        try {
            String comando = "Select * from omd.tb_user " + clauWhere;
            getConexao().ExpressaoSQL(comando);
            //    UserList = new User[getConexao().getStatement().getFetchSize()];
            ResultSet rs = getConexao().getDados();
            int contar = 0;
            if (rs != null) {
                while (rs.next()) {
                    contar = contar++;
                    retorno = true;
                    user = new User();
                    int Id = rs.getInt("UserId");
                    String UserName = rs.getString("UserName");
                    String Password = rs.getString("Password");
                    String ConfPassword = rs.getString("ConfPassword");
                    String CourtesyTitle = rs.getString("CourtesyTitle");
                    String FirstName = rs.getString("FirstName");
                    String LastName = rs.getString("LastName");
                    String JobTitle = rs.getString("JobTitle");
                    String OfficePhone = rs.getString("OfficePhone");
                    String MobilePhone = rs.getString("MobilePhone");
                    String Email = rs.getString("Email");
                    user.setUserId(Id);
                    user.setUserName(UserName);
                    user.setPassword(Password);
                    user.setConfPassword(ConfPassword);
                    user.setCourtesyTitle(CourtesyTitle);
                    user.setFirstName(FirstName);
                    user.setLastName(LastName);
                    user.setJobTitle(JobTitle);
                    user.setOfficePhone(OfficePhone);
                    user.setMobilePhone(MobilePhone);
                    user.setEmail(Email);
                    UserList.add(user);
                }
                if (!retorno) {
                    setErro("Return is empty!");
                }
            }
        } catch (SQLException sqle) {
            setErro("Os dados não foram listados!  " + sqle.getMessage());
        }
        return UserList;
    }

    public User ConsultaLogin(String userLogin, String password) {
        boolean retorno = false;
        user = new User();
        try {
            String comando = "SELECT UserName, RoleName "
                    + "FROM tb_user WHERE tb_user.UserName ='" + userLogin+ "' and 'etlplus' = decode(Password, '"+password+"')";
            getConexao().ExpressaoSQL(comando);
                //    UserList = new User[getConexao().getStatement().getFetchSize()];
                ResultSet rs = getConexao().getDados();
                int contar = 0;
                if (rs != null) {
                    while (rs.next()) {
                        contar = contar++;
                        retorno = true;
                        String UserName = rs.getString("UserName");
                        String Role = rs.getString("RoleName");
                        user.setUserName(UserName);
                        user.setRole(Role);
                    }
                }
                if (!retorno) {
                    setErro("Return is empty!");
                    return user;
                }
            
        } catch (SQLException sqle) {
            setErro("Os dados não foram listados!  " + sqle.getMessage());
        }
        return user;
    }

    private String montaClauWhere() {
        String clauWhere = new String();
        ArrayList<String> arrayWhere;
        arrayWhere = new ArrayList<String>();
        if (user.getUserName().length() > 0 || !user.getUserName().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(1) + "=" + "'" + user.getUserName().toString() + "'");
        }
        if (user.getPassword().length() > 0 || !user.getPassword().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(2) + "=" + "'" + user.getPassword().toString() + "'");
        }
        if (user.getConfPassword().length() > 0 || !user.getConfPassword().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(3) + "=" + "'" + user.getConfPassword().toString() + "'");
        }
        if (user.getCourtesyTitle().length() > 0 || !user.getCourtesyTitle().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(4) + "=" + "'" + user.getCourtesyTitle().toString() + "'");
        }
        if (user.getFirstName().length() > 0 || !user.getFirstName().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(4) + "=" + "'" + user.getFirstName().toString() + "'");
        }
        if (user.getLastName().length() > 0 || !user.getLastName().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(5) + "=" + "'" + user.getLastName().toString() + "'");
        }
        if (user.getJobTitle().length() > 0 || !user.getJobTitle().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(6) + "=" + "'" + user.getJobTitle().toString() + "'");
        }
        if (user.getOfficePhone().length() > 0 || !user.getOfficePhone().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(7) + "=" + "'" + user.getOfficePhone().toString() + "'");
        }
        if (user.getMobilePhone().length() > 0 || !user.getMobilePhone().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(8) + "=" + "'" + user.getMobilePhone().toString() + "'");
        }
        if (user.getEmail().length() > 0 || !user.getEmail().contains("")) {
            arrayWhere.add(" " + user.getCampos().get(9) + "=" + "'" + user.getEmail().toString() + "'");
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

    public void Atualizar() {
        String Comando = " Update omd.tb_user SET ";
        for (int i = 1; i < user.getCampos().size(); i++) {
            switch (i) {
                case 1:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getUserName().toString() + "'" + ",";
                    break;
                case 2:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getPassword().toString() + "'" + ",";
                    break;
                case 3:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getConfPassword().toString() + "'" + ",";
                    break;
                case 4:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getCourtesyTitle().toString() + "'" + ",";
                    break;
                case 5:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getFirstName().toString() + "'" + ",";
                    break;
                case 6:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getLastName().toString() + "'" + ",";
                    break;
                case 7:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getJobTitle().toString() + "'" + ",";
                    break;
                case 8:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getOfficePhone().toString() + "'" + ",";
                    break;
                case 9:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getMobilePhone().toString() + "'" + ",";
                    break;
                case 10:
                    Comando += ((String) user.getCampos().get(i)) + "=" + "'" + user.getEmail().toString() + "'" + ",";
                    break;
            }
        }
        if (Comando.length() > 0 && Comando.charAt(Comando.length() - 1) == ',') {
            Comando = Comando.substring(0, Comando.length() - 1);
        }
        Comando += " where UserId = " + user.getUserId();
        try {
            getConexao().ExpressaoSQL(Comando);
        } catch (Exception sqle) {
            setErro("SQL Invalid Comando = " + Comando + " the data didn't updated! " + sqle.getMessage());
        }
    }
}
