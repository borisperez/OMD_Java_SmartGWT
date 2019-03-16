/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.server.util;

import java.sql.*;

public final class Connexion {

    public String Usuario, Senha, Servidor, DataBase, porta;
    private String erroMessage;
    private String message;
    public Connection Con;
    public ResultSet resultset;
    public boolean conected;
    private Statement statement;

    public Connexion(String USU, String SENHA) {
        super();

        /*		setSenha("inf0server");
         setUsuario("xmeta");
         setServidor("is-server.ibm.com");
         setConectado(false);
         setCon(null);
         setDados(null);
         setDataBase("xmeta");
         setPorta("50000");*/

        /*      setSenha("ronaldo");
         setUsuario("omdgove");
         setServidor("10.199.8.52");
         setConectado(false);
         setCon(null);
         setDados(null);
         setDataBase("db2ger");
         setPorta("8516");*/

        setUsuario(USU);
        setSenha(SENHA);
        setServidor("localhost");
        setConected(false);
        setCon(null);
        setDados(null);
        setDataBase("omd");
        setPorta("3306");

    }

    public Connexion(String SERV, String DB, String USU, String SENHA) {
        super();
        setSenha(SENHA);
        setUsuario(USU);
        setServidor(SERV);
        setDataBase(DB);
        setConected(false);
        setCon(null);
        setDados(null);
    }

    public Connexion() {
        super();
        setSenha("xpto0101");
        setUsuario("boris");
        setServidor("localhost");
        setDataBase("omd");
        setConected(false);
        setCon(null);
        setDados(null);
        setPorta("3306");
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public final void setCon(Connection con) {
        Con = con;
    }

    public final void setConected(boolean Conected) {
        conected = Conected;
    }

    public final void setSenha(String senha) {
        Senha = senha;
    }

    public final void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public final void setServidor(String servidor) {
        Servidor = servidor;
    }

    public final void setDados(ResultSet dados) {
        resultset = dados;
    }

    public final void setDataBase(String DB) {
        DataBase = DB;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public boolean getConected() {
        return conected;
    }

    public Connection getCon() {
        return Con;
    }

    public String getServidor() {
        return Servidor;
    }

    public ResultSet getDados() {
        return resultset;
    }

    public String getDataBase() {
        return DataBase;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void ConectMySql() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://" + getServidor() + ":" + getPorta() + "/" + getDataBase();
            setCon(DriverManager.getConnection(URL, getUsuario(), getSenha()));
            setConected(true);
        } catch (SQLException e) {
            setMessage("Erro na conexao com o Banco de Dados : " + e.getMessage().replace('\n', '\0'));
            setConected(false);
        }
    }

    public void ConectDB2() throws ClassNotFoundException {
        try {
            String URL = "jdbc:db2://" + getServidor() + ":" + getPorta() + "/" + getDataBase();
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            setCon(DriverManager.getConnection(URL, getUsuario(), getSenha()));
            setConected(true);
        } catch (SQLException e) {
            setMessage("Erro na conexao com o Banco de Dados : " + e.getMessage().replace('\n', '\0'));
            setConected(false);
        } catch (Exception e) {
            setMessage("Erro na conexao com o Banco de Dados : " + e.getMessage().replace('\n', '\0'));
            setConected(false);
        }
    }

    public void Conect(String tipoDB) throws ClassNotFoundException {
        if ("mysql".equals(tipoDB)) {
            ConectMySql();
        }
        if ("db2".equals(tipoDB)) {
            ConectDB2();
        }
    }

    public void FecharConexao() {
        try {
            if (getConected()) {
                getCon().close();
            }
        } catch (Exception e) {
        }
    }

    public void ExpressaoSQL(String Comando) {
        if (getConected()) {
            try {
                setStatement(getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY));
                if (Comando.toUpperCase().indexOf("SELECT") != -1) {
                    setDados(statement.executeQuery(Comando));
                } else {
                    setDados(null);
                    statement.executeUpdate(Comando);
                    int count = statement.getUpdateCount();                   
                    if (Comando.toUpperCase().indexOf("UPDATE") != -1) {
                        setMessage(count+" records have been updated.");
                        FecharConexao();
                    } else if (Comando.toUpperCase().indexOf("DELETE") != -1) {
                        setMessage(count+" records have been deleted.");
                        FecharConexao();
                    } else if (Comando.toUpperCase().indexOf("INSERT") != -1) {
                        setMessage(count+" records have been inserted.");
                        FecharConexao();
                    }
                }
            } catch (SQLException sqle) {
                setMessage("Error on query : " + sqle.getMessage().replace('\n', '\0'));
            }
        }
    }
}
